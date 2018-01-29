Ext.define('OrgazicationDeptApp.view.OrgazicationDeptView' ,{
	extend: 'Ext.panel.Panel',
	alias : 'widget.orgazicationDeptView',
	frame: true,
	title: '企业详情',
//	autoWidth: true,
	header : false,
    border:false,
	layout : 'form',
	items : [ {
		xtype : 'form',
//		anchor : '100%',
		height:Ext.getBody().getViewSize().height-40,
	    border:false,
	    tbar:[{
	        text:'添加',
	        id:'110101',
	        tooltip:'添加企业',
	        iconCls:'add',
	        action : 'add'
	    },'-',{
	        text:'保存',
	        id:'110102',
	        tooltip:'保存企业',
	        iconCls:'save',
	        action : 'save'
	    },'-',{
	        text:'删除',
	        id:'110103',
	        tooltip:'删除企业',
	        iconCls:'delete',
	        action : 'delete'
	    },'-',{
	        text:'企业转移',
	        id:'110104',
	        tooltip:'企业转移',
	        iconCls:'deptmove',
	        action : 'deptmove'
	    }],
				"items" : [{
					"xtype" : "fieldset",
					"title" : "<b><font style='color:green;font-size:16px' >企业基础信息</font></b>",
				columnWidth : 1,
				layout : 'form',
				border : true,
//				width:775,
				fieldDefaults: {
			        labelAlign: 'right'
			    },
				items : [ {
					layout : 'column',
					columnWidth : 1,
					border : false,
					items : [ {
						columnWidth : 0.33,
						layout : 'form',
						border : false,
						items : [ {
							xtype : 'hidden',
							id : 'id',
							name :'orgazicationDept.id'
						},{
							xtype : 'hidden',
							id : 'parentid',
							name :'orgazicationDept.parentid'
						},{
							xtype : 'textfield',
							fieldLabel : '<font style="color:red" >*</font>企业名称',
							labelWidth : 60,
							width:200,
							id : 'bloc_name',
							name : 'orgazicationDept.bloc_name',
							allowBlank : false,
							maxLength : 50,
							maxLengthText : '最大长度不超过50位!',
							blankText : '企业名称不能为空'
						},{
							xtype : 'textfield',
							fieldLabel : '责任人',
							labelWidth : 60,
							width:200,
							id : 'res_per',
							name : 'orgazicationDept.res_per',
							maxLength : 20,
							maxLengthText : '最大长度不超过20位!'
						},{
							xtype : 'textfield',
							fieldLabel : '详细地址',
							labelWidth : 60,
							width:200,
							id : 'address',
							name : 'orgazicationDept.address',
							maxLength : 100,
							maxLengthText : '最大长度不超过100位!'
						},{
							xtype : 'textfield',
							fieldLabel : '注册地址',
							labelWidth : 60,
							width:200,
							id : 'reg_address',
							name : 'orgazicationDept.reg_address',
							maxLength : 100,
							maxLengthText : '最大长度不超过100位!'
						},{
							xtype : 'textfield',
							fieldLabel : '公司主页',
							labelWidth : 60,
							width:200,
							id : 'com_homepage',
							name : 'orgazicationDept.com_homepage',
							maxLength : 100,
							maxLengthText : '最大长度不超过100位!'
						},{
							xtype : 'textfield',
							fieldLabel : '传真',
							labelWidth : 60,
							width:200,
							id : 'fax',
							name : 'orgazicationDept.fax',
							maxLength : 20,
							maxLengthText : '最大长度不超过20位!',
							regex :/^[+]{0,1}(\d){1,3}[]?([-]?((\d)|[]){1,12})+$/,
							regexText : '请输入正确的传真号码！'
						},{
							xtype : 'combo',
							fieldLabel : '收款公司',
							labelWidth : 60,
							name : 'orgazicationDept.companyid',
							id : 'companyid',
							store : 'CompanyListStore',
							displayField : 'company',
							valueField : 'id',
							editable : false
						} ]
					},{
						columnWidth : 0.33,
						layout : 'form',
						border : false,
						items : [ {
							xtype : 'textfield',
							fieldLabel : '法人代表',
							labelWidth : 80,
							id : 'leg_per',
							name : 'orgazicationDept.leg_per',
							maxLength : 20,
							maxLengthText : '最大长度不超过20位!'
						},{
							xtype : 'combo',
							fieldLabel : '经济性质',
							labelWidth : 80,
							name : 'orgazicationDept.ec_nature',
							id : 'ec_nature',
							store : 'Ec_natureStore',
							displayField : 'type',
							valueField : 'id',
							editable : false
							
						},{
							xtype : 'textfield',
							fieldLabel : '注册编号',
							labelWidth : 80,
							id : 'reg_number',
							name : 'orgazicationDept.reg_number',
							maxLength : 25,
							maxLengthText : '最大长度不超过25位!'
						},{
							xtype : 'textfield',
							fieldLabel : '注册人数',
							labelWidth : 80,
							id : 'reg_person',
							name : 'orgazicationDept.reg_person',
							maxLength : 10,
							maxLengthText : '最大长度不超过10位!',
							regex : /^\+?[1-9]\d*$/,
							regexText : '请输入一个大于0的整数'
						},{
							xtype : 'textfield',
							fieldLabel : '企业联系电话',
							labelWidth : 80,
							id : 'tel',
							name : 'orgazicationDept.tel',
							regex : /(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/,
							regexText : '格式：3-4位区号，7-8位直播号码，1－4位分机号'
						},{
							xtype : 'datefield',
							fieldLabel : '成立日期',
							labelWidth : 80,
							id : 'est_date',
							name : 'orgazicationDept.est_date',
							format:'Y-m-d',
							editable : false
						},{
							xtype : 'textarea',
							fieldLabel : '备注',
							rows : 3,
							labelWidth : 80,
							width:200,
							id : 'remark',
							name : 'orgazicationDept.remark',
							maxLength : 256,
							maxLengthText : "输入不能大于256位"
						} ]
					},{
						columnWidth : 0.33,
						layout : 'form',
						border : false,
						items : [ {
							xtype : 'textfield',
							fieldLabel : '法人身份证',
							labelWidth : 80,
							id : 'leg_per_card',
							name : 'orgazicationDept.leg_per_card'
						},{
							xtype : 'textfield',
							fieldLabel : '经营方式',
							labelWidth : 80,
							id : 'management',
							name : 'orgazicationDept.management',
							maxLength : 20,
							maxLengthText : '最大长度不超过20位!'
						},{
							xtype : 'textfield',
							fieldLabel : '注册资金',
							labelWidth : 80,
							id : 'reg_capital',
							name : 'orgazicationDept.reg_capital',
							maxLength : 20,
							maxLengthText : '最大长度不超过20位!'
						},{
							xtype : 'datefield',
							fieldLabel : '注册日期',
							labelWidth : 80,
							id : 'reg_date',
							name : 'orgazicationDept.reg_date',
							format:'Y-m-d',
							editable : false
						},{
							xtype : 'textfield',
							fieldLabel : '电子邮箱',
							labelWidth : 80,
							id : 'email',
							name : 'orgazicationDept.email',
							regex : /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/,
							regexText : '请输入正确的邮箱'
						},{
							xtype : 'datefield',
							fieldLabel : '企业登记日期',
							labelWidth : 80,
							id : 'ent_reg_date',
							name : 'orgazicationDept.ent_reg_date',
							format:'Y-m-d',
							editable : false
						} ]
					}]
						
				} ]
			},{
				"xtype" : "fieldset",
				"title" : "<b><font style='color:green;font-size:16px' >企业经营信息</font></b>",
			columnWidth : 1,
			layout : 'form',
			border : true,
			height : 200,
//			width:775,
			items : [ {
				layout : 'column',
				columnWidth : 1,
				border : false,
				items : [ {
					columnWidth : 0.33,
					layout : 'form',
					border : false,
					items : [ {
							xtype : 'textfield',
							fieldLabel : '营业执照号',
							labelWidth : 80,
							id : 'bu_li_number',
							name : 'orgazicationDept.bu_li_number',
							maxLength : 50,
							maxLengthText : '最大长度不超过50位!'
						},{
							xtype : 'textfield',
							fieldLabel : '税务登记号',
							labelWidth : 80,
							id : 'tax_reg_number',
							name : 'orgazicationDept.Tax_reg_number',
							maxLength : 50,
							maxLengthText : '最大长度不超过50位!'
						},{
							xtype : 'datefield',
							fieldLabel : '发证日期',
							labelWidth : 80,
							id : 'issuing_date',
							name : 'orgazicationDept.issuing_date',
							format:'Y-m-d',
							editable : false
						},{
							xtype : 'textfield',
							fieldLabel : '办证人',
							labelWidth : 80,
							id : 'permit_person',
							name : 'orgazicationDept.permit_person',
							maxLength : 20,
							maxLengthText : '最大长度不超过20位!'
						},{
							xtype : 'textfield',
							fieldLabel : '经营范围',
							labelWidth : 80,
							id : 'business_scope',
							name : 'orgazicationDept.business_scope',
							maxLength : 100,
							maxLengthText : '最大长度不超过100位!'
						} ]
				} ]
			} ]
		} ]
			
			
	} ]
});