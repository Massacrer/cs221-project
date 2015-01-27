<?php
	include_once('php/dbconnect.php');
	function outputtable(){
			
		$con = opendatabase();
		
		$where = "";
		
		if(isset($_GET['user'])  && strlen($_GET['user']) > 0){  
			header('Location: reservelist.php?name=' . getidfromname(mysqli_real_escape_string($con, $_GET['user'])));  
		}		
		if(isset($_GET['place']) && strlen($_GET['place']) > 1){  
			$where = $where . " && reserveLocationLat='" . mysqli_real_escape_string($con, $_GET['place']) . "'";  
		}
		if(isset($_GET['frommonth']) && strlen($_GET['frommonth']) > 1 && isset($_GET['fromday'] ) && strlen($_GET['fromday']) > 1 && isset($_GET['fromyear']) && strlen($_GET['fromyear']) > 1){
			//yyyy-mm-dd hh:mm:ss
			$where = $where . " && reserveDatetimeCreation LIKE '" . mysqli_real_escape_string($con, $_GET['fromyear']) . "-" . mysqli_real_escape_string($con, $_GET['frommonth']) . "-" . mysqli_real_escape_string($con, $_GET['fromday']) . "%'"; 
		}	
		if(isset($_GET['id']) && strlen($_GET['id']) > 0){
			$where =" && reserveId = '" . mysqli_real_escape_string($con, $_GET['id']) . "'"; 
		}
		
		if(isset($_GET['name'])){
			$name = mysqli_real_escape_string($con, $_GET['name']);
			$where = $where . " && reserveUserid = '" . $name . "'";		
		}	
		
		$query = "SELECT * FROM Reserve WHERE reservehidden = '0'" . $where . " ORDER BY reserveDatetimeCreation DESC"; 
		
		if(!$result = mysqli_query($con, $query)){
			echo "Error: " . mysqli_error($con);
		}
			
		while ($row=mysqli_fetch_row($result))
		{
			?>
			
			<div class="row">
				<div class="col-12 element">
					<div class="row">
						<div class="col-12 elementheadingrow">
							<a class='elementheading' href='specieslist.php?id=<?php echo $row["0"];?>'><?php echo $row[1]; ?></a>
						</div>
					</div>
					<div class="row">
						<div class="col-10 padding">
							<strong>OS grid ref: </strong><?php echo $row['2']; ?>
						</div>
						<div class="col-2 padding">
							<?php echo $row['3']; ?>
						</div>
					</div>
					<div class="row">
						<div class="col-10 padding">
							<?php
								$str = "" . $row['4'];
								if (strlen($str) > 90){
									$str = substr($str, 0, 90) . '...';
								}
								if(!isset($_GET['id'])){ echo $str; }
							?>
						</div>	
						<div class="col-2 elementusername" id="personsname" >
							<a class='elementbutton button' href='reservelist.php?name=<?php echo $row['6'];?>'><?php echo getnamefromid($row["6"]); ?></a>
						</div>
					</div>
				</div>
			</div>
				
				<div id="decrip_<?php echo $row['0']; ?>" class="largedescriptionholder">
						<?php 
							echo $row['4'];
						?>
				</div>	<hr />		
			<?
		}
		// Free result set
		mysqli_free_result($result);
		closedatabase();
	}
?>
