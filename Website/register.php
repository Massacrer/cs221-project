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

			<!-- SEPERATOR -->
			<div class="row seperator"></div>
			<!-- END SEPERATOR -->

			<div class="row mainsection">
				<div class="col-12 ">
					<!-- REGISTER FORM -->
					<form method="POST" action="">
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
								<td><input name="username" type="text" id="username" /></td>
							</tr>
							<tr>
								<td>Password</td>
								<td>:</td>
								<td><input name="password1" type="password" id="password1" /></td>
							</tr>
							<tr>
								<td>Confirm Password</td>
								<td>:</td>
								<td><input name="password2" type="password" id="password2" /></td>
							</tr>
							<tr>
								<td>First Name</td>
								<td>:</td>
								<td><input name="fname" type="text" id="fname" /></td>
							</tr>
							<tr>
								<td>Second Name</td>
								<td>:</td>
								<td><input name="sname" type="text" id="sname" /></td>
							</tr>
							<tr>
								<td>Phone Number</td>
								<td>:</td>
								<td><input name="phone" type="text" id="phone" /></td>
							</tr>
							<tr>
								<td>E-mail Address</td>
								<td>:</td>
								<td><input name="email" type="email" id="email"></td>
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
