/*<![CDATA[*/
           
window.branches = "";
window.random = 1;
const saveBtn = document.getElementById('save');
const postBtn = document.getElementById('post');
//const searchButtons = document.querySelectorAll('.searchBtn');

if(saveBtn != null){
	saveBtn.addEventListener('click', (e) => {
		e.preventDefault();
		onSubmit('save')
	});
}
   
//if the post button is clicked, call the onSubmit method with the post para
if(postBtn != null) {
	postBtn.addEventListener('click', (e) => {
		e.preventDefault();
		onSubmit('post')
	});
}
 
 
$(document).ready(function(){
	getAccounts("GET GL", "GA");
	$(".accountNo").select2({width: "180px", height: "36px"}).trigger("change");
	$(".accountNo").each(function() {
		$(this).trigger("change.select2");
	});
});
   
$(document.body).on("change", ".accountType", function(){
	var acc_type = $(this)[0].value;
	accountSetup($(this).parent(), acc_type);
});

$('.add').click(function(){
	div = document.createElement('div');
	var $src = $("#accountNo0");

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
	markup = '<div style="display:inline-table;"><input type="text" class="accountNoSearch" id="accountNoSearch"  th:value="${journalEntry.accountNo}" required="required" disabled="disabled" style="display:none;"/><button type="button" class="searchBtn btn btn-xs btn-primary" style="display:none;"><i class="fa fa-search"></i></button></div><select id="postCode"  required="required"><option value="">Select..</option><option value="DR">GL Debit</option><option value="CR">GL Credit</option></select><select id="branchID"  required="required">' + window.branches + '</select><input type="text" id="amount"  placeholder="amount"  required="required" /><input type="text" id="refNo"  placeholder="ref no"  required="required" /><input type="text" id="desc"  placeholder="description"  required="required" /><button type="button" class="btn btn-xs btn-default delete"><i class="fa fa-trash-o"></i></button>';
	$(div).addClass("journal").css({"display": "inline-table", "width": "100%", "margin-top": "10px"}).html(markup);

	$(div).prepend($dup);
	
	select = document.createElement('select');
	
	
	var opt = document.createElement('option');
	var opt2 = document.createElement('option');
	var opt3 = document.createElement('option');
	var opt4 = document.createElement('option');
	var opt5 = document.createElement('option');
	
	opt.value = '';
	opt.innerHTML = 'Select...';
	
	opt2.value = 'GA';
	opt2.innerHTML = 'GL';

    opt3.value = 'CA';
    opt3.innerHTML = 'Customer';
    
    opt4.value = 'HA';
    opt4.innerHTML = 'HMO';
    
    opt5.value = 'VA';
    opt5.innerHTML = 'Vendor';

    select.appendChild(opt);
    select.appendChild(opt2);
    select.appendChild(opt3);
    select.appendChild(opt4);
    select.appendChild(opt5);

    $(select).addClass("accountType");

	$(div).prepend(select);

	$('form').append(div);
	$src.select2({width: "180px", height: "36px"});
	$src.trigger("change.select2");
	$dup.select2({width: "180px", height: "36px"});

	window.random++;
	
	accountSetup($dup.parent(), "");
});


$(document.body).on("click", ".delete", function(){
	$(this).parent().remove();
});

$(document.body).on("click", ".searchBtn", function(){
	searchButton($(this));
});

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
		var selClass = $(this)[0].className;
		if(selClass == "postCode")
			var selValue = $(this)[0].value;
		else
			var selValue = $(this)[0]["attributes"]["value"]["value"];
		
		
		console.log(selClass, selValue);
		if(selClass == "accountType") {
			console.log("setting up account no");
			console.log("2", selValue);
			accountSetup($(this).parent(), selValue);
		} 

		

		console.log("now setting up selected account no");
		var options = $(this)[0]["options"];
		$.each(options, function( index ) {
			if(this.value == selValue) {
				this.selected = true;
			} 
		});

		$(this).trigger("change");
	});
}

