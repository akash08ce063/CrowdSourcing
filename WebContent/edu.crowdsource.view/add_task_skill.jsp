
<%@ page import="java.util.*" %>
<%@ page import="edu.crowdsource.model.*" %>
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

<%
		  		
		  				UserInformation user_info =	(UserInformation)session.getAttribute("user_information");
						int user_id = user_info.getUserId();
		  				
		  		
		  		
		  		%>
		  	
<div class="container" style="margin-top: 3%">
	   <div class="row">
		 	<div class="col-md-12" id="error_div">
		 		 	
		 	</div>
 	   </div>
 	   
 	    <div class="col-md-12" style="margin-top: 2% ">
	 		<div class="pull-right">
			     	<a href="DashboardCrowdSource" ><span class="glyphicon glyphicon-home" style="font-size:25px"></span>
			     	<span class="glyphicon-class" style="font-size:20px">Dashboard</span></a>
			     	
			</div>
	 	</div>	
 	   

      <!-- Example row of columns -->
      <div class="row" >
      	
	      <div class="page-header" style="border-bottom:1px solid #F94E10">
	    			<h1>Add Task Skills</h1>      
	  	  </div>
	  	  <div class="row">
  	  	 <div class="col-md-6">	
		  	  <form role="form">
				  <div class="form-group">
				    <label for="email">Task Name:</label>
				  	<select class="form-control" id="task_name"  name="task_name"></select>
				  </div>
				  
				  <div class="form-group">
				    <label for="email">Availability(Hours):</label>
				    <input type="number" class="form-control" name="availability" id="availability">
				  </div>
				  
				  <div class="form-group">
				    <label for="email">Amount:</label>
				    <input type="text" class="form-control" name="charge" id="charge">
				  </div>
				
				  
				  <button type="button" name="add_tasks" id="add_tasks" class="btn btn-default">Add Task</button>
				</form>
		</div>	
  	  </div>
	  	  
  	  </div>
</div>
<script type="text/javascript" src="static/js/history.js"></script>
<link href="static/css/font-awesome.min.css" rel="stylesheet">

<script>

		$(document).ready(function(){

			function showOverLay(){
				var docHeight = $(document).height();
				   $("#overlay_div").html("<div id='overlay'><div style='position: fixed; top: 50%; left: 40%; font-size:40px; color: WHITE'><i class='fa fa-cog fa-spin' ></i> Loading </div></div>");
				   $("#overlay")
				      .height(docHeight)
				      .css({
				         'opacity' : 0.4,
				         'position': 'absolute',
				         'top': 0,
				         'left': 0,
				         'background-color': 'black',
				         'width': '100%',
				         'z-index': 5000
				      });

			 }
				
			showOverLay();
			$.post("FetchAllTasks", function(result){
				$("#overlay_div").html("");
				var options = $("#task_name");
				for(var i = 0 ; i <result.length ; i++){
					console.log(result[i].task_name);
					options.append($("<option />").val(result[i].task_id).text(result[i].task_name));
				}
				
			});

			$("#add_tasks").click(function(){

					var task_id = $("#task_name").val();
					var availibility = $("#availability").val();
					var charge = $("#charge").val();
					if($.isNumeric(charge) && availibility!=null ){
							showOverLay();
							$.post("AddTaskToWorkerProfile", { worker_id : <%=user_id%> , task_availability : availibility, charges : charge, task_id : task_id} , function(result){
									$("#overlay_div").html("");
									alert("Task has been successfully added to your profile");
									window.location.href = "DashboardCrowdSource";
							});
					}else{
							alert("Add number in charges");
					}

			});



			

		});
	

</script>

<jsp:include page="../common.view.crowdsource/commonFooter.jsp"></jsp:include>