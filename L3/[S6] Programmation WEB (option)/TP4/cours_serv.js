// création de l'application
const express = require('express');
const app = express();
const port = 8080;

app.set('view engine', 'esj');

// cours

let cours = [];
cours[0] = {
    nom : "Java - CPOO",
    description : "Programmation orientée objet",
    enseignant : ["Eugène Asarin", "Isabelle Fagnot", "Ny Aina Andriambolamalala", "Wael Boutglay"],
    responsable : "Yan Jurski"
}


cours[1] = {
    nom : "C - CPOO",
    description : "Programmation orientée objet",
    enseignant : ["Yan Jurski", "Isabelle Fagnot", "Ny Aina Andriambolamalala", "Wael Boutglay"],
    responsable : "Eugène Asarin"
}


// création d'une unique route pour les requêtes en GET vers / :
app.use(
    express.static("pictures")
);

app.get('/logo.jpg', (req, res) => {
    res.sendFile('logo.jpg', {root:'pictures'});
});

app.get('/:num', (req, res) => {
    
    console.log("requête reçue \n"+ Date.now());
    if(parseInt(req.params.num) < cours.length){
        res.render('cours.ejs', cours[parseInt(req.params.num)]);
    }
    //res.send('Vous avez demandé le cours : '+ req.params.titre)

});



app.use((req, res) => {
    // gestion des requêtes non attendues
    console.log("abort");
    res.status(400).send("Bad Request");

});
    


// lancement
app.listen(port, () => {
    console.log(`Example app listening at http://localhost:${port}`)
});