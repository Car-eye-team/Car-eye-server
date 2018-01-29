<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/include_login.jsp" %>

<head>
<title>柱状图</title>

</head>

<body>
	<div id="main" style="height:400px"></div>
</body>

<script src="<%=path%>/resource/echarts-2.2.7/dist/echarts.js"></script>

<script type="text/javascript">
        // 路径配置
        require.config({
            paths: {
                echarts: window.BIZCTX_PATH + '/resource/echarts-2.2.7/dist'
            }
        });
        
        // 使用
        require(
            [
                'echarts',
                'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main')); 
                
                var option = {
                    tooltip: {
                        show: true
                    },
                    legend: {
                        data:['销量']
                    },
                    xAxis : [
                        {
                            type : 'category',
                            data : ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value'
                        }
                    ],
                    series : [
                        {
                            "name":"销量",
                            "type":"bar",
                            "data":[5, 20, 40, 10, 10, 20]
                        }
                    ]
                };
        
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );
  </script>

</html>
