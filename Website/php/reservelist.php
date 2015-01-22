<?php

	include('php/dbconnect.php');

	function outputtable(){
	
		$con = opendatabase();
		$query = "SELECT * FROM Reserve"; 
		$result = mysqli_query($con, $query);
	

		printf("Select returned %d rows.\n", mysqli_num_rows($result));
		
		while ($row=mysqli_fetch_row($result))
		{
			?>
			<hr>
			<div class="row">
				<div class="col-12">
					<div class="row">
						<div class="col-12">
							<!-- GENERATED NAME HERE -->
							<?php echo $row['1']; ?>
						</div>
					</div>
					<div class="row">
						<div class="col-10">
							<!-- GENERATED LOCATION HERE -->
							<?php echo $row['2']; ?>

						</div>
						<div class="col-2">
							<!-- GENERATED TIME + DATE HERE -->
							<?php echo $row['3']; ?>
						</div>
					</div>
					<div class="row">
						<div class="col-10">
							<!-- GENERATED DESCRIPTION (0..100) -->
							<?php
							$longer = false;
							
								$str = "" . $row['4'];
								if (strlen($str) > 100){
									$str = substr($str, 0, 100) . '...';
									$longer = true;
								}
								echo $str;
							?>
						</div>
						<div class="col-2">
							<? if($longer){echo "More";}
							?>
						</div>
					</div>
				</div>
			</div>
			
			
			<?
		}
		// Free result set
		mysqli_free_result($result);
		closedatabase();
	}

?>