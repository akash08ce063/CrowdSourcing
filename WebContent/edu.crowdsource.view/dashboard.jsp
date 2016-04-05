
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
 



<div class="container" style="">
	  <%
		  		
		  				UserInformation user_info =	(UserInformation)session.getAttribute("user_information");
		  				
		  				if(user_info.getType() == 2){
		  				
		  		%>
		  		
		  		
				  		
			  <div class="col-md-12">
			 		<div class="pull-right">
					     	<a href="SearchPotentialWorker" ><span class="glyphicon glyphicon-search" style="font-size:25px"></span>
					     	<span class="glyphicon-class" style="font-size:20px">Search Worker</span></a>
					     	
					</div>
			 </div>	
			 
	 		<% } else { %>
	 		
	 		 <div class="col-md-12">
			 		<div class="pull-right">
					     	<a href="AddTaskSkillController" ><span class="glyphicon glyphicon-plus" style="font-size:25px"></span>
					     	<span class="glyphicon-class" style="font-size:20px">Add Task Skill</span></a>
					     	
					</div>
			 </div>	
	 		
	 		<% } %>
	 
	   <div class="row">
		 	<div class="col-md-12" id="error_div">
		 		 	
		 	</div>
 	   </div>

      <!-- Example row of columns -->
      <div class="row" >
      	
	      <div class="page-header" style="border-bottom:1px solid #F94E10">
	    			<h1>Dashboard </h1> <h3>(<span style="font-family: "Lucida Sans Unicode", "Lucida Grande", sans-serif; "> <i> Hello <%=user_info.getUserName() %> ! </i></span>)</h3>
	  	  </div>
	  	  <div class="row">
  	  	 		<div class="col-md-6" >	
		  	
		  		
		  	
		  	    <h3>Notifications</h3> 
		  	    <hr>  
		  		<div id="notification_div"></div>
		  	
		  	
		  	
				</div>	
				
				<div class="col-md-6">
					<h3>Completed Task Log</h3> 
		  	    	<hr>
					<div id="completed_tasks_div"></div>
				
				
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



			$.post("FetchAllCompletedTasks", {user_id: <%=user_info.getUserId() %> , user_type: <%=user_info.getType()%>} , function(result){
				var notification_panels = "";
				
				for(var i = 0 ; i < result.length ; i++){
					notification_panels = notification_panels + '<div class="panel panel-default" id="panel_'+result[i].task_log_id+'" ><div class="panel-body"><form action="UpdateRating" method="get">';
					notification_panels = notification_panels + "<b>Task Name:</b>" + result[i].task_name;
					notification_panels = notification_panels  + "          <b style='margin-left:2%'>Client Name : </b>" + result[i].client_username + "<input type='hidden' name='task_log_id' value='"+result[i].task_log_id+"'  />";
					<% if(user_info.getType() == 2) {%>
						notification_panels = notification_panels  + '			<button style="margin-left:2%"  type="submit" class="rate_it btn btn-primary" id="'+result[i].task_log_id+'" > Rate it! </button>';
					<%  } %>
	
					notification_panels = notification_panels + '</form></div></div>';
				}

				if(notification_panels == ""){
					notification_panels = "No Task Log to show!";
				}

				$("#completed_tasks_div").html(notification_panels);
			});

			 

			<%  if(user_info.getType() == 1){  %>
			
				$.post("FetchNotification", {worker_id : <%=user_info.getUserId()%>} , function(result){

					var notification_panels = "";
					
					for(var i = 0 ; i < result.length ; i++){
						notification_panels = notification_panels + '<div class="panel panel-default" id="panel_'+result[i].task_log_id+'" ><div class="panel-body">';
						notification_panels = notification_panels + "<b>Task Name:</b>" + result[i].task_name;
						notification_panels = notification_panels  + "          <b style='margin-left:2%'>Client Name : </b>" + result[i].client_username;
						notification_panels = notification_panels  + '			<button style="margin-left:2%" class="accept_invite btn btn-primary" id="'+result[i].task_log_id+'" >Accept Invite</button>';
						notification_panels = notification_panels + '</div></div>';
					}

					if(notification_panels == ""){
						notification_panels = "No Notification to show!";
					}

					$("#notification_div").html(notification_panels);
					$(".accept_invite").click(function(){
							var task_log_id = 	$(this).attr('id');
							showOverLay();
							$.post("AcceptTasks", {task_log_id : task_log_id}, function(){
								$("#overlay_div").html("");
								alert("Task has been added to progress list");
								$("#panel_" + task_log_id).remove();
								
							});
					});
						
				});	

			<% } else { %>
			
			  $.post("FetchAllAssignedTasks", {client_id : <%=user_info.getUserId()%>}, function(result){
				  var notification_panels = "";
					
					for(var i = 0 ; i < result.length ; i++){
						notification_panels = notification_panels + '<div class="panel panel-default" id="panel_'+result[i].task_log_id+'" ><div class="panel-body">';
						notification_panels = notification_panels + "<b>Task Name:</b>" + result[i].task_name;
						notification_panels = notification_panels  + "          <b style='margin-left:2%'>Client Name : </b>" + result[i].client_username;
						notification_panels = notification_panels  + '			<button style="margin-left:2%" class="accept_invite btn btn-primary" id="'+result[i].task_log_id+'" >Mark Complete</button>';
						notification_panels = notification_panels + '</div></div>';
					}

					if(notification_panels == ""){
						notification_panels = "No tasks in progress!";
					}

					$("#notification_div").html(notification_panels);

					$(".accept_invite").click(function(){
						var task_log_id = 	$(this).attr('id');
						showOverLay();
						$.post("MarkCompleteTask", {task_log_id : task_log_id}, function(){
							$("#overlay_div").html("");
							alert("Task has been added to complete list");
							$("#panel_" + task_log_id).remove();
							
						});
				    });
					
			  });

		<%  }  %>		


				
		});
		

</script>

<jsp:include page="../common.view.crowdsource/commonFooter.jsp"></jsp:include>