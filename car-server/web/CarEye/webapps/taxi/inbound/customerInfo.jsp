<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
	<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld"%>
	<%@ include file="/common/include_noextjs.jsp"%>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>车辆管理部标平台-客户来电信息</title>
		<link href="<%=path%>/taxi/inbound/popupMap.css" rel="stylesheet"
			type="text/css" />
		<link href="<%=path%>/taxi/inbound/style.css" rel="stylesheet"
			type="text/css" />
	</head>

	<body style="overflow:auto">
		<input type="hidden" value='<%=request.getParameter("phone")%>' />
		
		<div class="bigbj">
			<div class="whitebox">
				<div class="tit">
					<p class="pbm">
						客户基本信息
					</p>
					
					<input type="hidden" id="c_id" value='${customer.id}' />
					
					<s:if test="customer.typename == 'VIP' ">
						<p class="titlx"></p>
					</s:if>
					<s:elseif test="customer.typename == '普通客户' ">
						<p class="titlx titlx02"></p>
					</s:elseif>
					<s:elseif test="customer.typename == '重要客户' ">
						<p class="titlx titlx03"></p>
					</s:elseif>
					<s:elseif test="customer.typename == '黄金客户' ">
						<p class="titlx titlx04"></p>
					</s:elseif>
					<s:elseif test="customer.typename == '默认客户' ">
						<p class="titlx titlx05"></p>
					</s:elseif>
					<s:else>
						<p class="titlx titlx05"></p>
					</s:else>
				</div>
				<div class="rightbox">
					<!--list-->
					<div class="list">
						<div class="op g01">
							客户名称：
							<input type="text" id="c_cname" value="${customer.cname}" onblur="loadUsetwoname(this);"/>
						</div>
						<div class="op g02">
							客户类型:
							<s:select list="cusTypeList" theme="simple"
										cssClass="input_wd4" id="c_typeid" listKey="id"
										listValue="typename" name="customer.typeid" >
									</s:select>
						</div>
						<div class="op g03">
							客户性别:
							<select class="khlx" name="customer.sex" class="input_wd2" id="c_sex" >
								<option value="2"
									<s:if test='customer.sex=="2"'> selected='selected'</s:if>>
									女
								</option>
								<option value="1"
									<s:if test='customer.sex=="1"'> selected="selected"</s:if>>
									男
								</option>
							</select>
						</div>
						<div class="op g01">
							手机号码：
							<input type="text" id="c_phone" value='${customer.phone}' />
						</div>
					</div>

					<!--list-->
					<div class="list">
						<div class="op g04">
							联系电话：
							<input type="text" id="c_tel" value='${customer.tel}' />
						</div>
						<div class="op g05">
							邮政编码：
							<input type="text" id="c_postalcode" value='${customer.postalcode}' />
						</div>
						<div class="op g06">
							地址：
							<input type="text" id="c_postaddr" value='${customer.postaddr}' />
						</div>
					</div>
					<div class="list">
						<div class="op g07">
							信息描述：
							<input type="text" id="c_remark" value='${customer.remark}' />
						</div>
					</div>
				</div>

				<div class="save" id="customerSaveButton">
					保存
				</div>
			</div>


			<!--whitebox02-->
			<div class="whitebox whitebox02">
				<div class="tit">
					最后一次约车记录
				</div>
				
				<input type="hidden" id="t_id" value='${transaction.t_id}' />
				<input type="hidden" id="t_cid" value='${transaction.cid}' />
				<input type="hidden" id="t_slat" value='${transaction.slat}' />
				<input type="hidden" id="t_slng" value='${transaction.slng}' />
				<input type="hidden" id="t_elat" value='${transaction.elat}' />
				<input type="hidden" id="t_elng" value='${transaction.elng}' />
				
				
				<s:if test='transaction.t_phone != null'>
					<div class="rightbox">
						<div class="list list01">
							<div class="op g01">
								用户名称：
								<input type="text" id="t_usernametwo" value="${customer.cname == '' ? transaction.username : customer.cname}" />
							</div>
							<div class="op g02">
								订单时间：
								<input onfocus="SelectDate(this,'yyyy-MM-dd hh:mm:ss');" readonly="readonly" class="input inputxx" id="t_usetime" type="text" value="${transaction.usetime}" />
							</div>
							<div class="op g03">
								业务类型:
								<s:select list="traTypeList" theme="simple"
											cssClass="input_wd4" id="t_typeid" listKey="id"
											listValue="typename" name="transaction.t_typeid">
										</s:select>
							</div>
							<div class="op g04">
								是否抢答:
								<select class="khlx" name="resstatus" class="input_wd2" id="t_resstatus">
									<option value=1 <s:if test="transaction.resstatus==1"> selected='selected'</s:if>>
										抢答
									</option>
									<option value=0 <s:if test="transaction.resstatus==0"> selected='selected'</s:if>>
										未抢答
									</option>
								</select>
							</div>
						</div>
	
						<div class="list list02">
							<div class="op g01">
								订单类型:
								<select class="khlx" id="t_tratype" class="input_wd2">
									<option value=0 <s:if test="transaction.tratype==0">selected="selected"</s:if>>
										即时召车
									</option>
									<option value=1 <s:if test="transaction.tratype==1">selected="selected"</s:if>>
										预约召车
									</option>
									<option value=2 <s:if test="transaction.tratype==2">selected="selected"</s:if>>
										车辆指派
									</option>
								</select>
							</div>
							<div class="op g02">
								召车联系电话：
								<input type="text" id="t_phone" value="${transaction.t_phone}" />
							</div>
							<div class="op g03">
								调度消息：
								<input id="t_remark" value="${transaction.t_remark}" type="text"/>
							</div>
						</div>
	
						<div class="list list03">
							<div class="op g01">
								<p class="p1">
									召车出发地：
									<input id="t_saddress" value="${transaction.saddress}"  style="width:270px;overflow-x:hidden;"/>
								</p>
								<p class="p2"  id="t_sselmap">
									地图
								</p>
							</div>
							<div class="op g01">
								<p class="p1">
									召车目的地：
									<input id="t_eaddress" value="${transaction.eaddress}" style="width:270px;overflow-x:hidden;"/>
								</p>
								<p class="p2" id="t_eselmap">
									地图
								</p>
							</div>
						</div>
					</div>
				
					<div class="save" id="transactionSaveButton">
						保存
					</div>
				</s:if>
				<s:else>
					<div class="emptymessage">
						没有约车记录信息!
					</div>
				</s:else>
				
			</div>

			<div class="whitebox whitebox03">
				<input type="hidden" id="tr_cid" />
				<input type="hidden" id="tr_slat" />
				<input type="hidden" id="tr_slng" />
				<input type="hidden" id="tr_elat" />
				<input type="hidden" id="tr_elng" />
				
				<div class="tit">
					召车信息
				</div>

				<div class="rightbox">
					<div class="list list04">
						<div class="op g01">
							用户名称：
							<input id="usernametwo" type="text" value="${customer.cname == '' ? phone : customer.cname}"/>
						</div>
						<div class="op g02">
							订单时间：
							<input onfocus="SelectDate(this,'yyyy-MM-dd hh:mm:ss');" value="${transaction.createtime}" readonly="readonly" class="input inputxx" id="tr_usetime" type="text" />
						</div>
						<div class="op g03">
							业务类型:
							<s:select list="traTypeList" theme="simple"
								cssClass="input_wd4" id="tr_typeid" listKey="id"
								listValue="typename" name="transaction.typeid">
							</s:select>
						</div>

						<div class="op g04">
							订单类型:
							<select class="khlx" name="tratype" class="input_wd2" id="tr_tratype">
								<option value=0>
									即时召车
								</option>
								<option value=1>
									预约召车
								</option>
								<option value=2>
									车辆指派
								</option>
							</select>
						</div>
					</div>

					<div class="list list05">
						<div class="op g01">
							召车联系电话：
							<input id="tr_phone" value="${phone}" type="text"/>
						</div>
						<div class="op g02">
							<p class="p1">
								召车车辆：
								<input id="carNumbers" style="overflow-x:hidden;"/>
							</p>
							<p class="p2" id="carChooseButton">
								选择
							</p>
						</div>
						<div class="op g03">
							调度消息：
							<input id="tr_remark" type="text"/>
						</div>
					</div>

					<div class="list list06">
						<div class="op g01">
							<p class="p1">
								召车出发地：
								<input id="tr_saddress" readonly="readonly" style="overflow-x:hidden;"/>
							</p>
							<p class="p2" id="tr_sselmap">
								地图
							</p>
						</div>

						<div class="op g01">
							<p class="p1">
								召车目的地：
								<input id="tr_eaddress" readonly="readonly" style="overflow-x:hidden;"/>
							</p>
							<p class="p2" id="tr_eselmap">
								地图
							</p>
						</div>
					</div>
				</div>

				<div class="save" id="transactionAddButton">
					发送召车信息
				</div>
			</div>
		</div>
		
		
		
		<div class="bigbox" id="popupMapDiv">
   			<div class="mapbox" id="map"></div>
   			
   			<div class="ssbox">
      			<div class="srk">
         			<input type="text" id="keyWord" type="text" />
         			<div id="submitSearch"></div>
      			</div>
      			<div class="csure" onclick="addPosition()">确定</div>
      			<div class="chacha" onclick="closeDiv()"></div>
   			</div>
   			
   			<div class="neirbox" id="searchFuzzy"></div>
		</div>

		<div id="mask"></div>

		<!-- 选择车辆 -->
		<div class="bigbox"  id="carChooseDiv">
  			<div class="top">
    			<div class="zb">
    				<span>周边多少米</span>
    				<input type="text" id="radius" type="text" value=1000 />
    			</div>
    
     			<div class="zb zb02">
    				<span>空重车状态</span>
    				<input id="kz" value="全部">
    				<input type="hidden" id="kzstate" value="">
					<div class="xiala">
				       <div class="" onClick="dj(this);">全部</div>
				       <div class="0" onClick="dj(this);">空车</div>
				       <div class="1" onClick="dj(this);">重车</div>
    				</div>
    				<div class="aa"></div>
    			</div>
			    <div class="wbox">
			       	<div id="carSearch">搜索</div>
			       	<div onclick="addCarList()">确定</div>
			    </div>
    			<div class="chacha" onclick="closeDiv()"></div>
 			</div>
 
			<div class="blue">
			    <div class="firsta">车牌号</div>
			    <div>在线状态</div>
			    <div>ACC状态</div>
			    <div>速度(km/h)</div>
			    <div class="lasta">当前位置</div>
			</div>
 
 			<div class="biaogbox" id="carList"></div>
 		</div>
 
 		<div class="xian"></div>
		
		
	</body>


	<!-- 高德 -->
	<script type="text/javascript" src="<%=GAODE_MAP_URL%>"></script>
	<script type="text/javascript"
		src="<%=path%>/resource/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="<%=BAIDU_MAP_URL%>"></script>
	<script type="text/javascript"
		src="<%=path%>/resource/js/Map.js?v=<%=ver%>"></script>
	<script type="text/javascript" src="<%=path%>/resource/js/date.js"></script>
	<script type="text/javascript"
		src="<%=path%>/taxi/inbound/inboundcus.js"></script>
</html>