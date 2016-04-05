
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
	    			<h1>Searching Potential Worker</h1>      
	  	  </div>
	  	  
  	  </div>
  	  
  	  <div class="row">
  	  		<div class="col-md-4">
  	  			  <form role="form">
					  <div class="form-group">
					  	<label for="sel1">Task Select:</label>
						  <select class="form-control" id="task_name">
						   
						  </select>
					  </div>
					  <div class="form-group">
					    <label for="email">Address:</label>
					    <input type="text" class="form-control" name="address" id="address">
					  </div>
					  <div class="form-group">
					    <label for="email">Number of Workers:</label>
					    <input type="text" class="form-control" name="number_workers" id="number_workers">
					  </div>
					  <div class="form-group">
					    <label for="email">Budget:</label>
					    <input type="text" class="form-control" name="budget" id="budget">
					  </div>
					  
					  <button id="serach" class="btn btn-primary" type="button">Search</button>
					  
				</form>	
  	  		
  	  		</div>
  	  
  	  		<div class="col-md-8">
  	  			<div id="search_result"></div>
  	  		
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


		$("#serach").click(function(){
				showOverLay();
				$.post("SearchPotentialWorker", {number_of_workers : $("#number_workers").val(), budget : $("#budget").val() , task_id : $("#task_name").val()} ,function(result){
					var search_panel = "";
					$("#overlay_div").html("");
					for(var i = 0 ; i < result.length ; i++){
						search_panel = search_panel + '<div class="panel panel-default" id="panel_'+result[i].user_id+'" ><div class="panel-body">';
						search_panel = search_panel + "<b>Task Id:</b>" + $("#task_name").val();
						search_panel = search_panel  + "          <b style='margin-left:2%'>Worker Name : </b>" + result[i].user_name + '<b style="margin-left: 2%"> Charges:</b>' + result[i].budget + "" ;
						search_panel = search_panel  + '			<button style="margin-left:2%" class="connect_req btn btn-primary" id="'+result[i].user_id+'" value= "'+result[i].user_id+'" >Assign Task</button>';
						search_panel = search_panel + '</div></div>';
					}

					if(search_panel == ""){
						search_panel = "No Search to show!";
					} else {
						$("#search_result").html("<h3>Search Result</h3></br>" + search_panel);
					} 
					

					$(".connect_req").click(function(){
								showOverLay();
								var worker_id = $(this).val();
								console.log(<%=user_id%>);
								$.post("AddTaskLog", {worker_id : worker_id , client_id : <%=user_id%> , task_id : $("#task_name").val()}, function(result){
									$("#overlay_div").html("");
									$("#panel_" +worker_id ).remove();
								});

					});
					
				});
			
		});
		

   });


</script>

<jsp:include page="../common.view.crowdsource/commonFooter.jsp"></jsp:include>