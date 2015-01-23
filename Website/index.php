<!-- 
Author: Michael Higginbottom
This is the main index page for the website.
It contains the LOGIN form
-->

<!DOCTYPE html>

<html lang="en">

	<!-- HEAD -->
	<head>
		<link rel="shortcut icon" href="res/icon.ico">
		<title>Reserve Plant Species Recording</title>
		<meta charset="UTF-8" />
		<link href='http://fonts.googleapis.com/css?family=Lato:100,400' rel='stylesheet' type='text/css'> 
		<link rel="stylesheet" type="text/css" href="css/master.css">
		<link rel="stylesheet" type="text/css" href="css/index.css">
		<script type="text/javascript" src="js/checkLoginPassword.js">	</script>
	</head>
	<!-- END HEAD -->

	<!-- PHP INCLUDE -->
	<?php
		include("php/index.php");
	?>
	<!-- END PHP INCLUDE -->

	<!-- BODY -->
	<body>

		<!-- CONTAINER -->
		<div class="container">
		
			<!-- LOGIN -->
			<div class="row">
				<div class="col-3"></div>
				<div class="col-6">

					<!-- LOGIN FORM -->
					<form method="post" onsubmit="return checkLoginPassword();">
						<table width="4">
							<tr>
								<td colspan="3" class="heading">LOG IN</td>
								<td rowspan="4" class="align-fix"><img src="res/logo.png" /></td>
							</tr>
							</tr>
							
							<tr>
								<td colspan="3">
									<!-- RETURN FIELD -->
									<div id="message_after_login">
										<?php  loginmessage(); ?>
									</div>
									<!-- END OF RETURN FIELD -->
								</td>
							</tr>

							<tr>
								<td>Username</td>
								<td>:</td>
								<td><input name="myusername" type="text" id="myusername"/></td>
							</tr>
							<tr>
								<td>Password</td>
								<td>:</td>
								<td><input name="mypassword" type="password" id="mypassword" /></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td><input name="submit" type="submit" id="submit" value="Login" />&nbsp;Or <a href="register.php">Register</a></td>
							</tr>
						</table>
					</form>
					<!-- END LOGIN FORM -->

				</div>
				<div class="col-3"></div>
			</div>
			<!-- END LOGIN -->
			
		</div>
		<!-- END CONTAINER -->

	</body>
	<!-- END BODY -->

</html>