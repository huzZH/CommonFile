<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看当前流程图</title>
</head>
<body>
<!-- 1.获取到规则流程图 -->
<img style="position: absolute;top: 0px;left: 0px;" src="applicationAction_viewImage.action?deploymentId=<s:property value='#deploymentId'/>&imageName=<s:property value='#imageName'/>">

<!-- 2.根据当前活动的坐标，动态绘制DIV -->
<div style="position: absolute;border:1px solid red;top:<s:property value="#acs.y"/>px;left: <s:property value="#acs.x-1"/>px;width: <s:property value="#acs.width-1"/>px;height:<s:property value="#acs.height"/>px;   "></div></body>
</html>