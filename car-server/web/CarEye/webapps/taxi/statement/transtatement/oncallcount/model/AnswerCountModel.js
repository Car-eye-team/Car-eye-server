Ext.define("OnCallCountApp.model.AnswerCountModel", {
			extend : 'Ext.data.Model',
			fields : [{
						name : 'id',
						type : 'int',
						useNull : true
					}, {
						name : 'blocname',
						type : 'string'
					}, {
						name : 'carnumber',
						type : 'string'
					}, {
						name : 'drivername',
						type : 'string'
					}, {
						name : 'terminal',
						type : 'string'
					}, {
						name : 'zbstatus',
						type : 'int',
						useNull:true
					}, {
						name : 'orderid',
						type : 'string'
					}, {
						name : 'createtime',
						type : 'string'
					}]
		});
