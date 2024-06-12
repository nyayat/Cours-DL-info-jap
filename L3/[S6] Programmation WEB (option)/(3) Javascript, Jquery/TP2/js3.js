$(document).ready (function () {
    let list = $("ul");
    list.hide();
    let buttonA = $("#afficher");
    buttonA.click(function(){
        if(buttonA.text() == "Afficher"){
            buttonA.text("Masquer");
        }
        else{
            buttonA.text("Afficher");
        }
        
        list.slideToggle("slow");
    })

    let bonjour = $("p");
    let buttonB = $("#bienvenue")
    bonjour.hide();
    buttonB.click(function(){
        bonjour.show();
        bonjour.animate({
            left: "50%"
            //version bye
            //left: "150%"
        });
    })
});
    