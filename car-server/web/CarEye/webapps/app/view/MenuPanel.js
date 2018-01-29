
Ext.define('FMS.view.MenuPanel',{
	extend:'Ext.Toolbar',
	alias:'widget.menuPanel',
	border : true,
    height: 35,
    layout: {
     overflowHandler: 'Menu'
    }, 
    listeners : {
		render : function(view, eOpts){
			view.add("<span><img src='"+window.BIZCTX_PATH+"/resource/images/logo.png' width='100'/></span>", "-");
			var url = window.BIZCTX_PATH + '/servlet/AuthorityMenu';
	        Ext.Ajax.request({
	        	url : url,
				success : function(response, options){
					var menuArray = Ext.JSON.decode(response.responseText);
					view.add(menuArray);
				    
			    	//按钮菜单放入menuidArray中
			    	Ext.Ajax.request({
			        	url : window.BIZCTX_PATH + '/system/authorityjson/getMenuidList.action',
						success : function(response, options){
							var text = response.responseText;
						    var result =  Ext.JSON.decode(text).result;
							menuidArray = result.menuidlist;
						}
			    	});
				    
			    	setTimeout(function(){
						view.add("->", "<span id='commserver' style='width:120px'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>");
						view.add("-");
						view.add("<span style='color:blue;'>您好!"+admin.username+",分机号："+((admin.runNumber==null ||admin.runNumber=="null")　?"":admin.runNumber)+"</span>");
						view.add("-");
						view.add(
							{
						    	iconCls : 'icon_fullscreen',
						    	text: "全屏",
						    	action:'fullscreen',
		                        handler: function(){
								   var con = Ext.create('FMS.controller.Controllers');
								   con.fullscreen();
							   }
						    },"-",{
					    	iconCls : 'icon_lock',
					        text: "锁定",
					        handler:function(){
					        	var userid = admin.id;
					        	Ext.Ajax.request( {
										url : window.BIZCTX_PATH + '/system/userjson/clearSession.action',
										method : 'POST',  
										timeout : 5000,
										success : function(response) {
										},
										failure : function() {
										}
								});
					       	   Ext.create('Ext.window.Window', {
								    title: '锁定',
								    id:'lock_window',
								    width: 230,
								    animCollapse:false,
									constrain : true,
									constrainHeader : true,
									maximizable : false,
									minimizable : false,
									frame : true,
								    modal : true,
								    closable : false,
								    layout: 'fit',
								    items: {  
								        xtype: 'form',
								        id:'open_lock_form',
								        border:false,
								        //frame : true,
								        items : [{
								        	xtype : 'hidden',
								        	id:'lock_userid',
								        	value :userid
								        },{
								        	xtype : 'textfield',
											fieldLabel : '用户密码',
											id : 'lock_password',
											labelWidth : 60,
											allowBlank : false,
											labelAlign: 'right',
											width: 200,
											margin : '5 auto',
											inputType : 'password'
								        }]
								    },
								    buttons: [{
										text: '解锁',
										action: 'unlock',
										iconCls : 'openlock',
										handler: function(){
											var form = Ext.getCmp('open_lock_form');
											if (!form.isValid()) {
												return;
											}
											var userid = Ext.getCmp('lock_userid').getValue();
											var password = Ext.getCmp('lock_password').getValue();
											
											Ext.Ajax.request( {
													url : window.BIZCTX_PATH + '/system/userjson/openLockUser.action',
													method : 'POST',  
													timeout : 5000,
													params : {userid:userid,password : password},
													success : function(response) {
														var text = response.responseText;
														var obj = eval( "(" + text + ")" );//转换后的JSON对象
									   					var su = obj.result.su;
														if( su == -1){
															Ext.Msg.alert('提示',"密码错误");
														}else if(su == 1){
															Ext.getCmp('lock_window').close();
														}
													},
													failure : function() {
													}
											});
										}
									}]
								}).show();
					        }
					    });	
					    view.add("-");
					    view.add({
									iconCls : 'password',
									text : "修改密码",
									action:'editpassword',
		                            handler: function(){
								       var con = Ext.create('FMS.controller.Controllers');
								       con.editpassword();
							        }
					    				    
					    });
					    view.add("-");
					    view.add({
					    	iconCls : 'icon_logout',
					    	text: "退出",
					    	action : 'exit',
		                    handler: function(){
								   var con = Ext.create('FMS.controller.Controllers');
								   con.logout();
							}
					    });
				    }, 1000); 	 
				}
	        });
		}
	}
});