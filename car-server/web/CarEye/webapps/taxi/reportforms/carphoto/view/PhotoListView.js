Ext.define('CarPhotoApp.view.PhotoListView', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.photoListView',
	split : true,
	border:false,
	frame : false,
	id:'photoListView',
	height:Ext.getBody().getViewSize().height-100,
	title:'上传图片列表',
	autoScroll:true,
	region : "east",
	dockedItems: [
	              {
	                  xtype: 'toolbar',
	                  layout: {
					        overflowHandler: 'Menu'
					  },   //溢出隐藏
	                  dock: 'top',
	                  items: [{
								text:'Word导出',
				                iconCls:'common-word-icon',
				                action : 'exportWord'
						    }]
    }],
	html:'<div id="photolist"></div>'
});
