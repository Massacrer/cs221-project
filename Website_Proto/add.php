<!DOCTYPE HTML>
<? require("./php/loggedin.php") ?>
<!-- Author: Niall Bunting (nib28@aber.ac.uk) -->
<html>
<head>
	<title>PROTO: ADD</title>
	<link rel="stylesheet" type="text/css" href="css/add.css">
	<script type="text/JavaScript" src="js/add.js"></script> 
</head>
<body>

<div id="menu">
	<? require("./menu/mainmenu.php") ?>
</div>

<h2>This page will let the user add visits</h2>

<div class="visit">
	<? require("./forms/visits.php") ?>
</div>

<hr>

<div class="species" id="species_1">
	<? require("./forms/species.php") ?>


	<div class="plant">
		<? require("./forms/plant.php") ?>
	</div>
	
	<div id="1"> </div>

	<div id="plantadder">
		<a onclick="addplant('1')" href="#" > Add A Plant </a>
	</div>

</div>

<hr>

<div id="speciesadder">
	<a onclick="addspecies()" href="#"> Add A Species </a>
</div>

</body>
</html>
