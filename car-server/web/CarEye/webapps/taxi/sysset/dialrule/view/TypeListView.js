Ext.define('DialRuleApp.view.TypeListView', {
	extend : 'Ext.panel.Panel',
	id : 'typeViewGrid',
	// frame: true,
	split : true,
	collapsible : true,
	collapseMode : "mini",
	region : "west",
	width : 250,
	alias : 'widget.typeListView',
	autoScroll : true,
	title : '设置选项',
	layout : 'form',
	items : [{
		xtype : 'form',
		anchor : '100%',
		border : false,
		frame : false,
		autoScroll : true,
		collapsible : false,
		buttonAlign : 'right',
		items : [{
			layout : 'fit',
			xtype : 'treepanel',
			collapseMode : "mini",
			border : false,
			rootVisible : false,
			id : 'tree-panel',
			store : 'ParmTypeTreeStore',
			listeners : {
				'itemclick' : {
					fn : function(view, record, item, index, event) {
						if (record.get('id').length > 1) {
							var pidList = ['jsdd', 'yydd', 'zpms', 'zdd'];
							var j = 0;
							for (var i = 0, len = pidList.length; i < len; i++) {
								Ext.getCmp("form_" + pidList[i])
										.setVisible(false);
								Ext.getCmp("form_" + pidList[i]).form.reset();
								// Ext.getCmp("form_"+pidList[i]).form.remove(pidList[i]+'_cartype');
								// Ext.getCmp("form_"+pidList[i]).setDisabled(true);
								if (pidList[i] == record.get('id')) {
									j = i + 1;
								}
							}
							// Ext.getCmp("form_"+record.get('id')).setDisabled(false);
							Ext.getCmp("form_" + record.get('id'))
									.setVisible(true);
							// Ext.getCmp("form_"+record.get('id')).form.add(record.get('id')+'_cartype');
							Ext.getCmp("dr_type").setValue(j);
							Ext.create("DialRuleApp.controller.DialRuleCtrl")
									.searchRecordList();
							Ext.getCmp("form_" + record.get('id')).form.load({
								url : window.BIZCTX_PATH
										+ '/dialrulejson/findLastDialRule.action',
								params : {
									type : j
								},
								success : function(form, action) {
									var data = action.result.data;
									var jc = data.jsdd_carstatus;
									if (jc != null && jc.length > 0) {
										// 给下拉框赋值
										var vls = [];
										var str = jc.split(",");
										if (str.length > 0) {
											for (var i = 0; i < str.length; i++) {
												vls.push(str[i]);
											}
										}
										form.findField("jsdd_carstatus")
												.setValue(vls);
									} 
									var jz=data.jsdd_zkstate;
									if(jz != null && jz.length > 0){
										var vls = [];
										var str = jz.split(",");
										if (str.length > 0) {
											for (var i = 0; i < str.length; i++) {
												vls.push(str[i]);
											}
										}
										form.findField("jsdd_zkstate")
												.setValue(vls);
									}
									var yc=data.yydd_carstatus;
									if(yc != null && yc.length > 0){
										var vls = [];
										var str = yc.split(",");
										if (str.length > 0) {
											for (var i = 0; i < str.length; i++) {
												vls.push(str[i]);
											}
										}
										form.findField("yydd_carstatus")
												.setValue(vls);
									}
									var yz=data.yydd_zkstate;
									if(yz != null && yz.length > 0){
										var vls = [];
										var str = yz.split(",");
										if (str.length > 0) {
											for (var i = 0; i < str.length; i++) {
												vls.push(str[i]);
											}
										}
										form.findField("yydd_zkstate")
												.setValue(vls);
									}
									var zc=data.zpms_carstatus;
									if(zc != null && zc.length > 0){
										var vls = [];
										var str = zc.split(",");
										if (str.length > 0) {
											for (var i = 0; i < str.length; i++) {
												vls.push(str[i]);
											}
										}
										form.findField("zpms_carstatus")
												.setValue(vls);
									}
									var zz=data.zpms_zkstate;
									if(zz != null && zz.length > 0){
										var vls = [];
										var str = zz.split(",");
										if (str.length > 0) {
											for (var i = 0; i < str.length; i++) {
												vls.push(str[i]);
											}
										}
										form.findField("zpms_zkstate")
												.setValue(vls);
									}
								}
							});
						}
					}
				},
				'render' : function(cmp, obj) {
					Ext.getCmp("form_jsdd").form.load({
								url : window.BIZCTX_PATH
										+ '/dialrulejson/findLastDialRule.action',
								params : {
									type : 1
								},
								success : function(form, action) {
									var data = action.result.data;
									var jc = data.jsdd_carstatus;
									if (jc != null && jc.length > 0) {
										// 给下拉框赋值
										var vls = [];
										var str = jc.split(",");
										if (str.length > 0) {
											for (var i = 0; i < str.length; i++) {
												vls.push(str[i]);
											}
										}
										form.findField("jsdd_carstatus")
												.setValue(vls);
									} 
									var jz=data.jsdd_zkstate;
									if(jz != null && jz.length > 0){
										var vls = [];
										var str = jz.split(",");
										if (str.length > 0) {
											for (var i = 0; i < str.length; i++) {
												vls.push(str[i]);
											}
										}
										form.findField("jsdd_zkstate")
												.setValue(vls);
									}
								}
							});
				}
			}
		}]
	}]

});
