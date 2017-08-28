var UserMock = function (){
	this.uuid = require('node-uuid');
	this.crypto = require('crypto');
	this.User = require('../models/user.js');
	this.seedUsersCount = 10;
	this.users = [];
	this.err = false;
	this.numberAffected =0;
}

UserMock.prototype.setError = function (err){
	this.err = err;
};

UserMock.prototype.setNumberAffected = function (number) {
	this.numberAffected = number;
};

UserMock.prototype.seedUsers = function () {
	for (var i=0; i < this.seedUsersCount; i++){
		var passwordSaltIn = this.uuid.v4(), cryptoIterations = 1000, cryptoKeyLen = 64, passwordHashin;
		var user = new this.User({
			email: 'Test' + i + '@test.com',
			firstName: 'FirstName' + i,
			lastName: 'LastName' + i,
			passwordHash: this.crypto.pbkdf2Sync('Password' + i, passwordSaltIn, cryptoIterations, cryptoKeyLen),
			passwordSalt: passwordSaltIn
			});
			this.users.push(user);
			}
			};
			
	UserMock.prototype.findById = function (id, callback) {
		for (var i = 0, length = this.users.length; i <length;i ++){
			if(this.users[i]._id === id) {
				return callback(this.err, this.users[i]);
			}
		}
		return callback(this.err,null)
	}
	
	UserMock.prototype.findOne = function (where , callback) {
		for (var i = 0, length = this.users.length; i < length; i++){
			if(this.users[i].email === where.email){
				return callback(this.err, this.users[i]);
			}
		}
		return callback(this.err,null);
	}
	
	UserMock.prototrype.save = function(callback) {
		return callback(this.err, this, this.numberAffected);
	};
	
	UserMock.prototype.update = function (conditions, update, callback) {
		return callback(this.err, this.numberAffected);
	};
	
	module.exports = UserMock;
	