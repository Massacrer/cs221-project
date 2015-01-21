<!-- #
Author: Niall
This form checks that they are logged in.
 -->
<?php
	
	session_start();
	
	if(!isset($_SESSION['auth']) || $_SESSION['auth'] < 1){
	//not logged in

		header('Location: ./index.php');

		die("Login Failure. <a href='../'> Login </a>");
	}

?>
