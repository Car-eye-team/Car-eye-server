
Ext.define('authoritySetApp.controller.AuthoritySetCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['AuthoritySetListStore'],//声明该控制层要用到的store
    models: ['AuthoritySetModel'],//声明该控制层要用到的model
    views: ['AuthoritySetListView'],//声明该控制层要用到的view
    refs: [{//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
            ref: 'authoritySetListView',
            selector: 'authoritySetListView'
        }
    ],
    init: function() {
    	this.control({
			'authoritySetListView button[action=assignSet]' : {
				click : this.assignSet
			}
		});
	},
	assignSet : function(button) {
			var view = Ext.widget('authoritySetListView');
			var treeMenu = view.getChecked();
			var jsonData = "";
			for ( var i = 0, len = treeMenu.length; i < len; i++) {
				var ss = treeMenu[i].get("id");
				if (i == 0) {
					jsonData = jsonData + ss;
				} else {
					jsonData = jsonData + "," + ss;
				}
			}
			var mask=new Ext.LoadMask('authoritySetListView',{msg:"正在设置默认权限,请稍后......"});
			mask.show();
			Ext.Ajax.request( {
					url : window.BIZCTX_PATH + '/system/authorityjson/assignAuthority.action',
					method : 'POST',  
					timeout : 150000,
					params : "userGroup.blocgroupid=-1&ids="+ jsonData,
					success : function(response) {
						mask.hide();
						Ext.Msg.alert('提示',"默认权限设置成功");
					},
					failure : function() {
						Ext.Msg.alert('提示',"默认权限设置失败");
					}
			});
		return false;
	}
	
	
	
});

