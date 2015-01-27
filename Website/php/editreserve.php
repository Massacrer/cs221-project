<?php

include_once('php/dbconnect.php');

//This shows the reserves
function loadreserve(){

	//connect to the database, return the values
	$con = opendatabase();
	$id =  mysqli_real_escape_string($con, $id);
	$query = "SELECT * FROM Reserve WHERE reserveId = '" . $_GET['editid'] . "' && reservehidden = '0'"; 
	
	$result = mysqli_query($con, $query);

	$row=mysqli_fetch_row($result);

	$name = $row['1'];
	$time = $row['3'];
	$lat = $row['2'];
	$lng = $row['3'];
	$desc = $row['4'];

		//Outputs the html data form
?>
	<form name="reserveedit" action="" method="POST">
			<input type="hidden" value="<?php echo $_GET['editid']; ?>"/>
		<table>
			<tr>
				<td>Name of Reserve</td>
				<td>:</td>
				<td><input type="text" name="name" placeholder="Name of Reserve" value="<?php echo $name; ?>" /></td>
			</tr>
			<tr>
				<td>Time</td>
				<td>:</td>
				<td><input type="text" name="time" placeholder="Time" value="<?php echo $time; ?>"/></td>
			</tr>
			<tr>
				<td>Location</td>
				<td>:</td>
				<td><input type="text" name="lat" placeholder="OS grid refrence" value="<?php echo $lat; ?>"/></td>
			</tr>
			<tr>
				<td>Description:</td>
			</tr>
			<tr>
				<td colspan="4"><textarea rows="4" name="desc" cols="65" type="textarea" placeholder="Description" /><?php echo $desc; ?></textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Create/Update" name="submit_reserve"/></textarea></td>
				<td colspan="2"><input type="submit" value="Delete Reserve" name="delete_reserve"/></textarea></td>
			</tr>
		</table>
	</form>

<?php

}

// Here it outputs the species in a reserve for editing
function loadspecies(){

	//checks on a created reserve
	if(!isset($_GET['editid'])){echo "No species yet.<hr>"; return;}
	
	//connect to the database
	$con = opendatabase();
	$id =  mysqli_real_escape_string($con, $id);
	$query = "SELECT * FROM Species WHERE ReserveId = '" . $_GET['editid'] . "' && SpeciesHidden = '0'"; 
	
	$result = mysqli_query($con, $query);

	//check there are some rows made
	if(mysqli_num_rows($result) == 0){echo "No species yet.<hr />"; return;}

	//loop through each species creating a form for each one
	while($row=mysqli_fetch_row($result)){

		$name = $row['1'];
		$time = $row['6'];
		$dafor = $row['2'];
		$lat = $row['4'];
		$lng = $row['5'];
		$desc = $row['3'];
		$imagearea = $row['10'];
		$imageindiviul = $row['9'];
	?>
		<form name="speciesedit" method="POST">
			<a name="<?php echo $row['0']; ?>"></a> 
			<input type="hidden" name="species" value="<?php echo $row['0']; ?>"/>
			<table width="6">
				<tr>
					<td>Name</td>
					<td>:</td>
					<td colspan="4"><input name="name" type="text" placeholder="Name of Species" value="<?php echo $name; ?>" /></td>
				</tr>
					<td>Time</td>
					<td>:</td>
					<td><input type="text" placeholder="Time" name="time" value="<?php echo $time; ?>" /></td>
					<td>DAFOR:
						<select name="dafor">
						   <option value="<?php echo $dafor; ?>"><?php echo $dafor; ?></option>
						   <option value="D">D</option>
						   <option value="A">A</option>
						   <option value="F">F</option>
						   <option value="O">O</option>
						   <option value="R">R</option>
						</select>
					</td>
				<tr>
					<td>Location</td>
					<td>:</td>
					<td><input type="text" name="lat" placeholder="Latitude" value="<?php echo $lat; ?>"/></td>
					<td><input type="text" name="lng" placeholder="Longitude" value="<?php echo $lng; ?>"/></td>
				</tr>
				<tr>
					<td>Image of Area</td>
					<td>:</td>
					<td colspan="2"><input type="text" name="imagearea" name="areaimage" placeholder="Image of Area URL" size="40"value="<?php echo $imagearea; ?>" /></td>
				</tr>
					<td>Image of Indiviual Plant</td>
					<td>:</td>
					<td colspan="2"><input type="text"  name="imageplant" name="individualimage" placeholder="Image of Individual URL" size="40" value="<?php echo $imageindiviul; ?>"/></td>
				<tr>
					<td>Description</td>
					<td>:</td>
				</tr>
				<tr>
					<td colspan="4"><textarea rows="4" name="desc" cols="65" type="textarea" placeholder="Description" /><?php echo $desc; ?></textarea></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" name="submit_species" value="Update" /></td>
					<td colspan="2"><input type="submit" name="delete_species" value="Delete" /></td>
				</tr>
			</table>
		</form>
		<hr />

	<?php
	}
}

