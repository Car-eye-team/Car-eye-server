Ext.define("PhotoRecordApp.store.CommandTypeStore", {
			extend : "Ext.data.Store",
			fields : ['id', 'commandtype'],
			data : [{
						"id" : "0",
						"commandtype" : "停止拍摄"
					}, {
						"id" : "65535",
						"commandtype" : "录像"
					},{
						'id':'1',
						'commandtype':'拍照'
					}
					
			]
		});