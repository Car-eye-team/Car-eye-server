Ext.define('systemFunctionApp.view.SystemFunctionListView' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.systemFunctionListView',
	title: '系统菜单列表',
	autoWidth: true,
	//height:Ext.getBody().getViewSize().height-115,
	//minHeight:350,
	region: "center",
	frame: true,
	store: "SystemFunctionListStore",
	multiSelect: true,
			viewConfig : {enableTextSelection:true}, //grid中的内容能够复制 
	stripeRows:true, //表格是否隔行换色，默认为false
	loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
	selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
	columns: [
	    new Ext.grid.RowNumberer({header:"编号",width:35,align:"center"}), 
		{ header: '菜单编号', flex: 1.5, dataIndex: 'menuid', sortable: true },
		{ header: '排序号', flex: 1, dataIndex: 'menusort', sortable: true },
		{ header: '菜单名称', flex: 2.5, dataIndex: 'menuname', sortable: true },
		{ header: '菜单等级', flex: 1.3, dataIndex: 'menulevel', sortable: true ,renderer:function(value){
				if(value == 1){
					return "一级";
				}else if(value == 2){
					return "二级";
				}else if(value == 3){
					return "三级";
				}else {
					return "四级";
				}
		}},
		{ header: '父级菜单名称', flex: 2, dataIndex: 'parentmenuname', sortable: true },
		{ header: '功能访问路径', flex: 6, dataIndex: 'menuaddr', sortable: true },
		{ header: '是否激活', flex: 1.3, dataIndex: 'ifactivate', sortable: true ,renderer:function(value){
				if(value == 2){
					return "<font color='red'>停用</font>"
				}else{
					return "<font color='green'>激活</font>"
				}
		}},
		{ header: '菜单具体类型',flex: 1.6,dataIndex: 'medetype',renderer:function(value){
				if(value == 0){
					return "树枝";
				}else if(value == 1){
					return "叶子";
				}else if(value == 2){
					return "左按钮";
				}else if(value == 3){
					return "右按钮";
				}else if(value == 4){
					return "右键";
				}else if(value == 5){
					return "页面按钮";
				}
		}},
		{ header: '创建时间', flex: 2, dataIndex: 'createtime' ,sortable: true},
		{ header: '父级ID',dataIndex: 'parentid',hidden:true},
		{ header: '图标名',dataIndex: 'iconcls',hidden:true},
		{ header: '创建用户ID',dataIndex: 'createperson',hidden:true},
		{ header: '模块',dataIndex: 'module',hidden:true},
		{ header: '菜单类型', dataIndex: 'menutype', hidden: true }
		],
		dockedItems: [
                          {
                              xtype: 'toolbar',
                              layout: {
                                    overflowHandler: 'Menu'
                              },   //溢出隐藏
                              dock: 'top',
                              items: [{
						            text:'添加',
						            id: '180501',
						            tooltip:'添加系统菜单',
						            iconCls:'add',
						           // action : 'add',
						            handler: function(button){
                                            var con = Ext.create("systemFunctionApp.controller.SystemFunctionCtrl");
                                            con.createSystemFunction(button);
                                     }
						        }, '-', {
						            text:'修改',
						            id: '180502',
						            tooltip:'修改系统菜单',
						            iconCls:'edit',
						            //action : 'edit',
						            handler: function(button){
                                            var con = Ext.create("systemFunctionApp.controller.SystemFunctionCtrl");
                                            con.editSystemFunction(button);
                                    }
						        },'-',{
						            text:'激活',
						            id: '180503',
						            tooltip:'激活系统菜单',
						            iconCls:'active',
						            //action : 'active',
						            handler: function(button){
                                            var con = Ext.create("systemFunctionApp.controller.SystemFunctionCtrl");
                                            con.activeSystemFunction(button);
                                    }
						        },'-',{
						            text:'停用',
						            id: '180504',
						            tooltip:'停用系统菜单',
						            iconCls:'inactive',
						            //action : 'inactive',
						            handler: function(button){
                                            var con = Ext.create("systemFunctionApp.controller.SystemFunctionCtrl");
                                            con.inactiveSystemFunction(button);
                                    }
						        }]
            }],  
		bbar : {
			xtype : 'pagingtoolbar',
			store: "SystemFunctionListStore",   
            displayInfo: true,   
            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',   
            emptyMsg: "没有数据" 
		}
		
});

