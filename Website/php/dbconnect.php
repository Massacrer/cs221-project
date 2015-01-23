<!-- database connection
Author: Niall

 -->
 
 <?php
 
 //connect to the database
 
// Ill use my details
$con;
 
 function opendatabase(){
	global $con;
	$con=mysqli_connect("db.dcs.aber.ac.uk", "amdcrj10", "group5db1337", "csgp05_14_15");

	if (mysqli_connect_errno()) {
		echo "Failed to connect to MySQL: " . mysqli_connect_error();
	}
	return $con;
 
 }
 
 function closedatabase(){
	global $con;
	if($con) {mysqli_close($con);} 
 } 
 
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
 ?>