<!--
Author: Niall
This file is for the logging in
They will run this script
-->
<?php


//if submit
//call the methods
if($_POST['submit']){
	logtheuserin();
}





$loginmessage = '';

//This is the login function
//True logs the user in
//False means they did not login
function logtheuserin(){
	$username = $_POST['myusername'];
	$password = $_POST['mypassword'];
	
	
	// include database
	
	//connect databse
	
	//lookforl ine with that username
	
	//get the details and see if the passwords match
	
	
	return true;

}

//This function will return a message on a failed login
// returns a string 
function loginmessage(){
	global $loginmessage;
	echo ($loginmessage);
}



?>