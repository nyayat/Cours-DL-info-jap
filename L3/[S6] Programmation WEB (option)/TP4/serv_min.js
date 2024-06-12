
const http = require('http');
const hostname = '127.0.0.1';
const port = 8080;
const server = http.createServer((req, res) => {
    res.setHeader('Content-Type', 'text/html; charset=UTF-8');
    const fs = require('fs');
    fs.readFile('./text.txt', 'utf8', (err, data) => {
        if (err){
            res.statusCode = 500;
            
            res.write("erreur lecture du fichier");
            
        }
        else{
            
            res.statusCode = 200;
            res.write("<title>Le titre</title>");
            res.write("<h1>Hehe je suis le h1</h1>")
            res.write("<p>"+data+"</p>");
            res.write("<footer> FRANCOIS Tanya</footer>")
        }
        res.end();
    });
});
server.listen(port, hostname, () => {
    console.log(`Server running at http://${hostname}:${port}/`);

});