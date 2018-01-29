Ext.define("authoritySetApp.store.AuthoritySetListStore",{
	extend:"Ext.data.TreeStore",
	root : {
		expanded : true,
		text : '选择菜单'
	},
	 proxy: {
		 type: 'ajax',
		 url: window.BIZCTX_PATH + '/servlet/MenuTreeServlet?blocgroupid=-1',
		 reader: {
			 type: 'json'
		 }
	 }
 });