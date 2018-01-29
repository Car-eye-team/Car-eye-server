Ext.define('VedioDuosenApp.view.VedioView1' ,{
    extend: 'Ext.panel.Panel',
    alias : 'widget.vedioView1',
    id:'vediopanel1',
	//title: '视频',
    header : false,
	autoHeight : true,
	region: "center",
	width:(Ext.getBody().getViewSize().width-280)*0.5,
    border: true,
	frame: true,
	bodyStyle:'padding:5 5 5 5',
	layout: {
        type: 'table',
        columns: 2
    },
    defaults: { 
    	frame: true,
    	id: 'vediopanel1_1',
    	height:Ext.getBody().getViewSize().height-15,
        width:(Ext.getBody().getViewSize().width-280)*0.49
    },
    items: [{
    	 xtype: "panel",
         html: "<object classid='clsid:1EE1C648-F4A9-42F9-9AA7-2C8E3AF7B7FD' height='100%' width='100%' id = 'EasyPlayerOcx2'></object>",
         tbar:[{
                text:'实时预览',
//                tooltip:'实时播放',
                iconCls:'icon_recovery',
                handler: function() {
                	StartShow1();
                }
            },"-",{
                text:'停止预览',
//                tooltip:'停止播放',
                iconCls:'icon_stop',
                handler: function() {
                	StopShow1();
                }
            }
            ]
      }
    ]
});

