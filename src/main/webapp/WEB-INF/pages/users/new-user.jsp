<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Create new User</title>
<link href="../resources/css/main.css" rel="stylesheet" type="text/css"/>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
   
    $(document).ready(function() {
      
      $('#newUserForm').submit(function(event) {
    	  
    	  var email = $('#email').val();
    	  var password = $('#password').val();
    	  var name = $('#name').val();
    	  
    	  var json = { "email" : email, "password" : password, "name" : name};
    	  
        $.ajax({
        	url: $("#newUserForm").attr( "action"),
        	data: JSON.stringify(json),
        	type: "POST",
        	
        	beforeSend: function(xhr) {
        		xhr.setRequestHeader("Accept", "application/json");
        		xhr.setRequestHeader("Content-Type", "application/json");
        		$(".error").remove();
        	},
        	success: function(user) {
        		var respContent = "";
		  		respContent += "<span class='success'>User was created: [";
		  		respContent += user.email + "]</span>";
        		
        		$("#sUserFromResponse").html(respContent);   		
        	},
        	error: function(jqXHR, textStatus, errorThrown) {
        		var respBody = $.parseJSON(jqXHR.responseText);
        		var respContent = "";
        		
        		respContent += "<span class='error-main'>";
        		respContent += respBody.message;
        		respContent += "</span>";
        		
        		$("#sUserFromResponse").html(respContent);
        		
        		$.each(respBody.fieldErrors, function(index, errEntity) {
        			var tdEl = $("."+errEntity.fieldName+"-info");
        			tdEl.html("<span class=\"error\">"+errEntity.fieldError+"</span>");
        		});
        	}
        });
         
        event.preventDefault();
      });
       
    });   
  </script>
</head>
<body>
<div id="container">
<h1>Create new User</h1>
<div>
<p>Here you can create new Book:</p>
<div id="sUserFromResponse"></div>
</div>
<form:form id="newUserForm" action="${pageContext.request.contextPath}/users/create.json" commandName="sUser">
<table>
<tbody>
<tr>
<td>Name:</td>
<td><form:input path="name" /></td>
<td class="name-info"></td>
</tr>
<tr>
<td>Email:</td>
<td><form:input path="email" /></td>
<td class="email-info"></td>
</tr>
<tr>
<td>Password:</td>
<td><form:input path="password" /></td>
<td class="password-info"></td>
</tr>

<tr>
<td><input type="submit" value="Create" /></td>
<td></td>
<td></td>
</tr>
</tbody>
</table>
</form:form>
<a href="${pageContext.request.contextPath}/index.html">Home page</a>
</div>
</body>
</html>