Ext.define('TaxiMeterApp.view.TaxiMeterSearchView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.taxiMeterSearchView',
    title: '计价器信息搜索',
    frame : true,
    region: "north",
    height:90,
    collapsible: true,
    collapseMode: "mini",
    split: true,
	bodyStyle : 'padding:4px 2px 3px 4px',
	layout : {
		type : 'table',
		align : 'right',
		columns:5
	},
	fieldDefaults: {
    	labelAlign: 'right',
    	labelWidth:60
	},
    items : [
          				    { xtype: "fieldcontainer",
							     layout: { type: "hbox" },
							     width:250, 
							     items: [  
			                    {
					            xtype : 'textfield',
								fieldLabel : '车牌号',
								width: 140,
								labelWidth: 40,
								id : 'tm_carnumber',
								itemId : 'tm_carnumber',
//								store : 'CarInfoListStore',
								labelAlign: 'right'
//								valueField : 'carnumber',
//								displayField : 'carnumber',
//								emptyText:'请选择车辆'
		                    }, {
						         boxLabel: '最新一条记录',
						         name: 'tm_new', 
						         id: 'tm_new',
						         itemId : 'tm_new',
						         //inputValue: '1', 
						         xtype: 'checkbox',
						         listeners : {
		                         "change" : function(el, checked) {
		                            if (checked) {//只有在点击时触发
		                            	var carnumber=Ext.getCmp('tm_carnumber').getValue();
										if(carnumber==null){
											Ext.Msg.alert('提示', '请选择车牌号!');
											return false;
										}
		                            }
		                         }
                                }
						       }
                             ]  
                             },
						   {
								xtype : 'datetimefield',
								width : 180,
								id : 'tm_stime',
								name:'tm_stime',
								fieldLabel : '上车开始时间',
								editable: false,
								labelAlign: 'right',
								emptyText:'请选择',
								typeAhead:false, 
								labelWidth : 80,
					            editable:false
							},  {
								xtype : 'datetimefield',
								width : 140,
								maxLength : 20,
								id : 'tm_etime',
								name:'tm_etime',
								fieldLabel : '到',
								editable: false,
								labelAlign: 'right',
								labelWidth : 25,
								emptyText:'请选择',
								typeAhead:false, 
					            editable:false
							},{
								xtype : 'datetimefield',
								width : 180,
								id : 'tm_stime1',
								name:'tm_stime1',
								fieldLabel : '下车开始时间',
								editable: false,
								labelAlign: 'right',
								emptyText:'请选择',
								typeAhead:false, 
								labelWidth : 80,
					            editable:false
							},  {
								xtype : 'datetimefield',
								width : 140,
								maxLength : 20,
								id : 'tm_etime1',
								name:'tm_etime1',
								fieldLabel : '到',
								editable: false,
								labelAlign: 'right',
								labelWidth : 25,
								emptyText:'请选择',
								typeAhead:false, 
					            editable:false
							}
    ],
    buttons : [{
	    text : '查询',
	    tooltip : '查询计价器信息',
	    iconCls : 'common-search-icon',
	    action: 'search'
	}, {
	    text : '重置',
	    tooltip : '清空查询条件',
	    iconCls : 'common-reset-icon',
//	    action : 'reset',
        handler: function(button){
        	button.up('form').getForm().reset();
        }
	}]

});

