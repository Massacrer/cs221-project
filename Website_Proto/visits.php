<!DOCTYPE HTML>
<? require("./php/loggedin.php") ?>
<!-- Author: Niall Bunting (nib28@aber.ac.uk) -->
<html>
<head>
	<title>PROTO: VISITS</title>
</head>
<body>

<div id="menu">
	<? require("./menu/mainmenu.php") ?>
</div>

<h2>This page will contain all the visits.</h2>

<table>
<tr><td>Id</td><td>Name</td><td>Location</td><td>Date</td><td>Time</td></tr>
<tr><td>1</td><td><a href="./species.php?id=1">HardCodedExample</a></td><td>Aber</td><td>99-99-9999</td><td>00:00</td></tr>
</table>

</body>
</html>
