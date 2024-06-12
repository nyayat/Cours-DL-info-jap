$(document).ready (function () {
    let p = $("#bloc");
    let cpt_up = 0;
    let cpt_down = 0;
    
    $( "p" ).click(function() {
        alert( "Handler for .click() called." );
      });
    
    


    p.mouseover(function(){
            console.log("Attention vous entrez dans la zone rouge");
            
    
        
    });

    p.mousedown(function(){
        p.css({
            border:"0.2em solid green"
        })
        cpt_up++;
        $("header").append("<p>up" + cpt_up + "</p>");
        console.log();
    });

    p.mouseup(function(){
        p.css({
            border:"0.2em solid #882424"
        })
        cpt_down++;
        $("footer").append("<p> down" + cpt_down + "</p>");
    });




    

});
    