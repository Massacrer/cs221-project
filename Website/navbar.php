<!--
//nav bar
// Author: Niall
-->
<link rel="stylesheet" type="text/css" href="css/navbar.css" />

		<div class="row list">
			<div class="col-3 green">
				<div class="row">
					<div class="col-3 logo"></div>
					<div class="col-9 title">
						<p class="titletext">RESERVE PLANT SPECIES RECORDING</p>
					</div>
				</div>
			</div>
			<a href="reservelist.php" class="col-2 listelement button">
				RESERVE LIST
			</a>
			<a href="editreserve.php" class="col-2 listelement button">
				ADD RESERVE
			</a>
			<a href="reservelist.php?name=<?php echo $_SESSION['user']; ?>" class="col-2 listelement button">
				USER PAGE
			</a>
			<div class="col-1"></div>
			<a href="php/logout.php" class="col-1 listelement button">
				LOGOUT
			</a>		
		</div>
