'use strict';
var mongoose = require('mongoose');
var databaseSchema = mongoose.Schema;


var seattleDataSchema = new databaseSchema({
   permit_number: {type: Number, default: '0'},
   category: {type: String, default: 'N/A'},
   description: {type: String, default: 'N/A'},
   date: {type: String, default: "N/A"},
   address: {type: String, default: 'N/A'},
   value: {type: Number, default: '0'},
   longitude: {type: Number, default: '0'},
   latitude: {type: Number, default: '0'}
   
   
 
});

module.exports = mongoose.model('seattleBuildingPermit', seattleDataSchema);