Ext.define("CarInfoApp.store.DeptWinTreeStore",{
	 extend:"Ext.data.TreeStore",
	 autoLoad : false,
	 root : {expanded : true,text : '企业' },
	 proxy: {
		 type: 'ajax',
		 url: window.BIZCTX_PATH + '/servlet/DeptTree?type=200', 
		 reader: {
			 type: 'json'
		 }
	 }
 });