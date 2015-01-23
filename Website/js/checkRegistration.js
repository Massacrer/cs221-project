function checkRegistration(){
return checkEmail();
}

 
 function checkPassword(){
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
 
 function checkEmail() {
			var eMail = document.getElementById('email');
			var filter = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
			if (!filter.test(eMail.value)) {
			document.getElementById("message_after_register").innerHTML = "Wrong Email Address";
			return false;
		 }
		 else
		 {
			document.getElementById("message_after_register").innerHTML = "";
			return true;
		 }		 
 }

