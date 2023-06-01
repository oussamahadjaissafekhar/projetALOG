const http = require('http');
const { URL } = require('url');
const hostMachine = require('../../conf');
const { url } = require('inspector');
const port = 8088;

const server = http.createServer((req, res) => {
  if (req.url.startsWith('/userManagement')) {
    console.log("Client Connected");
    const restOfUrl = req.url.replace('/userManagement', '');
    const backendUrl = new URL(`http://${hostMachine}:8085${restOfUrl}`);

    // Create the options for the forwarded request
    const requestOptions = {
      method: req.method,
      hostname: backendUrl.hostname,
      port: backendUrl.port,
      path: backendUrl.pathname,
      headers: req.headers
    };

    // Create the request to the backend service
    const backendReq = http.request(requestOptions, (backendRes) => {
      // Forward the backend service response to the client
      res.writeHead(backendRes.statusCode, backendRes.headers);
      backendRes.pipe(res);
    });

    // Forward the request body to the backend service
    req.pipe(backendReq);

    // Handle errors that occur during the request
    backendReq.on('error', (error) => {
      console.error('Error occurred during the request:', error);
      res.writeHead(500);
      res.end('An error occurred while processing the request');
    });
  } else {
    // Handle other routes or return a 404 response
    res.writeHead(404);
    res.end('Not Found');
  }
});

server.listen(port, () => {
  console.log('Server running on port:', port);
});
