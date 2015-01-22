<!--
Authors: Niall, Cai
This is the screen the user will login to
this form will be called when they attempt to register.

-->
<?php

include 'ssbcrypt.php';
//If submit
//Then call the function
if($_POST['submit']){
	userregister();
}

$registermessage = '';

//This function will return a message on a failed login
// returns a string 
function registermessage(){
	global $registermessage;
	echo $registermessage;
}





function userregister(){
	$username = $_POST['username'];
	$password = $_POST['password1'];
	//$password = $_POST['password2']; redundant
	$fnams = $_POST['fname'];
	$sname = $_POST['sname'];
	$number = $_POST['phone'];
	$email = $_POST['email'];
	


}

function sechash() {
//hashes the password for security
	$hash = password_hash($password, PASSWORD_DEFAULT);
}

?>