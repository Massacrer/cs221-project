<!--
	Author: Niall

-->
<?php
	session_start();
	
	$_SESSION['auth'] = 0;
	
	session_destroy();
	
	header('Location: ../');

	die("Logged Out. <br> <a href='../'> Back to home </a>");
?>