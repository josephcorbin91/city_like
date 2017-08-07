'use strict';
var mongoose = require('mongoose');
var databaseSchema = mongoose.Schema;


var seattleDataSchema = new databaseSchema({
   id: Number,
   category: String,
   description: String,
   address: String,
   value: Number,
   longitude: Number,
   latitude: Number,
   
   
 
});

module.exports = mongoose.model('seattleBuildingPermit', seattleDataSchema);