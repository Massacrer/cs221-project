<!-- LOGIN PHP -->
<?php
	session_start();

	if(isset($_SESSION['auth_level'])){
		$_SESSION['auth_level'] = 0;
		unset($_SESSION['auth_level']);
		session_destroy();
	}
	
	if(isset($_GET['submit'])){
		$username = $_GET['username'];
		$password = $_GET['password'];

		if($username == "jim"){
			$_SESSION['auth_level'] = 1;
			header( 'Location: ./visits.php');
		}
	}
?>
