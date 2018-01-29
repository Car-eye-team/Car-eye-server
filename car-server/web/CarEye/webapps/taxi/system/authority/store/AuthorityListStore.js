Ext.define("authorityApp.store.AuthorityListStore",{
	extend:"Ext.data.TreeStore",
	root : {
		expanded : true,
		text : '选择菜单'
	},
	 proxy: {
		 type: 'ajax',
		 url: window.BIZCTX_PATH + '/servlet/MenuTreeServlet',
		 reader: {
			 type: 'json'
		 }
	 }
 });