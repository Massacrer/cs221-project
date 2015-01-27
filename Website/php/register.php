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
	 /*
 
 Opens a connection to the database
 sanitises the data inputs using the mySQLi escape string function
 
 */
	$con=mysqli_connect("db.dcs.aber.ac.uk", "amdcrj10", "group5db1337", "csgp05_14_15");
	$username = mysqli_real_escape_string($con,$_POST['username']);
	$password = mysqli_real_escape_string($con,$_POST['password']);
	$hash = password_hash($password, PASSWORD_DEFAULT);
	$fname = mysqli_real_escape_string($con,$_POST['fname']);
	$sname = mysqli_real_escape_string($con,$_POST['sname']);
	$number = mysqli_real_escape_string($con,$_POST['phone']);
	$email = mysqli_real_escape_string($con,$_POST['email']);
	//checks if the connection succeeds
	if (mysqli_connect_errno()) {
		echo "Failed to connect to MySQL: " . mysqli_connect_error();
	}
	else {
		// creates a query to register the user on the database
		$query = "INSERT INTO User (Forename, Surname, Password, Phone, Email, Username, Hidden) VALUES ('$fname', '$sname', '$hash', '$number', '$email', '$username', '0');"; 
		$result = mysqli_query($con, $query);
		if(! $result) {
		// checks if the user's Username or Email has already been registered
			$result = 0;
			
			$query = "SELECT COUNT(*) FROM User WHERE Username='$username' LIMIT 1;";
			$result = mysqli_query($con, $query);
			if ($result == 1) {
				echo "Account creation failed - Username is already in use.";
			} 
		}
		else {
			echo "Success";
			header( 'Location: index.php' );	
		}
	}


}


?>