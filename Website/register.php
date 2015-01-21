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
		<link rel="stylesheet" type="text/css" href="css/master.css" />
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
			<div class="row">
				<div class="row">
					<!-- REGISTER FORM -->
					<form method="POST" action="register.php">
						<table>
							<tr>
								<td colspan="3">Register</td>
							</tr>
							<tr>
								<td>Username</td>
								<td>:</td>
								<td><input name="username" type="text" id="fname" /></td>
							</tr>
							<tr>
								<td>Password</td>
								<td>:</td>
								<td><input name="password" type="password" id="password" /></td>
							</tr>
							<tr>
								<td>Confirm Password</td>
								<td>:</td>
								<td><input name="password2" type="password" id="password" /></td>
							</tr>
							<tr>
								<td>First Name</td>
								<td>:</td>
								<td><input name="fname" type="text" id="fname" /></td>
							</tr>
							<tr>
								<td>Second Name</td>
								<td>:</td>
								<td><input name="sname" type="text" id="fname" /></td>
							</tr>
							<tr>
								<td>Phone Number</td>
								<td>:</td>
								<td><input name="phone" type="number" id="phone" /></td>
							</tr>
							<tr>
								<td>E-mail Address</td>
								<td>:</td>
								<td><input name="myemail" type="email" id="myemail"></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td><input type="submit" name="submit" value="Register" /></td>
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