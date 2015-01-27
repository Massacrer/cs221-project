<!DOCTYPE HTML>
<? require("./php/loggedin.php") ?>
<!-- Author: Niall Bunting (nib28@aber.ac.uk) -->
<html>
<head>
	<title>PROTO: SPECIES</title>
</head>
<body>

<div id="menu">
	<? require("./menu/mainmenu.php") ?>
</div>

<h2>This page will contain all the species of the visit</h2>

<table>
<tr><td>Id</td><td>Plant Type</td><td>Location</td><td>DAFOR</td></tr>
<tr><td>1</td><td><a href="./plant.php?id=1">Dandeilion</a></td><td>Aber</td><td>9</td></tr>
<tr><td>2</td><td><a href="./plant.php?id=2">Charmander</a></td><td>Pokemon</td><td>2</td></tr>
</table>

</body>
</html>
