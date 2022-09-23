<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
#customers {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

#customers td, #customers th {
  border: 1px solid #ddd;
  padding: 8px;
}

#customers tr:nth-child(even){background-color: #f2f2f2;}

#customers tr:hover {background-color: #ddd;}

#customers th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #04AA6D;
  color: white;
}
</style>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<div class="w3-padding w3-xlarge w3-teal">
    USER LIST
    <a href="/adminhomepage"><i class="fa fa-home" style="padding-left: 75%"></i></a>
    <a href="/register"><i class="fa fa-sign-out" style="color:red;padding-left: 0%" ></i></a>
  	</div>
	<table id="customers" border="2" width="70%" cellpadding="2">
		<tr>
			<th>Id</th>
			<th>Username</th>
			<th>Email</th>
			<th>User Type</th>
		</tr>
		<c:forEach var="emp" items="${msg}">
			<tr>
				<td>${emp.id}</td>
				<td>${emp.name}</td>
				<td>${emp.email}</td>
				<td>${emp.usertype}</td>
			</tr>
		</c:forEach>
	</table>
	<br />
<!-- 	<a href="/SpringMVCPaginationExample/viewemp/1">1</a> -->
<!-- 	<a href="/SpringMVCPaginationExample/viewemp/2">2</a> -->
<!-- 	<a href="/SpringMVCPaginationExample/viewemp/3">3</a> -->
</body>
</html>
