var species_num = 1;

function addplant(num){

	document.getElementById(num).innerHTML = "form for the plants<div id='temp'>temp</div>";
	document.getElementById(num).className = "plant";
	document.getElementById(num).id = 'plant1';
	document.getElementById("temp").id = num;

}

function addspecies(){
	species_num++;

	document.getElementById("speciesadder").innerHTML = "form for the species<div class=plant>plant1</div> <div id='1'> </div><div id='plantadder'><a onclick='addplant(1)' href='#' > Add A Plant </a></div>";

	document.getElementById("speciesadder").className = "species";

		
	document.getElementById("speciesadder").id = "species_" + species_num;



	var newHTML         = document.createElement ('div');
	newHTML.innerHTML   = "   <hr>          \
 	   <div id='speciesadder'>		\
		<a onclick='addspecies()' href='#'> Add A Species </a>	\
		</div> \
	";

	document.body.appendChild (newHTML);
}
