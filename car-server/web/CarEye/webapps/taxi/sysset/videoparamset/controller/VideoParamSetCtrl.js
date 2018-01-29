Ext.define('VideoParamSetApp.controller.VideoParamSetCtrl', {
    extend: 'Ext.app.Controller',
    views: ['VideoParamSetView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
       {
            ref: 'videoParamSetView',
            selector: 'videoParamSetView'
        }
    ],
    init: function() {
		this.control({
			'videoParamSetView #videoparamset':{
				click : this.updateVideoParamSet
			},
			'videoParamSetView' : {
				afterrender : this.loadVideoParamSet
			}
			
		});
	},
	
	loadVideoParamSet : function(){
		Ext.getCmp('parameterSet_Form').getForm().load({
		      url: window.BIZCTX_PATH + '/videoparamsetjson/queryVideoParamSet.action' //请求的服务器地址
	    });
	},
	updateVideoParamSet : function(button) {
		var form = Ext.getCmp('parameterSet_Form');
		if (!form.getForm().isValid()) {
			return;
		}
		
		form.getForm().submit({
			url : window.BIZCTX_PATH + '/videoparamsetjson/updateVideoParamSet.action',
			method : 'post',
			waitMsg:'正在设置,请稍侯...',
			
			success : function(form,action) {
				var resp = action.result.result;
				var su = resp.su;
				if (su == -1) {
					Ext.Msg.alert("提示","设置失败!");  
				}else if (su == 1) {
					Ext.Msg.alert("提示","设置成功!");
				}else {
					Ext.Msg.alert("提示","设置失败!"); 
				}
		    },
			failure : function(form,action) {
				Ext.Msg.alert("提示","设置失败!"); 
			}
		});
		
		return false;
	}
	
	
	
});

