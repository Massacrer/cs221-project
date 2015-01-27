<!-- database connection
Author: Niall

 -->
 
 <?php
 /*
  EDIT HERE AND ADD IN YOUR CORRECT DATABSE DETAILS
 */
 $hostname = "db.dcs.aber.ac.uk";
 $username = "amdcrj10";
 $password = "group5db1337";
 $database = "csgp05_14_15";
/*
	END OF THE DATABASE SECTION
*/
 
// Ill use my details
$con;
 
 //calls the database used throughout the code
 function opendatabase(){
	global $con;
	global $hostname, $username, $password, $database;
	$con=mysqli_connect($hostname, $username, $password, $database);

	if (mysqli_connect_errno()) {
		echo "Failed to connect to MySQL: " . mysqli_connect_error();
	}
	return $con;
 
 }
 
 //a call to close the database - depreciated
 function closedatabase(){
	global $con;
	if($con) {mysqli_close($con);} 
 } 
 
 // Returns a string name from a id passed in
function getnamefromid($id){
	$con = opendatabase();
	$id = mysqli_real_escape_string($con, $id);
	$query = "SELECT Username FROM User WHERE ID = '". $id ."' LIMIT 1";
	
	$result = mysqli_query($con, $query);
		
	while ($row=mysqli_fetch_row($result)){
		return $row['0'];
	}

	closedatabase();
}

// Gets a name from an id
function getidfromname($name){
	$con = opendatabase();
	$id = mysqli_real_escape_string($con, $name);
	$query = "SELECT ID FROM User WHERE Username = '". $name ."' LIMIT 1";
	
	$result = mysqli_query($con, $query);
		
	while ($row=mysqli_fetch_row($result)){
		return $row['0'];
	}
	
	
	closedatabase();
}
 ?>