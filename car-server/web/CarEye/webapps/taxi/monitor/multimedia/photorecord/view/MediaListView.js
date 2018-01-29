Ext.define('PhotoRecordApp.view.MediaListView' ,{
	extend : 'Ext.window.Window',
	alias : 'widget.mediaListView',
	title : '图片列表',
    width : 300,
    height : 350,
	layout : 'form',
	animCollapse:false,
//	autoScroll:true,
	constrain : true,
	constrainHeader : true,
	maximizable : false,
	minimizable : false,
	closable : true,
	modal : true,
	border : false,
	closeAction : 'destroy',
	items : [ {
		    xtype: 'grid',
			autoScroll:true,
			height:320,
			frame: true,
			store: "MediaListStore",
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			columns: [
				{ header: '照片', flex:1, dataIndex: 'multimediapath',
					renderer: function(value){ 
						return "<a target='_blank' href='"+value+"'><img src=" + value + " height='60' width='80'></img></a>";  
			    	}
		       }
			]
	} ]
});

	
	
	