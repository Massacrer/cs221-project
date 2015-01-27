<?php 


if( isset($_POST["json"]) ) {
     $data = json_decode($_POST["json"], TRUE);
     //$data->msg = strrev($data->msg);
	 $con=mysqli_connect("db.dcs.aber.ac.uk", "amdcrj10", "group5db1337", "csgp05_14_15");
   //$data = json_decode($data);  
	 $data = json_encode($data);
	 //echo $data;
	 $databck = json_decode($data);
	 //echo $databck
	 //echo $databck->{'name'};
	 //echo $data->name . " lol";
	 if (mysqli_connect_errno()) 
	{
		echo "Failed to connect to MySQL: " . mysqli_connect_error();
	}
	else 
	{
		$sql = "SELECT * FROM User where Username='" . $databck->user_name . "';";
		$result = mysqli_query($con, $sql);
		if (!result) {
			echo  "sql issue with user";
			//echo $sql;
		}
		
		else {
		echo $databck->user_name;
		$row = mysqli_fetch_row($result);
		echo $row[0];
		echo $row[0];
		 $sql = "INSERT INTO Reserve (reserveName, reserveDescription , reserveLocationLat , reserveDateTimeCreation, reserveHidden,reserveUserID ) VALUES('" . $databck->name . "', '" . $databck->description . "', '" . $databck->latitude . "' , '" . $databck->date . "', 0 , '" . $row[0] . "'  );";
		echo $row[0];
		
		$result = mysqli_query($con, $sql);
		$id = mysqli_insert_id($con);
		foreach($databck->species as $vars)
		{
				
		$sql = "INSERT INTO Species (SpeciesName, SpeciesDafor, SpeciesDescription, SpeciesLocationLat, SpeciesLocationLng, SpeciesTimeDate, SpeciesHidden, ReserveID) VALUES ('" .  $vars->name ."' ,'" .  $vars->abundance . "' ,'" . $vars->comment .  "', '" . $vars->latitude . "' ,'" . $vars->longitude . "', ' " . $vars->date .  "', 0, '" . $id . "' ) ;";
		//echo $sql;
		
		$result = mysqli_query($con, $sql);

		}
		
		
		$result = mysqli_query($con, $sql);
		//echo $sql;
		
		}
		//echo $sql;
		if(! $result) 
		{
			echo "sql issue";
		}
	}
 
    
    // INSERT INTO `Reserves` (`reserveName`, 'reserveDescription' `reserveLocationLat`, 'reserveLocationLng' 'reserveDateTimeCreation', 'reserveHidden','reserveUserID' ) VALUES('', '', '', '', '',0 ' ', '' , '');
}
	
	
	

/*
if( isset($_POST["j"]) ) {
     $data = json_decode($_POST["j"]);
     $data->msg = strrev($data->msg);
	 $con=mysqli_connect("db.dcs.aber.ac.uk", "amdcrj10", "group5db1337", "csgp05_14_15");
     echo json_encode($data);
	echo "j";
	
	
}
*/	

?>