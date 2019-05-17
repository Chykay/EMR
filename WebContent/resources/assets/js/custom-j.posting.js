/*<![CDATA[*/
           

window.branches = "";
const saveBtn = document.getElementById('save');
const postBtn = document.getElementById('post');
//const searchButtons = document.querySelectorAll('.custSearch');

saveBtn.addEventListener('click', (e) => {
	e.preventDefault();
	onSubmit('save')
});
   
//if the post button is clicked, call the onSubmit method with the post para
postBtn.addEventListener('click', (e) => {
	e.preventDefault();
	onSubmit('post')
});
 
/*  for (const searchButton of searchButtons) {
searchButton.addEventListener('click', function(event) {
custSearch(searchButton);
});
} */
 
$(document).ready(function(){
	getAccounts("GET GL", "GA");
});
   
$(document.body).on("change", ".accountType", function(){
	var acc_type = $(this)[0].value;
	accountSetup($(this).parent(), acc_type);
});

$('.add').click(function(){
	div = document.createElement('div');
	markup = '<select class="accountType"  required="required"><option value="">Select..</option><option value="CA">Customer</option><option value="GA">GL</option></select><div style="display:inline-table;"> <input type="text" id="accountNoSearch" placeholder="account no" th:value="${journalEntry.accountNo}" required="required" disabled="disabled"/> <br /><select id="accountNo"  style="margin-top:0px;"  required="required"  hidden="hidden"></select><button type="button" class="custSearch btn btn-xs btn-primary" hidden="hidden">searcn</button></div> <select id="postCode"  required="required"><option value="">Select..</option><option value="DR">GL Debit</option><option value="CR">GL Credit</option></select><select id="branchID"  required="required">' + window.branches + '</select><input type="text" id="amount"  placeholder="amount"  required="required" /><input type="text" id="refNo"  placeholder="ref no"  required="required" /><input type="text" id="desc"  placeholder="description"  required="required" /><button type="button" class="btn btn-xs btn-default delete"><i class="fa fa-trash-o"></i></button>';
	
	$(div).addClass("journal").html(markup);
	$('form').append(div);
});


$(document.body).on("click", ".delete", function(){
	$(this).parent().remove();
});

$(document.body).on("click", ".custSearch", function(){
	custSearch($(this));
});


function onSubmit(action){
	var isFormValid = true;
	$("#form input, textarea, select").each(function(){
		if ($.trim($(this).val()).length == 0){
			$(this).addClass("highlight");
			/* console.log($(this)[0].id, "outside", "no values");
			 */
			if($(this)[0].id != "accountNo"){
				/* console.log("no value: not account no");
				 */
				isFormValid = false;
			} else {
				if($(this).parent().parent().find('.accountType option:selected')[0].value != 'CA') {
					isFormValid = false;
					/* console.log("no value: IT IS account no IT IS NOT CUSTOMERSELECTED"); */
				}/*  else {

					console.log("no value: IT IS account no IT IS CUSTOMERSELECTED");
				} */
			}
				
		} else {
			$(this).removeClass("highlight");
		}
	});
	
	if (!isFormValid) alert("Please fill in all the required fields");
	else{
		var journalEntries = [];
		var header = {
			"journalID": $('[name="description"]')[1].id,
			"description": $('[name="description"]')[1].value,
			"action": action
		}; 
		
		 
		$('.journal').each(function(){
			let account_type = $(this).find('.accountType option:selected')[0].value;
			  	 	
			var entry = {
				"account_type": account_type,
				"post_code": $(this).find('#postCode option:selected')[0].value,
				"branch_id": $(this).find('#branchID option:selected')[0].value,
				"amount": $(this).find('#amount')[0].value,
				"ref_no": $(this).find('#refNo')[0].value,
				"desc": $(this).find('#desc')[0].value
			};
			
			if (account_type == "GA"){
				entry.account_no = $(this).find('#accountNo option:selected')[0].value;
			} else {
				entry.account_no = $(this).find('#accountNoSearch')[0].value;
			}
			 		 
			journalEntries.push(entry);
		});
		 	 
		journal = {
			"journalHeader": header,
			"journalEntries": journalEntries
		}
			
		$.ajax({
			type: "POST",
			contentType: "application/json",
			'processData': false,
			url : '/../'+ window.location.pathname.split('/')[1] + '/ledger/journal/edit',
			beforeSend : function() {
				$.gritter.add({
					title : "Progress...",
					text : "Processing Journal Entries...",
					time : 3000
				}); 
			},
			data: JSON.stringify(journal),
			dataType: "json",
			
			success: function(response, data){
				alert("yuppie");
				window.location = '/../'+ window.location.pathname.split('/')[1] + '/ledger/journal/index';
			},
			error: function(response){
				alert("nopppiee");
				window.location = '/../'+ window.location.pathname.split('/')[1] + '/ledger/journal/index';
			}
		});
	}
}
   
