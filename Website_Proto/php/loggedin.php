<!-- LOGGEDIN PHP -->
<!-- the purpose of this file is to make sure the user is logged in -->
<?php
	
	session_start();
	
	if($_SESSION['auth_level'] < 1){
	//not logged in

		header('Location: ./index.php');

		require("./forms/login.php");

		die("Login Failure. <a href='./'> Login </a>");
	}

?>
