/*<![CDATA[*/
           
window.branches = "";
window.random = 1;
const saveBtn = document.getElementById('save');
const postBtn = document.getElementById('post');

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
	getBranches();
	$(".accountNo").select2().trigger("change");
	$(".accountNo").each(function() {
		$(this).trigger("change.select2");
	});
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
	

	markup = '<select id="postCode"  required="required"><option value="">Select..</option><option value="DR">GL Debit</option><option value="CR">GL Credit</option></select><select id="branchID"  required="required">' + window.branches + '</select><input type="text" class="amount" id="amount" oninput="this.value = this.value.replace(/[^0-9.]/g, ""); this.value = this.value.replace(/(\..*)\./g, "$1");" placeholder="amount"  required="required" /><input type="text" id="refNo"  placeholder="ref no"  required="required" /><input type="text" id="desc"  placeholder="description"  required="required" /><button type="button" class="btn btn-xs btn-default delete"><i class="fa fa-trash-o"></i></button>';
	
	$(div).addClass("journal").html(markup);
	$(div).prepend($dup);
	
	$('form').append(div);
	
	$src.select2({width: "120px"});
	$src.trigger("change.select2");
	$dup.select2({width: "120px"});

	window.random++;
});


$(document.body).on("click", ".delete", function(){
	$(this).parent().remove();
});

$(document.body).on("click", ".custSearch", function(){
	custSearch($(this));
});


$(document.body).on("blur", ".amount", function(){
	 var valuee = parseFloat(this.value.replace(/,/g, ""))
	   .toFixed(2)
	   .toString()
	   .replace(/\B(?=(\d{3})+(?!\d))/g, ","); 
		
		this.value = valuee;
		
});


function onSubmit(action){
	var isFormValid = true;
	$("#form input, textarea, select").each(function(){
		if ($.trim($(this).val()).length == 0){
			$(this).addClass("highlight");
			isFormValid = false;
				
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
				"account_no": $(this).find('.accountNo option:selected')[0].value,
				"post_code": $(this).find('#postCode option:selected')[0].value,
				"branch_id": $(this).find('#branchID option:selected')[0].value,
				"amount": $(this).find('#amount')[0].value,
				"ref_no": $(this).find('#refNo')[0].value,
				"desc": $(this).find('#desc')[0].value
			};
			 		 
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
				//window.location = '/../'+ window.location.pathname.split('/')[1] + '/ledger/journal/index';
			},
			error: function(response, data){
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
		
		var selClass = $(this)[0].id;
			
		if(selClass == "postCode")
			var selValue = $(this)[0].value;
		else
			var selValue = $(this)[0]["attributes"]["value"]["value"];
		
		
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
	
	accNoElem2 = journalElem.querySelector('.select2');
	
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

	}
}
   
function getAccounts(elem, account_type) {
	
		$.ajax({
			type : "GET",
			url : '/../'+ window.location.pathname.split('/')[1] + '/ledger/fetch/' + account_type,
			
			success : function(value) {
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



	
		/*]]>*/