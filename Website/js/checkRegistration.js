function checkRegistration() {
	var returnVal = true;

	if(!CheckUserName()){
		return false;
	}

	if (!checkPassword()) {
		return false;
	}

	if (!checkConfirmPassword()) {
		return false;
	}

	if (!CheckFirstName()) {
		return false;
	}

	if (!CheckLastName()) {
		return false;
	}

	if (!CheckPhoneNumber()) {
		return false;
	}


	if(!checkEmail()){
		return false;
	}

return returnVal;
}


function CheckUserName() {
	var userName = document.getElementById('username').value;
	if (userName.length < 3){
		document.getElementById("message_after_register").innerHTML = "USERNAME MUST BE ATLEAST 3 CHARACTERS";
		return false;
	}
	else{
		document.getElementById("message_after_register").innerHTML = "";
		return true;
	}
}


function checkPassword() {
	var pWord = document.getElementById('password').value;
	if (pWord.length < 6 || pWord.length > 20 ){
		document.getElementById("message_after_register").innerHTML = "PASSWORD MUST BE MINIMUM OF 6 CHARACTERS";
		return false;
	}
	else{
		document.getElementById("message_after_register").innerHTML = "";
		return true;
	}
}

function checkConfirmPassword(){
	var pWord = document.getElementById('password').value;
	var confirmPWord = document.getElementById('passwordConfirm').value;
	if (pWord != confirmPWord){
		document.getElementById("message_after_register").innerHTML = "password you have entered do not match";
		return false;
	}
	else{
		document.getElementById("message_after_register").innerHTML = "";
		return true;
	}		 
}


function CheckFirstName() {
	var firstName = document.getElementById('fname').value;
	if (firstName.length < 3){
		document.getElementById("message_after_register").innerHTML = "FIRST NAME MUST BE ATLEAST 3 CHARACTERS";
		return false;
	}
	else{
		document.getElementById("message_after_register").innerHTML = "";
		return true;
	}
}

function CheckLastName() {
	var lastName = document.getElementById('sname').value;
	if (lastName.length < 3){
		document.getElementById("message_after_register").innerHTML = "SECOND NAME MUST BE ATLEAST 3 CHARACTERS";
		return false;
	}
	else{
		document.getElementById("message_after_register").innerHTML = "";
		return true;
	}
}


function CheckPhoneNumber() {
	var phoneNumber = document.getElementById('phone').value;
	if (phoneNumber.length < 11 || phoneNumber.length > 14 ){
		document.getElementById("message_after_register").innerHTML = "NOT A VALID PHONE NUMBER";
		return false;
	}
	else{
		document.getElementById("message_after_register").innerHTML = "";
		return true;
	}
}

function checkEmail() {
	var eMail = document.getElementById('email');
	var filter = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
	if (!filter.test(eMail.value)) {
		document.getElementById("message_after_register").innerHTML = "Wrong Email Address";
		return false;
	 }
	 else{
		document.getElementById("message_after_register").innerHTML = "";
		return true;
	}		 
}