function getBranches() {
	$.ajax({
		type : "GET",
		url : '/../'+ window.location.pathname.split('/')[1] + '/ledger/branches/',
		success : function(value) {
			value = '{"branches":' + value + '}';
			var branches = JSON.parse(value).branches;
			selectBranches = document.querySelectorAll('#branchID');
			
			for(let selBranch of selectBranches) {
				branches.forEach( (branch) => {
					let option = document.createElement('option');
					option.value = branch["id"];
					option.textContent = branch["name"];
					selBranch.appendChild(option);
					window.branches += '<option value="' + branch["id"] + '">' + branch["name"] + '</option>';
				});
			}

			selectOptions();
		}
	});
}
 
function selectOptions() {
	$("select").each(function(){
		var selValue = $(this)[0].value;
		var selClass = $(this)[0].className;
		var options = $(this)[0]["options"];
		
		if(selClass == "accountType") {
			//console.log("setting up account no");
			accountSetup($(this).parent(), $(this)[0].value);
		} else if(selClass == "accountNo") {
			//console.log
			//var options = $(this)[0]["options"];
			console.log(options, $(this));
		}

		

		//console.log("now setting up selected account no");
		$.each(options, function( index ) {
			if(this.value == selValue) {
				this.selected = true;
			} 
		});
	});
}
   
function accountSetup(journalElem, acc_type) {

	accNoElem = journalElem.find('#accountNo');
	searchBtn = journalElem.find('.custSearch')[0];
	accNoSearchElem = journalElem.find('#accountNoSearch')[0];
	brElem = journalElem.find('br')[0];
	//console.log(journalElem, acc_type, accNoElem, searchElem);
			
	if(acc_type == 'GA') {
		accNoElem[0].removeAttribute("hidden");
		searchBtn.style.display = "none";
		//accNoSearchElem.style.display = "none";
		accNoSearchElem.removeAttribute("disabled");
		/*brElem.style.display = "none";
		
		 if(accNoSearchElem.value.length < 1)
			accNoSearchElem.value = "account no"; */

		accNoElem.html(window.generalLedgers);
		console.log(accNoElem, "doig it now");
	} else {
		//accNoSearchElem.style.display="inline-block";
		accNoSearchElem.setAttribute("disabled", true);
		searchBtn.style.display="inline-block";
		/*brElem.style.display="inline-block";*/
		accNoElem[0].setAttribute("hidden", true);

		if(accNoSearchElem.value.length < 1)
			accNoSearchElem.value = "";
	}
}
   
function getAccounts(elem, account_type) {
	
		$.ajax({
			type : "GET",
			url : '/../'+ window.location.pathname.split('/')[1] + '/ledger/fetch/' + account_type,
			
			success : function(value) {
				/* elem.html(value); */
				window.generalLedgers = value;
				
				selectLedgers = document.querySelectorAll('#accountNo');
				
				for(let selectLedger of selectLedgers) {
					selectLedger.append(window.generalLedgers);
				}
				
				getBranches();
			},
			error : function() {
				window.genLedgers = "";
			}
		});
	
}
   
function custSearch(searchBtn) {
	window.searchBtn = searchBtn;
	var myWindow = window.open("/../" + window.location.pathname.split('/')[1] + "/search/customer_acc/", "MsgWindow", "width=500, height=500");
	myWindow.focus();
	
	return false
}

function updateAccNo(customerAccNo){
	const accNoSearch = window.searchBtn.parent().find('#accountNoSearch')[0];
	accNoSearch.value = customerAccNo;
}















