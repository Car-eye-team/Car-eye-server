/**
* Description: car-eye车辆管理平台
* 文件名：EntityTree.java
* 版本信息：1.0
* 日期：2014-2-26
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.common.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import net.sf.json.JSONArray;

/**
 * @项目名称：car-eyeTms
 * @类名称：EntityTree
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2014-2-26 下午01:37:47
 * @修改人：zhangrong
 * @修改时间：2014-2-26 下午01:37:47
 * @修改备注：
 * @version 1.0
 */

public class EntityTreeUtil {
	
	
	/**
	 * menuList菜单列表
	 * type 1.加载所有 2.加载对应组织机构下面菜单
	 * deptid type为2时代表组织机构id 为1时默认为0代表根节点
	 * @param menuList
	 * @return
	 */
    public static String getTreeJsonString(List<MenuTree> menuList,int type,int deptid) {
        /**
         * 定义“数组-链表”，该数组链表的每一项相当于一深度为2的小树
         * Map的key相当于“数组”的某一项，Map的value相当于该key所拥有的“链表”
         * 这里，key为父节点ID，list为具有相同父节点ID的所有同级子节点实体list（属于该父节点的所有子节点）
         */
        Map<String, List<MenuTree>> arrayListMap = new HashMap<String, List<MenuTree>>();
 
        for (MenuTree menuTree : menuList) {
            // 变量定义务必在循环内，对象是引用，不能重复使用同一个对象变量
            String fatherId = menuTree.getParentId();
            // 获取当前遍历结点的父ID，并判断该父节点的数组链表项是否存在，如果该“数组项-链表项”不存在，则新建一个，并放入“数组-链表”
        if (arrayListMap.get(fatherId) == null) {
            List<MenuTree> list = new ArrayList<MenuTree>();
            MenuTree tree = new MenuTree();
            tree.setId(menuTree.getId());
            tree.setChildren(menuTree.getChildren());
            tree.setExpanded(menuTree.isExpanded());
            tree.setHrefTarget(menuTree.getHrefTarget());
            tree.setLeaf(menuTree.isLeaf());
            tree.setText(menuTree.getText());
            tree.setChecked(menuTree.isChecked());
            tree.setParentId(menuTree.getParentId());
            list.add(tree);
            arrayListMap.put(fatherId, list);
       } else {
            List<MenuTree> valueList = arrayListMap.get(fatherId);
                valueList.add(menuTree);
                arrayListMap.put(fatherId, valueList);
            }
        }
        // 以上，至此，第一遍遍历完毕，非叶子节点都拥有一个“数组-链表项”，也即“最小的树”已创建完毕
 
        // 以下，对“数组链表”Map进行遍历，更改“最小的树”的从属关系（更改指针指向），也即把所有小树组装成大树
        for (Map.Entry<String, List<MenuTree>> entry : arrayListMap.entrySet()) {
            // 获取当前遍历“数组项-链表项”的链表项，并对链表项进行遍历，从“数组-链表”小树中找到它的子节点，并将该子节点加到该小树的children中
            List<MenuTree> smallTreeList = new ArrayList<MenuTree>();
            smallTreeList = entry.getValue();
            int nodeListSize = smallTreeList.size();
            for (int i = 0; i < nodeListSize; i++) {
                String findID = smallTreeList.get(i).getId();
                List<MenuTree> findList = arrayListMap.get(findID);
                // 以下操作不能取出对象存放在变量中，否则将破坏树的完整性
                smallTreeList.get(i).setChildren(findList);
            }
        }
        // 获取以1为父Id的链表项，该链表项是根节点实体，里面已封装好各子节点，可以由于多个根节点，即这些根结点的父Id都为1
        List<MenuTree> rootNodeList = arrayListMap.get(deptid+"");
        JSONArray jsonArray = JSONArray.fromObject(rootNodeList);
        return jsonArray.toString();
    }
    
