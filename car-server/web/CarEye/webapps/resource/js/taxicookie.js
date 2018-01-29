//新建cookie方法。   
function setCookie(name, value, hours, path) {
	var name = escape(name);
	var value = escape(value);
	var expires = new Date();
	expires.setTime(expires.getTime() + hours * 3600000);
	path = path == "" ? "" : ";path=" + path;
	_expires = (typeof hours) == "string" ? "" : ";expires="
			+ expires.toUTCString();
	document.cookie = name + "=" + value + _expires + path;
}
//获取cookie值方法 
function getCookieValue(name) {
	var name = escape(name);
	var allcookies = document.cookie;
	//查找名为name的cookie的开始位置   
	name += "=";
	var pos = allcookies.indexOf(name);
	//如果找到了具有该名字的cookie，那么提取并使用它的值   
	if (pos != -1) { //如果pos值为-1则说明搜索"version="失败   
		var start = pos + name.length; //cookie值开始的位置   
		var end = allcookies.indexOf(";", start); //从cookie值开始的位置起搜索第一个";"的位置,即cookie值结尾的位置   
		if (end == -1)
			end = allcookies.length; //如果end值为-1说明cookie列表里只有一个cookie   
		var value = allcookies.substring(start, end); //提取cookie的值   
		return unescape(value); //对它解码         
	} else
		return ""; //搜索失败，返回空字符串   
}

function addCookie() {
	var inUser = Ext.getCmp('loginname').getValue();
	var inPass = Ext.getCmp('password').getValue();
	setCookie('cookTaxiUser', BASE64.encoder(inUser), 24 * 15, '/');
	setCookie('cookTaxiPass', BASE64.encoder(inPass), 24 * 15, '/');
}