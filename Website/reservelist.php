<!--
Author: Himalya Singh
This file is part of the main 3 homepages of the website
-->

<!DOCTYPE html>
<html lang="en">

	<!-- HEAD -->
	<head>
		<title>Reserve List</title>
		<meta charset="UTF-8" />		
		<link href='http://fonts.googleapis.com/css?family=Lato:100,400' rel='stylesheet' type='text/css'>
		<link rel="stylesheet" type="text/css" href="css/reservelist.css" />
		<link rel="stylesheet" type="text/css" href="css/master.css">
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
						<p class="heading"><?php if(!isset($_GET['name'])){ echo "Reserves"; }else{echo getnamefromid($_GET['name']) . "'s Reserves";} ?></p></td>
					</div>
				</div>

				<div class="row results">
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