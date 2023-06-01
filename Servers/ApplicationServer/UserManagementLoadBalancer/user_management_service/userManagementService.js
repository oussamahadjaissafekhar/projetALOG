const http = require('http');
const url = require('url'); 
const querystring = require('querystring');
const {Connect,Disconnect,Authentication ,insertUser} = require('./userManagementBack');
const port = 5000

const server = http.createServer((req, res) => {
  if (req.method === 'POST') {
    console.log('Client connected'); 
    let body = '';
    req.on('data', (chunk) => {
      body += chunk;
    });  
    req.on('end', async () => {
      // recuperer les cordonnees de la requete POST
      const formData = querystring.parse(body);  
      // verifer le service desirer a consulter 
      const requestUrl = url.parse(req.url, true); // Parse the request URL
      const pathname = requestUrl.pathname;
      //console.log("pathename : ",pathname);
      if (pathname === '/addUser') {
        // Handle add user request
        // Add your logic here to process the user data
        res.write('User added successfully');
        res.end();
      } else if (pathname === '/connect') {
        let username = formData.username;
        let password = formData.password;
        if(username == undefined){
          username = null;
        }
        if(password == undefined){
          password = null;
        }
        console.log('Authentication request with username:', username);
        console.log('Authentication request with password:', password);
        try{
          const user = {
            username:username,
            password:password
          }
          await Authentication(res,user);
        }catch (error) {
          console.error('Error retrieving data:', error);
          res.write('{}');
      }
    }else {
      res.writeHead(404, { 'Content-Type': 'text/html' });
      res.write('<h1>404 Not Found</h1>');
      res.end();
    }
    });
  } else {
    res.writeHead(404, { 'Content-Type': 'text/html' });
    res.write('<h1>404 Not Found</h1>');
    res.end();
  }
})

server.listen(port, () => {
  console.log('Server running on port',port);
});




