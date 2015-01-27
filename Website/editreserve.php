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
		if(isset($_GET['editid']) && !usersreservedata($_GET['editid'])){ header('Location: editreserve.php'); die("Unable to modify this reserve."); }
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
						<p class="heading">Edit Reserve</p><a href="#addnew">(Jump to add new)</a>
					</div>
				</div>

				<div class="row">
					<div class="col-12">
						<?php loadreserve(); ?>
					</div>
				</div>

				<div class="row">
					<div class="col-12">
						<hr />
					</div>
				</div>

				<div class="row">
					<div class="col-12">
						<p class="heading">Edit Existing Species</p>
					</div>
				</div>

				<div class="row">
					<div class="col-12">
						<?php loadspecies(); ?>
					</div>
				</div>

				<div class="row">
					<div class="col-12">
						<p class="heading">Add New Species</p>Note: Create a new reserve first.
						<a name="addnew"></a> 
					</div>
				</div>

				<div class="row">
					<div class="col-12">
						<form name="speciesedit" method="POST">
							<input type="hidden" value="<?php echo $_GET['editid']; ?>" name="reserve"/>
							<table>
								<tr>
									<td>Name</td>
									<td>:</td>
									<td colspan="2"><input type="text" placeholder="Name of Species"  name="name" /></td>
								</tr>
								<tr>
									<td>Time (yyyy-mm-dd hh:mm:ss)</td>
									<td>:</td>
									<td><input type="text" placeholder="Time"  name="time" /></td>
									<td>DAFOR:
										<select  name="dafor">
										   <option value="D">D</option>
										   <option value="A">A</option>
										   <option value="F">F</option>
										   <option value="O">O</option>
										   <option value="R">R</option>
										</select>
									</td>
								</tr>
								<tr>
									<td>Location</td>
									<td>:</td>
									<td><input type="text" placeholder="Latitude"  name="lat" /></td>
									<td><input type="text" placeholder="Longitude" name="lng" /></td>
								</tr>
								<tr>
									<td>Image of Area</td>
									<td>:</td>
									<td colspan="2"><input type="text" name="imagearea" placeholder="Image of Area URL" size="40" /></td>
								</tr>
								<tr>
									<td>Image of Indiviual Plant</td>
									<td>:</td>
									<td colspan="2"><input type="text" name="imageplant" placeholder="Image of Individual URL" size="40" /></td>
								</tr>	
								<tr>
									<td>Description</td>
									<td>:</td>
								</tr>
								<tr>
									<td colspan="4"><textarea rows="4" cols="65"  name="desc" placeholder="Description"></textarea></td>
								</tr>
								<tr>
									<td colspan="4"><input type="submit" name="add_new" value="Add new species" /></td>
								</tr>
							</table>
						</form>
						<hr />
					</div>
				</div>			
			</div>
			<!-- END MAIN SECTION -->
			
		</div>
		<!-- END CONTAINER -->

	</body>
	<!-- END BODY -->
</html>
