var cmsv6_demo;
var devid;
var ServerIP = "120.31.131.73";
var ServerPort = "6603";
//var ServerIP = "192.168.1.234";
//var ServerPort = "6603";

function Int(){
/*     cmsv6_demo = document.getElementById("cmsv6");   
     cmsv6_demo.ServerIP="121.197.0.50";
     cmsv6_demo.ServerPort = "6605";
     
      cmsv6_demo.attachEvent("RecSearchEvent", MyRecSearchEvent);  */
     
    //alert(MyRecSearchEvent);
    //cmsv6_demo.addEventListener("RecSearchEvent", MyRecSearchEvent);
    //alert(cmsv6.RecSearchEvent);
    //cmsv6.attachEvent("RecSearchEvent", MyRecSearchEvent); 
    //alert('2');
}

//开始视频
function StartShow() {
    try{  
        cmsv6_demo.StartVideo(devid,0,0);
        cmsv6_demo.StartVideo(devid,1,1);
        cmsv6_demo.StartVideo(devid,2,2);
        cmsv6_demo.StartVideo(devid,3,3);	
        cmsv6_demo.SetViewTitle(0,"通道一");
	    cmsv6_demo.SetViewTitle(1,"通道二");
	    cmsv6_demo.SetViewTitle(2,"通道三");
	    cmsv6_demo.SetViewTitle(3,"通道四");
	    cmsv6_demo.StopSubscribeGps();   
       }catch(e){
          alert(e.message);
       }
}

//停止视频
function StopShow() {
	try{            
     //cmsv6_demo = document.getElementById("cmsv6");   
      cmsv6_demo.StopVideo(0);
      cmsv6_demo.StopVideo(1);
      cmsv6_demo.StopVideo(2);
      cmsv6_demo.StopVideo(3);   
             
    }catch(e){
   		 alert(e.message);
    }
}

//打开声音
function OSound() {
   try{            
    // cmsv6 = document.getElementById("cmsv6");   
      cmsv6_demo.OpenSound(0);
      cmsv6_demo.OpenSound(1);
      cmsv6_demo.OpenSound(2);
      cmsv6_demo.OpenSound(3);   
             
    }catch(e){
   	   alert(e.message);
    }
}

//关闭声音
function CSound() {
	try{            
     //cmsv6_demo = document.getElementById("cmsv6");   
            
      cmsv6_demo.CloseSound();
   
             
    }catch(e){
    	alert(e.message);
    }
}

//抓拍图片
function CapturePicture(nWindow) {
	try{        
        cmsv6_demo.CapturePicture(nWindow);
    }catch(e){
    	alert(e.message);
    }
}

//抓拍所有通道的图片
function CaptureAllWindowPicture () {
	try{            
        cmsv6_demo.CaptureAllWindowPicture();
    }catch(e){
    	alert(e.message);
    }
}
 
//开始录像
function StartRecord(Channel) {
	try{            
    	cmsv6_demo.StartRecord(devid,Channel);
    	alert("开始录像中");
    }catch(e){
    	alert(e.message);
    }
}
  
//停止录像
function StopRecord(Channel) {
	try{            
        cmsv6_demo.StopRecord(devid,Channel);
        alert("录像结束");
    }catch(e){
    	alert(e.message);
    }
}

function FullScreen(){
  // cmsv6_demo = document.getElementById("cmsv6");   
	cmsv6_demo.FullScreen();
}

/**
 * 视频搜索
 * @param {} lChannelNumber
 * @param {} lFileType
 * @param {} nStartTime
 * @param {} nEndTime
 */
function vedioPlaybackSearchFile(lChannelNumber,lFileType,nStartTime,nEndTime){
    try{
        //var cmsv6 = document.getElementById("cmsv6");	
        cmsv6_demo.StopRecSearch();
        cmsv6_demo.StartRecSearch(devid,lChannelNumber,-1,nStartTime,nEndTime,1,lFileType);
    }catch(e){
   	    alert(e.message);
    }
}

