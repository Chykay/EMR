/*<![CDATA[*/
           
$('#p_account_type').change(function() {
	document.getElementById("p_account_no_search").value = "";
	var sel_acc_type = $('#p_account_type option:selected');
	var acc_type = sel_acc_type[0].value;

	$("#p_branch_id").prop("disabled", false);
	

	if(acc_type != ""){
		getLedgers($("#p_account_no"), acc_type);
	} else {
		$("#p_account_no").html("");
	}
	
	if (acc_type == "CL") {
		$(".p_cust_search").addClass("btn btn-primary btn-xs");
		$(".p_cust_search").removeAttr("hidden");
		$("#p_branch_id").prop("disabled", "disabled");
		//remove hidden attr on p_cust_search OR add class="btn btn-primary btn-xs p_cust_search"
	}
	/* if(acc_type == "GL"){
		//enable bracnh selection
		
		getLedgers($("#p_account_no"), acc_type);
	} else if (acc_type == "CL") {
		//remove hidden attr on p_cust_search OR add class="btn btn-primary btn-xs p_cust_search"
	} */
});

$('#r_account_type').change(function() {
	document.getElementById("r_account_no_search").value = "";
	var sel_acc_type = $('#r_account_type option:selected');
	var acc_type = sel_acc_type[0].value;

	$("#r_branch_id").prop("disabled", false);

	if(acc_type != ""){
		getLedgers($("#r_account_no"), acc_type);
	} else {
		$("#r_account_no").html("");
	}
	
	if (acc_type == "CL") {
		$(".r_cust_search").addClass("btn btn-primary btn-xs");
		$(".r_cust_search").removeAttr("hidden");
		$("#r_branch_id").prop("disabled", "disabled");
	}
	/* if(acc_type == "GL"){
		getLedgers($("#r_account_no"), acc_type);
	} else if (acc_type == "CL") {
		//remove hidden attr on r_cust_search OR add class="btn btn-primary btn-xs r_cust_search"
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
	
    var keyword = document.getElementById("p_account_no_search").value;
    var fleet = document.getElementById("p_account_no");
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
	var keyword = document.getElementById("p_account_no_search").value;
    var fleet = document.getElementById("p_account_no");
    for (var i = 0; i < fleet.length; i++) {
        var txt = fleet.options[i].text.toLowerCase();
        if (!txt.includes(keyword.toLowerCase())) {
            fleet.options[i].style.display = 'none';
        } else {
        	fleet.options[i].selected = true;
        	document.getElementById("p_account_no_search").value = txt;
        	fleet.size = 0;
        	
        	var selected_branch = $('#p_branch_id option:selected');
        	var sel_acc_type = $('#p_account_type option:selected');
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
    var keyword = document.getElementById("r_account_no_search").value;
    var fleet = document.getElementById("r_account_no");
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
	var keyword = document.getElementById("r_account_no_search").value;
    var fleet = document.getElementById("r_account_no");
    for (var i = 0; i < fleet.length; i++) {
        var txt = fleet.options[i].text.toLowerCase();
        if (!txt.includes(keyword.toLowerCase())) {
            fleet.options[i].style.display = 'none';
        } else {
        	fleet.options[i].selected = true;
        	document.getElementById("r_account_no_search").value = txt;
        	fleet.size = 0;
        	
			var selected_branch = $('#r_branch_id option:selected');
			var sel_acc_type = $('#r_account_type option:selected');
        	var acc_type = sel_acc_type[0].value;
        	
        	if(acc_type == 'CL'){
        		getBalance($("#r_branch_bal"), fleet.options[i].value, "0", "CL");
        	}
    		else if(selected_branch[0].value != ""){
        		getBalance($("#r_branch_bal"), fleet.options[i].value, selected_branch[0].value, "GL");
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

$("#p_account_no").change(function(){
	var selected_option = $('#p_account_no option:selected');
	document.getElementById("p_account_no_search").value = selected_option.val(); 
	
	var selected_branch = $('#p_branch_id option:selected');
	
	var sel_acc_type = $('#p_account_type option:selected');
	var acc_type = sel_acc_type[0].value;
	
	if(acc_type == 'CL'){
		getBalance($("#p_branch_bal"), selected_option[0].value, "0", "CL");
		}
	else if(selected_branch[0].value != ""){
		getBalance($("#p_branch_bal"), selected_option[0].value, selected_branch[0].value, "GL");
	}
	// call ajax function
});

$('#p_branch_id').change(function() {
	
	var search_field = document.getElementById("p_account_no_search").value;
	
	var selected_option = $('#p_account_no option:selected');

	var selected_branch = $('#p_branch_id option:selected');
	
	var sel_acc_type = $('#p_account_type option:selected');
	var acc_type = sel_acc_type[0].value;
	
	if(acc_type == 'CL'){
		getBalance($("#p_branch_bal"), selected_option[0].value, "0", "CL");
		}
	else if(search_field != ""){
		getBalance($("#p_branch_bal"), selected_option[0].value, selected_branch[0].value, "GL");
	}
});

$("#r_account_no").change(function(){
	var selected_option = $('#r_account_no option:selected');
	document.getElementById("r_account_no_search").value = selected_option.val();


	var selected_branch = $('#r_branch_id option:selected');
	
	var sel_acc_type = $('#r_account_type option:selected');
	var acc_type = sel_acc_type[0].value;
	
	if(acc_type == 'CL'){
		getBalance($("#r_branch_bal"), selected_option[0].value, "0", "CL");
	}
	else if(selected_branch[0].value != ""){
		getBalance($("#r_branch_bal"), selected_option[0].value, selected_branch[0].value, "GL");
	}
});


$('#r_branch_id').change(function() {
	
	var selected_option = $('#r_account_no option:selected');

	var selected_branch = $('#r_branch_id option:selected');
	
	var sel_acc_type = $('#r_account_type option:selected');
	var acc_type = sel_acc_type[0].value;
	
	if(acc_type == 'CL'){
		getBalance($("#r_branch_bal"), selected_option[0].value, "0", "CL");
	}
	else if(selected_option[0].value != ""){
		getBalance($("#r_branch_bal"), selected_option[0].value, selected_branch[0].value, "GL");
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
    /* var keyword = $("#p_account_no_search").val();
    console.log(keyword);

	$("#p_account_no option").each(function() {
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
	  $('#p_account_no').filterByText($('#p_account_no_search'));
	});
	
	$(function() {
		  $('#r_account_no').filterByText($('#r_account_no_search'));
		}); */
		
		/*]]>*/