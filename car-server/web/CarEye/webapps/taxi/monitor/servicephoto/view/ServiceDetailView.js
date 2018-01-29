Ext.define('PhotoSetApp.view.ServiceDetailView', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.serviceDetailView',
	split : true,
	frame: false,
	width : 350,
	border:false,
	layout : 'form',
	autoScroll:true,
	items : [ {
		xtype : 'form',
		id : 'parameterSet_Form',
		anchor : '100%',
		border : false,
		collapsible : false,
		padding:'10 10 10 50',
		fieldDefaults : {
			labelAlign : 'right',
			labelWidth : 60
		},
		items : [{
			xtype : 'hidden',
			id : 'carid'
		},{
			xtype : 'hidden',
			id : 'starlevel'
		},{
			xtype : 'hidden',
			id : 'picturepath'
		},{
			xtype : 'textfield',
			fieldLabel : '车牌号码',
			id : 'carnumber',
			readOnly:true
		},{
			xtype : 'textfield',
			fieldLabel : '企业名称',
			id : 'blocname',
			readOnly:true
		},{
			xtype : 'textfield',
			fieldLabel : '服务证号',
			id : 'servicenumber',
			readOnly:true
		},{
			xtype : 'textfield',
			fieldLabel : '姓名',
			id : 'drivername',
			readOnly:true
		},{
			xtype : 'textfield',
			fieldLabel : '司机代码',
			id : 'driverid',
			readOnly:true
		},{
			xtype : 'textfield',
			fieldLabel : '星级',
			id : 'starleveltext',
			readOnly:true
		},{
			xtype : 'textfield',
			fieldLabel : '版本',
			id : 'version',
			readOnly:true
		},{
			xtype : 'button',
			margin : '5 0 5 120',
			action : 'save',
			text : '补发'
		}]
	} ]
});
