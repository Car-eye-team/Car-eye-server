Ext.define('TransactionApp.view.TransactionSearchView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.transactionSearchView',
    title: '约车业务信息搜索',
    region: "north",
    height:120,
    frame: true,
    collapsible: true,
    collapseMode: "mini",
    split: true,
	bodyStyle : 'padding:4px 2px 3px 4px',
	layout : {
		type : 'table',
		align : 'right',
		columns:6
	},
	fieldDefaults: {
    	labelAlign: 'right',
    	labelWidth:60
	},
    items : [
			{
				xtype : 'textfield',
				id : 'tran_orderid',
				fieldLabel : '订单号',
				emptyText:'请输入',
				width:150,
				labelAlign: 'right'
			},{
			    xtype : 'combo',
				maxLength : 20,
				id : 'tran_source',
				name : 'tran_source',
				itemId : 'tran_source',
				store : "SourceStore",
				valueField : 'id',
				displayField : 'source',
				labelWidth : 55,
				width:140,
				editable : false,
				fieldLabel : '业务来源'
			},{
			xtype : 'combo',
			width:140,
			hidden:true,
			name : 'tran_typeid',
			id:'tran_typeid',
			itemId:'tran_typeid',
			store :"TypeListStore",
			editable: false,
			valueField : 'id',
			displayField : 'typename',
			labelWidth : 55,
			blankText : '请选择',
			fieldLabel : '约车类型'
		 }, {
				xtype : 'combo',
				anchor : '100%',
				maxLength : 20,
				id : 'tran_tratype',
				name : 'tran_tratype',
				itemId : 'tran_tratype',
				store : "TraTypeStore",
				valueField : 'id',
				displayField : 'tratype',
				editable : false,
				labelWidth : 55,
				width:200,
				fieldLabel : '业务类型'
			},{
				xtype : 'combo',
				anchor : '100%',
				maxLength : 20,
				id : 'tran_status',
				name : 'tran_status',
				itemId : 'tran_status',
				store : "StatusStore",
				valueField : 'id',
				displayField : 'status',
				editable : false,
				labelWidth : 55,
				width:150,
				fieldLabel : '业务状态'
			},{
			xtype : 'datetimefield',
			width : 200,
			id : 'tran_stime',
			name:'tran_stime',
			fieldLabel : '抢答时间从',
			editable: false,
			labelAlign: 'right',
			emptyText:'请选择',
			typeAhead:false, 
			labelWidth : 70,
            editable:false
		}, {
			xtype : 'datetimefield',
			width : 150,
			maxLength : 20,
			id : 'tran_etime',
			name:'tran_etime',
			fieldLabel : '到',
			editable: false,
			labelAlign: 'right',
			labelWidth : 20,
			emptyText:'请选择',
			typeAhead:false, 
            editable:false
		},{
			xtype : 'textfield',
			fieldLabel : '用车电话',
			labelWidth: 60,
			width:150,
			id:'tran_phone',
			regex : /^[^('"\\%|'"\\_)]+$/,
            regexText : '不可输入特殊字符%或_'
		},{
			xtype : 'combo',
			maxLength : 20,
			id : 'tran_resstatus',
			name : 'tran_resstatus',
			itemId : 'tran_resstatus',
			store : "ResstatusStore",
			valueField : 'id',
			displayField : 'resstatus',
			editable : false,
			width:140,
			labelWidth : 55,
			fieldLabel : '是否抢答'
	     },
	     {
				xtype : 'combo',
				hidden:true,
				maxLength : 20,
				id : 'tran_carpool',
				name : 'tran_carpool',
				itemId : 'tran_carpool',
				store : "CarpoolStore",
				valueField : 'id',
				displayField : 'carpool',
				labelWidth : 55,
				width:140,
				editable : false,
				fieldLabel : '是否合乘'
			},
	      {
			xtype : 'datetimefield',
			width : 200,
			id : 'tran_stime1',
			name:'tran_stime1',
			fieldLabel : '业务开始时间从',
			editable: false,
			labelAlign: 'right',
			emptyText:'请选择',
			typeAhead:false, 
			labelWidth : 90,
            editable:false
		},  {
			xtype : 'datetimefield',
			width : 150,
			maxLength : 20,
			id : 'tran_etime1',
			name:'tran_etime1',
			fieldLabel : '到',
			editable: false,
			labelAlign: 'right',
			labelWidth : 20,
			emptyText:'请选择',
			typeAhead:false, 
            editable:false
		},{
			xtype : 'datetimefield',
			width : 200,
			id : 'tran_stime2',
			name:'tran_stime2',
			fieldLabel : '业务结束时间从',
			editable: false,
			labelAlign: 'right',
			emptyText:'请选择',
			typeAhead:false, 
			labelWidth :90,
            editable:false
		},  {
			xtype : 'datetimefield',
			width : 150,
			maxLength : 20,
			id : 'tran_etime2',
			name:'tran_etime2',
			fieldLabel : '到',
			editable: false,
			labelAlign: 'right',
			labelWidth : 20,
			emptyText:'请选择',
			typeAhead:false, 
            editable:false
		}
							
    ],
    buttons : [{
	    text : '查询',
	    tooltip : '查询约车业务信息',
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