function accountSetup(journalElem, acc_type) {
	accNoElem = journalElem.find('.select2-container');
	
	searchBtn = journalElem.find('.searchBtn')[0];
	accNoSearchElem = journalElem.find('#accountNoSearch')[0];
	//console.log(journalElem, acc_type, accNoElem, searchElem);
			
	if(acc_type == 'GA') {
		accNoElem[0].style.display = "inline-block";
		searchBtn.style.display = "none";
		accNoSearchElem.style.display = "none";
		
		if(accNoSearchElem["attributes"]["value"]){
			alert("if");
			console.log(accNoSearchElem["attributes"]["value"]);
			accNoSearchElem["attributes"]["value"]["value"] = "";
		}
		
		
	} else if(acc_type == '') {
		accNoElem[0].style.display = "none";
		accNoSearchElem.style.display="none";
		searchBtn.style.display="none";
	} else {
		
		accNoElem[0].style.display = "none";
		accNoSearchElem.style.display="inline-block";
		searchBtn.style.display="inline-block";
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
   
function searchButton(searchBtn) {
	window.searchBtn = searchBtn;
	var acc_type = $(searchBtn).parent().parent().find('.accountType option:selected')[0].value;
	alert(acc_type);
	
	var myWindow = window.open("/../" + window.location.pathname.split('/')[1] + "/search/productsearchwin/" + acc_type, "MsgWindow", "width=500, height=500");
	
	
	myWindow.focus();
	
	return false
}

function updateAccNo(productAccNo){
	const accNoSearch = window.searchBtn.parent().find('#accountNoSearch')[0];
	accNoSearch.value = productAccNo;
}

function onSubmit(action){
	
	console.log(token, header);
	var isFormValid = true;
	$("#form input, textarea, select").each(function(){
		if ($.trim($(this).val()).length == 0){
			$(this).addClass("highlight");
			/* console.log($(this)[0].id, "outside", "no values");
			 */
			if($(this)[0].id == "accountNo"){
				if($(this).parent().parent().find('.accountType option:selected')[0].value != 'CA') {
					isFormValid = false;
					/* console.log("no value: IT IS account no IT IS NOT CUSTOMERSELECTED"); */
				}/*  else {

					console.log("no value: IT IS account no IT IS CUSTOMERSELECTED");
				} */
				
				
			} else if($(this)[0].id == "accountNoSearch") {
				if($(this).parent().parent().find('.accountType option:selected')[0].value != 'GA') {
					isFormValid = false;
					 console.log("no value: IT IS account no IT IS NOT CUSTOMERSELECTED"); 
				}
			} else {

				/* console.log("no value: not account no");
				 */
				isFormValid = false;
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
				entry.account_no = $(this).find('.accountNo option:selected')[0].value;
			} else {
				entry.account_no = $(this).find('#accountNoSearch')[0].value;
			}
			 		 
			journalEntries.push(entry);
		});
		 	 
		journal = {
			"journalHeader": header,
			"journalEntries": journalEntries
		}
		
		var urL = '/../'+ window.location.pathname.split('/')[1] + '/ledger/journal/edit';
		
		alert(urL);
		
		$.ajax({
			type: "POST",
			contentType: "application/json",
			url : urL,
			beforeSend : function() {
				$.gritter.add({
					title : "Progress...",
					text : "Processing Journal Entries...",
					time : 3000
				}); 
				    
				  
			},
			data: JSON.stringify(journal),
			dataType: "json",
			
			success : function(result) {
		        if(result.status == "Done"){
		        	alert("done");
		        } else {
		        	alert("not dne");
		        }
				//window.location = '/../'+ window.location.pathname.split('/')[1] + '/ledger/journal/index';
			},
			error: function(e){
				alert("Error!")
		        console.log("ERROR: ", e);
			}
		});
		
		return false;
	}
	return false;
}
		/*]]>*/