Ext.define("FMS.store.CarTreeStore",{
	 extend:"Ext.data.TreeStore",
	 autoLoad : 'true',
	 root : {expanded : true,text : '功能菜单' },
	 proxy: {
		 type: 'ajax',
		 timeout:180000,
		 url: window.BIZCTX_PATH + '/servlet/CarTree', 
		 reader: {
			 type: 'json'
		 }
	 },
	 listeners: {
	    load: function(treestore,node,records,successful,eOpts ){
	    	if((admin.blocid == 1) && depthflag){
	    	    Ext.getCmp("tree-panel").getRootNode().cascadeBy(function(node) {
	            	if(node.getDepth() >= depth){
						node.collapse();
					}
	            });
	            depthflag = false;
	    	}
	    }
	  }
 });