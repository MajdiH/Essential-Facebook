<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>All Flows</title>
<link href="resources/css/main.css" rel="stylesheet" type="text/css" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		var deleteLink = $("a:contains('Delete')");

		$(deleteLink).click(function(event) {

			$.ajax({
				url : $(event.target).attr("href"),
				type : "DELETE",

				beforeSend : function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},

				success : function(flow) {
					var respContent = "";
					var rowToDelete = $(event.target).closest("tr");

					rowToDelete.remove();

					respContent += "<span class='success'>Flow was deleted: [";
					respContent += flow.body + " : ";
					respContent += flow.created_at + " : ";
					respContent += flow.created_by + " : ";
					respContent += flow.likes + "]</span>";

					$("#sFlowFromResponse").html(respContent);
				}
			});
			event.preventDefault();

		});
		var likeLink = $("a:contains('Like')");
		$(likeLink).click(function(event) {

			$.ajax({
				url : $(event.target).attr("href"),
				type : "GET",

				beforeSend : function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},

				success : function(flow) {
					$("#likes-count-"+flow.id).html(flow.likes);
				}
			});

			event.preventDefault();
		});

	});
</script>

</head>
<body>
	<div id="container">
		<h1>All Flows</h1>
		<div>
			<p>Here you can see a list of Flows:</p>
			<div id="sFlowFromResponse"></div>
		</div>
		<table border="1px" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<th>id</th>
					<th>Body</th>
					<th>Created</th>
					<th>Author</th>
					<th>Likes</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="sFlow" items="${flows}">
					<tr>
						<td>${sFlow.id}</td>
						<td>${sFlow.body}</td>
						<td>${sFlow.created_at}</td>
						<td>${sFlow.created_by}</td>
						<td><div id="likes-count-${sFlow.id}">${sFlow.likes}</div></td>
						<td><a
							href="${pageContext.request.contextPath}/flows/like/${sFlow.id}.json">Like</a><br />
							<a
							href="${pageContext.request.contextPath}/flows/delete/${sFlow.id}.json">Delete</a><br />
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<a href="${pageContext.request.contextPath}/index.html">Home page</a>
	</div>
</body>
</html>