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





$databasemessage = "";

//This is the login function
//True logs the user in
//False means they did not login
function logtheuserin(){
	//$username = $_POST[];
	//$password = $_POST[];
	
	
	// include database
	
	//connect databse
	
	//lookforl ine with that username
	
	//get the details and see if the passwords match
	
	
	
	
	//returm true;

}

//This function will return a message on a failed login
// returns a string 
function loginmessage(){
	return $databasemessage;
}



?>