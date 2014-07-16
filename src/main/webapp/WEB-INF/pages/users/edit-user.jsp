<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Edit User</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(
	    function () {

	        $('#editUserForm').submit(
	            function (event) {

	                var name = $('#name').val();
	                var introduction = $('#introduction').val();
	                var profession = $('#profession').val();
	                var city = $('#city').val();
	                var country = $('#country').val();


	                var json = {
	                    "name": name,
	                    "introduction": introduction,
	                    "profession": profession,
	                    "city": city,
	                    "country": country
	                };

	                $.ajax({
	                    url: $("#editUserForm").attr("action"),
	                    data: JSON.stringify(json),
	                    headers: {
	                        'Content-Type': 'multipart/form-data'
	                    },
	                    type: "PUT",

	                    beforeSend: function (xhr) {
	                        xhr.setRequestHeader("Accept", "application/json");
	                        xhr.setRequestHeader("Content-Type", "application/json");
	                        $(".error").remove();
	                    },
	                    success: function (user) {
	                        var respContent = "";

	                        respContent += "<span class='success'>User was edited: [";
	                        respContent += user.name + " : ";
	                        respContent += user.introduction + " : ";
	                        respContent += user.profession + " : ";
	                        respContent += user.city + " : ";
	                        respContent += user.country + "]</span>";

	                        $("#sUserFromResponse").html(respContent);
	                    },
	                    error: function (jqXHR,
	                        textStatus,
	                        errorThrown) {
	                        var respBody = $.parseJSON(jqXHR.responseText);
	                        var respContent = "";

	                        respContent += "<span class='error-main'>";
	                        respContent += respBody.message;
	                        respContent += "</span>";

	                        $("#sUserFromResponse").html(respContent);

	                        $.each(respBody.fieldErrors, function (index, errEntity) {
	                                    var tdEl = $("." + errEntity.fieldName + "-info");
	                                    tdEl
	                                        .html("<span class=\"error\">" + errEntity.fieldError + "</span>");
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
		<h1>Edit User</h1>
		<div id="sUserFromResponse">
			<p>Here you can edit User info:</p>
			<div id="sUserFromResponse"></div>
		</div>
		<form action="${pageContext.request.contextPath}/users/edit/${sUser.id}/upload" method="post" enctype="multipart/form-data">
		  File: <input type="file" name="profile-picture"/><br/>
		  <button type="submit">Upload</button>
		</form>
		<form:form id="editUserForm" method="PUT" commandName="sUser"
			action="${pageContext.request.contextPath}/users/edit/${sUser.id}.json">
			<table>
				<tbody>
					<tr>
						<td>Name:</td>
						<td><form:input path="name" /></td>
						<td class="name-info"></td>
					</tr>
					<tr>
						<td>introduction:</td>
						<td><form:textarea path="introduction" /></td>
						<td class="introduction-info"></td>
					</tr>
					<tr>
						<td>profession:</td>
						<td><form:input path="profession" /></td>
						<td class="profession-info"></td>
					</tr>
					<tr>
						<td>website:</td>
						<td><form:input path="website" /></td>
						<td class="website-info"></td>
					</tr>
					<tr>
						<td>city:</td>
						<td><form:input path="city" /></td>
						<td class="city-info"></td>
					</tr>
					<tr>
						<td>country:</td>
						<td><form:input path="country" /></td>
						<td class="country-info"></td>
					</tr>
					<tr>
						<td><input type="submit" value="Edit" /></td>
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