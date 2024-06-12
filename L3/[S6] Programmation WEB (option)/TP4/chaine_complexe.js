// création de l'application
const express = require('express');
const app = express();
const port = 8080;


// création d'une unique route pour les requêtes en GET vers / :

app.get('/', (req, res) => {
    console.log("requête reçue \n"+ Date.now());
    res.send('on est dans /')
});

app.get('/private', (req, res) => {
    console.log("requête reçue \n"+ Date.now());
    res.send('on est dans /private')
});

app.get('/pictures', (req, res) => {
    console.log("requête reçue \n"+ Date.now());
    res.download('Algorithme.pdf', function (err) {
        if (err) {
          // Handle error, but keep in mind the response may be partially-sent
          // so check res.headersSent
            console.log("Error : ", err)
        } else {
          // decrement a download credit, etc.
        }
      })
    res.send('image')
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