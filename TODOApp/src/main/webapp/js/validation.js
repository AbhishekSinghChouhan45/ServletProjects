function check(frm){
	let upass = frm.upass.value;
	let rpass = frm.rpass.value;
	let vflag = true;
	document.getElementById("pass").innerHTML=""; 
	if(upass!=rpass){
		document.getElementById("pass").innerHTML="<b>Pass does not match</b>";
		vflag = false
	}
	return false;
}