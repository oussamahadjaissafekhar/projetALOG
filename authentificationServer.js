const http = require('http');
const url = require('url'); 
const querystring = require('querystring');
const {Connect,Disconnect,Authentification ,getDataFromDatabase} = require('./authentificationBack');

const server = http.createServer((req, res) => {
  if (req.method === 'POST') {
    console.log('Client connected');
    
    let body = '';
    req.on('data', (chunk) => {
      body += chunk;
    });  
    req.on('end', async () => {
      const formData = querystring.parse(body);
      const username = formData.user;
      const password = formData.password;  
      console.log('Authentication request with username:', username);
      console.log('Authentication request with password:', password);
      const db = Connect();
      try {
        const jsonData = await getDataFromDatabase(db, username, password);
        if (jsonData == null) {
          res.write('{}');
        } else {
          const jsonString = JSON.stringify(jsonData);
          res.write(jsonString);
        }
      } catch (error) {
        console.error('Error retrieving data:', error);
        res.write('{}');
      } finally {
        Disconnect(db);
        res.end();
      }
    });
  } else {
    res.writeHead(404, { 'Content-Type': 'text/html' });
    res.write('<h1>404 Not Found</h1>');
    res.end();
  }
})

server.listen(8080, () => {
  console.log('Server running on port 8080');
});




