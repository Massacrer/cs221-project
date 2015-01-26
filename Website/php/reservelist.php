<?php

	include_once('php/dbconnect.php');

	function outputtable(){
			
		$con = opendatabase();
		
		$where = "";
		
		if(isset($_GET['user'])  && strlen($_GET['user']) > 1){  
			header('Location: reservelist.php?name=' . getidfromname(mysqli_real_escape_string($con, $_GET['user'])));  
		}		
		if(isset($_GET['place']) && strlen($_GET['place']) > 1){  
			$where = $where . " && reserveLocation LIKE '%" . mysqli_real_escape_string($con, $_GET['place']) . "%'";  
		}
		if(isset($_GET['frommonth']) && strlen($_GET['frommonth']) > 1 && isset($_GET['fromday'] ) && strlen($_GET['fromday']) > 1 && isset($_GET['fromyear']) && strlen($_GET['fromyear']) > 1){
			//yyyy-mm-dd hh:mm:ss
			$where = $where . " && reserveDatetimeCreation LIKE '" . mysqli_real_escape_string($con, $_GET['fromyear']) . "-" . mysqli_real_escape_string($con, $_GET['frommonth']) . "-" . mysqli_real_escape_string($con, $_GET['fromday']) . "%'"; 
		}	
		if(isset($_GET['id']) && strlen($_GET['id']) > 0){
			$where =" && reserveId = '" . mysqli_real_escape_string($con, $_GET['id']) . "'"; 
		}
	

		$query = "SELECT * FROM Reserve WHERE reservehidden = '0'" . $where; 
		
		
		if(isset($_GET['name'])){
			$name = mysqli_real_escape_string($con, $_GET['name']);
			$query = $query . " && reserveUserid = '" . $name . "'";		
		}
		
		$result = mysqli_query($con, $query);
			
		while ($row=mysqli_fetch_row($result))
		{
			?>
			
			<div class="row">
				<div class="col-12 element">
					<?php echo "<a class='heading' href='specieslist.php?id=" . $row['0'] . " '>";?>
					<span></span>
					<div class="row">
						<div class="col-12">
							<?php echo $row[1]; ?>
						</div>
					</div>
					<div class="row">
						<div class="col-10 padding">
							<strong>Location: </strong><?php echo $row['2']; ?>
						</div>
						<div class="col-2 padding">
							<?php echo $row['3']; ?>
						</div>
					</div>
					<div class="row">
						<div class="col-10 descriptionreserve padding" id="smalldecrip_<?php echo $row['0']; ?>" >
							<?php
								$longer = false;
								$str = "" . $row['4'];
								if (strlen($str) > 90){
									$str = substr($str, 0, 90) . '... <u><a onclick="showdescription(' . $row['0'] . ', 1)";>(More)</a></u>';
									$longer = true;
								}
								echo $str;
							?>
						</div>					
						<div id="decrip_<?php echo $row['0']; ?>" class="largedescriptionholder">
							<?php if($longer) {echo $row['4'] . '<u><a onclick="showdescription(' . $row['0'] . ', 0)";>(Less)</a></u>';} ?>
						</div>
						<div class="col-2 elementusername" id="personsname" >
							<?php echo "<a class='elementbutton button' href='reservelist.php?name=" . $row['6'] . "'>" . getnamefromid($row['6'])  . "</a>";?>
						</div>
					</div>
					</a>
				</div>
			</div>
			<hr />			
			<?
		}
		// Free result set
		mysqli_free_result($result);
		closedatabase();
	}
?>