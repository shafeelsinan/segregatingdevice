<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Registration Form</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<style type="text/css">
	body {
    background: #222D32;
    font-family: 'Roboto', sans-serif;
}

.error {
         color: #ff0000;
      }

.errorblock {
         color: #000;
         background-color: #ffEEEE;
         border: 3px solid #ff0000;
         padding: 8px;
         margin: 16px;
      }

.login-box {
    margin-top: 75px;
    height: auto;
    background: #1A2226;
    text-align: center;
    box-shadow: 0 3px 6px rgba(0, 0, 0, 0.16), 0 3px 6px rgba(0, 0, 0, 0.23);
}

.login-key {
    height: 100px;
    font-size: 80px;
    line-height: 100px;
    background: -webkit-linear-gradient(#27EF9F, #0DB8DE);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
}

.login-title {
    margin-top: 15px;
    text-align: center;
    font-size: 30px;
    letter-spacing: 2px;
    margin-top: 15px;
    font-weight: bold;
    color: #ECF0F5;
}

.login-form {
    margin-top: 25px;
    text-align: left;
}

input[type=text] {
    background-color: #1A2226;
    border: none;
    border-bottom: 2px solid #0DB8DE;
    border-top: 0px;
    border-radius: 0px;
    font-weight: bold;
    outline: 0;
    margin-bottom: 20px;
    padding-left: 0px;
    color: #ECF0F5;
}

input[type=password] {
    background-color: #1A2226;
    border: none;
    border-bottom: 2px solid #0DB8DE;
    border-top: 0px;
    border-radius: 0px;
    font-weight: bold;
    outline: 0;
    padding-left: 0px;
    margin-bottom: 20px;
    color: #ECF0F5;
}

.form-group {
    margin-bottom: 40px;
    outline: 0px;
}

.form-control:focus {
    border-color: inherit;
    -webkit-box-shadow: none;
    box-shadow: none;
    border-bottom: 2px solid #0DB8DE;
    outline: 0;
    background-color: #1A2226;
    color: #ECF0F5;
}

input:focus {
    outline: none;
    box-shadow: 0 0 0;
}

label {
    margin-bottom: 0px;
}

.form-control-label {
    font-size: 10px;
    color: #6C6C6C;
    font-weight: bold;
    letter-spacing: 1px;
}

.btn-outline-primary {
    border-color: #0DB8DE;
    color: #0DB8DE;
    border-radius: 0px;
    font-weight: bold;
    letter-spacing: 1px;
    box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
}

.btn-outline-primary:hover {
    background-color: #0DB8DE;
    right: 0px;
}

.login-btm {
    float: left;
}

.login-button {
    padding-right: 0px;
    text-align: right;
    margin-bottom: 25px;
}

.login-text {
    text-align: left;
    padding-left: 0px;
    color: #A2A4A4;
}

.loginbttm {
    padding: 0px;
}
</style>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<div class="w3-padding w3-xlarge w3-teal">
    SELL FORM
    <a href="/adminhomepage"><i class="fa fa-home" style="padding-left: 75%"></i></a>
    <a href="/register"><i class="fa fa-sign-out" style="color:red;padding-left: 0%" ></i></a>
  	</div>
	<div align="center">
		<h2>SELL Form</h2>
		<form:form action="${pageContext.request.contextPath}/sellproduct" method="post" modelAttribute="sell">
		
		
    <div class="container">
        <div class="row">
            <div class="col-lg-3 col-md-2"></div>
            <div class="col-lg-6 col-md-8 login-box">
                
                <div class="col-lg-12 login-title">
                  SELL FORM 
                </div>
				<form:errors path = "*" cssClass = "errorblock" element = "div" />
                <div class="col-lg-12 login-form">
                    <div class="col-lg-12 login-form">
                        <form>
                        <div class="form-group">
					            <form:label class="form-control-label" path="docnum">DOC No.</form:label>
								<form:input class="form-control" path="docnum"/>
                            </div>
                        	<div class="form-group">
                           <form:label class="form-control-label" path="productid">Product</form:label>
                           <form:select path="productid" class="textbox" >
							<form:option value="0" label="Please Select"/>
           				 	<form:options path="productid" items='${productlist}' itemValue = "id" itemLabel="name"/>
							</form:select>
							<form:hidden path="id" />
							<form:hidden path="docnum" />
							<form:hidden path="createdtime" />
							<form:hidden path="recycletime" />
							<form:hidden path="status" />
							<form:hidden path="productname" />
							<form:hidden path="username" />
							<form:hidden path="userid" />
							<form:hidden path="productid" />
							<form:hidden path="adminRemarks" />
							
							</div>
							
							<div class="form-group">
					            <form:label class="form-control-label" path="price">Price</form:label>
								<form:input class="form-control" path="price"/>
                            </div>
                            <div class="form-group">
					            <form:label class="form-control-label" path="qty">Quantity</form:label>
								<form:input class="form-control" path="qty"/>
                            </div>
							<div class="form-group">
	                            <form:label class="form-control-label" path="remarks">Remarks</form:label>
								<form:textarea path="remarks" cols="40" rows="3"/><br/>
							</div>
							
                            
                            <div class="col-lg-12 loginbttm">
                                <div class="col-lg-6 login-btm login-text">
                                    <a href="/viewsellrequser">Sell List</a>
                                </div>
                                <div class="col-lg-6 login-btm login-button">
                                    <button type="submit" class="btn btn-outline-primary">Save</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="col-lg-3 col-md-2"></div>
            </div>
        </div>
		</form:form>
	</div>
</body>
</html>