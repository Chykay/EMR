/*<![CDATA[*/
           
window.branches = "";
window.random = 1;
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
	getBranches();
	$(".accountNo").select2();
});
   

$('.add').click(function(){
	div = document.createElement('div');

	//var $src = document.querySelectorAll('.accountNo')[0];
	var $src = $("#accountNo");
	//console.log($src, $src2);


	$src.select2("destroy");

	if (true) {
	$src.attr("data-select2-id",null);
	  $src.find("[data-select2-id]").each(function() {
	    $(this).attr("data-select2-id",null);
	  })
	};
	var $dup = $src.clone();
	if (true) {
	  $dup.attr('id',"added" + window.random);
	  var nrs='xxx';
	  $dup.find('*[id]').attr('id',function(index,oldval) {
	     return oldval.replace(/\d*$/,nrs);
	  });
	};
	

	markup = '<select id="postCode"  required="required"><option value="">Select..</option><option value="DR">GL Debit</option><option value="CR">GL Credit</option></select><select id="branchID"  required="required">' + window.branches + '</select><input type="text" id="amount"  placeholder="amount"  required="required" /><input type="text" id="refNo"  placeholder="ref no"  required="required" /><input type="text" id="desc"  placeholder="description"  required="required" /><button type="button" class="btn btn-xs btn-default delete"><i class="fa fa-trash-o"></i></button>';
	
	$(div).addClass("journal").html(markup);
	$(div).prepend($dup);
	
	$('form').append(div);
	
	$src.select2({width: "120px"});
	$src.trigger("change.select2");
	$dup.select2({width: "120px"});

	/*$(".accountNo").each(function() {
		$(this).select2({width: "120px"});
		$(this).trigger("change.select2");
	});*/
	window.random++;
	//$src.val("b").trigger("change.select2");
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

			isFormValid = false;
			/*
			if($(this)[0].id == "accountNo"){
				if($(this).parent().parent().find('.accountType option:selected')[0].value != 'CA') {
					isFormValid = false;
				}
				
				
			} else if($(this)[0].id == "accountNoSearch") {
				if($(this).parent().parent().find('.accountType option:selected')[0].value != 'GA') {
					isFormValid = false;
					 console.log("no value: IT IS account no IT IS NOT CUSTOMERSELECTED"); 
				}
			} else {
				isFormValid = false;
			}*/
				
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
			/*let account_type = $(this).find('.accountType option:selected')[0].value;*/
			  	 	
			var entry = {
				"account_no": $(this).find('#accountNo option:selected')[0].value,
				"post_code": $(this).find('#postCode option:selected')[0].value,
				"branch_id": $(this).find('#branchID option:selected')[0].value,
				"amount": $(this).find('#amount')[0].value,
				"ref_no": $(this).find('#refNo')[0].value,
				"desc": $(this).find('#desc')[0].value
			};
			
			/*if (account_type == "GA"){
				entry.account_no = $(this).find('#accountNo option:selected')[0].value;
			} else {
				entry.account_no = $(this).find('#accountNoSearch')[0].value;
			}*/
			 		 
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
				// alert("nopppiee");
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
		//var selClass = $(this)[0].className;
		var options = $(this)[0]["options"];
		
		/*if(selClass == "accountType") {
			//console.log("setting up account no");
			accountSetup($(this).parent(), $(this)[0].value);
		} else if(selClass == "accountNo") {
			//console.log
			//var options = $(this)[0]["options"];
			console.log(options, $(this));
		}*/

		

		//console.log("now setting up selected account no");
		$.each(options, function( index ) {
			if(this.value == selValue) {
				this.selected = true;
			} 
		});
	});
}
   
function accountSetup(journalElem, acc_type) {

	accNoElem = journalElem.find('.select2-container');
	console.log(accNoElem);
	
	accNoElem2 = journalElem.querySelector('.select2');
	console.log("acc No 2", accNoElem2);
	
	searchBtn = journalElem.find('.custSearch')[0];
	accNoSearchElem = journalElem.find('#accountNoSearch')[0];
	//console.log(journalElem, acc_type, accNoElem, searchElem);
			
	if(acc_type == 'GA') {
		accNoElem[0].style.display = "inline-block";
		searchBtn.style.display = "none";
		accNoSearchElem.style.display = "none";
		/*
		 if(accNoSearchElem.value.length < 1)
			accNoSearchElem.value = "account no"; */

		accNoElem.html(window.generalLedgers);
	} else {
		accNoElem[0].style.display = "none";
		accNoSearchElem.style.display="inline-block";
		searchBtn.style.display="inline-block";

		/*if(accNoSearchElem.value.length < 1)
			accNoSearchElem.value = "";*/
	}
}
   
function getAccounts(elem, account_type) {
	
		$.ajax({
			type : "GET",
			url : '/../'+ window.location.pathname.split('/')[1] + '/ledger/fetch/' + account_type,
			
			success : function(value) {
				/* elem.html(value); */
				window.generalLedgers = value;
				
				
				
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

function updateAccNo(customerAccNo){/*
	const accNoSearch = window.searchBtn.parent().find('#accountNoSearch')[0];*/
	accNoSearch.value = customerAccNo;
}

/*function filter(elem, keyword) {
	var fleet = elem.next().next()[0];
	console.log(element, text);
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

function selectAccount(elem, keyword){
	var fleet = elem.next().next()[0];
    for (var i = 0; i < fleet.length; i++) {
        var txt = fleet.options[i].text.toLowerCase();
        if (!txt.includes(keyword.toLowerCase())) {
            fleet.options[i].style.display = 'none';
        } else {
        	fleet.options[i].selected = true;
        	elem[0].value = txt;
        	fleet.size = 0;
        	
            break;
        }
    }
}*/





/*function insertAccNo(elem, accNo){
	var selected_option = $('#pAccountNo option:selected');
	elem.prev().prev()[0].value = selected_option.val(); 
	
	var selected_branch = $('#rBranchID option:selected');
	
	
}
	*/	
		/*]]>*/