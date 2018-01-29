Ext.define("TerminalFunCountApp.model.TerminalFunCountModel", {
	extend : 'Ext.data.Model',
	fields : [ {
				name : 'id',
				type : 'int',
				useNull : true//这样数字如果值为空则不会自动转成0,则提交时注意后台bean类中的属性int要用对象类型，否则解析出错  
			}, {
				name : 'carid',
				type : 'string'
			}, {
				name : 'terminal',
				type : 'string'
			}, {
				name : 'carnumber',
				type : 'string'
			}, {
				name : 'count1',
				type : 'int'
			}, {
				name : 'count2',
				type : 'int'
			}, {
				name : 'count3',
				type : 'int'
			}, {
				name : 'count4',
				type : 'int'
			}, {
				name : 'count5',
				type : 'int'
			}, {
				name : 'count6',
				type : 'int'
			}, {
				name : 'count7',
				type : 'int'
			}, {
				name : 'count8',
				type : 'int'
			}, {
				name : 'count9',
				type : 'int'
			}, {
				name : 'count10',
				type : 'int'
			}, {
				name : 'count11',
				type : 'int'
			}, {
				name : 'count12',
				type : 'int'
			}, {
				name : 'count13',
				type : 'int'
			}, {
				name : 'count14',
				type : 'int'
			}, {
				name : 'count15',
				type : 'int'
			}, {
				name : 'count16',
				type : 'int'
			}, {
				name : 'count17',
				type : 'int'
			}, {
				name : 'count18',
				type : 'int'
			}, {
				name : 'count19',
				type : 'int'
			}, {
				name : 'count20',
				type : 'int'
			}, {
				name : 'blocname',
				type : 'string'
			}, {
				name : 'createtime',
				type : 'string'
			} ],
	idProperty : 'id'// 极为重要的配置。关系到表格修改数据的获取 
});
