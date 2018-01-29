Ext.define("CustomerInboundApp.model.CustomerInboundModel",{
	extend : 'Ext.data.Model',  
    fields : [{  
                name : 'id',  
                type : 'int'  
            },{  
                name : 'cid',  
                type : 'int'  
            },{  
                name : 'agentid',  
                type : 'string'  
            },{  
                name : 'callnumber',  
                type : 'string'  
            },{  
                name : 'inboundcalltime',  
                type : 'string'  
            },{  
                name : 'hangupcalltime',  
                type : 'string'  
            },{  
                name : 'createtime',  
                type : 'string'  
            },{  
                name : 'cname',  
                type : 'string'  
            },{  
                name : 'typename',  
                type : 'string'  
            },{  
                name : 'userid',  
                type : 'int'  
            },{  
                name : 'username',
                type : 'string' 
            },{  
                name : 'recordfile',  
                type : 'string'  
            },{  
                name : 'uniqueid',  
                type : 'string' 
            }],  
    idProperty : 'id'// 极为重要的配置。关系到表格修改数据的获取 
 });
 
