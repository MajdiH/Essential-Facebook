<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>All Users</title>
<link href="resources/css/main.css" rel="stylesheet" type="text/css"/>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
   
    $(document).ready(function() {
    	
		var deleteLink = $("a:contains('Delete')");
      
		$(deleteLink).click(function(event) {
    	  
			$.ajax({
				url: $(event.target).attr("href"),
			  	type: "DELETE",
			  	
			  	beforeSend: function(xhr) {
			  		xhr.setRequestHeader("Accept", "application/json");
			  		xhr.setRequestHeader("Content-Type", "application/json");
			  	},
			  	
			  	success: function(user) {
			  		var respContent = "";
			  		var rowToDelete = $(event.target).closest("tr");
			  		
			  		rowToDelete.remove();
			  		
			  		respContent += "<span class='success'>User was deleted: [";
			  		respContent += user.email + "]</span>";
			  		
			  		$("#sUserFromResponse").html(respContent);   		
			  	}
			});
  
			event.preventDefault();
		});
       
});   
</script>

</head>
<body>
<div id="container">
<h1>All Books</h1>
<div>
<p>Here you can see a list of Books:</p>
<div id="sUserFromResponse"></div>
</div>
	<table border="1px" cellpadding="0" cellspacing="0">
	<thead>
	<tr>
	<th>Email</th><th>Password</th><th>Name</th><th>Picture</th>
	<th>Introduction</th><th>Profession</th><th>City</th><th>Country</th>
	<th>Role</th><th>Created At</th>
	<th>Actions</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="sUser" items="${users}">
	<tr>
	<td>${sUser.email}</td>
	<td>${sUser.password}</td>
	<td>${sUser.name}</td>
	<td>${sUser.picture}</td>
	<td>${sUser.introduction}</td>
	<td>${sUser.profession}</td>
	<td>${sUser.city}</td>
	<td>${sUser.country}</td>
	<td>${sUser.role_id}</td>
	<td>${sUser.created_at}</td>
	<td>
	<a href="${pageContext.request.contextPath}/users/edit/${sUser.id}.html">Edit</a><br/>
	<a href="${pageContext.request.contextPath}/users/delete/${sUser.id}.json">Delete</a><br/>
	</td>
	</tr>
	</c:forEach>
	</tbody>
	</table>

<a href="${pageContext.request.contextPath}/index.html">Home page</a>
</div>
</body>
</html>