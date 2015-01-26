function showdescription(id, makebig){
	var smalldesc = document.getElementById( 'smalldecrip_' + id);
	var desc = document.getElementById( 'decrip_' + id);
	
	if(makebig == 1){
		var temp = smalldesc.innerHTML;
		smalldesc.innerHTML = desc.innerHTML;
		desc.innerHTML = temp;
	}
	else{
		var temp = desc.innerHTML;
		desc.innerHTML = smalldesc.innerHTML;
		smalldesc.innerHTML = temp;
	}
}
