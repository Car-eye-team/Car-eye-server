<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<head>
<title>软电话DEMO程序</title>
</head>
<style>
body{font-family:Verdana;font-size:11px;color:#333;}
#Btstyle{[position:absolute;left:20;}
#Spstyle{[position:absolute;left:20;top:43;width:800px;height:145px;border:1px solid #007dd3;}
#ICCstyle{[position:absolute;left:20;top:208;width:400px;height:400px;border:1px solid #007dd3;}
#ACCstyle{[position:absolute;left:435;top:208;width:300px;height:500px;border:1px solid #007dd3;}
#Infostyle{[position:absolute;left:630;top:43;width:110px;height:160px;border:1px solid #007dd3;}
.title{width:100%;background:#007dd3;height:18px;color:#fff;cursor: move;} 
.btn {BORDER-RIGHT: #7b9ebd 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #7b9ebd 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#cecfde); BORDER-LEFT: #7b9ebd 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #7b9ebd 1px solid}
</style>

<script>
var move=false; 
function StartDrag(obj) 
{
if(event.button==1&&event.srcElement.tagName.toUpperCase()=="DIV")
{
obj.setCapture();
obj.style.background="#999";
move=true;
} 
}

function Drag(obj) 
{
if(move)
{
var oldwin=obj.parentNode;
oldwin.style.left=event.clientX-100;
oldwin.style.top=event.clientY-10;
}

}

function StopDrag(obj)
{
obj.style.background="#007dd3";
obj.releaseCapture();
move=false;
} 

</script> 
<body>
    <div id="Btstyle">
    <input class="btn" id="btnService"  type="button" value="启动软电话Service" /><input class="btn" id="btnInit" type="button" value="初始化SP" /><input class="btn" id="btnInitICC" type="button" value="初始化ICC" /><input class="btn" id="btnInitACC"  type="button" value="初始化ACC" /><input class="btn" id="btnUnInit" type="button" value="销毁ALL" />
    </div>  
    <br>
    <div id="Spstyle">
    <div class="title" onMousedown="StartDrag(this)" onMouseup="StopDrag(this)" onMousemove="Drag(this)" >软电话
    <OBJECT id = "objSp" style="Z-INDEX: 101; LEFT: 24px; WIDTH: 800px; HEIGHT: 145px" classid=clsid:5E57CDEB-8FBB-4AB5-9050-3A307E5BCFBD></OBJECT>
   	</div>
    </div>
   	<div id="ICCstyle">
   	<div class="title" onMousedown="StartDrag(this)" onMouseup="StopDrag(this)" onMousemove="Drag(this)" >ICC
		<object id="objICC" style="Z-INDEX: 101; LEFT: 24px; WIDTH: 400px; HEIGHT: 400px" classid=clsid:E1D03AC4-94A5-466A-8FFC-DEDBCDC8446A>
		</object>
	  </div>
	  </div>
	  <div id="ACCstyle">
	  <div class="title" onMousedown="StartDrag(this)" onMouseup="StopDrag(this)" onMousemove="Drag(this)" >ACC
		<object id="objAcc" style="Z-INDEX:101; LEFT:724px; WIDTH:300; HEIGHT: 500px" classid="clsid:0D640616-583B-40C1-8981-E08602FA1460" >
		</object>
	  </div>
    </div>
    
    <div id="Infostyle">
	  <div class="title" onMousedown="StartDrag(this)" onMouseup="StopDrag(this)" onMousemove="Drag(this)" >软电话状态信息
	  </div>
	  <div id=InfoText>
	  </div>
    </div>
    
    <script language=javascript type="text/javascript">
    	
    var sp;
    var ICC;
    var Acc;
    var btn_Init;
    var btn_UnInit;
    var btn_Service;
    var btn_InitICC;
    var btn_InitACC;


    document.body.onload = function()
    {
        sp = document.getElementById("objSp");
        ICC = document.getElementById("objICC");
        Acc = document.getElementById("objAcc");
        InfoSp = document.getElementById("InfoText");

        
        sp.attachEvent('OnError',Error);
        sp.attachEvent('OnInService',InService);
    	  sp.attachEvent('OnInBoundCall',inBoundCall); 
    	
    	  btn_Init = document.getElementById("btnInit");
        btn_Init.onclick = btnInit_Click;
        btn_UnInit = document.getElementById("btnUnInit");
        btn_UnInit.onclick = btnUnInit_Click;
        btn_Service = document.getElementById("btnService");
        btn_Service.onclick = btnService_Click;
        btn_InitICC = document.getElementById("btnInitICC");
        btn_InitICC.onclick = btnInitICC_Click;
        btn_InitACC = document.getElementById("btnInitACC");
        btn_InitACC.onclick = btnInitACC_Click;
        
        var NumMail;
        
        NumMail=0;
       
     
                  
    }
    
    //初始化服务
    function btnInit_Click()
    {
      var retCode = sp.SPInit('101', 1);
      if (retCode == 1) {
      	
      InfoSp.innerText="软电话启动成功";	
      	
      }
      else
      {
      	
      InfoSp.innerText="软电话启动失败";	
      
      }
    }
    
   function InService()
    {    
    	
    	 InfoSp.innerText="软电话服务启动成功";
    
    }
    
    
    //反初始化

    function btnUnInit_Click()
    {
       sp.SPUnInit();
       ICC.ICCUnInit();
       Acc.UnRegister();
      
    }
    // 初始化服务
    function btnService_Click()
    {
       InfoSp.innerText="开始启动软电话服务，请稍候......";
       sp.StartService();
      
    }
    // 初始化ICC
    function btnInitICC_Click()
    {
       var retICC=ICC.ICCInit("1234567");
       
       if (retICC == 1) {
      	
       InfoSp.innerText="ICC启动成功";	
      	
       }
       else
       {
      	
       InfoSp.innerText="ICC启动失败";	
      
       }
      
    }
    // 初始化ACC    
    function btnInitACC_Click()
    {

       var retAcc = Acc.Register("60000002");
       
       if (retAcc == 1) {
      	
       InfoSp.innerText="ACC启动成功";	
      	
       }
       else
       {
      	
       InfoSp.innerText="ACC启动失败";	
      
       }
    }
    
    
    //InBoundCallEvent  //来电弹屏事件
  
    function inBoundCall(customercallid, callid, MediaType, customercallno, serviceno, currqueue, trunkno)      //来电弹屏事件
    {
    	
      alert(MediaType+'|=='+customercallid+'|=='+customercallno+'|=='+serviceno+'|=='+currqueue+'|=='+callid);
      
      
     // NumMail =  NumMail + 1;
      //alert("NumMail:" + NumMail);
      //MediaType=1，ICC处理
      if (MediaType=='1') 
      {
      	 InfoSp.innerText="互联网呼叫来电";
         ICC.ICCAddCallCtrl(callid);

         ICC.ICCAnswer(callid);
      }
      //MediaType=2,短信处理
      //if (MediaType ==2)
      //{
      //	
      //}
      //MediaType=3,邮件处理
      if (MediaType ==3)
      {
         InfoSp.innerText="邮件呼叫来电";
      }
      else 
      {
         InfoSp.innerText="电话呼叫来电";
      }

    }
    
    
    function Error(ErrorCode, ErrorStr)
    {
       InfoSp.innerText = "错误码:"+ ErrorCode;

    }
    
    </script>
</body>
</html>
