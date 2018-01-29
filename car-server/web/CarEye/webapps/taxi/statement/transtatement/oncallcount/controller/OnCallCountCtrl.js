Ext.define('OnCallCountApp.controller.OnCallCountCtrl', {
	extend : 'Ext.app.Controller',
	stores : ['OnCallCountListStore', 'CarDriverCancelListStore',
			'CarDriverBreakListStore', 'CusBreachListStore',
			'CusCancelListStore', 'SendSuccessCountListStore',
			'AnswerCountListStore', 'TransactionListStore',
			'InSystemListStore', 'InBoundListStore'],// 声明该控制层要用到的store
	models : ['OnCallCountModel', 'CarDriverCancelModel', 'CusBreachModel',
			'SendSuccessCountModel', 'AnswerCountModel', 'TransactionModel',
			'InSystemModel', 'InBoundModel'],// 声明该控制层要用到的model
	views : ['OnCallCountListView', 'OnCallCountSearchView',
			'CarDriverCancelListView', 'CarDriverBreakListView',
			'CusBreachListView', 'CusCancelListView',
			'SendSuccessCountListView', 'AnswerCountListView',
			'TransactionListView', 'InSystemListView', 'InBoundListView'],// 声明该控制层要用到的view
	refs : [// 相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
	{
				ref : 'onCallCountListView',
				selector : 'onCallCountListView'
			}, {
				ref : 'onCallCountSearchView',
				selector : 'onCallCountSearchView'
			}],
	init : function() {

		this.control({});
	},
	excelExport : function(button) {
		var stime = Ext.util.Format.date(Ext.getCmp('cd_stime').getValue(),
				'Y-m-d H:i:s');
		var etime = Ext.util.Format.date(Ext.getCmp('cd_etime').getValue(),
				'Y-m-d H:i:s');
		if(validTime(stime,etime)==-1){
				Ext.Msg.alert("提示","结束日期不能小于开始日期!");
				return;
		}
		var blocid = Ext.getCmp('cm_blocid').getValue();
		var carnumber = encodeURI(Ext.getCmp('cd_carnumber').getValue());
		var drivername = encodeURI(Ext.getCmp('cd_drivername').getValue());
		var cusname = encodeURI(Ext.getCmp('cd_cusname').getValue());
		location.href = window.BIZCTX_PATH
				+ '/statement/oncalltransaction/oncallcountjson/exportOnCallCountData.action?stime='
				+ stime + '&etime=' + etime + '&blocid=' + blocid
				+ '&carnumber=' + carnumber + '&drivername=' + drivername
				+ '&cusname=' + cusname;
	},

	search : function(button) {
		var stime = Ext.util.Format.date(Ext.getCmp('cd_stime').getValue(),
				'Y-m-d H:i:s');
		var etime = Ext.util.Format.date(Ext.getCmp('cd_etime').getValue(),
				'Y-m-d H:i:s');
		if(validTime(stime,etime)==-1){
				Ext.Msg.alert("提示","结束日期不能小于开始日期!");
				return;
		}
		var blocid = Ext.getCmp('cm_blocid').getValue();
		var carnumber = encodeURI(Ext.getCmp('cd_carnumber').getValue());
		var drivername = encodeURI(Ext.getCmp('cd_drivername').getValue());
		var cusname = encodeURI(Ext.getCmp('cd_cusname').getValue());
		var form = button.up('form');
		if (!form.getForm().isValid()) {
			return;
		}
		var store = this.getOnCallCountListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function(store, options) {
					var new_params = {
						stime : encodeURI(Ext.util.Format.date(Ext
										.getCmp('cd_stime').getValue(),
								'Y-m-d H:i:s')),
						etime : encodeURI(Ext.util.Format.date(Ext
										.getCmp('cd_etime').getValue(),
								'Y-m-d H:i:s')),
						blocid : blocid,
						carnumber : carnumber,
						drivername : drivername,
						cusname : cusname
					};
					Ext.apply(store.proxy.extraParams, new_params);
				});
		store.loadPage(1);
		return false;
	},
	exportInBoundList : function(button) {
		var stime = Ext.util.Format.date(Ext.getCmp('cd_stime').getValue(),
				'Y-m-d H:i:s');
		var etime = Ext.util.Format.date(Ext.getCmp('cd_etime').getValue(),
				'Y-m-d H:i:s');
		if(validTime(stime,etime)==-1){
				Ext.Msg.alert("提示","结束日期不能小于开始日期!");
				return;
		}
		var blocid = Ext.getCmp('cm_blocid').getValue();
		var carnumber = encodeURI(Ext.getCmp('cd_carnumber').getValue());
		var drivername = encodeURI(Ext.getCmp('cd_drivername').getValue());
		var cusname = encodeURI(Ext.getCmp('cd_cusname').getValue());
		location.href = window.BIZCTX_PATH
				+ '/statement/oncalltransaction/oncallcountjson/exportInBoundList.action?'
				+ '&blocid=' + blocid + '&carnumber=' + carnumber
				+ '&drivername=' + drivername + '&cusname=' + cusname
				+ '&stime=' + stime + '&etime=' + etime;
	},
	exportInSystemList : function(button) {
		var stime = Ext.util.Format.date(Ext.getCmp('cd_stime').getValue(),
				'Y-m-d H:i:s');
		var etime = Ext.util.Format.date(Ext.getCmp('cd_etime').getValue(),
				'Y-m-d H:i:s');
		if(validTime(stime,etime)==-1){
				Ext.Msg.alert("提示","结束日期不能小于开始日期!");
				return;
		}
		var blocid = Ext.getCmp('cm_blocid').getValue();
		var carnumber = encodeURI(Ext.getCmp('cd_carnumber').getValue());
		var drivername = encodeURI(Ext.getCmp('cd_drivername').getValue());
		var cusname = encodeURI(Ext.getCmp('cd_cusname').getValue());
		location.href = window.BIZCTX_PATH
				+ '/statement/oncalltransaction/oncallcountjson/exportInSystemList.action?'
				+ '&blocid=' + blocid + '&carnumber=' + carnumber
				+ '&drivername=' + drivername + '&cusname=' + cusname
				+ '&stime=' + stime + '&etime=' + etime;
	},
	exportTransactionList : function(button) {
		var stime = Ext.util.Format.date(Ext.getCmp('cd_stime').getValue(),
				'Y-m-d H:i:s');
		var etime = Ext.util.Format.date(Ext.getCmp('cd_etime').getValue(),
				'Y-m-d H:i:s');
		if(validTime(stime,etime)==-1){
				Ext.Msg.alert("提示","结束日期不能小于开始日期!");
				return;
		}
		var blocid = Ext.getCmp('cm_blocid').getValue();
		var carnumber = encodeURI(Ext.getCmp('cd_carnumber').getValue());
		var drivername = encodeURI(Ext.getCmp('cd_drivername').getValue());
		var cusname = encodeURI(Ext.getCmp('cd_cusname').getValue());
		location.href = window.BIZCTX_PATH
				+ '/statement/oncalltransaction/oncallcountjson/exportTransactionList.action?'
				+ '&blocid=' + blocid + '&carnumber=' + carnumber
				+ '&drivername=' + drivername + '&cusname=' + cusname
				+ '&stime=' + stime + '&etime=' + etime;
	},
	exportInfoDriverCancel : function(button) {
		var blocid = Ext.getCmp('cm_blocid').getValue();
		var carnumber = encodeURI(Ext.getCmp('cd_carnumber').getValue());
		var drivername = encodeURI(Ext.getCmp('cd_drivername').getValue());
		var wy;
		var stime = Ext.util.Format.date(Ext.getCmp('cd_stime').getValue(),
				'Y-m-d H:i:s');
		var etime = Ext.util.Format.date(Ext.getCmp('cd_etime').getValue(),
				'Y-m-d H:i:s');
		if(validTime(stime,etime)==-1){
				Ext.Msg.alert("提示","结束日期不能小于开始日期!");
				return;
		}
		location.href = window.BIZCTX_PATH
				+ '/statement/transtatementjson/excelCarDriverCancel.action?'
				+ '&blocid=' + blocid + '&carnumber=' + carnumber
				+ '&drivername=' + drivername + '&wy=' + wy + '&stime=' + stime
				+ '&etime=' + etime;
	},
	exportInfoDriverBreak : function(button) {
		var blocid = Ext.getCmp('cm_blocid').getValue();
		var carnumber = encodeURI(Ext.getCmp('cd_carnumber').getValue());
		var drivername = encodeURI(Ext.getCmp('cd_drivername').getValue());
		var wy = 0;
		var stime = Ext.util.Format.date(Ext.getCmp('cd_stime').getValue(),
				'Y-m-d H:i:s');
		var etime = Ext.util.Format.date(Ext.getCmp('cd_etime').getValue(),
				'Y-m-d H:i:s');
		if(validTime(stime,etime)==-1){
				Ext.Msg.alert("提示","结束日期不能小于开始日期!");
				return;
		}
		location.href = window.BIZCTX_PATH
				+ '/statement/transtatementjson/excelCarDriverCancel.action?'
				+ '&blocid=' + blocid + '&carnumber=' + carnumber
				+ '&drivername=' + drivername + '&wy=' + wy + '&stime=' + stime
				+ '&etime=' + etime;
	},
	exportInfoCusBreak : function(button) {
		var username = encodeURI(Ext.getCmp('cd_cusname').getValue());
		var phone;
		var stime = Ext.util.Format.date(Ext.getCmp('cd_stime').getValue(),
				'Y-m-d H:i:s');
		var etime = Ext.util.Format.date(Ext.getCmp('cd_etime').getValue(),
				'Y-m-d H:i:s');
		if(validTime(stime,etime)==-1){
				Ext.Msg.alert("提示","结束日期不能小于开始日期!");
				return;
		}
		location.href = window.BIZCTX_PATH
				+ '/statement/transtatementjson/excelCusBreachCancel.action?'
				+ 'username=' + username + '&phone=' + phone + '&stime='
				+ stime + '&etime=' + etime;
	},
	exportInfoCusCancel : function(button) {
		var username = encodeURI(Ext.getCmp('cd_cusname').getValue());
		var phone;
		var wy = 2; // 取消
		var stime = Ext.util.Format.date(Ext.getCmp('cd_stime').getValue(),
				'Y-m-d H:i:s');
		var etime = Ext.util.Format.date(Ext.getCmp('cd_etime').getValue(),
				'Y-m-d H:i:s');
		if(validTime(stime,etime)==-1){
				Ext.Msg.alert("提示","结束日期不能小于开始日期!");
				return;
		}
		location.href = window.BIZCTX_PATH
				+ '/statement/transtatementjson/excelCusCancel.action?'
				+ 'username=' + username + '&phone=' + phone + '&wy=' + wy
		'&stime=' + stime + '&etime=' + etime;
	},
	exportInfoSendSuccessCount : function(button) {
		var stime = Ext.util.Format.date(Ext.getCmp('cd_stime').getValue(),
				'Y-m-d H:i:s');
		var etime = Ext.util.Format.date(Ext.getCmp('cd_etime').getValue(),
				'Y-m-d H:i:s');
		if(validTime(stime,etime)==-1){
				Ext.Msg.alert("提示","结束日期不能小于开始日期!");
				return;
		}
		var blocid = Ext.getCmp('cm_blocid').getValue();
		var carnumber = encodeURI(Ext.getCmp('cd_carnumber').getValue());
		var drivername = encodeURI(Ext.getCmp('cd_drivername').getValue());
		location.href = window.BIZCTX_PATH
				+ '/statement/oncalltransaction/answercountjson/excelExportSendSuccessList.action?stime='
				+ stime + '&etime=' + etime + '&blocid=' + blocid
				+ '&carnumber=' + carnumber + '&drivername=' + drivername;
	},
	exportInfoAnswerCount : function(button) {
		var stime = Ext.util.Format.date(Ext.getCmp('cd_stime').getValue(),
				'Y-m-d H:i:s');
		var etime = Ext.util.Format.date(Ext.getCmp('cd_etime').getValue(),
				'Y-m-d H:i:s');
		if(validTime(stime,etime)==-1){
				Ext.Msg.alert("提示","结束日期不能小于开始日期!");
				return;
		}
		var blocid = Ext.getCmp('cm_blocid').getValue();
		var carnumber = encodeURI(Ext.getCmp('cd_carnumber').getValue());
		var drivername = encodeURI(Ext.getCmp('cd_drivername').getValue());
		var zbstatus;
		location.href = window.BIZCTX_PATH
				+ '/statement/oncalltransaction/answercountjson/excelExportAnswerCountList.action?stime='
				+ stime + '&etime=' + etime + '&blocid=' + blocid
				+ '&carnumber=' + carnumber + '&drivername=' + drivername
				+ '&zbstatus=' + zbstatus;

	}

});

 function validTime(startTime,endTime){
       var arr1 = startTime.split("-");
       var arr2 = endTime.split("-");
       var date1=new Date(parseInt(arr1[0]),parseInt(arr1[1])-1,parseInt(arr1[2]),0,0,0);
       var date2=new Date(parseInt(arr2[0]),parseInt(arr2[1])-1,parseInt(arr2[2]),0,0,0);
       if(date1.getTime()>date2.getTime()) {                               
               return -1;
         }else{
             return 1;
         }
         return -1;
}
