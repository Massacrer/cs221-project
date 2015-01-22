<!--
Author: Katarzyna Turczynska
This page is redirected to from the add reserve page.
-->

<!DOCTYPE html>

<html lang="en">

	<!-- HEAD -->
	<head>
		<title>Add Reserve</title>
		<meta charset="UTF-8" />
		<link rel="stylesheet" type="text/css" href="css/master.css" />
		<link rel="stylesheet" type="text/css" href="css/register.css" />
	</head>
	<!-- END HEAD -->

	<!-- PHP INCLUDE -->
	<?php 
		require('php/loggedin.php');
		//include("php/addreserve.php");
	?>
	<!-- END PHP INCLUDE -->

	<!-- BODY -->
	<body>

		<!-- CONTAINER -->
		<div class="container">

			<!-- NAV BAR -->
			<div class="row">
				<nav>
				<?php include("navbar.php"); ?>
				</nav> 
			</div>
			<!-- END NAV BAR -->

			<!-- MAIN SECTION -->
			<div class="row mainsection">
				<form method="POST" action="">
					<table>
						<tr> 
							<td colspan="3">Add Reserve</td>
						</tr>
						<tr>
							<td>Name</td>
							<td>:</td>
							<td colspan="3"><input type="text" name="Name"></td>
						</tr>
						<tr>
							<td>Location</td>
							<td>:</td>
							<td colspan="3"><input type="text" name="Location"></td>
						</tr>
						<tr>
							<td>OS Grid Reference</td>
							<td>:</td>
							<td><input type="text" name="OSGridReference"></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td><button type="button">Add Reserve</button></td>
						</tr>
					</table>
				</form>
			</div>
			<!-- END MAIN SECTION -->
		</div>
		<!-- END CONTAINER -->
	</body>
	<!-- END BODY -->

</html> 
