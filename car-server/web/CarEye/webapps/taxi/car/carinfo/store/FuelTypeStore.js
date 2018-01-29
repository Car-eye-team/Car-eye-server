Ext.define("CarInfoApp.store.FuelTypeStore",{
	extend:"Ext.data.Store",
//	autoLoad: true,
	fields: ['id', 'fueltype'],
         //1单一汽油、2单一柴油、3燃气、4双燃料、5电动、9其它
    data : [
        {"id":"1", "fueltype":"单一汽油"},
        {"id":"2", "fueltype":"单一柴油"},
        {"id":"3", "fueltype":"燃气"},
        {"id":"4", "fueltype":"双燃料 "},
        {"id":"5", "fueltype":"电动 "},
        {"id":"9", "fueltype":"其他 "}
    ]
 });