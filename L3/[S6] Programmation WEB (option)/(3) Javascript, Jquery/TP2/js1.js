$(document).ready (function () {
    let p = $("p");
    p.on ({
        mouseenter: function(){
            p.text("ceci n'est pas un paragraphe");
        },
        mouseleave: function () {
            p.text("ceci est un paragraphe");
        
        }
    })
    // corps du programme.
});
    