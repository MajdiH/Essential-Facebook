<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>All Relations</title>
<link href="resources/css/main.css" rel="stylesheet" type="text/css"/>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
   
    $(document).ready(function() {
    	
		var deleteLink = $("a:contains('Delete')");
		var enableLink = $("a:contains('Enable')");
      
		$(deleteLink).click(function(event) {
    	  
			$.ajax({
				url: $(event.target).attr("href"),
			  	type: "DELETE",
			  	
			  	beforeSend: function(xhr) {
			  		xhr.setRequestHeader("Accept", "application/json");
			  		xhr.setRequestHeader("Content-Type", "application/json");
			  	},
			  	
			  	success: function(relation) {
			  		var respContent = "";
			  		var rowToDelete = $(event.target).closest("tr");
			  		
			  		rowToDelete.remove();
			  		
			  		respContent += "<span class='success'>Relation was deleted: [";
			  		respContent += relation.applicant + " : ";
			  		respContent += relation.receiver + " : ";
			  		respContent += relation.enabled + "]</span>";
			  		
			  		$("#sRelationFromResponse").html(respContent);   		
			  	}
			})
			event.preventDefault();
		});
		$(enableLink).click(function(event) {
	    	  
			$.ajax({
				url: $(event.target).attr("href"),
			  	type: "PUT",
			  	
			  	beforeSend: function(xhr) {
			  		xhr.setRequestHeader("Accept", "application/json");
			  		xhr.setRequestHeader("Content-Type", "application/json");
			  	},
			  	
			  	success: function(relation) {
			  		$('#relation-enabled-'+relation.id).html(relation.enabled);
			  	}
			})
  
			event.preventDefault();
		});
		
       
});   
</script>

</head>
<body>
<div id="container">
<h1>All Relations</h1>
<div>
<p>Here you can see a list of Relations:</p>
<div id="sRelationFromResponse"></div>
</div>
	<table border="1px" cellpadding="0" cellspacing="0">
	<thead>
	<tr>
	<th>id</th><th>Applicant</th><th>Receiver</th><th>Enabled</th><th>Actions</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="sRelation" items="${relations}">
	<tr>
	<td>${sRelation.id}</td>
	<td>${sRelation.applicant}</td>
	<td>${sRelation.receiver}</td>
	<td><div id="relation-enabled-${sRelation.id}">${sRelation.enabled}</div></td>
	<td>
	<a href="${pageContext.request.contextPath}/relations/enable/${sRelation.id}.json">Enable</a><br/>
	<a href="${pageContext.request.contextPath}/relations/delete/${sRelation.id}.json">Delete</a><br/>
	</td>
	</tr>
	</c:forEach>
	</tbody>
	</table>

<a href="${pageContext.request.contextPath}/index.html">Home page</a>
</div>
</body>
</html>