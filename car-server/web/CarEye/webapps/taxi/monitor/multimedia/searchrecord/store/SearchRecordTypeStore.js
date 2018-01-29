Ext.define("SearchRecordApp.store.SearchRecordTypeStore",{
	extend:"Ext.data.Store",
	fields: ['id', 'mediatype'],
    data : [
        {"id":"0", "mediatype":"图像"},
        {"id":"1", "mediatype":"音频"},
        {"id":"2", "mediatype":"视频"}
        
    ]
 });