
//判断是否大于当前时间，大于返回true
function gtnow(value){
	if(value == null || value.length == 0){
		return false;
	}
	
    var timeArr = value.substring(0,10).split('-');

    var timearray = value.substring(10,19).split(':');
    var datetime = new Date(timeArr[0], timeArr[1]-1, timeArr[2],timearray[0],timearray[1],timearray[2]);
    var now = new Date();
    
    if(datetime - now > 0){
		return true;
	}
	
	return "不能小于当前时间";
}

//判断是否小于于当前时间，小于返回true
function ltnow(value){
	
	if(value == null || value.length == 0){
		return false;
	}
	
    var timeArr = value.substring(0,10).split('-');

    var timearray = value.substring(10,19).split(':');
    var datetime = new Date(timeArr[0], timeArr[1]-1, timeArr[2],timearray[0],timearray[1],timearray[2]);
    var now = new Date();
	if(datetime <= now){
		return true;
	}
	return "不能大于当前时间";
}