<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Success</title>

<link href="<c:url value="/style.css" />" rel="stylesheet" type="text/css">

<script src="<c:url value="/js/jquery-3.5.1.min.js" />"></script>

<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
.dropbtn {
  background-color: #3498DB;
  color: white;
  padding: 16px;
  font-size: 16px;
  border: none;
  cursor: pointer;
  width: 100%;
}

.dropbtn:hover, .dropbtn:focus {
  background-color: #2980B9;
}

.dropdown {
  position: relative;
  display: inline-block;
  width: 33%;
  background-color: #3498DB;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f1f1f1;
  min-width: 415px;
  overflow: auto;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

.dropdown a:hover {background-color: #ddd;}

.show {display: block;}
</style>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<div class="w3-padding w3-xlarge w3-teal">
    ADMIN PORTAL
    <a href="/register"><i class="fa fa-sign-out" style="color:red;padding-left: 81%" ></i></a>
  	</div>	
    
    <div class="dropdown">
	  <button onclick="myFunction('admindropdown')" class="dropbtn">ADMIN</button>
	  <div id="admindropdown" class="dropdown-content">
	    <a href="saveproduct/0" >Product</a>
	    <a href="viewproduct" >Product List</a>
	    <a href="viewalluser" >User List</a>
	  </div>
	</div>
	
	<div class="dropdown">
	  <button onclick="myFunction('sellerdropdown')" class="dropbtn">SELLER</button>
	  <div id="sellerdropdown" class="dropdown-content">
	    <a href="viewallsavedsellrequser" >Sell List</a>
	    <a href="viewallcollectedsellrequser" >Collected List</a>
	    <a href="viewallrecycledsellrequser" >Recycle List</a>
	    <a href="viewallrejectedsellrequser" >Reject List</a>
	  </div>
	</div>
	
	<div class="dropdown">
	  <button onclick="myFunction('buyerdropdown')" class="dropbtn">BUYER</button>
	  <div id="buyerdropdown" class="dropdown-content">
	    <a href="viewallordered">Ordered List</a>
	    <a href="viewallshipped">Shipped List</a>
	    <a href="viewalldelivered">Delivered List</a>
	  </div>
	</div>

<script>
/* When the user clicks on the button, 
toggle between hiding and showing the dropdown content */
function myFunction(usertype) {
	document.getElementById(usertype).classList.toggle("show");
}

// Close the dropdown if the user clicks outside of it
window.onclick = function(event) {
  if (!event.target.matches('.dropbtn')) {
    var dropdowns = document.getElementsByClassName("dropdown-content");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
  }
}
</script>



</body>
</html>