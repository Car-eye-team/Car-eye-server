Ext.define('authoritySetApp.view.AuthoritySetListView' ,{
    extend: 'Ext.tree.Panel', 
    alias : 'widget.authoritySetListView',
	border : false, // 没有边框
	autoWidth: true,
	title: '设置默认权限',
	height:Ext.getBody().getViewSize().height,
	frame: true,
	region: "center",
	store: "AuthoritySetListStore",
	layout:'fit',
	id:'authoritySetListView',
    minWidth: 175,
    animCollapse: true,
    viewConfig : {   //checkbox联动
         onCheckboxChange : function(e, t) {
	          var item = e.getTarget(this.getItemSelector(), this.getTargetEl()), record;
	          if (item){
		           record = this.getRecord(item);
		           var check = !record.get('checked');
		           record.set('checked', check);
		           if (check) {
			            record.bubble(function(parentNode) {
				             parentNode.set('checked', true);
				             parentNode.expand(false, true);
			            });
			            record.cascadeBy(function(node) {
				             node.set('checked', true);
				             node.expand(false, true);
			            });
		           } else {
			            record.cascadeBy(function(node) {
			            	 node.set('checked', false);
			            });
		           }
	          }
	     }
    },
    rootVisible: false, //默认不显示根节点
	tbar:[{
	        text:'设置权限',
	        tooltip:'设置权限',
	        iconCls:'common-assignauthority-icon',
	        action : 'assignSet'
	}]
    
});