/**NEW ONE HERE OOOO JOR OO BOBO YI*/





$('#pAccountType').change(function() {
	document.getElementById("pAccountNoSearch").value = "";
	var sel_acc_type = $('#pAccountType option:selected');
	var acc_type = sel_acc_type[0].value;

	$("#rBranchID").prop("disabled", false);
	

	if(acc_type != ""){
		getLedgers($("#pAccountNo"), acc_type);
	} else {
		$("#pAccountNo").html("");
	}
	
	if (acc_type == "CL") {
		$(".pCustSearch").addClass("btn btn-primary btn-xs");
		$(".pCustSearch").removeAttr("hidden");
		$("#rBranchID").prop("disabled", "disabled");
		//remove hidden attr on pCustSearch OR add class="btn btn-primary btn-xs pCustSearch"
	}
	/* if(acc_type == "GL"){
		//enable bracnh selection
		
		getLedgers($("#pAccountNo"), acc_type);
	} else if (acc_type == "CL") {
		//remove hidden attr on pCustSearch OR add class="btn btn-primary btn-xs pCustSearch"
	} */
});

$('#rAccountType').change(function() {
	document.getElementById("rAccountNoSearch").value = "";
	var sel_acc_type = $('#rAccountType option:selected');
	var acc_type = sel_acc_type[0].value;

	$("#rBranchID").prop("disabled", false);

	if(acc_type != ""){
		getLedgers($("#rAccountNo"), acc_type);
	} else {
		$("#rAccountNo").html("");
	}
	
	if (acc_type == "CL") {
		$(".rCustSearch").addClass("btn btn-primary btn-xs");
		$(".rCustSearch").removeAttr("hidden");
		$("#rBranchID").prop("disabled", "disabled");
	}
	/* if(acc_type == "GL"){
		getLedgers($("#rAccountNo"), acc_type);
	} else if (acc_type == "CL") {
		//remove hidden attr on rCustSearch OR add class="btn btn-primary btn-xs rCustSearch"
	} */
});

$('#amount').focus(function(){
	var selected_option = $('#p_post_code option:selected');
	if(selected_option[0].value == ""){
		$.gritter.add({
			title : "Invalid Action!",
			text : "You must select the post code first",
			time : 4000
		}); 
		
		this.val("");
		$('#amount').trigger("blur");
	}
});
$('#amount').blur(function(){
	 var valuee = parseFloat(this.value.replace(/,/g, ""))
    .toFixed(2)
    .toString()
    .replace(/\B(?=(\d{3})+(?!\d))/g, ","); 
	
	
	this.value = valuee;
	
	$('#r_amount').attr('value', valuee);
	
	/* $('#r_amount').attr('value', this.value); */
	
	
});

$('#p_post_code').change(function() {
	
	var selected_option = $('#p_post_code option:selected');
	
	$('#r_post_code option').each(function() {
	    if($(this)[0].value != selected_option[0].value ) {
	    	$(this)[0].selected = true;
	    } 
	});
});

$('#r_post_code').change(function() {
	var selected_option = $('#r_post_code option:selected');
	
	$('#p_post_code option').each(function() {
	    if($(this)[0].value != selected_option[0].value ) {
	    	$(this)[0].selected = true;
	    } 
	});
});

function setDesc() {
	$("#r_description")[0].value = $("#p_description")[0].value;
}




function filter() {
	
    var keyword = document.getElementById("pAccountNoSearch").value;
    var fleet = document.getElementById("pAccountNo");
    var selSize = 0;
    for (var i = 0; i < fleet.length; i++) {
        var txt = fleet.options[i].text.toLowerCase();
        if (!txt.includes(keyword.toLowerCase())) {
            fleet.options[i].style.display = 'none';
        } else {
            fleet.options[i].style.display = 'list-item';
            selSize++;
            fleet.style.width="200px";
			fleet.size = selSize;
        }
    }
}

