<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.chainsys.demos.model.*" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List Of Users</title>
</head>
<body>
	<%
	User namesearched = (User) request.getAttribute("searchName");
	//out.println(namesearched);
	%>
	<div class="container">
		<div class="row">
			<div class="col-sm-6"></div>
		</div>
		<div class="row">
			<div clas="col-sm-6">
				<table id="userTable" class="table table-bordered table-condensed table-striped" border="1">
				<thead>
					<tr>
						<th>Id</th>
						<th>User Name</th>
						<th>Password</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><%=namesearched.getId() %></td>
						<td><%=namesearched.getUsername() %></td>
					</tr>
				</tbody>
				</table>
			</div>
		</div>
	</div>

</body>
</html>