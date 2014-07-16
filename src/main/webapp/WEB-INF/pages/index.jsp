<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Home page</title>
<link href="resources/css/main.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div id="container">
<h1>Home page</h1>
<p>

<a href="${pageContext.request.contextPath}/flows/create.html">Create new Flow</a><br/>
<a href="${pageContext.request.contextPath}/flows.html">All Flows</a><br/>
<a href="${pageContext.request.contextPath}/users/create.html">Create new User</a><br/>
<a href="${pageContext.request.contextPath}/users.html">All Users</a><br/>
<a href="${pageContext.request.contextPath}/messages/create.html">Create new Message</a><br/>
<a href="${pageContext.request.contextPath}/messages.html">All Messages</a><br/>
<a href="${pageContext.request.contextPath}/relations/create.html">Create new Relation</a><br/>
<a href="${pageContext.request.contextPath}/relations.html">All Relations</a><br/>
</p>
</div>
</body>
</html>