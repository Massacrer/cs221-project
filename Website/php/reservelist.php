<?php

	include('php/dbconnect.php');

	function outputtable(){
	
		$con = opendatabase();
		$query = "SELECT * FROM Reserve"; 
		$result = mysqli_query($con, $query);
	
		printf("Select returned %d rows.\n", mysqli_num_rows($result));
		
		while ($row=mysqli_fetch_row($result))
    {
    printf ("%s (%s)\n",$row[0],$row[1]);
    }
  // Free result set
  mysqli_free_result($result);
		closedatabase();
	}

?>