Ext.define("OnCallCountApp.model.TransactionAnswerModel", {
			extend : 'Ext.data.Model',
			fields : [{
				name : 'id',
				type : 'int',
				useNull : true
				}, {
				name : 'blocid',
				type : 'int',
				useNull : true
			}, {
				name : 'preblocid',
				type : 'int',
				useNull : true
			}, {
				name : 'blocname',
				type : 'string'
			}, {
				name : 'userid',
				type : 'string'
			}, {
				name : 'username',
				type : 'string'
			}, {
				name : 'terminal',
				type : 'string'
			}, {
				name : 'sendtime',
				type : 'string'
			}, {
				name : 'isbidwin',
				type : 'int',
				useNull : true
			}, {
				name : 'password',
				type : 'string'
			}, {
				name : 'carnumber',
				type : 'string'
			}, {
				name : 'province',
				type : 'string'
			}, {
				name : 'city',
				type : 'string'
			}, {
				name : 'district',
				type : 'string'
			}, {
				name : 'provincename',
				type : 'string'
			}, {
				name : 'cityname',
				type : 'string'
			}, {
				name : 'districtname',
				type : 'string'
			}, {
				name : 'color',
				type : 'string'
			}, {
				name : 'cartype',
				type : 'string'
			}, {
				name : 'caruse',
				type : 'string'
			}, {
				name : 'drivername',
				type : 'string'
			}, {
				name : 'onedriverid',
				type : 'string'
			}, {
				name : 'twodriverid',
				type : 'string'
			}, {
				name : 'oiltype',
				type : 'string'
			}, {
				name : 'framenumber',
				type : 'string'
			}, {
				name : 'drivlicnum',
				type : 'string'
			}, {
				name : 'transnumber',
				type : 'string'
			}, {
				name : 'ventingmeasure',
				type : 'string'
			}, {
				name : 'enginenumber',
				type : 'string'
			}, {
				name : 'framenumber',
				type : 'string'
			}, {
				name : 'buytime',
				type : 'string'
			}, {
				name : 'carstatus',
				type : 'string'
			}, {
				name : 'createtime',
				type : 'string'
			}, {
				name : 'type',
				type : 'string'
			}, {
				name : 'typename',
				type : 'string'
			}, {
				name : 'phone',
				type : 'string'
			}, {
				name : 'typeid',
				type : 'int'
			}, {
				name : 'devicetype',
				type : 'int'
			}, {
				name : 'remark',
				type : 'string'
			}, {
				name : 'cartypename',
				type : 'string'
			}, {
				name : 'usename',
				type : 'string'
			}, {
				name : 'drivername1',
				type : 'string'
			}, {
				name : 'drivername2',
				type : 'string'
			}, {
				name : 'provincename',
				type : 'string'
			}, {
				name : 'cityname',
				type : 'string'
			}, {
				name : 'districtname',
				type : 'string'
			}, {
				name : 'orderid',
				type : 'string'
			}, {
				name : 'carnumbertwo',
				type : 'string'
			}],
			idProperty : 'id'// 极为重要的配置。关系到表格修改数据的获取
		});
