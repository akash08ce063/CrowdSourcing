
<%@ page import="java.util.*" %>
<jsp:include page="../common.view.crowdsource/commonHeader.jsp"></jsp:include>
<div id="overlay_div"></div>

<div class="row">
<nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Crowdsourcing</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          
        </div><!--/.nav-collapse -->
      </div>
</nav>
</div>
<div class="container" style="margin-top: 3%;" ng-app="myapp">
	   <div class="row">
		 	<div class="col-md-12" id="error_div">
		 		 	
		 	</div>
 	   </div>

      <!-- Example row of columns -->
      <div class="row" >
	      <div class="page-header" style="border-bottom:1px solid #F94E10">
	    			<h1>Registration</h1>      
	  	  </div>
	  	  
  	  </div>
  	  <div class="row" ng-controller="RegistrationController">
  	  	 <div class="col-md-6">	
		  	  <form role="form" >
				  <div class="form-group">
				    <label for="email">User name:</label>
				    <input type="text" class="form-control" name="user_name" id="user_name" ng-model="myForm.user_name">
				  </div>
				  <div class="form-group">
				    <label for="pwd">Password:</label>
				    <input type="password" class="form-control" name="password"  id="password" ng-model="myForm.password">
				  </div>
				  <div class="form-group">
				    <label for="pwd">Confirm Password:</label>
				    <input type="password" name="confirm_password" class="form-control" id="confirm_password" ng-model="myForm.confirm_password">
				  </div>
				  <div class="form-group">
				    <label for="pwd">Address:</label>
				    <input type="text" name="user_address" class="form-control" id="user_address" ng-model="myForm.user_address">
				  </div>
				  <div class="form-group">
					  <label for="sel1">Select Type:</label>
					  <select class="form-control" id="user_type" name="user_type" ng-model="myForm.user_type">
					    <option value="1">Worker</option>
					    <option value="2">Client</option>
					  </select>
				  </div>
				  
				   <button class="btn btn-primary" ng-click="myForm.submitTheForm()">Register !</button>
				</form>
		</div>	
  	  </div>
  	  
  	  
  	  
</div>
<script type="text/javascript" src="static/js/history.js"></script>
<script type="text/javascript" src="static/js/angular.min.js"></script>
<link href="static/css/font-awesome.min.css" rel="stylesheet">

<script>

	$(document).ready(function(){


		
		
		$.post("UserRegistration",{user_name : "as", password : "ads" , user_address : "22", user_type : "1"} ,function(result){
			//console.log(result.error_code);
		});


	});



	angular.module("myapp", []).controller("RegistrationController", function($scope,  $http){
		 $scope.myForm = {};
		 $scope.myForm.submitTheForm = function(item,event){

				var eligible_to_submit = true;
				var user_name = $scope.myForm.user_name;
				var password = $scope.myForm.password;
				var user_address = $scope.myForm.user_address;
				var user_type= $scope.myForm.user_type;
				
				var user_information = {};
				
				console.log($scope.myForm.user_name);
				if(angular.isUndefined(user_name)){
					alert("Please Enter Username!");
					eligible_to_submit = false;
				}
				
				if($scope.myForm.password != $scope.myForm.confirm_password){
					alert("please make sure you entered the same password in both the fields");
					eligible_to_submit = false;
				}

				if(eligible_to_submit){
					user_information = {
							user_name : user_name,
							password : password,
							user_address : user_address,
							user_type : user_type
					};



					$.post("UserRegistration", user_information ,function(result){
						//console.log(result.error_code);
						if(result.error_code == 0){
							alert("You have registered Successfully!");
							window.location.href ="UserLogin";
						} else {
							alert("There is a problem in registration");
						}
					});
					
				} else{
					
				}

					
				
		 }

	});

</script>

<jsp:include page="../common.view.crowdsource/commonFooter.jsp"></jsp:include>