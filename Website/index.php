<!-- 
Author: Michael Higginbottom
This is the main index page for the website.
It contains the LOGIN form
-->

<!DOCTYPE html>

<html lang="en">

	<!-- HEAD -->
	<head>
		<title>Reserve Plant Species Recording</title>
		<meta charset="UTF-8" />
		<link rel="stylesheet" type="text/css" href="stylesheet.css">
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

		<div class="row">
			<div class="col-12"></div>
		</div>
			<!-- LOGIN -->
			<div class="row">
				<div class="col-4"></div>
				<div class="col-3">
					<!-- LOGIN FORM -->
					<form method="post" action="">
						<table width="4">
							<tr>
								<td colspan="3">LOG IN</td>
							</tr>
							<tr>
								<td>Username</td>
								<td>:</td>
								<td><input name="myusername" type="text" id="myusername" /></td>
							</tr>
							<tr>
								<td>Password</td>
								<td>:</td>
								<td><input name="mypassword" type="password" id="mypassword" /></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td><input name="submit" type="submit" id="submit" value="Login" /></td>
							</tr>
						</table>
					</form>
					<!-- END LOGIN FORM -->
				</div>
				<div class="col-1">
					<!-- IMAGE -->
				</div>
				<div class="col-4"></div>
			</div>
			<!-- END LOGIN -->
			
			<a href="register.php">Register</a>
			
		</div>
		<!-- END CONTAINER -->

	</body>
	<!-- END BODY -->

</html>