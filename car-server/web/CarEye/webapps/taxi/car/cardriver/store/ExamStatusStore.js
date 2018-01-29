Ext.define("CarDriverApp.store.ExamStatusStore",{
	extend:"Ext.data.Store",
//	autoLoad: true,
	fields: ['id', 'examstatus'],
         //1已结业2未结业3未参与
    data : [
        {"id":"1", "examstatus":"通过"},
        {"id":"2", "examstatus":"不通过"},
        {"id":"3", "examstatus":"未考试"}
    ]
 });