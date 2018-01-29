Ext.define('DialRuleApp.controller.DialRuleCtrl', {
	extend : 'Ext.app.Controller',
	stores : ['ParmTypeTreeStore', 'DialRuleListStore'],
	models : ['DialRuleModel'],// 声明该控制层要用到的model
	views : ['TypeListView', 'ParameterSetView', 'JsddListView',
			'YyddListView', 'ZpmsListView', 'ZddListView'],
	refs : [// 相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
	{
				ref : 'typeListView',
				selector : 'typeListView'
			}, {
				ref : 'parameterSetView',
				selector : 'parameterSetView'
			}, {
				ref : 'jsddListView',
				selector : 'jsddListView'
			}, {
				ref : 'yyddListView',
				selector : 'yyddListView'
			}, {
				ref : 'zpmsListView',
				selector : 'zpmsListView'
			}, {
				ref : 'zddListView',
				selector : 'zddListView'
			}],
	init : function() {
		this.control({
					'parameterSetView button[action=paramset]' : {
						click : this.paramset
					},
					'jsddListView button[action=search]' : {
						click : this.searchRecordList
					},
					'yyddListView button[action=search]' : {
						click : this.searchRecordList
					},
					'zpmsListView button[action=search]' : {
						click : this.searchRecordList
					},
					'zddListView button[action=search]' : {
						click : this.searchRecordList
					}
				});
	},
	searchRecordList : function(button) {
		var type = Ext.getCmp("dr_type").getValue();
		var searchstime = "";
		var searchetime = "";
		if (typeof Ext.getCmp('jsdd_searchstime') != 'undefined'
				&& Ext.getCmp('jsdd_searchstime').getValue() != null) {
			searchstime = Ext.util.Format.date(Ext.getCmp('jsdd_searchstime')
							.getValue(), 'Y-m-d H:i:s');
		}
		if (typeof Ext.getCmp('jsdd_searchetime') != 'undefined'
				&& Ext.getCmp('jsdd_searchetime').getValue() != null) {
			searchetime = Ext.util.Format.date(Ext.getCmp('jsdd_searchetime')
							.getValue(), 'Y-m-d H:i:s');
		}

		if (typeof Ext.getCmp('yydd_searchstime') != 'undefined'
				&& Ext.getCmp('yydd_searchstime').getValue() != null) {
			searchstime = Ext.util.Format.date(Ext.getCmp('yydd_searchstime')
							.getValue(), 'Y-m-d H:i:s');
		}
		if (typeof Ext.getCmp('yydd_searchetime') != 'undefined'
				&& Ext.getCmp('yydd_searchetime').getValue() != null) {
			searchetime = Ext.util.Format.date(Ext.getCmp('yydd_searchetime')
							.getValue(), 'Y-m-d H:i:s');
		}

		if (typeof Ext.getCmp('zpms_searchstime') != 'undefined'
				&& Ext.getCmp('zpms_searchstime').getValue() != null) {
			searchstime = Ext.util.Format.date(Ext.getCmp('zpms_searchstime')
							.getValue(), 'Y-m-d H:i:s');
		}
		if (typeof Ext.getCmp('zpms_searchetime') != 'undefined'
				&& Ext.getCmp('zpms_searchetime').getValue() != null) {
			searchetime = Ext.util.Format.date(Ext.getCmp('zpms_searchetime')
							.getValue(), 'Y-m-d H:i:s');
		}

		if (typeof Ext.getCmp('zdd_searchstime') != 'undefined'
				&& Ext.getCmp('zdd_searchstime').getValue() != null) {
			searchstime = Ext.util.Format.date(Ext.getCmp('zdd_searchstime')
							.getValue(), 'Y-m-d H:i:s');
		}
		if (typeof Ext.getCmp('zdd_searchetime') != 'undefined'
				&& Ext.getCmp('zdd_searchetime').getValue() != null) {
			searchetime = Ext.util.Format.date(Ext.getCmp('zdd_searchetime')
							.getValue(), 'Y-m-d H:i:s');
		}
		if(validTime(searchstime,searchetime)==-1){
				Ext.Msg.alert("提示","结束日期不能小于开始日期!");
				return;
		}
		var store = this.getDialRuleListStoreStore();
		store.clearFilter(true);
		store.on('beforeload', function(store, options) {
					var new_params = {
						type : type,
						searchstime : encodeURI(searchstime),
						searchetime : encodeURI(searchetime)
					};
					Ext.apply(store.proxy.extraParams, new_params);
				});
		store.loadPage(1);
		return false;
	},
	paramset : function(button) {
		var form = button.up('form');
		if (!form.getForm().isValid()) {
			return;
		}
		var store = this.getDialRuleListStoreStore();
		var type = button.id.substring(5);
		Ext.getCmp("dr_type").setValue(type);
		var radius = "";
		var cartype = "";
		var carcount = "";
		var carstatus = "";
		var zkstate = "";
		var assigncount = "";
		var assignmin = "";
		var effectmin = "";
//		var effecttime = "";
		var totalassigncount = "";
		var aheadassignmin = "";
		var immassigncount = "";
		var assignwaitmin = "";
		var traincount = "";
		var stime = "";
		var etime = "";
		var principle = "";
		var arrearage = "";
		var breach = "";
		var blacklist = "";
		if (1 == type) {
			if (typeof Ext.getCmp('jsdd_radius') != 'undefined'
					// && Ext.getCmp('jsdd_metter').getValue() != ""
					&& Ext.getCmp('jsdd_radius').getValue() != null) {
				radius = Ext.getCmp('jsdd_radius').getValue();
			}
			if (typeof Ext.getCmp('jsdd_cartype') != 'undefined'
					&& Ext.getCmp('jsdd_cartype').getValue() != ''
					&& Ext.getCmp('jsdd_cartype').getValue() != null) {
				cartype = Ext.getCmp('jsdd_cartype').getValue();
			}
			if (typeof Ext.getCmp('jsdd_carcount') != 'undefined'
					&& Ext.getCmp('jsdd_carcount').getValue() != ""
					&& Ext.getCmp('jsdd_carcount').getValue() != null) {
				carcount = Ext.getCmp('jsdd_carcount').getValue();
			}
			if (typeof Ext.getCmp('jsdd_carstatus') != 'undefined'
					&& Ext.getCmp('jsdd_carstatus').getValue() != ''
					&& Ext.getCmp('jsdd_carstatus').getValue() != null) {
				carstatus = Ext.getCmp('jsdd_carstatus').getValue();
			}
			if (typeof Ext.getCmp('jsdd_zkstate') != 'undefined'
					&& Ext.getCmp('jsdd_zkstate').getValue() !== ''
					&& Ext.getCmp('jsdd_zkstate').getValue() != null) {
				zkstate = Ext.getCmp('jsdd_zkstate').getValue();
			}
			if (typeof Ext.getCmp('jsdd_assigncount') != 'undefined'
					&& Ext.getCmp('jsdd_assigncount').getValue() != ''
					&& Ext.getCmp('jsdd_assigncount').getValue() != null) {
				assigncount = Ext.getCmp('jsdd_assigncount').getValue();
			}
			if (typeof Ext.getCmp('jsdd_assignmin') != 'undefined'
					&& Ext.getCmp('jsdd_assignmin').getValue() != ''
					&& Ext.getCmp('jsdd_assignmin').getValue() != null) {
				assignmin = Ext.getCmp('jsdd_assignmin').getValue();
			}
			if (typeof Ext.getCmp('jsdd_effectmin') != 'undefined'
					&& Ext.getCmp('jsdd_effectmin').getValue() != ''
					&& Ext.getCmp('jsdd_effectmin').getValue() != null) {
				effectmin = Ext.getCmp('jsdd_effectmin').getValue();
			}
//			if (typeof Ext.getCmp('jsdd_effecttime') != 'undefined'
//					&& Ext.getCmp('jsdd_effecttime').getValue() != ''
//					&& Ext.getCmp('jsdd_effecttime').getValue() != null) {
//				effecttime = Ext.getCmp('jsdd_effecttime').getValue();
//			}
			if (typeof Ext.getCmp('jsdd_arrearage') != 'undefined'
					&& Ext.getCmp('jsdd_arrearage').getValue() != ''
					&& Ext.getCmp('jsdd_arrearage').getValue() != null) {
				arrearage = Ext.getCmp('jsdd_arrearage').getValue();
			};
			if (typeof Ext.getCmp('jsdd_breach') != 'undefined'
					&& Ext.getCmp('jsdd_breach').getValue() != ''
					&& Ext.getCmp('jsdd_breach').getValue() != null) {
				breach = Ext.getCmp('jsdd_breach').getValue();
			};
			if (typeof Ext.getCmp('jsdd_blacklist') != 'undefined'
					&& Ext.getCmp('jsdd_blacklist').getValue() != ''
					&& Ext.getCmp('jsdd_blacklist').getValue() != null) {
				blacklist = Ext.getCmp('jsdd_blacklist').getValue();
			};
		} else if (2 == type) {
			if (typeof Ext.getCmp('yydd_radius') != 'undefined'
					&& Ext.getCmp('yydd_radius').getValue() != ""
					&& Ext.getCmp('yydd_radius').getValue() != null) {
				radius = Ext.getCmp('yydd_radius').getValue();
			}
			if (typeof Ext.getCmp('yydd_cartype') != 'undefined'
					&& Ext.getCmp('yydd_cartype').getValue() != ''
					&& Ext.getCmp('yydd_cartype').getValue() != null) {
				cartype = Ext.getCmp('yydd_cartype').getValue();
			}
			if (typeof Ext.getCmp('yydd_carcount') != 'undefined'
					&& Ext.getCmp('yydd_carcount').getValue() != ""
					&& Ext.getCmp('yydd_carcount').getValue() != null) {
				carcount = Ext.getCmp('yydd_carcount').getValue();
			}
			if (typeof Ext.getCmp('yydd_carstatus') != 'undefined'
					&& Ext.getCmp('yydd_carstatus').getValue() != ''
					&& Ext.getCmp('yydd_carstatus').getValue() != null) {
				carstatus = Ext.getCmp('yydd_carstatus').getValue();
			}
			if (typeof Ext.getCmp('yydd_zkstate') != 'undefined'
					// && Ext.getCmp('yydd_zkstate').getValue() !== ''
					&& Ext.getCmp('yydd_zkstate').getValue() != null) {
				zkstate = Ext.getCmp('yydd_zkstate').getValue();
			}
			if (typeof Ext.getCmp('yydd_assigncount') != 'undefined'
					&& Ext.getCmp('yydd_assigncount').getValue() != ''
					&& Ext.getCmp('yydd_assigncount').getValue() != null) {
				assigncount = Ext.getCmp('yydd_assigncount').getValue();
			}//yydd_assignmin
			if (typeof Ext.getCmp('yydd_assignmin') != 'undefined'
					&& Ext.getCmp('yydd_assignmin').getValue() != ''
					&& Ext.getCmp('yydd_assignmin').getValue() != null) {
				assignmin = Ext.getCmp('yydd_assignmin').getValue();
			}
			if (typeof Ext.getCmp('yydd_effectmin') != 'undefined'
					&& Ext.getCmp('yydd_effectmin').getValue() != ''
					&& Ext.getCmp('yydd_effectmin').getValue() != null) {
				effectmin = Ext.getCmp('yydd_effectmin').getValue();
			}
//			if (typeof Ext.getCmp('yydd_effecttime') != 'undefined'
//					&& Ext.getCmp('yydd_effecttime').getValue() != ''
//					&& Ext.getCmp('yydd_effecttime').getValue() != null) {
//				effecttime = Ext.getCmp('yydd_effecttime').getValue();
//			}
			if (typeof Ext.getCmp('yydd_totalassigncount') != 'undefined'
					&& Ext.getCmp('yydd_totalassigncount').getValue() != ''
					&& Ext.getCmp('yydd_totalassigncount').getValue() != null) {
				totalassigncount = Ext.getCmp('yydd_totalassigncount')
						.getValue();
			};
			if (typeof Ext.getCmp('yydd_aheadassignmin') != 'undefined'
					&& Ext.getCmp('yydd_aheadassignmin').getValue() != ''
					&& Ext.getCmp('yydd_aheadassignmin').getValue() != null) {
				aheadassignmin = Ext.getCmp('yydd_aheadassignmin').getValue();
			}
			if (typeof Ext.getCmp('yydd_immassigncount') != 'undefined'
					&& Ext.getCmp('yydd_immassigncount').getValue() != ''
					&& Ext.getCmp('yydd_immassigncount').getValue() != null) {
				immassigncount = Ext.getCmp('yydd_immassigncount').getValue();
			};
			if (typeof Ext.getCmp('yydd_arrearage') != 'undefined'
					&& Ext.getCmp('yydd_arrearage').getValue() != ''
					&& Ext.getCmp('yydd_arrearage').getValue() != null) {
				arrearage = Ext.getCmp('yydd_arrearage').getValue();
			};
			if (typeof Ext.getCmp('yydd_breach') != 'undefined'
					&& Ext.getCmp('yydd_breach').getValue() != ''
					&& Ext.getCmp('yydd_breach').getValue() != null) {
				breach = Ext.getCmp('yydd_breach').getValue();
			};
			if (typeof Ext.getCmp('yydd_blacklist') != 'undefined'
					&& Ext.getCmp('yydd_blacklist').getValue() != ''
					&& Ext.getCmp('yydd_blacklist').getValue() != null) {
				blacklist = Ext.getCmp('yydd_blacklist').getValue();
			};
			if(totalassigncount<immassigncount){
				Ext.Msg.alert('提示', "总调派次数不能小于即时派送轮数");
				return ;
			}
		} else if (3 == type) {
			if (typeof Ext.getCmp('zpms_radius') != 'undefined'
					&& Ext.getCmp('zpms_radius').getValue() != ""
					&& Ext.getCmp('zpms_radius').getValue() != null) {
				radius = Ext.getCmp('zpms_radius').getValue();
			}

			if (typeof Ext.getCmp('zpms_carcount') != 'undefined'
					&& Ext.getCmp('zpms_carcount').getValue() != ""
					&& Ext.getCmp('zpms_carcount').getValue() != null) {
				carcount = Ext.getCmp('zpms_carcount').getValue();
			}
			if (typeof Ext.getCmp('zpms_carstatus') != 'undefined'
					&& Ext.getCmp('zpms_carstatus').getValue() != ''
					&& Ext.getCmp('zpms_carstatus').getValue() != null) {
				carstatus = Ext.getCmp('zpms_carstatus').getValue();
			}
			if (typeof Ext.getCmp('zpms_zkstate') != 'undefined'
					// && Ext.getCmp('zpms_zkstate').getValue() !== ''
					&& Ext.getCmp('zpms_zkstate').getValue() != null) {
				zkstate = Ext.getCmp('zpms_zkstate').getValue();
			}

			if (typeof Ext.getCmp('zpms_assignmin') != 'undefined'
					&& Ext.getCmp('zpms_assignmin').getValue() != ''
					&& Ext.getCmp('zpms_assignmin').getValue() != null) {
				assignmin = Ext.getCmp('zpms_assignmin').getValue();
			}
			if (typeof Ext.getCmp('zpms_effectmin') != 'undefined'
					&& Ext.getCmp('zpms_effectmin').getValue() != ''
					&& Ext.getCmp('zpms_effectmin').getValue() != null) {
				effectmin = Ext.getCmp('zpms_effectmin').getValue();
			}
//			if (typeof Ext.getCmp('zpms_effecttime') != 'undefined'
//					&& Ext.getCmp('zpms_effecttime').getValue() != ''
//					&& Ext.getCmp('zpms_effecttime').getValue() != null) {
//				effecttime = Ext.getCmp('zpms_effecttime').getValue();
//			}
			if (typeof Ext.getCmp('zpms_assignwaitmin') != 'undefined'
					&& Ext.getCmp('zpms_assignwaitmin').getValue() != ''
					&& Ext.getCmp('zpms_assignwaitmin').getValue() != null) {
				assignwaitmin = Ext.getCmp('zpms_assignwaitmin').getValue();
			}
			if (typeof Ext.getCmp('zpms_arrearage') != 'undefined'
					&& Ext.getCmp('zpms_arrearage').getValue() != ''
					&& Ext.getCmp('zpms_arrearage').getValue() != null) {
				arrearage = Ext.getCmp('zpms_arrearage').getValue();
			};
			if (typeof Ext.getCmp('zpms_breach') != 'undefined'
					&& Ext.getCmp('zpms_breach').getValue() != ''
					&& Ext.getCmp('zpms_breach').getValue() != null) {
				breach = Ext.getCmp('zpms_breach').getValue();
			};
			if (typeof Ext.getCmp('zpms_blacklist') != 'undefined'
					&& Ext.getCmp('zpms_blacklist').getValue() != ''
					&& Ext.getCmp('zpms_blacklist').getValue() != null) {
				blacklist = Ext.getCmp('zpms_blacklist').getValue();
			};
		} else if (4 == type) {
			if (typeof Ext.getCmp('zdd_traincount') != 'undefined'
					&& Ext.getCmp('zdd_traincount').getValue() != ''
					&& Ext.getCmp('zdd_traincount').getValue() != null) {
				traincount = Ext.getCmp('zdd_traincount').getValue();
			}
			if (typeof Ext.getCmp('zdd_stime') != 'undefined'
					&& Ext.getCmp('zdd_stime').getValue() != ''
					&& Ext.getCmp('zdd_stime').getValue() != null) {
				stime = Ext.getCmp('zdd_stime').getValue();
			}
			if (typeof Ext.getCmp('zdd_etime') != 'undefined'
					&& Ext.getCmp('zdd_etime').getValue() != ''
					&& Ext.getCmp('zdd_etime').getValue() != null) {
				etime = Ext.getCmp('zdd_etime').getValue();
			}
			if (typeof Ext.getCmp('zdd_principle') != 'undefined'
					&& Ext.getCmp('zdd_principle').getValue() != ''
					&& Ext.getCmp('zdd_principle').getValue() != null) {
				principle = Ext.getCmp('zdd_principle').getValue();
			};
		}
		var myMask = new Ext.LoadMask(Ext.getBody(), {
					msg : "正在设置终端参数，请稍后..."
				});
		myMask.show();
		Ext.Ajax.request({
					url : window.BIZCTX_PATH + '/dialrulejson/addRecord.action',
					method : 'POST',
					timeout : 5000,
					params : {
						type : type,
						radius : radius,
						cartype : encodeURI(cartype),
						carcount : carcount,
						carstatus : encodeURI(carstatus),
						zkstate : encodeURI(zkstate),
						assigncount : assigncount,
						assignmin : assignmin,
						effectmin : effectmin,
//						effecttime : encodeURI(Ext.util.Format.date(effecttime,
//								'Y-m-d H:i:s')),
						totalassigncount : totalassigncount,
						aheadassignmin : aheadassignmin,
						immassigncount : immassigncount,
						assignwaitmin : assignwaitmin,
						traincount : traincount,
						stime : encodeURI(Ext.util.Format.date(stime,
								'Y-m-d H:i:s')),
						etime : encodeURI(Ext.util.Format.date(etime,
								'Y-m-d H:i:s')),
						principle : encodeURI(principle),
						arrearage : arrearage,
						breach : breach,
						blacklist : blacklist
					},
					success : function(response) {
						myMask.hide();
						Ext.Msg.alert('提示', "操作调派规则成功!");
						store.reload();
					},
					failure : function() {
						myMask.hide();
						Ext.Msg.alert('提示', "操作调派规则失败!");
					}
				});
		// var form=Ext.getCmp('parameterSet_Form');
		// form.getForm().submit({
		// url : window.BIZCTX_PATH + '/dialrulejson/addRecord.action',
		// method : 'post',
		// success : function(form, action) {
		// Ext.Msg.alert("提示","设置指令下发成功!");
		// store.reload();
		// },
		// failure : function(form, action) {
		// Ext.Msg.alert('提示', "设置指令下发失败!");
		// store.reload();
		// }
		// });
	}
});
 function validTime(startTime,endTime){
       var arr1 = startTime.split("-");
       var arr2 = endTime.split("-");
       var date1=new Date(parseInt(arr1[0]),parseInt(arr1[1])-1,parseInt(arr1[2]),0,0,0);
       var date2=new Date(parseInt(arr2[0]),parseInt(arr2[1])-1,parseInt(arr2[2]),0,0,0);
       if(date1.getTime()>date2.getTime()) {                               
               return -1;
         }else{
             return 1;
         }
         return -1;
}