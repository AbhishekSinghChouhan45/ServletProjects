function check(frm){
	let cid = frm.cid.value;
	let vflag = true;
	document.getElementById("cid").innerHTML="";
	if(cid=="" || cid ==null){
	document.getElementById("cid").innerHTML = "<b>Enter Customer id</b>";
	vflag =false;
	}
	else if(isNaN(cid)){
	document.getElementById("cid").innerHTML = "<b>Enter Only Numeric values</b>";
	vflag =false;
	}
	return vflag;
}