    /**
     * 菜单下拉树
     * @param menuList
     * @return
     */
    public static String getAuthorityTreeString(List<MenuEntry> menuList) {
    	/**
    	 * 定义“数组-链表”，该数组链表的每一项相当于一深度为2的小树
    	 * Map的key相当于“数组”的某一项，Map的value相当于该key所拥有的“链表”
    	 * 这里，key为父节点ID，list为具有相同父节点ID的所有同级子节点实体list（属于该父节点的所有子节点）
    	 */
    	Map<String, List<MenuEntry>> arrayListMap = new HashMap<String, List<MenuEntry>>();
    	
    	for (MenuEntry menuEntry : menuList) {
    		// 变量定义务必在循环内，对象是引用，不能重复使用同一个对象变量
    		String fatherId = menuEntry.getParentId();
    		// 获取当前遍历结点的父ID，并判断该父节点的数组链表项是否存在，如果该“数组项-链表项”不存在，则新建一个，并放入“数组-链表”
    		if (arrayListMap.get(fatherId) == null) {
    			List<MenuEntry> list = new ArrayList<MenuEntry>();
    			MenuEntry tree = new MenuEntry();
    			tree.setId(menuEntry.getId());
    			tree.setItems(menuEntry.getItems());
    			tree.setMenu(tree.getItems());
    			tree.setHrefTarget(menuEntry.getHrefTarget());
    			tree.setText(menuEntry.getText());
    			tree.setParentId(menuEntry.getParentId());
    			list.add(tree);
    			arrayListMap.put(fatherId, list);
    		} else {
    			List<MenuEntry> valueList = arrayListMap.get(fatherId);
    			menuEntry.setMenu(menuEntry.getItems());
    			valueList.add(menuEntry);
    			arrayListMap.put(fatherId, valueList);
    		}
    	}
    	// 以上，至此，第一遍遍历完毕，非叶子节点都拥有一个“数组-链表项”，也即“最小的树”已创建完毕
    	
    	// 以下，对“数组链表”Map进行遍历，更改“最小的树”的从属关系（更改指针指向），也即把所有小树组装成大树
    	for (Map.Entry<String, List<MenuEntry>> entry : arrayListMap.entrySet()) {
    		// 获取当前遍历“数组项-链表项”的链表项，并对链表项进行遍历，从“数组-链表”小树中找到它的子节点，并将该子节点加到该小树的children中
    		List<MenuEntry> smallTreeList = new ArrayList<MenuEntry>();
    		smallTreeList = entry.getValue();
    		int nodeListSize = smallTreeList.size();
    		for (int i = 0; i < nodeListSize; i++) {
    			String findID = smallTreeList.get(i).getId();
    			List<MenuEntry> findList = arrayListMap.get(findID);
    			// 以下操作不能取出对象存放在变量中，否则将破坏树的完整性
    			smallTreeList.get(i).setItems(findList);
    			smallTreeList.get(i).setMenu(smallTreeList.get(i).getItems());
    		}
    	}
    	// 获取以1为父Id的链表项，该链表项是根节点实体，里面已封装好各子节点，可以由于多个根节点，即这些根结点的父Id都为1
    	List<MenuEntry> rootNodeList = arrayListMap.get("0");
    	JSONArray jsonArray = JSONArray.fromObject(rootNodeList);
    	return jsonArray.toString();
    }
    
    /**
     * 菜单下拉树
     * @param menuList
     * @return
     */
    public static String getMenuTreeString(List<MenuEntry> menuList) {
    	/**
    	 * 定义“数组-链表”，该数组链表的每一项相当于一深度为2的小树
    	 * Map的key相当于“数组”的某一项，Map的value相当于该key所拥有的“链表”
    	 * 这里，key为父节点ID，list为具有相同父节点ID的所有同级子节点实体list（属于该父节点的所有子节点）
    	 */
    	Map<String, List<MenuEntry>> arrayListMap = new HashMap<String, List<MenuEntry>>();
    	
    	for (MenuEntry menuEntry : menuList) {
    		// 变量定义务必在循环内，对象是引用，不能重复使用同一个对象变量
    		String fatherId = menuEntry.getParentId();
    		// 获取当前遍历结点的父ID，并判断该父节点的数组链表项是否存在，如果该“数组项-链表项”不存在，则新建一个，并放入“数组-链表”
    		if (arrayListMap.get(fatherId) == null) {
    			List<MenuEntry> list = new ArrayList<MenuEntry>();
    			MenuEntry tree = new MenuEntry();
    			tree.setId(menuEntry.getId());
    			tree.setItems(menuEntry.getItems());
    			tree.setHrefTarget(menuEntry.getHrefTarget());
    			tree.setText(menuEntry.getText());
    			tree.setParentId(menuEntry.getParentId());
    			list.add(tree);
    			arrayListMap.put(fatherId, list);
    		} else {
    			List<MenuEntry> valueList = arrayListMap.get(fatherId);
    			valueList.add(menuEntry);
    			arrayListMap.put(fatherId, valueList);
    		}
    	}
    	// 以上，至此，第一遍遍历完毕，非叶子节点都拥有一个“数组-链表项”，也即“最小的树”已创建完毕
    	
    	// 以下，对“数组链表”Map进行遍历，更改“最小的树”的从属关系（更改指针指向），也即把所有小树组装成大树
    	for (Map.Entry<String, List<MenuEntry>> entry : arrayListMap.entrySet()) {
    		// 获取当前遍历“数组项-链表项”的链表项，并对链表项进行遍历，从“数组-链表”小树中找到它的子节点，并将该子节点加到该小树的children中
    		List<MenuEntry> smallTreeList = new ArrayList<MenuEntry>();
    		smallTreeList = entry.getValue();
    		int nodeListSize = smallTreeList.size();
    		for (int i = 0; i < nodeListSize; i++) {
    			String findID = smallTreeList.get(i).getId();
    			List<MenuEntry> findList = arrayListMap.get(findID);
    			// 以下操作不能取出对象存放在变量中，否则将破坏树的完整性
    			smallTreeList.get(i).setItems(findList);
    		}
    	}
    	// 获取以1为父Id的链表项，该链表项是根节点实体，里面已封装好各子节点，可以由于多个根节点，即这些根结点的父Id都为1
    	List<MenuEntry> rootNodeList = arrayListMap.get("0");
    	JSONArray jsonArray = JSONArray.fromObject(rootNodeList);
    	return jsonArray.toString();
    }
    
}