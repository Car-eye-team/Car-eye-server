Ext.define('DialFeeSetApp.view.DialFeeSetSetView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.dialFeeSetSetView',
    itemId :'dialFeeSetSetView',
    title: '电召费用设置',
    frame : true,
	region: "north",
	height:100,
	collapsible: true,
	collapseMode: "mini",
	split: true,
	bodyStyle : 'padding:4px 2px 3px 4px',
	layout : {
		type : 'table',
		align : 'right'
	},
	fieldDefaults: {
    	labelAlign: 'right',
    	labelWidth: 80
    },
    items : [{   
                 fieldLabel: '<font color="red">*</font>电召费(元)',
			     xtype: "textfield",
			     name : 'dialfeeSet.dianfee',
				itemId : 'dianfee',
				id : 'dianfee',
			    cls : 'x-textfield',
			    labelAlign:"right",
			    allowBlank : false,
				cls : 'x-textfield',
				maxLength : 10,
				width : 180,
				blankText: '请输入金额',
				vtype: 'numeric'
			},{   
                 fieldLabel: '<font color="red">*</font>燃油费(元)',
			     xtype: "textfield",
			     name : 'dialfeeSet.oilcost',
				itemId : 'oilcost',
				id : 'oilcost',
			    cls : 'x-textfield',
			    labelAlign:"right",
			    allowBlank : false,
				cls : 'x-textfield',
				maxLength : 10,
				width : 180,
				blankText: '请输入金额',
				vtype: 'numeric'
			},{   
                 fieldLabel: '<font color="red">*</font>合乘折扣率(%)',
			     xtype: "textfield",
			     name : 'dialfeeSet.discount',
				itemId : 'discount',
				id : 'discount',
			    cls : 'x-textfield',
			    labelAlign:"right",
			    allowBlank : false,
				cls : 'x-textfield',
				maxLength : 10,
				width : 200,
    	        labelWidth: 120,
				blankText: '请输入合乘折扣率',
				vtype: 'numeric'
			},{
				xtype : 'datetimefield',
				format:"Y-m-d",
				width : 230,
				maxLength : 20,
				id : 'effecttime',
				name:'dialfeeSet.effecttime',
				itemId : 'effecttime',
				fieldLabel : '<font color="red">*</font>生效时间',
				allowBlank : false,
				labelWidth: 80,
				editable: false,
				labelAlign: 'right',
				emptyText:'请选择',
				typeAhead:false
			}],
	buttons : [{
				text : '设置',
				tooltip : '设置',
				id: '120801',
				iconCls : 'edit',
				action: 'set'
			}, {
				text : '重置',
				tooltip : '清空查询条件',
				iconCls : 'common-reset-icon',
				action : 'reset',
	        	handler: function(button){
	        		button.up('form').getForm().reset();
	        	}
			}]

});



Ext.apply(Ext.form.field.VTypes, {
               numeric: function (val, field) {
               	   if("00"==val){
               	     //Ext.Msg.alert('提示', '金额输入不合法！');
               	     return false;
               	   }else{
                     return /^\d+(\.\d{1,2})?$/.test(val);
               	   }
               },
               numericText: '只能输入数字！'
});