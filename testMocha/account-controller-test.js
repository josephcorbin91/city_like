var AccountController = require('../controllers/account.js'),
	mongoose = require('mongoose'),
	should = require('should'),
	uuid = require('crypto'),
	User require('../models/user.js'),
	UserMock = require('./user-mock.js'),
	MailerMock = require('./mailer-mock.js'),
	ApiMessages = require('../models/api-messages.js');
	
	
	describe('AccountController', function() {
		var controller,
			seedUsersCount = 10,
			testUser,
			userModelMock,
			session = {},
			mailMock;
	});
	
	beforeEach(function (done){
		userModelMock = new UserMock();
		mailerMock = new MailerMock();
		controller = new AccountController(userModelMock, session, mailerMock);
		done();
	});
	
	afterEach(function (done){
		userModelMock.setError(false);
		done();
	})