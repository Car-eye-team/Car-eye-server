Ext.define('OrgazicationDeptApp.controller.OrgazicationDeptCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['DeptTreeStore','DeptWinTreeStore','BranchStore','Ec_natureStore','CompanyListStore'],
    models: ['OrgazicationDeptModel','CompanyModel'],//声明该控制层要用到的model
    views: ['OrgazicationDeptView','DeptWinView','DeptWinTreeView'],
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'orgazicationDeptView',
            selector: 'orgazicationDeptView'
        },{
            ref: 'deptWinView',
            selector: 'deptWinView'
        },{
            ref: 'deptWinTreeView',
            selector: 'deptWinTreeView'
        }
    ],
    init: function() {
		this.control({
			'orgazicationDeptView button[action=add]' : {
				click : this.addOrgazicationDept
			},
			'orgazicationDeptView button[action=save]' : {
				click : this.saveOrgazicationDept
			},
			'orgazicationDeptView button[action=delete]' : {
				click : this.deleteOrgazicationDept
			},
			'orgazicationDeptView button[action=deptmove]' : {
				click : this.createdeptWin
			},
			'deptWinView button[action=save]' : {
				click : this.deptMove
			},
			'orgazicationDeptView':{
                render : this.buttonAccess
            }
		});
	},
	buttonAccess : function() {
		var buttonArray = ['110101','110102','110103','110104'];
		for(var i=0;i<buttonArray.length;i++){
			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	addOrgazicationDept : function(button){
		if(Ext.getCmp('parentid').getValue().length == 0){
			Ext.Msg.alert("提示","请选择上级企业!");
			return;
		}
		
		Ext.getCmp('id').setValue("");
		
		Ext.getCmp('bloc_name').setValue("");
		Ext.getCmp('res_per').setValue("");
		Ext.getCmp('address').setValue("");
		Ext.getCmp('reg_address').setValue("");
		Ext.getCmp('com_homepage').setValue("");
		Ext.getCmp('fax').setValue("");
		Ext.getCmp('remark').setValue("");
		
		Ext.getCmp('leg_per').setValue("");
		Ext.getCmp('ec_nature').setValue("");
		Ext.getCmp('reg_number').setValue("");
		Ext.getCmp('reg_person').setValue("");
		Ext.getCmp('tel').setValue("");
		Ext.getCmp('est_date').setValue("");
		
		Ext.getCmp('leg_per_card').setValue("");
		Ext.getCmp('management').setValue("");
		Ext.getCmp('reg_capital').setValue("");
		Ext.getCmp('reg_date').setValue("");
		Ext.getCmp('email').setValue("");
		Ext.getCmp('ent_reg_date').setValue("");
		
		Ext.getCmp('bu_li_number').setValue("");
		Ext.getCmp('tax_reg_number').setValue("");
		Ext.getCmp('issuing_date').setValue("");
		Ext.getCmp('permit_person').setValue("");
		Ext.getCmp('business_scope').setValue("");
		
		Ext.getCmp('companyid').setValue("");
		return false;
	},
	saveOrgazicationDept : function(button){
		var form = button.up('panel');
		var store = this.getDeptTreeStoreStore();
		if (!form.getForm().isValid()) {
			return;
		}
		
		if(Ext.getCmp('parentid').getValue().length == 0){
			Ext.Msg.alert("提示","请选择上级企业!");
			return;
		}
		
		form.getForm().submit({
			url : window.BIZCTX_PATH + '/orgazicationdeptjson/saveOrgazicationDept.action',
			method : 'post',
			waitMsg :'正在操作,请稍候...',
			success : function(form, action) {
					var resp = action.result.result;
					var su = resp.su;
					if (su == -2) {
						Ext.Msg.alert("提示","企业名称已存在，请更换!");
					}else if (su == -1) {
						Ext.Msg.alert("提示","系统异常,请与管理员联系!");
					}else {
						Ext.Msg.alert("提示","操作企业成功!");
						Ext.getCmp('id').setValue(resp.id);
						store.reload();
					}
		    },
			failure : function(form, action) {
				Ext.Msg.alert('提示', "操作企业失败!");
			}
		});
		return false;
	},
	deleteOrgazicationDept : function(button){
		var id = Ext.getCmp('id').getValue();
		if(id.length == 0){
			Ext.Msg.alert("提示","请选择企业");
			return;
		}
		var store = this.getDeptTreeStoreStore();
		Ext.MessageBox.confirm('提示','你确认要删除选中的企业?',
			function(btn) {
				if (btn == "yes") {
					Ext.Ajax.request( {
						url : window.BIZCTX_PATH + '/orgazicationdeptjson/deleteOrgazicationDept.action',
						method : 'POST',  
						params : "id="+ Ext.getCmp('id').getValue(),
						success : function(response) {
							var text = response.responseText;
							var result =  Ext.JSON.decode(text).result;
							var su = result.su;
							if(su == -1) {
								Ext.Msg.alert("提示","该企业下面有车辆不能删除!");
					   		}else if (su == -2) {
								Ext.Msg.alert("提示","该企业下面还有下级企业不能删除!");
					   		}else if(su == -3) {
								Ext.Msg.alert("提示","该企业下面分配有企业组不能删除!");
							}else if (su == 1) {
								Ext.Msg.alert("提示","删除企业成功!");
								var form = button.up('panel');
								form.getForm().reset();
								store.reload();
							}else {
								Ext.Msg.alert("提示","系统异常,请与管理员联系!");
							}
						},
						failure : function() {
						}
				    });
				}
			});
	},
	createdeptWin : function(button){
		var id = Ext.getCmp('id').getValue();
		if(id.length == 0){
			Ext.Msg.alert("提示","请选择需要转移的企业");
			return;
		}
		
		var store = this.getDeptWinTreeStoreStore();
		store.clearFilter(true);
	    store.reload();
	    
	    var view = Ext.widget('deptWinView');
		view.show();
	},
	deptMove : function(button){
		var win = button.up('window');
		var blocid = Ext.getCmp('id').getValue();
		var bloc_name = Ext.getCmp('bloc_name').getValue();
		var moveblocid = Ext.getCmp('move_blocid').getValue();
		var moveblocname = Ext.getCmp('move_blocname').getValue();
		if(moveblocid.length == 0){
			Ext.Msg.alert("提示","请单击需要转移到的企业");
			return;
		}
		if(blocid == admin.blocid){
			Ext.Msg.alert("提示","不能转移根企业!");
			return;
		}
		if(blocid == moveblocid){
			Ext.Msg.alert("提示","不能转移到自己所属企业!");
			return;
		}
		var store = this.getDeptTreeStoreStore();
		Ext.MessageBox.confirm('提示','您确认要把【'+bloc_name+'】转移至【'+moveblocname+'】?',
			function(btn) {
				if (btn == "yes") {
					Ext.Ajax.request( {
						url : window.BIZCTX_PATH + '/orgazicationdeptjson/deptMove.action',
						method : 'POST',  
						params : {blocid:blocid,bloc_name:encodeURI(bloc_name),moveblocid:moveblocid},
						success : function(response) {
							var text = response.responseText;
							var result =  Ext.JSON.decode(text).result;
							var su = result.su;
					   		if (su == -2) {
								Ext.Msg.alert("提示","转移失败!");
							}else if (su == 1) {
								Ext.Msg.alert("提示","转移成功!");
								store.reload();
								win.close();
							}else {
								Ext.Msg.alert("提示","系统异常,请与管理员联系!");
							}
						},
						failure : function() {
						}
				    });
				}
			});
	}
	
	
});