/**开始远程回放录像文件
 * 参数：BSTR strFile-文件路径
	DATE nFileStartTime-文件开始时间(COleDateTime::m_dt)
	DATE nFileEndTime-文件结束时间(COleDateTime::m_dt)
	BSTR strDevIDNO-设备IDNO
	long nChannel-录像通道号
	long nFileLen-文件长度
	long nFileType文件类型
	#define GPS_FILE_ATTRIBUTE_JPEG		1//图片
	#define GPS_FILE_ATTRIBUTE_RECORD	2//录像
	long nLocation-文件存储位置
	#define GPS_FILE_LOCATION_DEVICE			1	//设备
	#define GPS_FILE_LOCATION_STOSVR			2	//存储服务器
	//客户端3被用做本地搜索，定义出现重复，3先不用
	#define GPS_FILE_LOCATION_DOWNSVR			4		//下载服务器录像搜索
	long nSvrID-服务器ID
	DATE nPlayStartTime-开始播放时间(COleDateTime::m_dt)
	DATE nPlayEndTime-结束播放时间(COleDateTime::m_dt)
	boolean bPlayOnlyIFrame-是否只播放I帧
	返回值：成功返回0，否则失败
 */
function StartPlaybackRec(lChannelNumber,lFileType,nStartTime,nEndTime){
	try{
		var strFile ="";
		var nFileLen ="";
		var nSvrID ="";
		
		cmsv6_demo.StartPlaybackRec(strFile,nStartTime,nEndTime,devid, lChannelNumber, nFileLen, 
		lFileType, 1,nSvrID, nStartTime, nEndTime,false);
	}catch(e){
   	    alert(e.message);
    }
}

/**
 * 参数：BSTR strDevIDNO-设备IDNO
long nChannel-设备通道号
BSTR strFile-录像文件路径
long nFileLen-文件长度
DATE nFileStartTime-文件开始时间(COleDateTime::m_dt)
DATE nFileEndTime-文件结束时间(COleDateTime::m_dt)
long nLocation-录像文件存储位置
long nSvrID-服务器ID
long nFileType-文件类型
#define GPS_FILE_TYPE_NORMAL	0 //正常录像
#define GPS_FILE_TYPE_ALARM	1 //报警录像
long nFileAttr-文件属性
#define GPS_FILE_ATTRIBUTE_JPEG		1//图片
#define GPS_FILE_ATTRIBUTE_RECORD	2//录像
BSTR strFilePath-保存路径
 * @param {} lChannelNumber
 * @param {} lFileType
 * @param {} nStartTime
 * @param {} nEndTime
 */
function StartDownFile(lChannelNumber,lFileType,nStartTime,nEndTime){
	try{
		var strFile ="";
		var nFileLen ="";
		var nSvrID ="";
		
		cmsv6_demo.StartPlaybackRec(devid,lChannelNumber,strFile,nFileLen, nStartTime, nEndTime, 
		1,nSvrID,-1, lFileType, "F:\\vedio");
	}catch(e){
   	    alert(e.message);
    }
}

/**
 * 录像搜索回调方法
	strFile:录像文件路径
	nStartTime:文件开始时间(COleDateTime::m_dt)
	nEndTime:文件结束时间(COleDateTime::m_dt)
	nFileLen:文件长度
	nFileType:文件类型
	#define GPS_FILE_TYPE_NORMAL	0 //正常录像
	#define GPS_FILE_TYPE_ALARM	1 //报警录像
	nSvrID:服务器ID
	nLocation:存储的位置
	nChannel:通道
 */
function RecSearchEvent(strFile, nStartTime,nEndTime,nFileLen, 
 	nFileType, nSvrID, nLocation,nChannel){
 	
 	//调用录像播放方法
 	cmsv6_demo.StartPlaybackRec(strFile,nStartTime,nEndTime,devid, nChannel, nFileLen, 
		nFileType, nLocation,nSvrID, nStartTime, nEndTime,false);
 }






