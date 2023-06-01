const http = require('http');
const url = require('url'); 
const querystring = require('querystring');
const {EmptyReturns,EmptyReturnsByTransporterId,DriversByTransporter,VehiculesByTransporter,insertEmptyReturn} = require('./FreightManagementBack');
const port = 5000;
const server = http.createServer(async (req, res) => {
  if (req.method === 'GET') {
    console.log('Client connected');  
    const requestUrl = url.parse(req.url, true); // Parse the request URL
    if(requestUrl.pathname  === '/getEmptyReturns'){
        try{
            await EmptyReturns(res);
          }catch (error) {
            console.error('Error retrieving data:', error);
            res.write('{}');
        }
    }else if(requestUrl.pathname  === '/getEmptyReturnsByTransporter'){
      const TransporterId = req.query.TransporterId
      try{
          await EmptyReturnsByTransporterId(res,TransporterId);
        }catch (error) {
          console.error('Error retrieving data:', error);
          res.write('{}');
      }
    }else if(requestUrl.pathname  === '/getDriversByTransporter'){
      const TransporterId = req.query.TransporterId
        try{
          await DriversByTransporter(res,TransporterId);
        }catch (error) {
          console.error('Error retrieving data:', error);
          res.write('{}');
        }
    }else if(requestUrl.pathname  === '/getVehiculesByTransporter'){
      const TransporterId = req.query.TransporterId
        try{
          await VehiculesByTransporter(res,TransporterId);
        }catch (error) {
          console.error('Error retrieving data:', error);
          res.write('{}');
        }
    }else if(requestUrl.pathname  === '/addNewEmptyReturn'){
      const start_location = requestUrl.query.start_location
      const end_location = requestUrl.query.end_location
      const price = requestUrl.query.price
      const status = requestUrl.query.status
      const description = requestUrl.query.description
      const emptyReturn = {
        id:,
        start_location:start_location,
        end_location:end_location,
        price:price,
        status:status,
        description:description}
      id = id + 1

      console.log(emptyReturn);
        try{
          await insertEmptyReturn(res,emptyReturn);
        }catch (error) {
          console.error('Error retrieving data:', error);
          res.write('{}');
        }
    }  else {
        res.writeHead(404, { 'Content-Type': 'text/html' });
        res.write('<h1>404 Not Found</h1>');
        res.end();
    }
  }else{
    res.writeHead(404, { 'Content-Type': 'text/html' });
    res.write('<h1>404 Not Found</h1>');
    res.end();
  }
})

server.listen(port, () => {
  console.log('Server running on port',port);
});




