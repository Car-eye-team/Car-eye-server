Ext.define('PhotoSetApp.view.PhotoView', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.photoView',
	split : true,
	frame: false,
	border:false,
	padding:'10 10 10 80',
	height : 200,
	width : 350,
	region : "east",
	html:'<div><img id = "service_photo" alt="没有找到服务证图片" style="width:60%;" ' +
			'src="'+window.BIZCTX_PATH+'/taxi/monitor/servicephoto/sjfwz.png"></img></div>'
});
