
<%@ page import="java.util.*" %>
<jsp:include page="../common.view.crowdsource/commonHeader.jsp"></jsp:include>
<div id="overlay_div"></div>
<div class="jumbotron">
      		<div class="container">
	        	<h1>Crowdsourcing!</h1>
	        	<p>Crowdsourcing is an emerging business paradigm where services are not
					solicited from known providers as in the classic model, but from group
					of people (crowd) willing to provide those services with different
					fees
				</p>
	        	<p><a class="btn btn-primary btn-lg" href="#" role="button">Learn more »</a></p>
	      </div>
</div>
 	   
 	   
<div class="container">
	   <div class="row">
		 	<div class="col-md-12" id="error_div">
		 		 	<%
		 		 		if(request.getAttribute("user_login_message") != null){
		 		 			out.println("<b>" + request.getAttribute("user_login_message") + "</b>");
		 		 		}
		 		 	
		 		 	%>
		 	</div>
 	   </div>
 	   
 	   
 	   

      <!-- Example row of columns -->
      <div class="row" >
	      <div class="page-header" style="border-bottom:1px solid #F94E10">
	    			<h1>Login</h1>      
	  	  </div>
	  	  
  	  </div>
  	  <div class="row">
  	  	 <div class="col-md-6">	
		  	  <form role="form" action="UserLogin" method="post">
				  <div class="form-group">
				    <label for="email">User name:</label>
				    <input type="text" class="form-control" id="user_name" name="user_name" >
				  </div>
				  <div class="form-group">
				    <label for="pwd">Password:</label>
				    <input type="password" class="form-control" id="password" name="password" >
				  </div>
				  <div class="checkbox">
				    <label><input type="checkbox"> Remember me</label>
				  </div>
				  <button type="submit" class="btn btn-default">Submit</button>
				</form>
		</div>	
  	  </div>
  	  
</div>
<script type="text/javascript" src="static/js/history.js"></script>
<link href="static/css/font-awesome.min.css" rel="stylesheet">

<script>

</script>

<jsp:include page="../common.view.crowdsource/commonFooter.jsp"></jsp:include>