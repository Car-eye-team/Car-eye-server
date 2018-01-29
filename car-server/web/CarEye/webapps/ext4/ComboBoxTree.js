Ext.define('Ext.ux.ComboBoxTree', { 
    extend: 'Ext.form.field.Picker', 
    xtype: 'comboboxtree', 
    triggerCls: Ext.baseCSSPrefix + 'form-arrow-trigger', 
    config: { 
        displayField: null, 
        columns: null, 
        rootVisible: true, 
        selectOnTab: true, 
        firstSelected: false, 
        maxPickerWidth: 198, 
        maxPickerHeight: 350, 
        minPickerHeight: 100 
    }, 
    editable: false, 
    initComponent: function() { 
        var me = this; 
        me.callParent(arguments); 
        this.addEvents('select'); 
        me.store.on('load', me.onLoad, me); 
         
    }, 
    createPicker: function() { 
        var me = this, 
            picker = Ext.create('Ext.tree.Panel', { 
                store: me.store, 
                floating: true, 
                hidden: true, 
                width: me.maxPickerWidth, 
                displayField: me.displayField, 
                columns: me.columns, 
                maxHeight: me.maxTreeHeight, 
                shadow: false, 
                rootVisible: me.rootVisible, 
                manageHeight: false, 
                listeners: { 
                    itemclick: Ext.bind(me.onItemClick, me) 
                }, 
                viewConfig: { 
                    listeners: { 
                        render: function(view) { 
                            view.getEl().on('keypress', me.onPickerKeypress, me); 
                        } 
                    } 
                } 
            }), 
            view = picker.getView(); 
 
        view.on('render', me.setPickerViewStyles, me); 
        if (Ext.isIE9 && Ext.isStrict) { 
            view.on('highlightitem', me.repaintPickerView, me); 
            view.on('unhighlightitem', me.repaintPickerView, me); 
            view.on('afteritemexpand', me.repaintPickerView, me); 
            view.on('afteritemcollapse', me.repaintPickerView, me); 
        } 
        return picker; 
    }, 
    setPickerViewStyles: function(view) { 
        view.getEl().setStyle({ 
            'min-height': this.minPickerHeight + 'px', 
            'max-height': this.maxPickerHeight + 'px' 
        }); 
    }, 
    repaintPickerView: function() { 
        var style = this.picker.getView().getEl().dom.style; 
        style.display = style.display; 
    }, 
    alignPicker: function() { 
        var me = this, 
            picker; 
 
        if (me.isExpanded) { 
            picker = me.getPicker(); 
            if (me.matchFieldWidth) { 
                picker.setWidth(this.picker.getWidth()); 
            } 
            if (picker.isFloating()) { 
                me.doAlign(); 
            } 
        } 
    }, 
    onItemClick: function(view, record, node, rowIndex, e) { 
        this.selectItem(record); 
    }, 
    onPickerKeypress: function(e, el) { 
        var key = e.getKey(); 
 
        if(key === e.ENTER || (key === e.TAB && this.selectOnTab)) { 
            this.selectItem(this.picker.getSelectionModel().getSelection()[0]); 
        } 
    }, 
    selectItem: function(record) { 
        var me = this; 
        me.setValue(record.get(this.valueField || 'id')); 
        me.picker.hide(); 
        me.inputEl.focus(); 
        me.fireEvent('select', me, record) 
    }, 
    onExpand: function() { 
        var me = this, 
            picker = me.picker, 
            store = picker.store, 
            value = me.value; 
        if(value) { 
            var node = store.getNodeById(value); 
            if(node) 
                picker.selectPath(node.getPath()); 
        } else { 
            var hasOwnProp = me.store.hasOwnProperty('getRootNode'); 
            if(hasOwnProp) 
                picker.getSelectionModel().select(store.getRootNode()); 
        } 
 
        Ext.defer(function() { 
            picker.getView().focus(); 
        }, 1); 
    }, 
    setValue: function(value) { 
        var me = this,record; 
        me.value = value; 
        if (me.store.loading) { 
            return me; 
        } 
        try{ 
            var hasOwnProp = me.store.hasOwnProperty('getRootNode'); 
            record = value ? me.store.getNodeById(value) : (hasOwnProp ? me.store.getRootNode() : null); 
            me.setRawValue(record ? record.get(this.displayField) : ''); 
        }catch(e){ 
            me.setRawValue(''); 
        } 
 
        return me; 
    }, 
    getValue: function() { 
        return this.value; 
    }, 
    onLoad: function(store,node,records) { 
        var value = this.value; 
        if (value) { 
            this.setValue(value); 
        }else{ 
            if(this.firstSelected){ 
                if(records && records.length > 0){ 
                    var record = records[0]; 
                    this.setValue(record.get(this.valueField)); 
                } 
            } 
        } 
    }, 
    getSubmitData: function() { 
        var me = this, 
            data = null; 
        if (!me.disabled && me.submitValue) { 
            data = {}; 
            data[me.getName()] = '' + me.getValue(); 
        } 
        return data; 
    }, 
    onTriggerClick: function() { 
        var me = this; 
        //me.store.load(); 
        if (!me.readOnly && !me.disabled) { 
            if (me.isExpanded) { 
                me.collapse(); 
            } else { 
                me.expand(); 
            } 
            me.inputEl.focus(); 
        } 
    } 
}); 