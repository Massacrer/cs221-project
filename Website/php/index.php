<!--
Authors: Niall, Cai
This file is for the logging in
They will run this script
-->
<?php
/*
Salt and Hash Library
*/
include 'ssbcrypt.php';

//if submit
//call the methods

if($_POST['submit']){
	logtheuserin();
}



//This is the login function
//True logs the user in
//False means they did not login
function logtheuserin(){
 /*
 
 Opens a connection to the database
 sanitises the data inputs using the mySQLi escape string function
 
 */
	$con=mysqli_connect("db.dcs.aber.ac.uk", "amdcrj10", "group5db1337", "csgp05_14_15");
	$username = mysqli_real_escape_string($con,$_POST['myusername']);
	$password = mysqli_real_escape_string($con,$_POST['mypassword']);
	
	//checks if the connection succeeds
	if (mysqli_connect_errno()) {
		echo "Failed to connect to MySQL: " . mysqli_connect_error();
	}
	else {
		/* 
		*	Creates a SQL query which grabs the User in the table
		*/
		$query = "SELECT * FROM User WHERE Username='$username' LIMIT 1;";		
		$result = mysqli_query($con, $query); // opens the connection and executes the SQL query
		//if there is no results provide feedback
		if(! $result) {
			echo "Could not retrieve result.";
			
		}
		else{
		// get the row of the query as an array
			$row = mysqli_fetch_row($result);
			if (!$row){ 
				echo "Incorrect User/Password"; 
			}
			else{			
				/*
				*
				*	if the password matches the hash in the database then start the session and log the user in
				*/
				if (password_verify($password, $row[4])) {
					session_start();
					$_SESSION['auth'] = 1;
					$_SESSION['user'] = $username;
					
					header('Location: ./reservelist.php');
				}
				else {
					echo "password didn't verify";
				}
			}
	
		
	
		}

	}
	
}

function loginmessage() {
/**/
}
?>