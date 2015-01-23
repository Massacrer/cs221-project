<!--
Author: Michael Higginbottom
This page is redirected to from the login page.
For new users to register to the website
-->

<!DOCTYPE html>

<html lang="en">

	<!-- HEAD -->
	<head>
		<title>Register</title>
		<meta charset="UTF-8" />
		<link href='http://fonts.googleapis.com/css?family=Lato:100,400' rel='stylesheet' type='text/css'> 
		<link rel="stylesheet" type="text/css" href="css/master.css" />
		<link rel="stylesheet" type="text/css" href="css/register.css" />
		
		<script type="text/javascript" src="js/checkRegistration.js"></script>
	</head>
	<!-- END HEAD -->

	<!-- PHP INCLUDE -->
	<?php
		include("php/register.php");
	?>
	<!-- END PHP INCLUDE -->

	<!-- BODY -->
	<body>

		<!-- CONTAINER -->
		<div class="container">

			<div class="row mainsection">
				<div class="col-12 ">
			
					<!-- Back button to Login -->
					<a href="index.php">Back to Login</a>
					<!-- REGISTER FORM -->
					<form method="POST" onsubmit="return checkRegistration();">
						<table>
							<tr>
								<td colspan="3"><p class="heading">Register</p></td>
							</tr>
								<td colspan="3">
									<!-- RETURN FIELD -->
									<?php  registermessage(); ?>
									<div id="message_after_register">
										&nbsp;
									</div>
									<!-- END OF RETURN FIELD -->
								</td>
							<tr>
								<td>Username</td>
								<td>:</td>
								<td><input name="username" type="text" id="username" required/></td>
							</tr>
							<tr>
								<td>Password</td>
								<td>:</td>
								<td><input name="password" type="password" id="password" /></td>
							</tr>
							<tr>
								<td>Confirm Password</td>
								<td>:</td>
								<td><input name="passwordCOnfirm" type="password" id="passwordConfirm" onsubmit="return checkPassword();"/></td>
							</tr>
							<tr>
								<td>First Name</td>
								<td>:</td>
								<td><input name="fname" type="text" id="fname" required/></td>
							</tr>
							<tr>
								<td>Second Name</td>
								<td>:</td>
								<td><input name="sname" type="text" id="sname" required/></td>
							</tr>
							<tr>
								<td>Phone Number</td>
								<td>:</td>
								<td><input name="phone" type="text" id="phone" pattern="\d{5}-\d{3}-\d{3}|\d{11}|\d{4}-\d{4}-\d{3}-\d{3}|\d{14}" title='Example 0791-695-8912 or 0044-7916-958-912' required /></td>
							</tr>
							<tr>
								<td>E-mail Address</td>
								<td>:</td>
								<td><input name="email" type="text" id="email" ></td>
								
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td><input type="submit" name="submit" id="submit" value="Register" /></td>
								
							</tr>
						</table>
					</form>
					<!-- END REGISTER FORM -->
				</div>
			</div>
		</div>
		<!-- END CONTAINER -->

	</body>
	<!-- END BODY -->

</html>
