Ext.define("CarParamApp.store.ParmTypeTreeStore",{
	 extend:"Ext.data.TreeStore",
	 autoLoad : 'true',
	 root : {expanded : true,text : '功能菜单' },
	 proxy: {
		 type: 'ajax',
		 url: window.BIZCTX_PATH + '/taxi/monitor/terminalparam/ParmTypeTree.json', 
		 reader: {
			 type: 'json'
		 }
	 }
 });