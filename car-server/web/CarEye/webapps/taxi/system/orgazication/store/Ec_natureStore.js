Ext.define("OrgazicationDeptApp.store.Ec_natureStore",{
	extend:"Ext.data.Store",
	autoLoad: true,
	fields: ['id', 'type'],
         //0 股份制，1 国有，2 集体，3 私有，4其他
    data : [
        {"id":0, "type":"股份"},
        {"id":1, "type":"国有"},
        {"id":2, "type":"集体"},
        {"id":3, "type":"私有 "},
        {"id":4, "type":"其他 "}
       
    ]
 });