const knex = require('knex');
// connecter a la base de donnee
function Connect(){
    const db = knex({
        client: 'mysql',
        connection: {
            host: 'mysql-bossama.alwaysdata.net',
            user: 'bossama_projetal',
            password: 'projetal',
            database: 'bossama_projetalog',
          },
        });
        // check if the connection established  
    db.raw('SELECT 1')
    .then(() => {
    console.log('Connection to the database successful!');
    // Perform other operations
    })
    .catch((error) => {
    console.error('Error connecting to the database:', error);
    });
    return db;
}
// Disconnect from the database
function Disconnect(db){
    db.destroy()
    .then(() => {
      console.log('Disconnected from the database');
    })
    .catch((err) => {
      console.error('Error disconnecting from the database:', err);
    });
}
// get the empty returns
function getEmptyReturns(db) {
    return new Promise((resolve, reject) => {
        db('EmptyReturnTrip')
        .join('Program', 'EmptyReturnTrip.id', '=', 'Program.empty_return_trip_id')
        .join('Date', 'Program.date_id', '=', 'Date.id')
        .select('EmptyReturnTrip.*','Date.start_date')
        .then((data) => {
            resolve(data); // Resolve the promise with the retrieved data
        })
        .catch(error => {
            reject(error); // Reject the promise with the error
        });
    });
}
// get All Empty returns from the database 
async function EmptyReturns(res){
    const db = Connect();
    try {
      const jsonData = await getEmptyReturns(db);
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
}
// get the empty returns
function getEmptyReturnsByTransporterId(db,TransporterId) {
  return new Promise((resolve, reject) => {
      db('EmptyReturnTrip')
      .join('Program', 'EmptyReturnTrip.id', '=', 'Program.empty_return_trip_id')
      .join('Transporter', 'Program.transporter_id', '=', 'Transporter.ClientId')
      .join('Date', 'Program.date_id', '=', 'Date.id')
      .select('EmptyReturnTrip.*','Date.start_date')
      .where('Transporter.ClientId','=',TransporterId)
      .then((data) => {
          resolve(data); // Resolve the promise with the retrieved data
      })
      .catch(error => {
          reject(error); // Reject the promise with the error
      });
  });
}
// get All Empty returns from the database 
async function EmptyReturnsByTransporterId(res,TransporterId){
  const db = Connect();
  try {
    const jsonData = await getEmptyReturnsByTransporterId(db,TransporterId);
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
}
// get drivers of a company "Transporter"
function getDriversByTransporter(db,TransporterId){
  return new Promise((resolve, reject) => {
    db('Chauffeur')
    .select()
    .where('ClientId','=',TransporterId)
    .then((data) => {
        resolve(data); // Resolve the promise with the retrieved data
    })
    .catch(error => {
        reject(error); // Reject the promise with the error
    });
});
}
// get All the drivers of a company(Trasnporter)
async function DriversByTransporter(res,TransporterId){
  const db = Connect();
    try {
      const jsonData = await getDriversByTransporter(db,TransporterId);
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
}
// get drivers of a company "Transporter"
function getVehiculesByTransporter(db,TransporterId){
  return new Promise((resolve, reject) => {
    db('Vehicles')
    .select()
    .where('transporterid','=',TransporterId)
    .then((data) => {
        resolve(data); // Resolve the promise with the retrieved data
    })
    .catch(error => {
        reject(error); // Reject the promise with the error
    });
});
}
// get All the drivers of a company(Trasnporter)
async function VehiculesByTransporter(res,TransporterId){
  const db = Connect();
    try {
      const jsonData = await getVehiculesByTransporter(db,TransporterId);
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
}
// Add new empty return in the data base
function addNewEmptyReturn(db,EmptyReturn){
    return new Promise((resolve, reject) => {
        db('EmptyReturnTrip')
          .insert(EmptyReturn)
          .then((data) => {
            resolve(data); // Resolve the promise with the retrieved data
          })
          .catch((error) => {
            reject(error); // Reject the promise with the error
          });
      });
}
// Add new drive instance
function addNewDrive(db,EmptyReturn){

}
// Add new Programme instance 
function addNewProgram(db,program){
  return new Promise((resolve, reject) => {
    db('Program')
      .insert(program)
      .then((data) => {
        resolve(data); // Resolve the promise with the retrieved data
      })
      .catch((error) => {
        reject(error); // Reject the promise with the error
      });
  });
}
// Connect and insert into the data base 
async function insertEmptyReturn(res,EmptyReturn){
  const db = Connect();
    try {
      await db('EmptyReturnTrip').insert(EmptyReturn);
      res.write('user added with succes');
    } catch (error) {
      console.error('Error retrieving data:', error);
      res.write('{}');
    } finally {
      Disconnect(db);
      res.end();
    }
}
module.exports = {
    EmptyReturns,
    EmptyReturnsByTransporterId,
    DriversByTransporter,
    VehiculesByTransporter,
    insertEmptyReturn
  };


