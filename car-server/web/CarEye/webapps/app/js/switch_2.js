var  ifocus_btns;
var curScreen=0;
var  pic_yPos=[0,-225,-450,-675];
function $(id) { return document.getElementById(id); }
function  moveElement(elementId,left,top)
{
	var pic_list=document.getElementById(elementId);
	pic_list.style.left=left+"px";
	pic_list.style.top=top+"px";
}
//设置当前导航按钮和标题的样式
function classCurrent(iFocusBtnID,iFocusTxID,n){
	var iFocusBtns= $(iFocusBtnID).getElementsByTagName('li');
	var iFocusTxs = $(iFocusTxID).getElementsByTagName('li');
	iFocusBtns[n].className='current';
	iFocusTxs[n].className='current';
}
//设置导航按钮和标题未查看时的样式
function classNormal(iFocusBtnID,iFocusTxID){
	var iFocusBtns= $(iFocusBtnID).getElementsByTagName('li');
	var iFocusTxs = $(iFocusTxID).getElementsByTagName('li');
	for(var i=0; i<iFocusBtns.length; i++) {
		iFocusBtns[i].className='normal';
		iFocusTxs[i].className='normal';
	}
}
window.onload=function()
{
	ifocusBtns=document.getElementById("ifocus_btn").getElementsByTagName("ul")[0].getElementsByTagName("li");


		ifocusBtns[0].onmouseover=function()
		{
			moveElement("ifocus_piclist",0,pic_yPos[0]);
			classNormal('ifocus_btn','ifocus_tx');
			classCurrent('ifocus_btn','ifocus_tx',0);
			
		}

		
		ifocusBtns[1].onmouseover=function()
		{
			moveElement("ifocus_piclist",0,pic_yPos[1]);
			classNormal('ifocus_btn','ifocus_tx');
			classCurrent('ifocus_btn','ifocus_tx',1);
			
		}
		ifocusBtns[2].onmouseover=function()
		{
			moveElement("ifocus_piclist",0,pic_yPos[2]);
			classNormal('ifocus_btn','ifocus_tx');
			classCurrent('ifocus_btn','ifocus_tx',2);
		
		}
		ifocusBtns[3].onmouseover=function()
		{
			moveElement("ifocus_piclist",0,pic_yPos[3]);
			classNormal('ifocus_btn','ifocus_tx');
			classCurrent('ifocus_btn','ifocus_tx',3);
		}
	autoSwitch();
}

function autoSwitch()
{
		if(curScreen==4)
		{
			curScreen=0;
		}
		moveElement("ifocus_piclist",0,pic_yPos[curScreen]);
		classNormal('ifocus_btn','ifocus_tx');
		classCurrent('ifocus_btn','ifocus_tx',curScreen);
		curScreen++;
		setTimeout("autoSwitch()",2000);
}