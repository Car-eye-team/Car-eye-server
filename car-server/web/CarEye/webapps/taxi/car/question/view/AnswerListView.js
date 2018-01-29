Ext.define('QuestionApp.view.AnswerListView' ,{
    extend: 'Ext.grid.Panel',
    border : false,
    alias : 'widget.answerListView',
    title: '问题列表',
			region: "center",
			frame: true,
			store: "AnswerListStore",
			multiSelect: true,
			height:170,
			autoScroll:true,
			stripeRows:true, //表格是否隔行换色，默认为false
			loadMask:true,   //是否在加载数据时显示遮罩效果，默认为false
			selModel: { selType: 'checkboxmodel' }, //在首列實現checkbox，僅此在首列
			columns: [
			{ header: '序号', width:40, xtype: 'rownumberer'},
			{ header: 'id',  width:100, dataIndex: 'answerid', hidden: true },
			{ header: '企业名称',  width:100, dataIndex: 'blocid', hidden: true },
			{ header: '用户ID',  width:100, dataIndex: 'userid', hidden: true },
			{ header: '问题ID',  width:100, dataIndex: 'qid', hidden: true },
			{ header: '答案', width:200,dataIndex: 'answer', sortable: true },
			{ header: '创建时间',  width:100, dataIndex: 'createtime', sortable: true }
			],
			tbar:[{
                text:'添加',
                tooltip:'添加答案',
                iconCls:'add',
                action : 'add'
            }, '-', {
                text:'修改',
                tooltip:'修改答案',
                iconCls:'edit',
                action : 'edit'
            },'-',{
                text:'删除',
                tooltip:'删除答案',
                iconCls:'remove',
                action : 'delete'
            }]
            
});



