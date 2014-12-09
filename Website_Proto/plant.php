<!DOCTYPE HTML>
<? require("./php/loggedin.php") ?>
<!-- Author: Niall Bunting (nib28@aber.ac.uk) -->
<html>
<head>
	<title>PROTO: PLANT</title>
	<link rel="stylesheet" type="text/css" href="css/plant.css">
	<script type="text/JavaScript" src="js/plant.js"></script> 
</head>
<body>

<div id="menu">
	<? require("./menu/mainmenu.php") ?>
</div>

<h2>This page will contain all the individual plants of a species</h2>

<table>
<tr><td>Id</td><td>Plants</td><td>DAFOR</td><td>Picture</td></tr>
<tr><td>1</td><td>Individual Plant1</td><td>9</td><td><a onclick="openpic()" href="#"><img src="http://www.ikea.com/PIAimages/67447_PE181288_S5.JPG" width="30px"/></a></td></tr>
<tr><td>2</td><td>Individual Plant2</td><td>2</td><td><a onclick="openpic()" href="#"><img src="http://www.ikea.com/PIAimages/67447_PE181288_S5.JPG" width="30px"/></a></td></tr>
</table>

<div class=picture class="picture" id="1">
	<a onclick="closepic()" href="#">Close</a>
	<img src="http://www.ikea.com/PIAimages/67447_PE181288_S5.JPG"/>
</div>

</body>
</html>
