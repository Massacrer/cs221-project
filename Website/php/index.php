<!--
Author: Niall
This file is for the logging in
They will run this script
-->
<?php

include ('dbconnect.php');

//if submit
//call the methods
if($_POST['submit']){
	opendatabase();
	logtheuserin();
	closedatabase();
}



//This is the login function
//True logs the user in
//False means they did not login
function logtheuserin(){
	$username = $_POST['myusername'];
	$password = $_POST ['mypassword'];
	
	
	// include database
	
	//connect databse
	
	//lookforl ine with that username

	
	
	//checks if the user has logged in successfully
	//true logs the user in
	//false they don't log in
	if($username == "jim"){	
		session_start();
		$_SESSION['auth'] = 1;
		header('Location: ./reservelist.php');
		return true;
	}else{
		header('Location: ./index.php?attempt=1');
		return false;
	}

}

//This function will return a message on a failed login
// returns a string 
function loginmessage(){
	if($_GET['attempt'] > 0){
		echo ("Incorrect Login Details");
	}
}



?>