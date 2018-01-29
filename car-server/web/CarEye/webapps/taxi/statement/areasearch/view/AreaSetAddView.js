Ext.define('AreaSetApp.view.AreaSetAddView', {
	extend : 'Ext.window.Window',
	alias : 'widget.areaSetAddView',
	title : '新增多区域信息',
    width : 400,
	layout : 'form',
	itemId :'areaSetAddWindow',
	id:'areaSetAddView',
	animCollapse:false,
	constrain : true,
	constrainHeader : true,
	maximizable : false,
	minimizable : false,
	closable : true,
	modal : true,
	border : false,
	closeAction : 'destroy',
	items :[ {
		xtype : 'form',
		frame : true,
		collapsible : false,
		fieldDefaults: {
	        labelAlign: 'right',
	        labelWidth: 80
	    },
		buttonAlign : 'right',
		layout : 'form',
		items : [
					         {
								xtype : 'hidden',
								id:'ylng',
								name:'areaSet.ylng'
					         },{
								xtype : 'hidden',
								id:'ylat',
								name:'areaSet.ylat'
					         },{
								xtype : 'hidden',
								id:'radius',
								name:'areaSet.radius'
					         },{
								xtype : 'hidden',
								id:'latsrt',
								name:'areaSet.latsrt'
					         },{
								xtype : 'hidden',
								id:'lngsrt',
								name:'areaSet.lngsrt'
					         },{
								xtype : 'hidden',
								id:'latlt',
								name:'areaSet.latlt'
					         },{
								xtype : 'hidden',
								id:'lnglt',
								name:'areaSet.lnglt'
					         },{
								xtype : 'hidden',
								id:'latrb',
								name:'areaSet.latrb'
					         },{
								xtype : 'hidden',
								id:'lngrb',
								name:'areaSet.lngrb'
					         },{
								xtype : 'combo',
								fieldLabel : '<font color="red">*</font>信息类型',
								name : 'areaSet.areatype',
								itemId : 'areatype',
								id : 'areatype',
								allowBlank : false,
								blankText : '请选择',
								editable:false,
								store : 'ImformationTypeStore',
								displayField : 'name',
								valueField : 'type'
					         },
					         {
								xtype : 'textfield',
								fieldLabel : '<font color="red">*</font>区域名称',
								name : 'areaSet.areaname',
								itemId : 'areaname',
								id : 'areaname',
								tabIndex : 1,
								maxLength : 20,
								maxLengthText : '最大长度不超过20位!',
								allowBlank : false,
								blankText : '不能为空',
								enableKeyEvents : true,
								itemId : 'areaname',
								anchor : '100%',
								cls : 'x-textfield',
								regex : /^[\u4E00-\u9FA5A-Za-z0-9]+$/,
								regexText : '区域名称只能为数字、英文和中文',
								validator : vd
					         }
//					         ,
//					         {
//								xtype : 'combo',
//								fieldLabel : '有效期',
//								name : 'areaSet.termvalidity',
//								itemId : 'termvalidity',
//								id : 'termvalidity',
//								allowBlank : true,
//								blankText : '请选择',
//								store : 'DateTimeTypeStore',
//								displayField : 'name',
//								valueField : 'type'
//					         },
//					         {
//								xtype : 'datetimefield',
//								maxLength : 20,
//								id : 'stime',
//								format :'Y-m-d',
//								allowBlank : false,
//								blankText : '请选择',
//								name : 'areaSet.stime',
//								fieldLabel : '<font color="red">*</font>开始时间'
//							},  {
//								xtype : 'datetimefield',
//								maxLength : 20,
//								id : 'etime',
//								format :'Y-m-d',
//								allowBlank : false,
//								blankText : '请选择',
//								name : 'areaSet.etime',
//								fieldLabel : '<font color="red">*</font>结束时间'
//							},
//							 {
//								xtype : 'textfield',
//								fieldLabel : '<font color="red">*</font>最高速度(km/h)',
//								name : 'areaSet.maxspeed',
//								itemId : 'maxspeed',
//								id : 'maxspeed',
//								labelWidth: 100,
//								allowBlank : false,
//								blankText : '请选择',
//								vtype: 'numeric'
//					         }, 
//					         {
//								xtype : 'textfield',
//								fieldLabel : '<font color="red">*</font>超速持续时间',
//								name : 'areaSet.speedtime',
//								itemId : 'speedtime',
//								id : 'speedtime',
//								labelWidth: 100,
//								allowBlank : false,
//								blankText : '请选择',
//								vtype: 'numeric'
//					         },
//					         {//多选按钮组
//
//						            xtype: 'checkboxgroup',
//					        	    fieldLabel: '报警设置', 
//					        	    columns: 2,  
//						            autoHeight: true,
//						            defaultType: 'checkbox', //可以设置默认，也可以分别设置xtype属性
//						            hideLabels: true,
//						            items: [
//						                {
//						                   boxLabel: '根据时间',
//						                   name: 'areaSet.attr0', 
//						                   id: 'attr0',
//						                   inputValue: '1', 
//						                   xtype: 'checkbox'
//						                   },
//						                { 
//							                boxLabel: '限速', 
//							                name: 'areaSet.attr1', 
//							                id: 'attr1', 
//							                inputValue: '1', 
//							                xtype: 'checkbox' 
//						                },
//						                { 
//							                boxLabel: '进区域报警给驾驶员', 
//							                name: 'areaSet.attr2', 
//							                id: 'attr2', 
//							                inputValue: '1', 
//							                xtype: 'checkbox' 
//						                },
//						                { 
//							                boxLabel: '进区域报警给平台', 
//							                name: 'areaSet.attr3', 
//							                id: 'attr3', 
//							                inputValue: '1', 
//							                xtype: 'checkbox' 
//						                },
//						                { 
//							                boxLabel: '出区域报警给驾驶员', 
//							                name: 'areaSet.attr4', 
//							                id: 'attr4', 
//							                inputValue: '1', 
//							                xtype: 'checkbox' 
//						                },
//						                { 
//							                boxLabel: '出区域报警给平台', 
//							                name: 'areaSet.attr5', 
//							                id: 'attr5', 
//							                inputValue: '1', 
//							                xtype: 'checkbox' 
//						                }
//
//					            ]
//					
//					         }
					 ]
}],
	buttons: [{
		text: '保存',
		action: 'save'
	},{
		text: '取消',
		handler: function(btn){
			btn.up('window').close();
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