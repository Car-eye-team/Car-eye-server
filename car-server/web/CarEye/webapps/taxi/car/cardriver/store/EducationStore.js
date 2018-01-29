Ext.define("CarDriverApp.store.EducationStore",{
	extend:"Ext.data.Store",
	//autoLoad: true,
	fields: ['id', 'education'],
    data : [
        {"id":'1', "education":"小学"},
        {"id":'2', "education":"初中"},
        {"id":'3', "education":"高中"},
        {"id":'4', "education":"中专"},
        {"id":'5', "education":"大专"},
        {"id":'6', "education":"职高"},
        {"id":'7', "education":"本科"},
        {"id":'8', "education":"硕士研究生"},
        {"id":'9', "education":"博士研究生"},
        {"id":'10', "education":"博士后"},
        {"id":'99', "education":"其它"}
        
    ]
 });