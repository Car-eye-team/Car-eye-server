Ext.define('CarDriverApp.view.PreJobExamAddView', {
	extend : 'Ext.window.Window',
	alias : 'widget.preJobExamAddView',
	title : '新增岗前考试信息',
    width : 500,
	layout : 'form',
	itemId :'preJobExamAddView',
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
		anchor : '100%',
		collapsible : false,
//	"items": [
//	          {
//	              "xtype": "fieldset",
//	              "title": "<b><font style='color:green'>岗前考试信息 </font></b>",
	              fieldDefaults: {
	              	buttonAlign : 'left',
		            labelAlign : 'right',
	  	        	labelWidth: 80
	  	   		 },
	  		   items : [{
	  			layout : 'column',
	  			columnWidth : 1,
	  			border:false,
	              items : [{
	  						columnWidth : .50,
	  						border:false,
	  						items : [{
	  							xtype : 'hidden',
								id : 'driverid',
								name : 'preJobExam.driverid'
							},{
								xtype : 'textfield',
								fieldLabel : '合格证号',
								itemId : 'qualifiednumber',
								id : 'qualifiednumber',
								name : 'preJobExam.qualifiednumber',
								width :　210,
								anchor : '100%',
								cls : 'x-textfield',
								allowBlank : true
							},{
								xtype : 'datefield',
								fieldLabel : '合格证有效截止日期',
								format:"Y-m-d",
								id : 'closetime',
//								maxValue : new Date(),
								name : 'preJobExam.closetime',
								width :　210,
								anchor : '100%',
								allowBlank : true,
								cls : 'x-textfield'
							},{
								xtype : 'textfield',
								fieldLabel : '培训期数',
								itemId : 'trainexpect',
								id : 'trainexpect',
								name : 'preJobExam.trainexpect',
								width :　210,
								allowBlank : true
							},{
								xtype : 'textfield',
								fieldLabel : '培训编号',
								itemId : 'trainnumber',
								id : 'trainnumber',
								name : 'preJobExam.trainnumber',
								width :　210,
								allowBlank : true
							}]
	  				}, {
	  					columnWidth : .50,
	  					border:false,
	  					items : [{
								xtype : 'textfield',
								fieldLabel : '考试证编号',
								itemId : 'examnumber',
								id : 'examnumber',
								name : 'preJobExam.examnumber',
								width :　210,
								allowBlank : true
							},{
								xtype : 'datefield',
								fieldLabel : '考试证获取日期',
								format:"Y-m-d",
								id : 'gaintime',
								minValue : new Date(),
								name : 'preJobExam.gaintime',
								width :　210,
								anchor : '100%',
								allowBlank : true,
								cls : 'x-textfield'
							},{  
								xtype : 'combo',
								fieldLabel : '结业状态',
								itemId : 'completestatus',
								id : 'completestatus',
								name : 'preJobExam.completestatus',
								width :　210,
								anchor : '100%',
								store : 'CompleteStatusStore',
								editable: false,
								valueField : 'id',
								displayField : 'completestatus',
								cls : 'x-textfield',
								allowBlank : true
							},{  
								xtype : 'combo',
								fieldLabel : '考试状态',
								itemId : 'examstatus',
								id : 'examstatus',
								name : 'preJobExam.examstatus',
								width :　210,
								anchor : '100%',
								store : 'ExamStatusStore',
								editable: false,
								valueField : 'id',
								displayField : 'examstatus',
								cls : 'x-textfield',
								allowBlank : true
							}]
	  					}]
	                }]
	 
	                
	          }
//	      ]}
	      ],
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