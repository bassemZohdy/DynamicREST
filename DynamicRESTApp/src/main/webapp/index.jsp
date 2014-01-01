<%-- #-------------------------------------------------------------------------------
# Copyright (c) 2013 Bassem Reda Zohdy bassem.zohdy@gmail.com.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the GNU Public License v3.0
# which accompanies this distribution, and is available at
# http://www.gnu.org/licenses/gpl.html
# 
# Contributors:
#     Bassem Reda Zohdy bassem.zohdy@gmail.com - initial API and implementation
#-------------------------------------------------------------------------------
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dynamic REST</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<h1>Dynamic REST</h1>

	<p>This application is for generating resource types dynamically,
		you can use it to create a resource name and manage fields within this
		resource, then you can use this resource as a REST
		services{list,get,add,update and delete}.</p>

	<p>you can test it by executing the below list of REST commands</p>

	<h2>
		URL
		<%=getServletContext().getContextPath()%></h2>
	<%
		String URL = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + request.getContextPath();
	%>
	<table>
		<thead>
			<tr>
				<th>#</th>
				<th>URL</th>
				<th>Method</th>
				<th>Request Body</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>1</td>
				<td><%=URL%>/rest/resource</td>
				<td>GET</td>
				<td></td>
			</tr>
			<tr>
				<td>2</td>
				<td><%=URL%>/rest/resource</td>
				<td>POST</td>
				<td><div>{"name":"person"}</div></td>
			</tr>
			<tr>
				<td>3</td>
				<td><%=URL%>/rest/resource/1</td>
				<td>GET</td>
				<td></td>
			</tr>
			<tr>
				<td>4</td>
				<td><%=URL%>/rest/resource/1/field</td>
				<td>POST</td>
				<td>
					<div>{"name": "name","type": "string"}</div>
					<div>{"name": "birthDate","type": "date"}</div>
					<div>{"name": "number","type": "integer"}</div>
				</td>
			</tr>
			<tr>
				<td>5</td>
				<td><%=URL%>/rest/resource/1</td>
				<td>GET</td>
				<td></td>
			</tr>
			<tr>
				<td>6</td>
				<td><%=URL%>/rest/person</td>
				<td>POST</td>
				<td><div>{"name":"Bassem",
						"birthDate":"22/3/2013","number":5}</div></td>
			</tr>
			<tr>
				<td>7</td>
				<td><%=URL%>/rest/person</td>
				<td>GET</td>
				<td></td>
			</tr>
			<tr>
				<td>8</td>
				<td><%=URL%>/rest/person/1</td>
				<td>PUT</td>
				<td>{"name":"Ahmed", "birthDate":"28/3/2013","number":15}</td>
			</tr>
			<tr>
				<td>9</td>
				<td><%=URL%>/rest/person/1</td>
				<td>GET</td>
				<td></td>
			</tr>
		</tbody>

	</table>

</body>
</html>
