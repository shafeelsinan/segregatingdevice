<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
.card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  max-width: 300px;
  text-align: center;
  font-family: arial;
}

.price {
  color: grey;
  font-size: 22px;
}

.card button {
  border: none;
  outline: 0;
  padding: 12px;
  color: white;
  background-color: #000;
  text-align: center;
  cursor: pointer;
  width: 100%;
  font-size: 18px;
}

.card button:hover {
  opacity: 0.7;
}
</style>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="w3-padding w3-xlarge w3-teal">
    CART
    
    <a href="/adminhomepage"><i class="fa fa-home" style="padding-left: 75%"></i></a>
    <a href="/register"><i class="fa fa-sign-out" style="color:red;padding-left: 0%" ></i></a>
    
  	</div>
		

<div class="container">
	 <div class="card-columns">
	 	<c:forEach var="emp" items="${msg}">
		<div class="card">
<!-- 		<img src="/w3images/jeans3.jpg" alt="Denim Jeans" style="width:100%"> -->
		  <h1>${emp.productid.name}</h1>
		  <p class="price">Qty: ${emp.currenstockqty}</p>
		  <p class="price">RS : ${emp.sellingprice}</p>
		  <p>${emp.productid.remarks}</p>
		  <p><button>BUY</button></p>
		</div>
		</c:forEach>
	 	
		
		
	 </div>
</div>
	
	<br />
<!-- 	<a href="/SpringMVCPaginationExample/viewemp/1">1</a> -->
<!-- 	<a href="/SpringMVCPaginationExample/viewemp/2">2</a> -->
<!-- 	<a href="/SpringMVCPaginationExample/viewemp/3">3</a> -->
</body>
</html>
