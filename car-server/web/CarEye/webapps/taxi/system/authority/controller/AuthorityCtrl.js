
Ext.define('authorityApp.controller.AuthorityCtrl', {
    extend: 'Ext.app.Controller',
    stores: ['AuthorityListStore','UserGroupListStore'],//声明该控制层要用到的store
    models: ['AuthorityModel','UserGroupModel'],//声明该控制层要用到的model
    views: ['AuthoritySearchView','SpaceView','AuthorityListView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'authoritySearchView',
            selector: 'authoritySearchView'
        },{ 
            ref: 'spaceView',
            selector: 'spaceView'
        },{
            ref: 'authorityListView',
            selector: 'authorityListView'
        }
    ],
    init: function() {
    	this.control({
			'authoritySearchView button[action=assign]' : {
				click : this.assign
			},
			'authoritySearchView #authority_blocgroupid' : {
				change : this.loadUserGroup
			},
			'authoritySearchView button[action=search]' : {
				click : this.queryAuthorityByUsergroupid
			},
			'authoritySearchView':{
                render : this.buttonAccess
            }
		});
	},
	queryAuthorityByUsergroupid :function(){
		var store = this.getAuthorityListStoreStore();
		var blocgroupid = Ext.getCmp('authority_blocgroupid').getValue();
		if(blocgroupid != null && blocgroupid > 0){
			store.clearFilter(true);
			store.on('beforeload', function (store, options) {
	                var new_params = { 
	               		 blocgroupid: encodeURI(blocgroupid)
	                };
	                Ext.apply(store.proxy.extraParams, new_params);
	        });
		    store.reload();
		}else{
			Ext.Msg.alert('提示',"请先选择企业组");
			return;
		}
	},
	buttonAccess : function() {
		var buttonArray = ['110601'];
		for(var i=0;i<buttonArray.length;i++){
			if(parent.menuidArray.indexOf(buttonArray[i]) == -1){
				Ext.getCmp(buttonArray[i]).hide();
			}
		}
		return false;
	},
	loadUserGroup : function() {
		var store = this.getUserGroupListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function (store, options) {
			var blocgroupname = Ext.getCmp('authority_blocgroupid').getRawValue();
            var new_params = { 
            	blocgroupname: encodeURI(blocgroupname)
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        store.reload();
		return false;
	},
	assign : function(button) {
		
		var blocgroupid = Ext.getCmp('authority_blocgroupid').getValue();
		if(blocgroupid != null && blocgroupid > 0){
			
		}else{
			Ext.Msg.alert('提示',"请先选择企业组");
			return;
		}
		var view = Ext.widget('authorityListView');
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
		var mask=new Ext.LoadMask('authorityListView',{msg:"正在分配权限,请稍后......"});
		mask.show();
		Ext.Ajax.request( {
				url : window.BIZCTX_PATH + '/system/authorityjson/assignAuthority.action',
				method : 'POST',  
				timeout : 150000,
				params : "userGroup.blocgroupid="+ Ext.getCmp('authority_blocgroupid').getValue() +"&ids="+ jsonData,
				success : function(response) {
					mask.hide();
					Ext.Msg.alert('提示',"权限分配成功");
				},
				failure : function() {
					Ext.Msg.alert('提示',"权限分配失败");
				}
		});
		return false;
	}
	
	
	
});

