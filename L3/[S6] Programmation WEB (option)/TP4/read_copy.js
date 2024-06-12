
const fs = require('fs');
fs.readFile('./text.txt', 'utf8', (err, data) => {
    if (err) throw err;
    console.log("async : \n"+data);
});

try {
    const data = fs.readFileSync('./text.txt', 'utf8');
    console.log("sync : \n"+data);
} catch (error) {
}