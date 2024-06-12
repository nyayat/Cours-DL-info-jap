// création de l'application
const express = require('express');
const app = express();
const port = 8080;


// création d'une unique route pour les requêtes en GET vers / :
app.get('/', (req, res) => {
    console.log("envoie des infos");
    res.send('Hello World!')
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