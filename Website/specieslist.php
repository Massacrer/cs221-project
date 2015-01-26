<!--
Author: Himalya Singh
This file is part of the main 3 homepages of the website
-->

<!DOCTYPE html>
<html lang="en">

	<!-- HEAD -->
	<head>
		<link rel="shortcut icon" href="res/icon.ico">
		<title>Species List</title>
		<meta charset="UTF-8" />
		<link href='http://fonts.googleapis.com/css?family=Lato:100,400' rel='stylesheet' type='text/css'>
				<!--[if !IE]> -->
		<link rel="stylesheet" type="text/css" href="css/master.css">
		<link rel="stylesheet" type="text/css" href="css/specieslist.css">
				<!-- <![endif]-->
		<script type="text/javascript" src="js/reservelist.js"></script>
	</head>
	<!-- END HEAD -->

	<!-- PHP INCLUDE -->	
	<?php
		require('php/loggedin.php');
		include("php/specieslist.php");
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
						<p class="locationtext"><a href="reservelist.php">Reserve List</a> -> <a href="">SpeciesList</a></p>
					</div>
				</div>
				<div class="row">
					<div class="col-12">
						<hr />
						<p class="heading">Species List</p>
					</div>
				</div>
				<div class="row results">
					<div class="col-12">
						<!-- PHP GENERATED CONTENT -->
						<?php
						outputtable();?>
						<hr />
						<p class="heading">The actual plants</p>
						<?php 
						outputspeciestable(); 
						 ?>
					</div>
				</div>
			</section>
			<!-- END MAIN SECTION -->

		</div>
		<!-- END CONTAINER -->

	</body>
	<!-- END BODY -->

</html>