function selectAccount(){
	var keyword = document.getElementById("pAccountNoSearch").value;
    var fleet = document.getElementById("pAccountNo");
    for (var i = 0; i < fleet.length; i++) {
        var txt = fleet.options[i].text.toLowerCase();
        if (!txt.includes(keyword.toLowerCase())) {
            fleet.options[i].style.display = 'none';
        } else {
        	fleet.options[i].selected = true;
        	document.getElementById("pAccountNoSearch").value = txt;
        	fleet.size = 0;
        	
        	var selected_branch = $('#rBranchID option:selected');
        	var sel_acc_type = $('#pAccountType option:selected');
        	var acc_type = sel_acc_type[0].value;
        	
        	if(acc_type == 'CL'){
        		getBalance($("#p_branch_bal"), fleet.options[i].value, "0", "CL");
            }
    		else if(selected_branch[0].value != ""){
        		getBalance($("#p_branch_bal"), fleet.options[i].value, selected_branch[0].value, "GL");
        	}
            break;
        }
    }
}



function filterR() {
    var keyword = document.getElementById("rAccountNoSearch").value;
    var fleet = document.getElementById("rAccountNo");
    var selSize = 0;
    for (var i = 0; i < fleet.length; i++) {
        var txt = fleet.options[i].text.toLowerCase();
        if (!txt.includes(keyword.toLowerCase())) {
            fleet.options[i].style.display = 'none';
        } else { 
        	fleet.options[i].style.display = 'list-item';
	        selSize++;
	        fleet.style.width="200px";
			fleet.size = selSize;
        }
    }
}


function selectAccountR(){
	var keyword = document.getElementById("rAccountNoSearch").value;
    var fleet = document.getElementById("rAccountNo");
    for (var i = 0; i < fleet.length; i++) {
        var txt = fleet.options[i].text.toLowerCase();
        if (!txt.includes(keyword.toLowerCase())) {
            fleet.options[i].style.display = 'none';
        } else {
        	fleet.options[i].selected = true;
        	document.getElementById("rAccountNoSearch").value = txt;
        	fleet.size = 0;
        	
			var selected_branch = $('#rBranchID option:selected');
			var sel_acc_type = $('#rAccountType option:selected');
        	var acc_type = sel_acc_type[0].value;
        	
        	if(acc_type == 'CL'){
        		getBalance($("#rBranchBal"), fleet.options[i].value, "0", "CL");
        	}
    		else if(selected_branch[0].value != ""){
        		getBalance($("#rBranchBal"), fleet.options[i].value, selected_branch[0].value, "GL");
        	}
            break;
        }
    }
	
	/*
		collect the text
		use it to loop thryogh the options's text
		select the first one.
		get the inner html and set it to the text box.
		set selected = true
	*/
}

$("#pAccountNo").change(function(){
	var selected_option = $('#pAccountNo option:selected');
	document.getElementById("pAccountNoSearch").value = selected_option.val(); 
	
	var selected_branch = $('#rBranchID option:selected');
	
	var sel_acc_type = $('#pAccountType option:selected');
	var acc_type = sel_acc_type[0].value;
	
	if(acc_type == 'CL'){
		getBalance($("#p_branch_bal"), selected_option[0].value, "0", "CL");
		}
	else if(selected_branch[0].value != ""){
		getBalance($("#p_branch_bal"), selected_option[0].value, selected_branch[0].value, "GL");
	}
	// call ajax function
});

$('#rBranchID').change(function() {
	
	var search_field = document.getElementById("pAccountNoSearch").value;
	
	var selected_option = $('#pAccountNo option:selected');

	var selected_branch = $('#rBranchID option:selected');
	
	var sel_acc_type = $('#pAccountType option:selected');
	var acc_type = sel_acc_type[0].value;
	
	if(acc_type == 'CL'){
		getBalance($("#p_branch_bal"), selected_option[0].value, "0", "CL");
		}
	else if(search_field != ""){
		getBalance($("#p_branch_bal"), selected_option[0].value, selected_branch[0].value, "GL");
	}
});

$("#rAccountNo").change(function(){
	var selected_option = $('#rAccountNo option:selected');
	document.getElementById("rAccountNoSearch").value = selected_option.val();


	var selected_branch = $('#rBranchID option:selected');
	
	var sel_acc_type = $('#rAccountType option:selected');
	var acc_type = sel_acc_type[0].value;
	
	if(acc_type == 'CL'){
		getBalance($("#rBranchBal"), selected_option[0].value, "0", "CL");
	}
	else if(selected_branch[0].value != ""){
		getBalance($("#rBranchBal"), selected_option[0].value, selected_branch[0].value, "GL");
	}
});


