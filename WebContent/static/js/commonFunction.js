

	function showOverLay(){
				var docHeight = $(document).height();
				   $("#overlay_div").html("<div id='overlay'><div style='position: fixed; top: 50%; left: 40%; font-size:40px; color: WHITE'><i class='fa fa-cog fa-spin' ></i> Loading All Questions</div></div>");
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
	function showOverLay(message){
		var docHeight = $(document).height();
		   $("#overlay_div").html("<div id='overlay'><div style='position: fixed; top: 50%; left: 40%; font-size:40px; color: WHITE'><i class='fa fa-cog fa-spin' ></i> " + message + "</div></div>");
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
	
	
	function createMessageDiv(type, message){
		return '<div class="alert alert-'+type+'">'+ message +'</div>';
	}
	
	function createFontAwesomeStuff(tag, margin){
		return '<i class="fa fa-'+tag+'" style="margin-right: '+margin+'%; "></i>';
	}
	
	function createSpanForTag(tag_name, margin){
		return '<span class="label label-primary" style="margin-left: '+margin+'%" > '+tag_name+'</span>';
	}
	
	function createSpanTagWithStuffInside(outside_name, outside_margin, inside_name, inside_margin){
		return '<span class="label label-primary" style="margin-left: '+outside_margin+'% ; font-size: 100%; line-height: 2;" > '+ createFontAwesomeStuff(inside_name, inside_margin) + outside_name+'</span>';
	}
	
	function createTextField(id, name, value){
		return '<input  type="text" class="form-control" id="'+id+'" name="'+name+'" value="'+value+'" placeholder="type of operation" />';
	}
	
	function createTextFieldWithColor(id, name, value,color){
		return '<input  type="text" class="form-control" id="'+id+'" name="'+name+'" value="'+value+'" style="background-color: '+color+'" placeholder="type of operation" />';

	}
	
	function createTextFieldWithStyle(id, name, value,style){
		return '<input  type="text" class="form-control" id="'+id+'" name="'+name+'" value="'+value+'" style="'+style+'" placeholder="type of operation" />';

	}
	
	function showMessage(result, question_type ,op_type){
		var msg_div = "";
		if(result.error_code == 0){
			msg_div = msg_div = createMessageDiv('success',question_type+" has been "+op_type+"ed successfully");
		}else{
			msg_div = msg_div = createMessageDiv('danger',"Problem with "+op_type+"ing the "+ question_type);
		}
			
		removeOverLay();

		$("#message_div").html(msg_div);
	    fadingMessageAway();

	}
	
	function tableFilter(id, class_var){
		$('#'+id).keyup(function () {


            /*var rex = new RegExp($(this).val(), 'i');
            $('.' + class_var +' tr').hide();
            
         
            
            
             $('.' + class_var +' tr').filter(function () {
               return rex.test($(this).text());
             }).show();*/
			
			
			/*var rex = new RegExp($(this).val(), 'i'); // 'i' : Case Insensitive mode.
	        $('.' + class_var ).find('tr:first').hide();
	         
	            
             $('.' + class_var ).find('tr:first').filter(function () {
               return rex.test($(this).text());
             }).show();*/
			
			var rex = new RegExp($(this).val(), 'i');
            $('.all_projects').hide();
            $('.quotes').hide();
            $('.documents').hide();
         
            
          
            
             $('.all_projects').filter(function () {
               
              return rex.test($(this).text());
             }).show();
             
             $('.documents').filter(function () {
               
              return rex.test($(this).text());
              }).show();
                     
             $('.quotes').filter(function () {
               
                     return rex.test($(this).text());
              }).show();
             
             




        });
	}
	
    function removeOverLay(){
    	$("#overlay_div").html("");
    }	
	
    function fadingMessageAway(){
    	
    	window.setTimeout(function() {
    	    $(".alert").fadeTo(1500, 0).slideUp(500, function(){
    	        $(this).remove(); 
    	    });
    	}, 5000);
    }
    
    function collpasePageViewHandling(){
		$('.accord').on('shown.bs.collapse', function (e) {
		    console.log("scrolling down!");
	        var offset = $( this ).find('.panel-collapse.in').offset();
	        console.log(offset);
	        if(offset) {
	            $('html,body').animate({
	                scrollTop: $(this).find('.panel-heading a').offset().top -20
	            }, 500); 
	        }
	    }); 
	}
    
    