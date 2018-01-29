Ext.define("DialRuleApp.model.DialRuleModel", {
			extend : 'Ext.data.Model',
			fields : [{
				name : 'id',
				type : 'int',
				useNull : true
					// 这样数字如果值为空则不会自动转成0,则提交时注意后台bean类中的属性int要用对象类型，否则解析出错
				}, {
				name : 'type',
				type : 'int',
				useNull : true
			}, {
				name : 'radius',
				type : 'float',
				useNull : true
			}, {
				name : 'cartype',
				type : 'string'
			}, {
				name : 'carcount',
				type : 'int',
				useNull : true
			}, {
				name : 'carstatus',
				type : 'string'
			}, {
				name : 'zkstate',
				type : 'string'
			}, {
				name : 'assigncount',
				type : 'int',
				useNull : true
			}, {
				name : 'assignmin',
				type : 'int',
				useNull : true
			}, {
				name : 'effectmin',
				type : 'int',
				useNull : true
			}, {
				name : 'effecttime',
				type : 'string'
			}, {
				name : 'totalassigncount',
				type : 'string'
			}, {
				name : 'aheadassignmin',
				type : 'int',
				useNull : true
			}, {
				name : 'immassigncount',
				type : 'int',
				useNull : true
			}, {
				name : 'assignwaitmin',
				type : 'int',
				useNull : true
			}, {
				name : 'createtime',
				type : 'string'
			}, {
				name : 'traincount',
				type : 'int',
				useNull : true
			}, {
				name : 'stime',
				type : 'string'
			}, {
				name : 'etime',
				type : 'string'
			}, {
				name : 'principle',
				type : 'string'
			}, {
				name : 'arrearage',
				type : 'int',
				useNull : true
			}, {
				name : 'breach',
				type : 'int',
				useNull : true
			}, {
				name : 'blacklist',
				type : 'int',
				useNull : true
			}],
			idProperty : 'id'// 极为重要的配置。关系到表格修改数据的获取
		});