$('#rBranchID').change(function() {
	
	var selected_option = $('#rAccountNo option:selected');

	var selected_branch = $('#rBranchID option:selected');
	
	var sel_acc_type = $('#rAccountType option:selected');
	var acc_type = sel_acc_type[0].value;
	
	if(acc_type == 'CL'){
		getBalance($("#rBranchBal"), selected_option[0].value, "0", "CL");
	}
	else if(selected_option[0].value != ""){
		getBalance($("#rBranchBal"), selected_option[0].value, selected_branch[0].value, "GL");
	}
});

	
function getBalance(elem, account_no, branch_id, account_type) {
	$.ajax({
		type : "GET",
		url : '/../'+ window.location.pathname.split('/')[1] + '/ledger/balance/' + account_no + '/' + branch_id + '/' + account_type,
		beforeSend : function() {
			 $.gritter.add({
				title : "Progress...",
				text : "Fetching Balance...",
				time : 3000
			}); 
		},
		success : function(value) {
			elem.val(value);
			elem.value=value;
			elem.trigger("change");
			//call a function passing in "value", that sets the value and also 'un'-disables both postcodes
			 $.gritter.add({
				title : "Success!",
				text : "Balance Fetched",
				time : 4000
			}); 
		},
		error : function() {
			$.gritter.add({
				title : "Error!",
				text : "Error fetching Balance",
				time : 4000
			});

		}
});
}

function getLedgers(elem, account_type) {
	$.ajax({
		type : "GET",
		url : '/../'+ window.location.pathname.split('/')[1] + '/ledger/fetch/' + account_type,
		beforeSend : function() {
			 $.gritter.add({
				title : "Progress...",
				text : "Fetching Ledgers...",
				time : 3000
			}); 
		},
		success : function(value) {
			elem.html(value);
			//call a function passing in "value", that sets the value and also 'un'-disables both postcodes
			 $.gritter.add({
				title : "Success!",
				text : "Ledgers Fetched",
				time : 4000
			}); 
		},
		error : function() {
			elem.html("");
			$.gritter.add({
				title : "Error!",
				text : "Error fetching Ledgers",
				time : 4000
			});

		}
});
}

function filterP() {
    /* var keyword = $("#pAccountNoSearch").val();
    console.log(keyword);

	$("#pAccountNo option").each(function() {
    	var txt = $(this).toLowerCase();
        console.log(txt);
        console.log($(this)[0]);
        if (!txt.includes(keyword.toLowerCase())) {
        	$(this)[0].style.display = 'none';
        } else {
        	$(this)[0].style.display = 'list-item';
        }
    }); */
}
$('.datepicker').datepicker({
	autoclose : true,
	format: 'yyyy-mm-dd',
	todayHighlight : true
})//show datepicker when clicking on the icon
.next().on(ace.click_event, function() {
	$(this).prev().focus();
});

/* function onSave(){
	alert("save");
	$("form").each(function(){
		console.log($(this).find(':input'));
	});
}
 */


/* jQuery.fn.filterByText = function(textbox) {
	  return this.each(function() {
	    var select = this;
	    var options = [];
	    $(select).find('option').each(function() {
	      options.push({
	        value: $(this).val(),
	        text: $(this).text()
	      });
	    });
	    $(select).data('options', options);

	    $(textbox).bind('change keyup', function() {
	      var options = $(select).empty().data('options');
	      var search = $.trim($(this).val());
	      var regex = new RegExp(search, "gi");

	      $.each(options, function(i) {
	        var option = options[i];
	        if (option.text.match(regex) !== null) {
	        	
	        	
	           $(select).append(
	           '<option value="'+ option.value +'">' + option.text + '</option>'
	          );
	        }
	      });
	    });
	  });
	};

	// You could use it like this:

	$(function() {
	  $('#pAccountNo').filterByText($('#pAccountNoSearch'));
	});
	
	$(function() {
		  $('#rAccountNo').filterByText($('#rAccountNoSearch'));
		}); */
		
		/*]]>*/