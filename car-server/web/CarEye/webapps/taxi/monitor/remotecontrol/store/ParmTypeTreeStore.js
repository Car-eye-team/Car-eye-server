Ext.define("RemoteControlApp.store.ParmTypeTreeStore",{
	 extend:"Ext.data.TreeStore",
	 autoLoad : 'true',
	 root : {expanded : true,text : '功能菜单' },
	 proxy: {
		 type: 'ajax',
		 url: window.BIZCTX_PATH + '/taxi/monitor/remotecontrol/ParmTypeTree.json', 
		 reader: {
			 type: 'json'
		 }
	 }
 });