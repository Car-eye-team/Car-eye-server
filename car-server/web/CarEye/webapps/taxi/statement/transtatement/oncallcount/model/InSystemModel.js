Ext.define("OnCallCountApp.model.InSystemModel", {
			extend : 'Ext.data.Model',
			fields : [{
						name : 'id',
						type : 'int',
						useNull : true
					}, {
						name : 'cid',
						type : 'int',
						useNull : true
					}, {
						name : 'cusname',
						type : 'string'
					}, {
						name : 'agentid',
						type : 'int',
						useNull : true
					}, {
						name : 'callnumber',
						type : 'string'
					}, {
						name : 'inboundcalltime',
						type : 'string'
					}, {
						name : 'hangupcalltime',
						type : 'string'
					}, {
						name : 'userid',
						type : 'int',
						useNull : true
					}, {
						name : 'createtime',
						type : 'string'
					}]
		});
