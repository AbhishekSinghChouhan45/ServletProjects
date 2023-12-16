function check(frm){
	let pname = frm.name.value;
	let page = frm.age.value;
	let pgender = frm.gender.value;
	
	let vfalge = true;
		document.getElementById("pnameerr").innerHTML="";
		document.getElementById("pageerr").innerHTML="";
		document.getElementById("pgendererr").innerHTML="";
	if(pname==""){
		document.getElementById("pnameerr").innerHTML="<b>Name is required</b>";
		vfalge = false;
	}
	else if(pname.length<5){
		document.getElementById("pnameerr").innerHTML="<b>Name length is > 5</b>";
		vfalge = false;
	}
	if(page==""){
		document.getElementById("pageerr").innerHTML="<b>Age is required</b>";
		vfalge = false;
	}
	else if(isNaN(page)){
		document.getElementById("pageerr").innerHTML="<b>Age can be numeric value only</b>";
		vfalge = false;
	}
	else if(page>125 || page<5){
		document.getElementById("pageerr").innerHTML="<b>Age Must be < 125 and > 5 </b>";
		vfalge = false;
	}
	if(pgender==""){
		document.getElementById("pgendererr").innerHTML="<b>Gender is required</b>";
		vfalge = false;
	}
	return vfalge;
}