Ext.Loader.setConfig({enabled:true});//开启动态加载

Ext.application({
    name: 'OrgazicationDeptApp',//为应用程序起一个名字,相当于命名空间
    //应用的根目录
	appFolder : window.BIZCTX_PATH + '/taxi/system/orgazication',
    controllers: [//声明所用到的控制层
        'OrgazicationDeptCtrl'
    ],
    launch: function() {
		   Ext.create('Ext.container.Viewport', {
		             itemId:'OrgazicationDeptCtrlPanel',
		             border:false,
		             layout : 'border',
					 collapsible: true,
					 header : false, // 显示 header 默认 true
				     renderTo:Ext.getBody(),
					 items: [{
	                    region: 'west',
	                    frame: true,
	                    //collapsible: true,
					    collapseMode: "mini",
					    width:300,
					    split: true,
	                    title: '企业树',
	                    xtype: 'treepanel',
	                    id:'systemManage-depttree',
	                    height:Ext.getBody().getViewSize().height-10,
	                   // width:1*(Ext.getBody().getViewSize().width)/5,
	                    store: 'DeptTreeStore',
	                    rootVisible: false,
	                    listeners: {
					        itemclick: {
					            fn: function( view, record, item, index, e, eOpts ){
					            	Ext.getCmp('parentid').setValue(record.data.id);
					            	Ext.getCmp('id').setValue(record.data.id);
					            	Ext.Ajax.request( {
										url : window.BIZCTX_PATH + '/orgazicationdeptjson/quertDeptById.action',
										method : 'POST',  
										params : "id="+ record.data.id,
										success : function(response) {
											var text = response.responseText;
											var result =  Ext.JSON.decode(text);
											Ext.getCmp('id').setValue(result.result.id);
											Ext.getCmp('bloc_name').setValue(result.result.bloc_name);
											Ext.getCmp('res_per').setValue(result.result.res_per);
											Ext.getCmp('address').setValue(result.result.address);
											Ext.getCmp('reg_address').setValue(result.result.reg_address);
											Ext.getCmp('com_homepage').setValue(result.result.com_homepage);
											Ext.getCmp('fax').setValue(result.result.fax);
											Ext.getCmp('remark').setValue(result.result.remark);
											
											Ext.getCmp('leg_per').setValue(result.result.leg_per);
											Ext.getCmp('ec_nature').setValue(result.result.ec_nature);
											Ext.getCmp('reg_number').setValue(result.result.reg_number);
											Ext.getCmp('reg_person').setValue(result.result.reg_person);
											Ext.getCmp('tel').setValue(result.result.tel);
											Ext.getCmp('est_date').setValue(result.result.est_date);
											
											Ext.getCmp('leg_per_card').setValue(result.result.leg_per_card);
											Ext.getCmp('management').setValue(result.result.management);
											Ext.getCmp('reg_capital').setValue(result.result.reg_capital);
											Ext.getCmp('reg_date').setValue(result.result.reg_date);
											Ext.getCmp('email').setValue(result.result.email);
											Ext.getCmp('ent_reg_date').setValue(result.result.bu_li_number);
											
											Ext.getCmp('bu_li_number').setValue(result.result.bu_li_number);
											Ext.getCmp('tax_reg_number').setValue(result.result.tax_reg_number);
											Ext.getCmp('issuing_date').setValue(result.result.issuing_date);
											Ext.getCmp('permit_person').setValue(result.result.permit_person);
											Ext.getCmp('business_scope').setValue(result.result.business_scope);
											
											Ext.getCmp('companyid').setValue(result.result.companyid);
										},
										failure : function() {
										}
								    });
					            }
					        }
					    }
					},{
				     	 xtype: 'panel',
	                     title: '企业操作',
						 region: "center",
			   			 border: true,
						 frame: true,
						 autoScroll:true,
                     	 items: [{xtype:'orgazicationDeptView'}]
				    }]
			});
    }
});


