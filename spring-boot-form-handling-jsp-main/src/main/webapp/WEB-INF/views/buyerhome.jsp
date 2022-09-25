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
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<div class="w3-padding w3-xlarge w3-teal">
    BUYER PORTAL
    <a href="/register"><i class="fa fa-sign-out" style="color:red;padding-left: 81%" ></i></a>
  	</div>

    <div class="wrap">
        
        
        <div class="sidebar">
          <img src="/images/menu.png" alt="" id="menuicon">
           <h1>Dashboard</h1>
            <ul>
                <li><div class="linksd" id="homelink">Home</div></li>
                <li><div class="linksd" id="sellcartlink"><a href="viewallcurrenstocklist" style="color:white;">Buy Cart</a></div></li>
            </ul>
        </div>
        

        <div class="main" id="maincontext1">
        </div>
        
    </div>

<script>
    $(document).ready(function(){
        
        $("#menuicon").click(function(){
            $(".sidebar").toggleClass("opensidebar")
        });
        
        $("#homelink").click(function(){ 
        	$("#maincontext1").load("/jsp/register_success.jsp"); 
    	}); 
    	
    	$("#productlink").click(function(){ 
        	$("#maincontext1").load("/productform.jsp"); 
    	});
    	
    	$("#productlist").click(function(){ 
        	$("#maincontext1").load("/register_success.jsp"); 
    	});

    });
    
    
</script>



</body>
</html>