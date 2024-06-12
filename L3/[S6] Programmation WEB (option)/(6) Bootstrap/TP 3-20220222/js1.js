$(document).ready (function () {
    //var utile
    let article = $("#arti");
    let nav =$(".exo");

    //fonction
    function makeWord(length) {
        var result           = '';
        var characters       = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyz0123456789';
        var charactersLength = characters.length;
        
        for ( var i = 0; i < length; i++ ) {
          result += characters.charAt(Math.floor(Math.random() * charactersLength));
       }
       return result;
    }

    function makeArticle(nb){
        for (let index = 0; index < nb; index++) {
            //let res = '';
            //res = "<article id='post"+index+"'>";
            article.append("<article id='post"+index+"'></article>");
            let res = $("#post"+index);
            res.append("<header><h1>Article n°"+(index+1)+"</h1></header>");
            //article.append("<header><h1>Article n°"+(index+1)+"</h1></header>");
            let alea ='';
            let taille = Math.floor(Math.random() * 15)+1;
            for (let i = 0; i < taille; i++) {
                alea = alea + makeWord(40) + '\n';
                
            }
            res.append(alea);
            //article.append(alea);
            nav.append("<li><a href='#post"+index+"'>Article "+(index+1)+"</a></li>")  ;      
        }
        
    }

    makeArticle(8);
    
    
});
    