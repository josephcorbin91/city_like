var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var userSchema = new Schema({
	email: String,
	firstName: String,
	lastName: String,
	passwordHash: String,
	passwordSalt: String, 
});

module.exports = mongoose.model('User',UserSchema).