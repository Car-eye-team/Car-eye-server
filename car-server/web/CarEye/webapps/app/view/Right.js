Ext.define('FMS.view.Right',{
			extend:'Ext.panel.Panel',
			alias:'widget.right',
			region: 'east',
			title:'车辆订单信息',
            id: 'east-panel',
            collapsed: true,
		    collapsible: true,
            collapseMode: "mini",
            header : false,
            split: true,
            width: 180,
            layout: 'fit',
            margins: '0 3 0 0',
            items :[ {
			xtype : 'form',
			bodyStyle: 'border-width:0 0 0 0;',
			anchor : '100%',
			collapsible : false,
			fieldDefaults: {
		        labelAlign: 'right',
		        labelWidth: 60,
		        height:30
		    },
			layout : 'form',
			items : [{
				layout : 'column',
				border : false,
				columnWidth : 1,
				items : [{
							columnWidth : 1,
							layout : 'form',
							border:false,
							items : [{
										xtype : 'displayfield',
										fieldLabel : '<font color = "#004a91"><b>订单号</b></font>',
										id : 'loi_orderid',
										readOnly : true
									},{
										xtype : 'displayfield',
										fieldLabel : '<font color = "#004a91"><b>订单时间</b></font>',
										id : 'loi_createtime',
										readOnly : true
									},{
										xtype : 'displayfield',
										fieldLabel : '<font color = "#004a91"><b>订单来源</b></font>',
										id : 'loi_source',
										readOnly : true
									},{
										xtype : 'displayfield',
										fieldLabel : '<font color = "#004a91"><b>订单类型</b></font>',
										id : 'loi_tratype',
										readOnly : true
									},{
										xtype : 'displayfield',
										fieldLabel : '<font color = "#004a91"><b>订单状态</b></font>',
										id : 'loi_status',
										readOnly : true
									},{
										xtype : 'displayfield',
										fieldLabel : '<font color = "#004a91"><b>支付状态</b></font>',
										id : 'loi_pay',
										readOnly : true
									},{
										xtype : 'displayfield',
										fieldLabel : '<font color = "#004a91"><b>用户名称</b></font>',
										id : 'loi_usernametwo',
										readOnly : true
									},{
										xtype : 'displayfield',
										fieldLabel : '<font color = "#004a91"><b>联系电话</b></font>',
										id : 'loi_phone',
										readOnly : true
									},{
										xtype : 'displayfield',
										fieldLabel : '<font color = "#004a91"><b>出发地</b></font>',
										id : 'loi_saddress',
										readOnly : true
									},{
										xtype : 'displayfield',
										fieldLabel : '<font color = "#004a91"><b>目的地</b></font>',
										id : 'loi_eaddress',
										readOnly : true
									},{
										xtype : 'displayfield',
										fieldLabel : '<font color = "#004a91"><b>抢答时间</b></font>',
										id : 'loi_answertime',
										readOnly : true
									},{
										xtype : 'displayfield',
										fieldLabel : '<font color = "#004a91"><b>抢答司机</b></font>',
										id : 'loi_drivername',
										readOnly : true
									}
									]
					   }]
			}]
		}]
		}
);