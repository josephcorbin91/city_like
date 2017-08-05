'use strict';
var mongoose = require('mongoose');
var databaseSchema = mongoose.Schema;


var seattleDataSchema = new databaseSchema({
   permit_number: Number,
   permit_type: String,  
   address: String,
   description: String,
   category: String,
   action_type: String,
   work_type: String,
   value: Number,
   longitude: Number,
   latitude: Number,
   url: String,
   current_status: String,
   
   
 
});

module.exports = mongoose.model('seattleBuildingPermit', seattleDataSchema);