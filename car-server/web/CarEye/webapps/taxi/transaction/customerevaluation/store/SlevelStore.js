Ext.define("CustomerEvaluationApp.store.SlevelStore",{
	extend:"Ext.data.Store",
	fields: ['id', 'value'],
    data : [
        {"id":"1", "value":"一星"},
        {"id":"2", "value":"二星"},
        {"id":"3", "value":"三星"},
        {"id":"4", "value":"四星"},
        {"id":"5", "value":"五星"}
    ]
 });