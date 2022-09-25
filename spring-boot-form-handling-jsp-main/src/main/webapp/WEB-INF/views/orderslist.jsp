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
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
</head>
<body>
	<div class="w3-padding w3-xlarge w3-teal">
    ${apptitle}
    <c:if test="${type eq 'USER'}">
    	<a href="/viewallcurrenstocklist"><i class="material-icons" style="padding-left: 75%">&#xe05e;</i></a>
    </c:if>
    <a href="/adminhomepage"><i class="fa fa-home" style="${type ne 'USER'?'padding-left: 75%':'padding-left: 0%'}"></i></a>
    <a href="/register"><i class="fas fa-times" style="color:red;padding-left: 0%" ></i></a>
    
  	</div>
	<table id="customers" border="2" width="70%" cellpadding="2">
		<tr>
			<th>DOCNO</th>
			<th>PRODUCT</th>
			<th>USER</th>
			<th>PRICE</th>
			<th>QTY</th>
			<th>STATUS</th>
			<th>CREATED TIME</th>
			<c:if test="${status eq 'ORDERED' or status eq 'SHIPPED'}">
				<th>ACTION</th>
			</c:if>
		</tr>
		<c:forEach var="emp" items="${msg}">
			<tr>
				<td>${emp.docnum}</td>
				<td>${emp.productname}</td>
				<td>${emp.username}</td>
				<td>${emp.sellingprice}</td>
				<td>${emp.qty}</td>
				<td>${emp.status}</td>
				<td>${emp.createdtime}</td>
				<c:if test="${status eq 'ORDERED'}">
					<th>
						<div class="w3-padding w3-xlarge">
						    <a href="/updatetoshipped/${emp.id}/${type}/${status}" ><i class="fas fa-shipping-fast"></i></a>
						 </div>
					</th>
				</c:if>
				<c:if test="${status eq 'SHIPPED'}">
					<th>
						<div class="w3-padding w3-xlarge">
						    <a href="/updatetodelivered/${emp.id}/${type}/${status}" ><i class="fas fa-mail-bulk"></i></a>
						 </div>
					</th>
				</c:if>
			</tr>
		</c:forEach>
	</table>
	<br />
<!-- 	<a href="/SpringMVCPaginationExample/viewemp/1">1</a> -->
<!-- 	<a href="/SpringMVCPaginationExample/viewemp/2">2</a> -->
<!-- 	<a href="/SpringMVCPaginationExample/viewemp/3">3</a> -->
</body>
</html>
