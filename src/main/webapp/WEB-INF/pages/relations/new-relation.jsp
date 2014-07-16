<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Create new Relation</title>
<link href="../resources/css/main.css" rel="stylesheet" type="text/css"/>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
   
    $(document).ready(function() {
      
      $('#newRelationForm').submit(function(event) {
    	  
    	  var applicant = $('#applicant').val();
    	  var receiver = $('#receiver').val();
    	  var json = { "applicant" : applicant, "receiver" : receiver};
    	  
        $.ajax({
        	url: $("#newRelationForm").attr( "action"),
        	data: JSON.stringify(json),
        	type: "POST",
        	
        	beforeSend: function(xhr) {
        		xhr.setRequestHeader("Accept", "application/json");
        		xhr.setRequestHeader("Content-Type", "application/json");
        		$(".error").remove();
        	},
        	success: function(relation) {
        		var respContent = "";
		  		respContent += "<span class='success'>Relation was created: [";
		  		respContent += relation.applicant + " : ";
		  		respContent += relation.receiver + " : ";
		  		respContent += relation.enabled + "]</span>";
        		
        		$("#sRelationFromResponse").html(respContent);   		
        	},
        	error: function(jqXHR, textStatus, errorThrown) {
        		var respBody = $.parseJSON(jqXHR.responseText);
        		var respContent = "";
        		
        		respContent += "<span class='error-main'>";
        		respContent += respBody.message;
        		respContent += "</span>";
        		
        		$("#sRelationFromResponse").html(respContent);
        		
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
<h1>Create new Relation</h1>
<div>
<p>Here you can create new Relation:</p>
<div id="sRelationFromResponse"></div>
</div>
<form:form id="newRelationForm" action="${pageContext.request.contextPath}/relations/create.json" commandName="sRelation">
<table>
<tbody>
<tr>
<td>Applicant:</td>
<td><form:input path="applicant" /></td>
<td class="applicant-info"></td>
</tr>
<tr>
<td>Receiver:</td>
<td><form:input path="receiver" /></td>
<td class="receiver-info"></td>
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