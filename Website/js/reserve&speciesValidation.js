function checkValidation() {
	var returnVal = true;

	if(!CheckName()){
		return false;
	}

	

return returnVal;
}


function CheckName() {
	var firstName = document.getElementById('name1').value;
	if (firstName.length < 1){
		document.getElementById("----").innerHTML = "PLEASE ENTER ";
		return false;
	}
	else{
		document.getElementById("----").innerHTML = "";
		return true;
	}
}

function checkEmail() {
	var longitude = document.getElementById('lng');
	var latitude = document.getElementById('lat');
	
	var filter =  new RegExp("^-?([1-8]?[1-9]|[1-9]0)\.{1}\d{1,6}");
	if (!filter.test(longitude.value)) {
		document.getElementById("----").innerHTML = "NOT A VALID LONGITUDE";
		return false;
	 }
	 else{
		document.getElementById("----").innerHTML = "";
		return true;
	}		
	
	if (!filter.test(latitude.value)) {
		document.getElementById("----").innerHTML = "NOT A VALID LATITUDE";
		return false;
	 }
	 else{
		document.getElementById("----").innerHTML = "";
		return true;
	}		
}