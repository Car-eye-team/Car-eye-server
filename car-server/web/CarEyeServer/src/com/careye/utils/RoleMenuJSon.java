package com.careye.utils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RoleMenuJSon {
	
	/**
	 * @description : 解释roleMenus为JSON字符串
	 * @param roleMenus
	 * @return
	 * @throws JSONException
	 */
	public static String paseRoleMenu(List<Map> roleMenus, int isOpen)
			throws JSONException {
		try {
			int length = roleMenus.size();
			JSONArray jsonRoleMenus = new JSONArray();
			Map roleMenuMap;

			for (int i = 0; i < length; i++) {
				JSONObject roleMenu = new JSONObject();
				roleMenuMap = roleMenus.get(i);
				// menu_id, parent_menu_id, menu_name, role_id, authority
				long parentMenuId = (new BigDecimal(roleMenuMap.get(
						"parent_menu_id").toString())).longValue();
				long menuId = (new BigDecimal(roleMenuMap.get("menu_id")
						.toString())).longValue();
				roleMenu.put("name", (String) roleMenuMap.get("menu_name"));
				roleMenu.put("menuId", menuId);
				roleMenu.put("menuAddr", (String) roleMenuMap.get("menu_addr"));
				roleMenu.put("parentMenuId", parentMenuId);
				if ("1".equals(roleMenuMap.get("authority")+"")) {
					roleMenu.put("checked", true);
				}
				// 一级直接添加
				if (1 == parentMenuId) {
					jsonRoleMenus.put(roleMenu);
				} else {
					addNode(jsonRoleMenus, roleMenu, isOpen);
				}
			}

			return jsonRoleMenus.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * @description : 解释roleMenus为JSON字符串
	 * @param roleMenus
	 * @return
	 * @throws JSONException
	 */
	public static String paseRoleMenu(List<Map> roleMenus, String parentMenuId,
			int isOpen) throws JSONException {
		try {
			int length = roleMenus.size();
			JSONArray jsonRoleMenus = new JSONArray();
			Map roleMenuMap;

			for (int i = 0; i < length; i++) {
				JSONObject roleMenu = new JSONObject();
				roleMenuMap = roleMenus.get(i);
				Object menuId = roleMenuMap.get("menu_id");

				if (menuId != null && !menuId.equals("")) {
					if (parentMenuId.equals(menuId.toString().substring(0, 3))) {
						// menu_id, parent_menu_id, menu_name, role_id,
						// authority
						long pId = (new BigDecimal(roleMenuMap.get(
								"parent_menu_id").toString())).longValue();

						roleMenu.put("name", (String) roleMenuMap
								.get("menu_name"));
						roleMenu.put("menuId", (new BigDecimal(roleMenuMap.get(
								"menu_id").toString())).longValue());
						roleMenu.put("menuAddr", (String) roleMenuMap
								.get("menu_addr"));
						roleMenu.put("parentMenuId", (new BigDecimal(
								roleMenuMap.get("parent_menu_id").toString()))
								.longValue());
						if ("1".equals((String) roleMenuMap.get("authority"))) {
							roleMenu.put("checked", true);
						}
						// 一级直接添加
						if (pId == 0) {
							jsonRoleMenus.put(roleMenu);
						} else {
							addNode(jsonRoleMenus, roleMenu, isOpen);
						}
					}
				}

			}
			return jsonRoleMenus.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * @description : 添加节点
	 * @param jsonRoleMenus
	 *            节点要添加到目标JSONArray
	 * @param roleMenu
	 *            要添加的节点JSONObject
	 * @throws JSONException
	 */
	public static void addNode(JSONArray jsonRoleMenus, JSONObject roleMenu,
			int isOpen) throws JSONException {
		try {
			JSONObject jsonRoleMenu;
			for (int j = 0; j < jsonRoleMenus.length(); j++) {
				jsonRoleMenu = jsonRoleMenus.getJSONObject(j);
				long menuId = jsonRoleMenu.getLong("menuId");
				long parentMenuId = roleMenu.getLong("parentMenuId");
				if (menuId == parentMenuId) {
					JSONArray chidNode;
					if (jsonRoleMenu.isNull("nodes")) {
						// 添加第一个子节点
						chidNode = new JSONArray();
						chidNode.put(roleMenu);
						jsonRoleMenu.put("nodes", chidNode);
						if (!jsonRoleMenu.isNull("checked")) {
							if (isOpen == 1) {
								jsonRoleMenu.put("open", false);
							} else {
								jsonRoleMenu.put("open", true);
							}
						}
					} else {
						chidNode = jsonRoleMenu.getJSONArray("nodes");
						chidNode.put(roleMenu);
					}

				} else if (!jsonRoleMenu.isNull("nodes")) {
					// 递归到下一级
					addNode(jsonRoleMenu.getJSONArray("nodes"), roleMenu,
							isOpen);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
