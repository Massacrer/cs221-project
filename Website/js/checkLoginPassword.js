function checkLoginPassword() {
	var mypassword = document.getElementById('mypassword').value;
	var myusername = document.getElementById('myusername').value;
	if (mypassword.length < 1 || myusername.length < 1){
		document.getElementById("message_after_login").innerHTML = "Invalid User Details.";
		return false;
	}
	else{
		document.getElementById("message_after_login").innerHTML = "";
		return true;
	}
}