//Used to check that the user can edit that reserve
function usersreservedata($id){
	$con = opendatabase();
	$id =  mysqli_real_escape_string($con, $id);
	$query = "SELECT reserveUserid FROM Reserve WHERE reserveId = '$id'"; 
	
	$result = mysqli_query($con, $query);

	$row=mysqli_fetch_row($result);

	//if the ids are the same or an admin
	if($_SESSION['user'] == $row['0'] || $_SESSION['auth'] > 1){
		return true;
	}else{
		return false;
	}
}

/* ALL THE UPDATING AND CHECKING DOWN HERE */

//Checks if a reserve has been submitted for editing/creation
if(isset($_POST['submit_reserve'])){
	$con = opendatabase();		
	$id =  mysqli_real_escape_string($con, $_GET['editid']);
	//If editid is not there they must be creating a new reserve
	if(!isset($_GET['editid'])){
		//create a new reserve
		$query = "INSERT INTO Reserve (reserveUserid, reservehidden) VALUES (" . $_SESSION['user'] .", 0)";
		if (mysqli_query($con, $query)) {
		echo "New record created successfully";
		// set id to the new, for the following update
		$id = mysqli_insert_id($con);
		} else {
		    echo "Error: " . mysqli_error($con);
			die();
		}
	}else{
		//unable to modify this reserve
		if(!usersreservedata($id)){die ("Error with permissions.");}
	}

	// get all the values and update the table
	$name = mysqli_real_escape_string($con, $_POST['name']);
	$time = mysqli_real_escape_string($con, $_POST['time']);
	$lat = mysqli_real_escape_string($con, $_POST['lat']);
	$desc = mysqli_real_escape_string($con, $_POST['desc']);

	$query = "UPDATE Reserve SET reserveName='" . $name . "', reserveDatetimeCreation = '" . $time . "', reserveLocationLat = '" . $lat . "', reserveDescription = '" . $desc . "' WHERE reserveId='" . $id ."'"; 

	if(mysqli_query($con, $query)){

	echo "Updated.";	
	header('Location: ./editreserve.php?editid=' . $id);
	}else{
	die("An error occured.");
	}
}

//for if someone deletes a reserve, then set hidden to 1
if(isset($_POST['delete_reserve'])){

	$con = opendatabase();
	$id =  mysqli_real_escape_string($con, $_GET['editid']);

	if(!usersreservedata($id)){die ("Error with permissions.");}

	$query = "UPDATE Reserve SET reservehidden='1' WHERE reserveId='" . $id ."'"; 

	if(mysqli_query($con, $query)){

	echo "All data was removed.";	
	header('Location: ./reservelist.php');
	}else{
	die("An error occured.");
	}

}

