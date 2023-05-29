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
// Authentification
function Authentification(db,user,password){
    db('muser')
        .select()
        .where('userName', '=', user)
        .where('password', '=', password)
        .then((results) => {
          console.log('Retrieved data:', results[0]);
          const jsonData = JSON.stringify(results[0]);
          return jsonData
        })
        .catch((err) => {
          console.error('Error retrieving data:', err);
          return null
        });
}
function getDataFromDatabase(db,user,password) {
    return new Promise((resolve, reject) => {
      db('muser')
        .select()
        .where('userName', '=', user)
        .where('password', '=', password)
        .then((data) => {
          resolve(data); // Resolve the promise with the retrieved data
        })
        .catch((error) => {
          reject(error); // Reject the promise with the error
        });
    });
  }

module.exports = {
    Connect,
    Disconnect,
    Authentification,
    getDataFromDatabase
  };


