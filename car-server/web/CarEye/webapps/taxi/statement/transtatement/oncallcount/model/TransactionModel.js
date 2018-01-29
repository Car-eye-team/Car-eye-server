Ext.define("OnCallCountApp.model.TransactionModel", {
			extend : 'Ext.data.Model',
			fields : [{
						name : 'id',
						type : 'int'
					}, {
						name : 'cid',
						type : 'int'
					}, {
						name : 'source',
						type : 'int'
					}, {
						name : 'typeid',
						type : 'string'
					}, {
						name : 'phone',
						type : 'string'
					}, {
						name : 'usernametwo',
						type : 'string'
					}, {
						name : 'saddress',
						type : 'string'
					}, {
						name : 'slat',
						type : 'string'
					}, {
						name : 'slng',
						type : 'string'
					}, {
						name : 'eaddress',
						type : 'string'
					}, {
						name : 'elat',
						type : 'string'
					}, {
						name : 'elng',
						type : 'string'
					}, {
						name : 'status',
						type : 'string'
					}, {
						name : 'tratype',
						type : 'string'
					}, {
						name : 'carid',
						type : 'int'
					}, {
						name : 'resstatus',
						type : 'string'
					}, {
						name : 'answertime',
						type : 'string'
					}, {
						name : 'starttime',
						type : 'string'
					}, {
						name : 'endtime',
						type : 'string'
					}, {
						name : 'remark',
						type : 'string'
					}, {
						name : 'createtime',
						type : 'string'
					}, {
						name : 'username',
						type : 'string'
					}, {
						name : 'typename',
						type : 'string'
					}, {
						name : 'cname',
						type : 'string'
					}, {
						name : 'carnumber',
						type : 'string'
					}, {
						name : 'orderid',
						type : 'string'
					}, {
						name : 'sex',
						type : 'string'
					}, {
						name : 'callbackphone',
						type : 'string'
					}, {
						name : 'carpool',
						type : 'string'
					}, {
						name : 'carpoolpersonnum',
						type : 'int'
					}, {
						name : 'appointmenttime',
						type : 'string'
					}, {
						name : 'callfee',
						type : 'string'
					},
					{
						name : 'calltype',
						type : 'int'
					}],
			idProperty : 'id'// 极为重要的配置。关系到表格修改数据的获取
		});
