var express = require('express'),
  app = express(),
  port = process.env.PORT || 3000,
  mongoose = require('mongoose'),
  BuildingPermit = require('./api/models/model'),
  bodyParser = require('body-parser');
  app.use(express.static(__dirname));
  
  mongoose.Promise = global.Promise;
  global.db = mongoose.connect('mongodb://localhost/seattleBuildingPermit');
  
  app.use(bodyParser.urlencoded({extended: true}));
  app.use(bodyParser.json());
  var routes = require('./api/routes/routes');
  routes(app);
  

app.listen(port);

console.log('cityLike RESTful API server started on: fukc you' + port);
