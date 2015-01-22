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
	
	$con=mysqli_connect("db.dcs.aber.ac.uk", "amdcrj10", "group5db1337", "csgp05_14_15");
	$username = mysqli_real_escape_string($con,$_POST['username']);
	$password = mysqli_real_escape_string($con,$_POST['password']);
	sechash();
	$fname = mysqli_real_escape_string($con,$_POST['fname']);
	$sname = mysqli_real_escape_string($con,$_POST['sname']);
	$number = mysqli_real_escape_string($con,$_POST['phone']);
	$email = mysqli_real_escape_string($con,$_POST['email']);
	if (mysqli_connect_errno()) {
		echo "Failed to connect to MySQL: " . mysqli_connect_error();
	}
	else {
		$query = "INSERT INTO User (Forename, Surname, Password, Phone, Email, Username) VALUES ('$fname', '$sname', '$hash', '$number', '$email', $username);"; 
		$result = mysqli_query($con, $query);
		if(! $result) {
			$result = 0;
			$feedback = "Account creation failed.";
		}
		else {
			echo "Success";
		}
	}


}

function sechash() {
//hashes the password for security
	$hash = password_hash($password, PASSWORD_DEFAULT);
	
}

?>