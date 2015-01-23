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
		<link rel="stylesheet" type="text/css" href="css/master.css">
		<link rel="stylesheet" type="text/css" href="css/specieslist.css">
	</head>
	<!-- END HEAD -->

	<!-- PHP INCLUDE -->	
	<?php
		require('php/loggedin.php');
		include("php/specieslist.php");
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
			<section>
				<div class="row">
					<div class="col-12">
						<p class="heading">Species List</p>
					</div>
				</div>
				<div class="row mainsection results">
					<div class="col-12">
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
