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
			<a href="reservelist.php"><div class="col-2 listelement">
				RESERVE LIST
			</div></a>
			<a href="editreserve.php"><div class="col-2 listelement">
				EDIT RESERVE
			</div></a>
			<a href="reservelist.php?name=<?php echo $_SESSION['user']; ?>"><div class="col-2 listelement">
				USER PAGE
			</div></a>
			<div class="col-1"></div>
			<a href="php/logout.php"><div class="col-1 listelement">
				LOGOUT
			</div></a>		
		</div>
