<?php

	include('php/dbconnect.php');

	function outputtable(){
	
		$con = opendatabase();
		$query = "SELECT * FROM Species WHERE SpeciesHidden = '0'"; 
		
		
		if(isset($_GET['id'])){
			$name = mysqli_real_escape_string($con, $_GET['id']);
			$query = $query . " && ReserveId = '" . $name . "'";		
		}
		
		$result = mysqli_query($con, $query);
	

		printf("Select returned %d rows.\n", mysqli_num_rows($result));
		
		while ($row=mysqli_fetch_row($result))
		{?>
			<div class="row">
				<div class="col-12">
					<div class="row">
						<div class="col-2">
							<!-- PUT PICTURE HERE -->
							<?php echo $row['9']; ?>
						</div>
						<div class="col-10">
							<div class="row">
								<div class="col-12">
									<!-- GENERATED NAME HERE -->
									<?php echo $row['1']; ?>
								</div>
							</div>
							<div class="row">
								<div class="col-4">
									<!-- GENERATED DAFOR HERE -->
									DAFOR: <?php echo $row['2']; ?>
								</div>
								<div class="col-4">
									<!-- GENERATED LOCATION HERE -->
									Location: <?php echo $row['4']; ?>
								</div>
								<div class="col-4">
									<!-- GENERATED TIME/DATE HERE -->
									Time/Date: <?php echo $row['5']; ?>
								</div>
							</div>
							<div class="row">
								<div class="col-12">
									<!-- GENERATED DESCRIPTION HERE -->
									<?php echo $row['3']; ?>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<?php
		}
		// Free result set
		mysqli_free_result($result);
		closedatabase();
	}

?>