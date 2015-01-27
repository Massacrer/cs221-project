function checkValidation() {
	var returnVal = true;

	if(!CheckName()){
		return false;
	}
	
	if(!checkTime()){
		return false;
	}
	
	if(!checkLat()){
		return false;
	}
	
	if(!checkLng()){
		return false;
	}
	
return returnVal;
}
	
function CheckName() {
	var firstName = document.getElementById('name').value;
	if (firstName.length < 1){
		document.getElementById("message_after_submit").innerHTML = "PLEASE ENTER THE NAME ";
		return false;
	}
	else{
		document.getElementById("message_after_submit").innerHTML = "";
		return true;
	}
}

function checkTime() {
	var time = document.getElementById('time').value;
	
	var filter = /^(\d{4})-(\d{2})-(\d{2}) (\d{2}):(\d{2}):(\d{2})$/;		
	if (!filter.test(time)) {
		document.getElementById("message_after_submit").innerHTML = "NOT A VALID DATE/TIME";
		return false;
	 }
	 else{
		document.getElementById("message_after_submit").innerHTML = "";
		return true;
	}		
}

function checkLat() {
	var latitude = document.getElementById('lat').value;
	
	var filter = /^-?([1-8]?[1-9]|[1-9]0)\.{1}\d{1,6}/;		
	if (!filter.test(latitude)) {
		document.getElementById("message_after_submit").innerHTML = "NOT A VALID LATITUDE";
		return false;
	 }
	 else{
		document.getElementById("message_after_submit").innerHTML = "";
		return true;
	}		
}

function checkLng() {
	var longitude = document.getElementById('lng').value;
	
	var filter = /^-?([1-8]?[1-9]|[1-9]0)\.{1}\d{1,6}/;
	if (!filter.test(longitude)) {
		document.getElementById("message_after_submit").innerHTML = "NOT A VALID LONGITUDE";
		return false;
	 }
	 else{
		document.getElementById("message_after_submit").innerHTML = "";
		return true;
	}			
}