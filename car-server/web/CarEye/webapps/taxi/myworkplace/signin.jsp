<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/include.jsp" %>
<head>
<meta charset="utf-8">
<title>我的工作台</title>
<link rel="stylesheet" href="style.css">
</head>



<body >
<script type="text/javascript" src="<%=path%>/app/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript">

	$.ajax({
		type : 'POST',
	    url:window.BIZCTX_PATH +'/workplacejson/loadMyData.action',
	    dataType : 'json',
	    success: function(data){
	        var d = data.data;
			  	document.getElementById('bloccount').innerHTML=d.bloccount;
			  	document.getElementById('carcount').innerHTML=d.carcount;
			  	document.getElementById('drivercount').innerHTML=d.drivercount;
			  	
			  	document.getElementById('onlinecar').innerHTML=d.onlinecar;
			  	document.getElementById('unonlinecar').innerHTML=d.unonlinecar;
			  	document.getElementById('onlinecount').innerHTML=d.onlinecount;	
			  	document.getElementById('zhongcar').innerHTML=d.zhongcar;
			  	document.getElementById('kongcar').innerHTML=d.kongcar;
			  	document.getElementById('kzcount').innerHTML=d.kzcount;	
			  	
			  	
			  	document.getElementById('dialordercount').innerHTML=d.dialordercount;
			  	document.getElementById('custelcount').innerHTML=d.custelcount;
			  	document.getElementById('operaincome').innerHTML=d.operaincome;	
			  	document.getElementById('alipaymoney').innerHTML=d.alipaymoney;
			  	document.getElementById('alipaycount').innerHTML=d.alipaycount;
			  	document.getElementById('weixinmoney').innerHTML=d.weixinmoney;	
			  	document.getElementById('weixincount').innerHTML=d.weixincount;
			  	document.getElementById('cashpaymoney').innerHTML=d.cashpaymoney;	
			  	document.getElementById('cashpaycount').innerHTML=d.cashpaycount;
			  	
			  	
			  	document.getElementById('complaintcount').innerHTML=d.complaintcount;
			  	document.getElementById('cusgoodcount').innerHTML=d.cusgoodcount;
			  	document.getElementById('cussecondarycount').innerHTML=d.cussecondarycount;
			  	document.getElementById('cuspoorcount').innerHTML=d.cuspoorcount;
	    }
	});

	 
</script>

<div class="bigbj">

<!--红色-->
  <div class="redkk redkk01">
   <div class="onebox">
      <div class="redbox cpl"><P>基础</P><P>数据</P></div>
      
      <div class="qiyq qiyq02">
         <p>企业总数(个)</p>
         <p class="p2" id="bloccount"></p>
      </div>
      
            <div class="qiyq">
         <p>车辆总数(辆)</p>
         <p class="p2" id="carcount"></p>
      </div>
      
            <div class="qiyq">
         <p>驾驶员总数(人)</p>
         <p class="p2" id="drivercount"></p>
      </div>
   </div>
   
   <div class="xian"></div>
 </div>
 
 <!--绿色-->
   <div class="redkk">
   <div class="onebox">
      <div class="greenbox cpl"><P>在线</P><P>统计</P></div>
      
      <div class="zxcl">
        <p class="p1">在线车辆 <br>数量</p>
        <p class="p2"><span id="onlinecar"></span>辆</p>
      </div>
      
      
      <div class="zxcl zxcl02">
        <p class="p1">离线车辆 <br>数量</p>
        <p class="p2"><span id="unonlinecar"></span>辆</p>
      </div>
      
      
      <div class="qq">
         <div class="zxl" >在线率<br><span id="onlinecount"></span>%</div>
         <div class="zxl zcl" >重车率<br><span id="kzcount"></span>%</div>
      </div>
      
     <div class="zcsl">
        <p class="p1">重车<br>数量</p>
        <p class="p2" ><span id="zhongcar"></span> 辆</p>
     
     </div>
     
     
          <div class="zcsl zcsl02">
        <p class="p1">空车<br>数量</p>
        <p class="p2"><span id="kongcar"></span> 辆</p>
     
     </div>
     
     
     </div>
     
     
     
     
     
   
   <div class="xian"></div>
 </div>
 
 
  <!--蓝色-->
   <div class="redkk">
   <div class="onebox">
      <div class="bluebox cpl"><P>运营</P><P>统计</P></div>
      
    <div class="dzdd dzdd01">
       <p class="p1">电召订单数</p>  
       <p class="p2" ><span id="dialordercount"></span> 个</p>
    </div>
    
    <div class="dzdd">
       <p class="p1">客户来电数</p>  
       <p class="p2" ><span id="custelcount"></span> 个</p>
    </div>
    
    <div class="dzdd">
       <p class="p1">运营总收入</p>  
       <p class="p2" ><span id="operaincome"></span> 元</p>
    </div>



    <div class="zhif zhif02">
       <p class="p1" >支付<br>笔数(笔) <br><span id="alipaycount"></span></p>
       <div></div>
       <p class="p2" >支付宝<br>支付金额(元)<br>￥<span id="alipaymoney"></span></p>
    
    </div>
    
    <div class="zhif zhif03">
       <p class="p1" >支付<br>笔数(笔) <br><span id="weixincount"></span></p>
       <div></div>
       <p class="p2" >微信<br>支付金额(元)<br>￥<span id="weixinmoney"></span></p>
    
    </div>
    
    <div class="zhif  zhif04">
       <p class="p1">支付<br>笔数(笔) <br><span id="cashpaycount"></span></p>
       <div></div>
       <p class="p2">现金<br>支付金额(元)<br>￥<span id="cashpaymoney"></span></p>
    
    </div>
    
 </div>
   
   <div class="xian"></div>
   
 </div>
 
 
  
  <!--紫色-->
 
   <div class="redkk">
   <div class="onebox">
      <div class="purbox cpl"><P>服务</P><P>质量</P></div>
      
      <div class="pingj">
        <p class="p1" id="cusgoodcount"></p>
        <p>客户好评数</p>
      </div>
      
      <div class="pingj pingj02">
        <p class="p1" id="cussecondarycount"></p>
        <p>客户中评数</p>
      </div>
      
      <div class="pingj  pingj03">
        <p class="p1" id="cuspoorcount"></p>
        <p>客户差评数</p>
      </div>
      
      
      <div class="pingj  pingj04">
        <p class="p1" id="complaintcount"></p>
        <p>投诉数量</p>
      </div>
      
      
      
 
   </div>
   

 </div>


</div>

</body>
</html>
