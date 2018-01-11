<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
    <title>审批流程列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script language="javascript" src="${pageContext.request.contextPath}/script/jquery.js"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/pageCommon.js" charset="utf-8"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/PageUtils.js" charset="utf-8"></script>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/pageCommon.css" />
    <script type="text/javascript">
	     
	    	 window.showModalDialog = function(url,obj,sFeatures){

	    		 sFeatures = sFeatures.replace(/dialogHeight/gi,"height");

	    		 sFeatures = sFeatures.replace(/dialogWidth/gi,"width"); 
	    		 sFeatures = sFeatures.replace(/dialogTop/gi,"top"); 
	    		 sFeatures = sFeatures.replace(/dialogLeft/gi,"left");
	    		 sFeatures = sFeatures.replace(/:/gi, "=");
	    		 sFeatures = sFeatures.replace(/;/gi, ",");
	    		 var newWindow = window.open(url,'', sFeatures);
	    		 return newWindow;
	    		 }
	     
    </script> 
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 审批流程管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
            	<td width="200px">流程名称</td>
				<td width="80px">最新版本</td>
                <td width="300px">说明</td>
                <td>相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
		
        <tbody id="TableData" class="dataContainer" datakey="processDefList">
        	<s:iterator value="list">
				<tr class="TableDetail1 template">
						<td>${name}&nbsp;</td>
						<td align="CENTER">${version }&nbsp;</td>
						<td>${description}&nbsp;</td>
						<td><a onClick="return delConfirm()" href="${pageContext.request.contextPath }/processDefin_delete.action?key=${key}">删除</a>
							<%-- <a href="javascript: window.showModalDialog('${pageContext.request.contextPath}/processDefin_showPng.action
							?pdId=${id }','','dialogHeight:500px;dialogWidth:800px');">查看流程图</a> --%>
							<a href="javascript: window.open ('${pageContext.request.contextPath}/processDefin_showPng.action
							?pdId=${id }','newwindow','height=500,width=800,top=100,left=300, z-look=yes,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no') ;">查看流程图</a>
							<%-- <a href="javascript: showProcessImage(${id})">查看流程图</a> --%>
							
						</td>
				</tr>
			</s:iterator>
        </tbody>
        
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <table border="0" cellspacing="0" cellpadding="10" align="left">
                <tr>
			        <td><div class="FuncBtn">
                            <div class=FuncBtnHead></div>
                            <div class=FuncBtnMemo><a href="${pageContext.request.contextPath}/processDefin_deployUI.action">部署流程定义文档</a></div>
                            <div class=FuncBtnTail></div>
                        </div></td>
                </tr>
			</table>
        </div>
    </div>
</div>

<div class="Description">
	说明：<br />
	1，列表显示的是所有流程定义（不同名称）的最新版本。<br />
	2，删除流程定义时，此名称的所有版本的流程定义都会被删除。<br />
</div>

</body>
</html>
