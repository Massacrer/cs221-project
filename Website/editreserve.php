
<!--
Author: Katarzyna Turczynska
This page is redirected to from the add reserve page.
-->
<!DOCTYPE html>
<html lang="en">
	<!-- HEAD -->
	<head>
		<link rel="shortcut icon" href="res/icon.ico">
		<title>Edit Reserve</title>
		<meta charset="UTF-8" />
		<link href='http://fonts.googleapis.com/css?family=Lato:100,400' rel='stylesheet' type='text/css'>
				<!--[if !IE]> -->
		<link rel="stylesheet" type="text/css" href="css/master.css" />
		<link rel="stylesheet" type="text/css" href="css/editreserve.css" />
				<!-- <![endif]-->
	</head>
	<!-- END HEAD -->
	
	<!-- PHP INCLUDE -->
	<?php
		require('php/loggedin.php');
		include("php/editreserve.php");
	?>
	<!-- END PHP INCLUDE -->
	
	<!-- BODY -->
	<body>
	
		<!-- CONTAINER -->
		<div class="container">
		
			<!-- NAV BAR -->
			<nav>
				<?php include("navbar.php"); ?>
			</nav>
			<!-- END NAV BAR -->
			
			<!-- MAIN SECTION -->
			<div class="row mainsection">

				<div class="row">
					<div class="col-12">
						<a href="">Edit Reserve</a>
						<hr />
					</div>
				</div>

				<div class="row">
					<div class="col-12 ">
						<p class="heading">Edit Reserve</p>
					</div>
				</div>

				<div class="row">
					<div class="col-12">
						<form name="reserveedit" action="">
							<table>
								<tr>
									<td>Name of Reserve</td>
									<td>:</td>
									<td><input type="text" placeholder="Name of Reserve" /></td>
								</tr>
								<tr>
									<td>Time</td>
									<td>:</td>
									<td><input type="text" placeholder="Time" /></td>
								</tr>
								<tr>
									<td>Location</td>
									<td>:</td>
									<td><input type="text" placeholder="Longitude" /></td>
									<td><input type="text" placeholder="Latitude" /></td>
								</tr>
								<tr>
									<td>Description:</td>
								</tr>
								<tr>
									<td colspan="4"><textarea rows="4" cols="65" type="textarea" placeholder="Description" /></textarea></td>
								</tr>
							</table>
						</form>
					</div>
				</div>

				<div class="row">
					<div class="col-12">
						<hr />
					</div>
				</div>

				<div class="row">
					<div class="col-12">
						<p class="heading">Edit Species</p>
					</div>
				</div>

				<div class="row">
					<div class="col-12">
						<!-- PHP GENERATED CONTENT HERE - NIALL GRAB THIS CODE AND TAKE INTO PHP/EDITRESERVE.PHP -->
						<form name="speciesedit" action="">
							<table width="6">
								<tr>
									<td>Name</td>
									<td>:</td>
									<td colspan="4"><input type="text" placeholder="Name of Species" /></td>
								</tr>
									<td>Time</td>
									<td>:</td>
									<td><input type="text" placeholder="Time" /></td>
									<td>DAFOR:
										<select>
										   <option value="D">D</option>
										   <option value="A">A</option>
										   <option value="F">F</option>
										   <option value="O">O</option>
										   <option value="R">R</option>
										</select>
									</td>
								<tr>
									<td>Location</td>
									<td>:</td>
									<td><input type="text" placeholder="Latitude" /></td>
									<td><input type="text" placeholder="Longitude" /></td>
								</tr>
								<tr>
									<td>Image of Area</td>
									<td>:</td>
									<td colspan="2"><input type="file" name="areaimage" placeholder="Image of Area" size="40" /></td>
								</tr>
									<td>Image of Indiviual Plant</td>
									<td>:</td>
									<td colspan="2"><input type="file" name="individualimage" placeholder="Image of Individual" size="40" /></td>
								<tr>
									<td>Description</td>
									<td>:</td>
								</tr>
								<tr>
									<td colspan="4"><textarea rows="4" cols="65" type="textarea" placeholder="Description" /></textarea></td>
								</tr>
							</table>
						</form>
						<hr />
					</div>
				</div>
						
				</div>
			</div>
			<!-- END MAIN SECTION -->
			
		</div>
		<!-- END CONTAINER -->

	</body>
	<!-- END BODY -->
</html>

    
