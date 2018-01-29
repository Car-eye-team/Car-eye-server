Ext.define('VedioGuojiApp.view.VedioView' ,{
    extend: 'Ext.panel.Panel',
    alias : 'widget.vedioView',
    id:'vediopanel',
	//title: '视频',
    header : false,
	autoHeight : true,
	region: "center",
    border: true,
	frame: true,
	bodyStyle:'padding:10 10 10 10',
	layout: {
        type: 'table',
        columns: 2
    },
    defaults: { 
    	frame: true,
    	height:Ext.getBody().getViewSize().height-10,
        width:Ext.getBody().getViewSize().width-300
    },
    items: [{
    	 xtype: "panel",
         html: "<object classid='clsid:DAB63197-3FF9-4236-924C-F8641094DDFD' codebase = 'cmsv6.cab#version=6,0,0,4' height='100%' width='100%' id = 'cmsv6' name='cmsv6'></object>",
         tbar:[{
                text:'实时播放',
                tooltip:'实时播放',
                iconCls:'icon_recovery',
                handler: function() {
                	StartShow();
                }
            },"-",{
                text:'停止播放',
                tooltip:'停止播放',
                iconCls:'icon_stop',
                handler: function() {
                	StopShow();
                }
            },"-",{
                text:'打开音频',
                tooltip:'打开音频',
                handler: function() {
                	OSound();
                }
            },"-",{
                text:'关闭音频',
                tooltip:'关闭音频',
                handler: function() {
                	CSound();
                }
            }
            ]
      }
    ]
});

