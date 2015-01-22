function checkRegistration(){
return checkEmail(); 
}
function checkEmail() {
			var eMail = document.getElementById('email');
			var filter = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;

			if (!filter.test(eMail.value)) {
			document.getElementById("message_after_register").innerHTML = "Incorrect Login Details";
			//alert('Please provide a valid email address');
			//email.focus;
			return false;
		 }
		 else
		 {
			document.getElementById("message_after_register").innerHTML = "";
			return true;
		 }
		//}alert('Please provide a valid email address');
   // eMail.focus;
    
 }

