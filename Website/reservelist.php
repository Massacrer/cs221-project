<!--
Author: Himalya Singh
This file is part of the main 3 homepages of the website
-->

<!DOCTYPE html>
<html lang="en">

	<!-- HEAD -->
	<head>
		<title>Reserve List</title>
		<link rel="shortcut icon" href="res/icon.ico">
		<meta charset="UTF-8" />		
		<link href='http://fonts.googleapis.com/css?family=Lato:100,400' rel='stylesheet' type='text/css'>
		<!--[if !IE]> -->
		<link rel="stylesheet" type="text/css" href="css/master.css" />
		<link rel="stylesheet" type="text/css" href="css/reservelist.css" />
		<!-- <![endif]-->
		<script type="text/javascript" src="js/reservelist.js"></script>
	</head>
	<!-- END HEAD -->

	<!-- PHP INCLUDE -->	
	<?php
		require('php/loggedin.php');
		include("php/reservelist.php");
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
			<section class="mainsection">
			<div class="row">
				<div class="col-12">
					<a href=""><?php if(!isset($_GET['name'])){ echo "Reserve List"; }else{echo getnamefromid($_GET['name']) . "'s Reserve List";} ?></a>
					<hr />
				</div>
			</div>
				<div class="row">
					<div class="col-12">
						<p class="heading"><?php if(!isset($_GET['name'])){ echo "Reserves"; }else{echo getnamefromid($_GET['name']) . "'s Reserves";} ?></p></td>
					</div>
				</div>
				<div class="row">
					<!-- EDIT RESERVE FORM -->
					<form method="GET" onsubmit="">
						<table width="5">
							<tr>
								<td>Place</td>
								<td>:</td>
								<td colspan="3"><input type="text" placeholder="Place Name" name="place" /></td>
							</tr>
							<tr>
								<td>Date</td>
								<td>:</td>
								<td><input type="text" placeholder="dd" name="fromday" class="resized" /></td>
								<td><input type="text" placeholder="mm" name="frommonth" class="resized" /></td>
								<td><input type="text" placeholder="yyyy" name="fromyear" class="resized" /></td>
							</tr>
							<tr>
								<td>User</td>
								<td>:</td>
								<td colspan="3"><input type="text" placeholder="A username" name="user" /></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td colspan="3"><input type="submit" /></td>
							</tr>
						</table>
					</form>
					<!-- END EDIT RESERVE FORM -->
				</div>
				<div class="row results">
					<div class="col-12">
						<hr />
						<!-- PHP GENERATED CONTENT -->
						<?php outputtable(); ?>
					</div>
				</div>
			</section>
			<!-- END MAIN SECTION -->

		</div>
		<!-- END CONTAINER -->

	</body>
	<!-- END BODY -->

</html>