

function validateBlankField(fields, field_title){
	
	var error_array = new Array();
	for(var i = 0 ; i < fields.length ; i++){
		if($("#" + fields[i]).val() == ""){
			error_array.push(field_title[i]);
		}
	}
	
	return error_array;
}