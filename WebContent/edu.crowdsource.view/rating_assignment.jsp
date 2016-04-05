
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
	    			<h1>Rate the Worker</h1>      
	  	  </div>
	  	  
  	  </div>
  	  <form method="post" action="UpdateRating">
  	  <div class="row">
  	  		<div class="col-md-6">
  	  				<input type="hidden" name="task_log_id" value='<%=request.getAttribute("task_log_id") %>' />
  	  				<div class="form-group">
  	  				    <label for="email">Rating:</label>
					   
	  	  				<span class="rating">
					        <input type="radio" class="rating-input" value="5"
					            id="rating-input-1-5" name="rating-input-1">
					        <label for="rating-input-1-5" class="rating-star"></label>
					        <input type="radio" class="rating-input" value="4"
					            id="rating-input-1-4" name="rating-input-1">
					        <label for="rating-input-1-4" class="rating-star"></label>
					        <input type="radio" class="rating-input" value="3"
					            id="rating-input-1-3" name="rating-input-1">
					        <label for="rating-input-1-3" class="rating-star"></label>
					        <input type="radio" class="rating-input" value="2"
					            id="rating-input-1-2" name="rating-input-1">
					        <label for="rating-input-1-2" class="rating-star"></label>
					        <input type="radio" class="rating-input" value="1"
					            id="rating-input-1-1" name="rating-input-1">
					        <label for="rating-input-1-1" class="rating-star"></label>
					    </span>
				    </div>
				    
				    
				     <div class="form-group">
					    <label for="email">Comment:</label>
					    <textarea class="form-control" name="client_comment" id="client_comment"></textarea>
					  </div>
  	  				

					 <input type="hidden" name="rating" id="rating" />

  	  				 <button type="submit" class="btn btn-default">Submit</button>
  	  				
  	  			
  	  		</div>
  	  		
  	  </div>
  	  </form>
  	  
</div>
<script type="text/javascript" src="static/js/history.js"></script>
<link href="static/css/font-awesome.min.css" rel="stylesheet">
<link href="static/css/star_rating.css" rel="stylesheet">

<script>

	$(document).ready(function(){
		var logID = 'log',
		log = $('<div id="'+logID+'"></div>');
		$('body').append(log);
		$('[type*="radio"]').change(function () {
		  var me = $(this);
		     $("#rating").val(me.attr('value'));
		});
	});

		

	

		

</script>

<jsp:include page="../common.view.crowdsource/commonFooter.jsp"></jsp:include>