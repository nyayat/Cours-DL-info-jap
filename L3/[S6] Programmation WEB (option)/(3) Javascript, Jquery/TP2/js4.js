$(document).ready (function () {
    let img = $("img");
    let taille = img.length;
    let nb = 0;
    let suiv = $("#suivant");
    img.hide();
    img.eq(nb).show();
    
    suiv.click(defiler);
    window.setInterval(defiler, 1500);

    function changement(){
        
        if(taille-1 == nb){
            nb = 0;
        }
        else{
            nb++;
        }
        img.eq(nb).fadeIn("slow");
    }

    function defiler(){
        //img.eq(nb).hide();
        img.eq(nb).fadeOut("slow",changement);
    }
});
    