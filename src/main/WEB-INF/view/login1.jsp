<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<!DOCTYPE html>
<html>
<head>
	<title>Login Page</title>
  
   
	<!-- Bootsrap 4 CDN-->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    
    <!--Fontawesome CDN-->
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css">

	<!--Custom styles-->
   <!--  <link rel="stylesheet" type="text/css" href="resources/css/forlogin1.css"> -->
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">


</head>
<body>
<div class="container">
	<div class="d-flex justify-content-center h-100">
		<div class="card">
			<div class="card-header">
				<h3>Sign In</h3>
				
                    <div class="d-flex justify-content-end social_icon">
					<span><i class="fab fa-facebook-square"></i></span>
					<span><i class="fab fa-google-plus-square"></i></span>
					<span><i class="fab fa-twitter-square"></i></span>
				</div> 
			</div>
			<div class="card-body">
				<form:form action="${pageContext.request.contextPath}/authenticateTheUser" 
							   method="POST" class="form-horizontal">
					<div class="input-group form-group">
						
						<div>			
					<!-- Check for login error -->
	
								<c:if test="${param.error != null}">

									<div class="alert alert-danger col-xs-offset-1 col-xs-10">
										Invalid username and password.
									</div>
								
								</c:if> 
																	
									<!--  Check for logout -->	
										
									<c:if test="${param.logout != null }" >
									            
									<div class="alert alert-success col-xs-offset-1 col-xs-10">
										You have been logged out.
									</div>
									</c:if>								    

					            </div>
					            <div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-user"></i></span>
						</div>
						<input type="text" class="form-control" name="username" placeholder="username">
						
					</div>
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-key"></i></span>
						</div>
						<input type="password" name="password" class="form-control" placeholder="password">
					</div>
					<div class="row align-items-center remember">
						<input type="checkbox">Remember Me
					</div>
					<div class="form-group">
						<input type="submit" value="Login" class="btn float-right login_btn">
					</div>
				</form:form>
			</div>
			
			
			<div class="card-footer">
				<div class="d-flex justify-content-center links">
					Don't have an account? <a href="register1.html">Sign Up as a coder</a> 
					<a href="register2.html">Sign Up as a company</a>
				</div>
				<div class="d-flex justify-content-center">
					<a href="forgotpass.html">Forgot your password?</a>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</body>
</html>