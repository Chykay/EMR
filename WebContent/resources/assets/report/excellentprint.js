

//var idPost //define a global variable
function printDiv() {
//   idPost = document.getElementById("status").innerHTML; //update the global variable
     var printContents = document.getElementById('printableArea').innerHTML;
    var originalContents = document.body.innerHTML;

    document.body.innerHTML = printContents;

     window.print();

     document.body.innerHTML = originalContents;
}


