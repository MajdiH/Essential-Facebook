<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Create new Flow</title>
<link href="../resources/css/main.css" rel="stylesheet" type="text/css"/>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
   
    $(document).ready(function() {
      
      $('#newFlowForm').submit(function(event) {
    	  
    	  var body = $('#body').val();
    	  var created_by = $('#created_by').val();
    	  var json = { "body" : body, "created_by" : created_by};
    	  
        $.ajax({
        	url: $("#newFlowForm").attr( "action"),
        	data: JSON.stringify(json),
        	type: "POST",
        	
        	beforeSend: function(xhr) {
        		xhr.setRequestHeader("Accept", "application/json");
        		xhr.setRequestHeader("Content-Type", "application/json");
        		$(".error").remove();
        	},
        	success: function(flow) {
        		var respContent = "";
		  		respContent += "<span class='success'>Flow was created: [";
		  		respContent += flow.body + " : ";
		  		respContent += flow.created_at + " : ";
		  		respContent += flow.created_by + " : ";
		  		respContent += flow.likes + "]</span>";
        		
        		$("#sFlowFromResponse").html(respContent);   		
        	},
        	error: function(jqXHR, textStatus, errorThrown) {
        		var respBody = $.parseJSON(jqXHR.responseText);
        		var respContent = "";
        		
        		respContent += "<span class='error-main'>";
        		respContent += respBody.message;
        		respContent += "</span>";
        		
        		$("#sFlowFromResponse").html(respContent);
        		
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
<h1>Create new Flow</h1>
<div>
<p>Here you can create new Flow:</p>
<div id="sFlowFromResponse"></div>
</div>
<form:form id="newFlowForm" action="${pageContext.request.contextPath}/flows/create.json" commandName="sFlow">
<table>
<tbody>
<tr>
<td>Body:</td>
<td><form:input path="body" /></td>
<td class="body-info"></td>
</tr>
<tr>
<td><input type="hidden" id="created_by" value="1" /><input type="submit" value="Create" /></td>
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