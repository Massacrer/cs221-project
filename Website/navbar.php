<!--
//nav bar
// Author: Niall
-->

<link rel="stylesheet" type="text/css" href="css/master.css" />
<link rel="stylesheet" type="text/css" href="css/navbar.css" />
	<div class="row list">
		<div class="col-12 logo">
			<!-- CSS IN NAV BACKGROUND -->
			<ul>
				<li class="col-2 logo">
				<li class="listelement"><a href="reservelist.php">RESERVE LIST</a></li>
				<li class="listelement"><a href="addreserve.php">ADD RESERVE</a></li>
				<li class="listelement"><a href="php/logout.php">LOGOUT</a><li>
				<li class="listelement"><a href="reservelist.php?name=<?php echo $_SESSION['user']; ?>">USER PAGE</a></li>
			</ul>
		</div>
		
	</div>