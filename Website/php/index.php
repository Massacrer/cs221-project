<!--
Authors: Niall, Cai
This file is for the logging in
They will run this script
-->
<?php

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

	$con=mysqli_connect("db.dcs.aber.ac.uk", "amdcrj10", "group5db1337", "csgp05_14_15");
	$username = mysqli_real_escape_string($con,$_POST['myusername']);
	$password = mysqli_real_escape_string($con,$_POST['mypassword']);
	if (mysqli_connect_errno()) {
		echo "Failed to connect to MySQL: " . mysqli_connect_error();
	}
	else {
		$query = "SELECT * FROM User WHERE Username='$username' LIMIT 1;";		
		$result = mysqli_query($con, $query);
		
		if(! $result) {
			echo "Could not retrieve result.";
		}
		else{
			$row = mysqli_fetch_row($result);
			if (!$row){ 
				echo "Incorrect User/Password"; 
			}
			else{			
				for ($i=0;$i<7;$i++) {
					echo $i . "</br>";
					echo $row[$i];
				}
				if (password_verify($password, $row[4])) {
					session_start();
					$_SESSION['auth'] = 1;
					header('Location: ./reservelist.php');
				}
				else {
					echo "password didn't verify";
				}
			}
	
	
	
		}

	}
}
?>