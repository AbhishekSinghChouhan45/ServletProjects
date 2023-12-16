function opcheck(frm){
	let vname = frm.name.value;
	let vadd = frm.address.value;
	let vnum = frm.number.value;
	let vgen = frm.gender.value;
	let vdob = frm.dob.value;
	let vbal = frm.balance.value;
	let vuser = frm.username.value;
	let vpass = frm.password.value;
	frm.vstatus.value ="enabled";
	let vflag = true;
	document.getElementById("name").innerHTML="";
	document.getElementById("address").innerHTML="";
	document.getElementById("number").innerHTML="";
	document.getElementById("gender").innerHTML="";
	document.getElementById("dob").innerHTML="";
	document.getElementById("balance").innerHTML="";
	document.getElementById("username").innerHTML="";
	document.getElementById("password").innerHTML="";
	if(vname==""||vname==null){
		document.getElementById("name").innerHTML="Plese Enter Name";
		vflag = false;
	}
	else if(vname.length<5||vname.length>20){
		document.getElementById("name").innerHTML="Name can not be less than 5 and greater than 20";
		vflag = false;
	}
	if(vadd==""||vadd==null){
		document.getElementById("address").innerHTML="Plese Enter Address";
		vflag = false;
	}
	if(vnum==""||vnum==null){
		document.getElementById("number").innerHTML="Plese Enter Number";
		vflag = false;
	}
	else if(vnum.length>10 || vnum.length<10){
		document.getElementById("number").innerHTML="Enter Vadlid Number";
		vflag = false;
	}
	else if(isNaN(vnum)){
		document.getElementById("number").innerHTML="Enter only digits";
		vflag = false;
	}
	if(vgen==""||vgen==null){
		document.getElementById("gender").innerHTML="Plese Enter Gender";
		vflag = false;
	}
	if(vdob==""||vdob==null){
		document.getElementById("dob").innerHTML="Plese Enter Date of Birth";
		vflag = false;
	}
	if(vbal==""||vbal==null){
		document.getElementById("balance").innerHTML="Plese Enter Balance";
		vflag = false;
	}
	else if(vbal < 1000){
		document.getElementById("balance").innerHTML="Minimum Balance amount requires is 1000";
		vflag = false;
	}
	else if(vbal > 200000){
		document.getElementById("balance").innerHTML="You can try our premium";
		vflag = false;
	}
	else if(isNaN(vbal)){
		document.getElementById("balance").innerHTML="Enter only diaagits";
		vflag = false;
	}
	if(vuser=="" || vuser==null){
		document.getElementById("username").innerHTML="Please Enter Username";
		vflag = false;
	}
	else if(vuser.length<5 || vuser.length >20){
		document.getElementById("username").innerHTML="Username can not be <5 && >20";
		vflag = false;
	}
	if(vpass==""||vpass==null){
		document.getElementById("password").innerHTML="Please Enter Password";
		vflag = false;
	}
	else if(vpass.length<5 || vpass.length>30){
		document.getElementById("password").innerHTML="Password can not be <5 && >30";
		vflag = false;
	}
	return vflag;
}
function checkaccount(frm){
	let vuser = frm.username.value;
	let vpass = frm.password.value;
	frm.vstatus.value ="enabled";
	let vflag = true;
		document.getElementById("username").innerHTML="";
		document.getElementById("password").innerHTML="";
	if(vuser=="" || vuser==null){
		document.getElementById("username").innerHTML="Please Enter Username";
		vflag = false;
	}
	else if(vuser.length<2 || vuser.length >20){
		document.getElementById("username").innerHTML="Username can not be <2 && >20";
		vflag = false;
	}
	if(vpass==""||vpass==null){
		document.getElementById("password").innerHTML="Please Enter Password";
		vflag = false;
	}
	else if(vpass.length<5 || vpass.length>30){
		document.getElementById("password").innerHTML="Password can not be <5 && >30";
		vflag = false;
	}
	return vflag;
}
function withdraw(frm){
	let vuser = frm.username.value;
	let vpass = frm.password.value;
	let vamt = frm.amount.value;
	frm.vstatus.value ="enabled";
	let vflag = true;
		document.getElementById("username").innerHTML="";
		document.getElementById("password").innerHTML="";
		document.getElementById("amount").innerHTML="";
	if(vuser=="" || vuser==null){
		document.getElementById("username").innerHTML="Please Enter Username";
		vflag = false;
	}
	else if(vuser.length<2 || vuser.length >20){
		document.getElementById("username").innerHTML="Username can not be <2 && >20";
		vflag = false;
	}
	if(vpass==""||vpass==null){
		document.getElementById("password").innerHTML="Please Enter Password";
		vflag = false;
	}
	else if(vpass.length<5 || vpass.length>30){
		document.getElementById("password").innerHTML="Password can not be <5 && >30";
		vflag = false;
	}
	if(vamt==""||vamt==null){
		document.getElementById("amount").innerHTML="Please Enter Amount";
		vflag = false;
	}
	else if(isNaN(vamt)){
		document.getElementById("amount").innerHTML="Please Enter Valid Amount";
		vflag = false;
	}
	return vflag;
}
function transfer(frm){
	let user = frm.username.value;
	let duser = frm.dusername.value;
	let pass = frm.password.value;
	let amt = frm.amount.value;
	frm.vstatus.value = "enabled";
	let vflag = true;
	document.getElementById("username").innerHTML="";
	document.getElementById("dusername").innerHTML="";
	document.getElementById("password").innerHTML="";
	document.getElementById("amount").innerHTML="";
	if(user=="" || user==null){
	document.getElementById("username").innerHTML="Please Enter Username";
	vflag = false;	
	}
	else if(user.length<3 || user.length>20){
	document.getElementById("username").innerHTML="Username can not be <3 || >20";
	vflag = false;
	}
	if(duser=="" || duser==null){
	document.getElementById("dusername").innerHTML="Please Enter Destination Username";
	vflag = false;	
	}
	else if(duser.length<3 || duser.length>20){
	document.getElementById("dusername").innerHTML="Username can not be <3 || >20";
	vflag = false;
	}
	if(pass==""||pass==null){
		document.getElementById("password").innerHTML="Please Enter Password";
		vflag = false;
	}
	else if(pass.length<5 || pass.length>30){
		document.getElementById("password").innerHTML="Password can not be <5 && >30";
		vflag = false;
	}
	if(amt==""||amt==null){
		document.getElementById("amount").innerHTML="Please Enter Amount";
		vflag = false;
	}
	else if(isNaN(amt)){
		document.getElementById("amount").innerHTML="Please Enter Valid Amount";
		vflag = false;
	}
	return vflag;
	
}