//to edit a species
if(isset($_POST['submit_species'])){
	$con = opendatabase();		
	$id =  mysqli_real_escape_string($con, $_GET['editid']);
	if(!usersreservedata($id)){die ("Error with permissions.");}
	
	//get all the values and update the database
	if(isset($_GET['editid'])){
		$name = mysqli_real_escape_string($con, $_POST['name']);
		$time = mysqli_real_escape_string($con, $_POST['time']);
		$lat = mysqli_real_escape_string($con, $_POST['lat']);
		$lng = mysqli_real_escape_string($con, $_POST['lng']);
		$desc = mysqli_real_escape_string($con, $_POST['desc']);
		$dafor = mysqli_real_escape_string($con, $_POST['dafor']);
		$imagearea = mysqli_real_escape_string($con, $_POST['imagearea']);
		$imageplant = mysqli_real_escape_string($con, $_POST['imageplant']);
		$species = mysqli_real_escape_string($con, $_POST['species']);
		$query = "UPDATE Species SET SpeciesName = '".$name."', SpeciesDafor='".$dafor."', SpeciesDescription='".$desc."', SpeciesLocationLat='".$lat."', SpeciesLocationLng='".$lng."', SpeciesTimeDate='".$time."', SpeciesPicUrlIndvidual='".$imageplant."', SpeciesPicUrlArea='".$imagearea."' WHERE SpeciesId='" . $species ."' && ReserveId='" . $id . "'"; 

		if (mysqli_query($con, $query)) {
			echo "Edited successfully.";
			header('Location: ./editreserve.php?editid='.$id.'#'.$species);
		} else {
			echo "Error: " . mysqli_error($con);
			die();
		}
	}
	else{
		echo "Need to create reserve first";
	}

}	

//deletes a species
if(isset($_POST['delete_species'])){
	$con = opendatabase();
	$id =  mysqli_real_escape_string($con, $_GET['editid']);
	//checks the user can delete
	if(!usersreservedata($id)){die ("Error with permissions.");}

	$species = mysqli_real_escape_string($con, $_POST['species']);
	//sets the hidden value to one effectively removing it
	$query = "UPDATE Species SET SpeciesHidden='1' WHERE SpeciesId='" . $species ."' && ReserveId='" . $id . "'"; 

	if(mysqli_query($con, $query)){

	echo "Data was removed.";	
	header('Location: ./editreserve.php?editid='.$_GET['editid']);
	}else{
	die("An error occured.");
	}

}

//adds a new species
if(isset($_POST['add_new'])){
	$con = opendatabase();		
	$id =  mysqli_real_escape_string($con, $_GET['editid']);
	if(isset($_GET['editid'])){
		$name = mysqli_real_escape_string($con, $_POST['name']);
		$time = mysqli_real_escape_string($con, $_POST['time']);
		$lat = mysqli_real_escape_string($con, $_POST['lat']);
		$lng = mysqli_real_escape_string($con, $_POST['lng']);
		$desc = mysqli_real_escape_string($con, $_POST['desc']);
		$dafor = mysqli_real_escape_string($con, $_POST['dafor']);
		$imagearea = mysqli_real_escape_string($con, $_POST['imagearea']);
		$imageplant = mysqli_real_escape_string($con, $_POST['imageplant']);
		$reserve  = mysqli_real_escape_string($con, $_POST['reserve']);
		$query = "INSERT INTO Species (SpeciesName, SpeciesDafor, SpeciesDescription, SpeciesLocationLat, SpeciesLocationLng, SpeciesTimeDate, ReserveId, SpeciesPicUrlIndvidual, SpeciesPicUrlArea ) VALUES ('".$name."', '".$dafor."', '".$desc."', '".$lat."', '".$lng."', '".$time."', '".$reserve."', '".$imageplant."', '".$imagearea."')";
		
		//query to add new species
		if (mysqli_query($con, $query)) {
			echo "New record created successfully";
			$id = mysqli_insert_id($con);
			header('Location: ./editreserve.php?editid='.$reserve.'#'.$id);
		} else {
			echo "Error: " . mysqli_error($con);
			die();
		}
	}
	else{
		echo "Need to create reserve first";
	}

}

?>
