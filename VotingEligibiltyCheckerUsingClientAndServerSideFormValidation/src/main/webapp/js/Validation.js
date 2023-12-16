function check(frm){
	let name = frm.vname.value;
	let age = frm.vage.value;
	let gender = frm.vgender.value;
	frm.vhidden.value ="enabled";
	let vflag = true;
	document.getElementById("vnameerr").innerHTML=""; 
	document.getElementById("vageerr").innerHTML=""; 
	document.getElementById("vgendererr").innerHTML=""; 
	if(name==""){
		document.getElementById("vnameerr").innerHTML="<b>Name is required </b>";
		vflag = false;
	}
	else if(name.length<5 || name.length>20){
		document.getElementById("vnameerr").innerHTML="<b>Name must be >5  && <20 </b>";
		vflag = false;
	}
	if(age==""){
		document.getElementById("vageerr").innerHTML="<b>Age must be required</b>";
		vflag = false;
	}
	else if(age < 5 || age > 125){
		document.getElementById("vageerr").innerHTML="<b>Age can not be < 5 && > 125</b>";
		vflag = false;
	}
	else if(isNaN(age)){
		document.getElementById("vageerr").innerHTML="<b>Only Integer Values allowed</b>";
		vflag = false;
	}
	if(gender==""){
		document.getElementById("vgendererr").innerHTML="<b>Gender can not ber empty</b>";
		vflag = false;
	}
	
	return vflag;
}