<!--
//nav bar
// Author: Niall
-->

<link rel="stylesheet" type="text/css" href="css/master.css" />
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
			<div class="col-2 listelement">
				<a href="reservelist.php">RESERVE LIST</a>
			</div>
			<div class="col-2 listelement">
				<a href="addreserve.php">EDIT RESERVE</a>
			</div>
			<div class="col-2 listelement">
				<a href="reservelist.php?name=<?php echo $_SESSION['user']; ?>">USER PAGE</a>
			</div>
			<div class="col-1"></div>
			<div class="col-1 listelement">
				<a href="php/logout.php">LOGOUT</a>
			</div>			
		</div>
