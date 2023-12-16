function check(frm){
	let age = frm.age.value;
	frm.hidden.value = "enabled";
	document.getElementById("age").innerHTML="";
	let flag = true;
	if(age==""||age==null){
	document.getElementById("age").innerHTML="Please Enter Age";
	flag = false;
	}
	else if(isNaN(age)){
	document.getElementById("age").innerHTML="Please Enter Numeric Age";
	flag = false;
	}
	return flag;
}