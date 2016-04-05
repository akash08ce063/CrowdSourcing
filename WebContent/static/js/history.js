/**
 *   this js is use to handle the back button click in the browser
 */


  if(window.history && history.pushState){ // check for history api support
        window.addEventListener('load', function(){
          // create history states
          history.pushState(-1, null); // back state
          history.pushState(0, null); // main state
          history.pushState(1, null); // forward state
          history.go(-1); // start in main state
              
          this.addEventListener('popstate', function(event, state){
            // check history state and fire custom events
            if(state = event.state){
        
              event = document.createEvent('Event');
              event.initEvent(state > 0 ? 'next' : 'previous', true, true);
              this.dispatchEvent(event);
              
              // reset state
              history.go(-state);
            }
          }, false);
        }, false);
}

window.addEventListener('next', function(){
    alert('You cannot press forward button');
   }, false);
 
   window.addEventListener('previous', function(){
      alert("You cannot click back button");
   }, false);
 