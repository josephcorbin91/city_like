'use strict';

var mongoose = require('mongoose'),
    SeattleBuildingPermit = mongoose.model('seattleBuildingPermit');
    exports.list_permits = function(req, res){
        SeattleBuildingPermit.find({},function(err,seattleBuildingPermit){
        if(err)
            res.send(err);
        res.json(seattleBuildingPermit);  
        });
    };
    
    exports.create_permit = function(req, res){
        var new_permit = new SeattleBuildingPermit(req.body);
    new_permit.save(function(err, seattleBuildingPermit){
        if(err)
            res.send(err);
        res.json(seattleBuildingPermit);
    });
    };
    
    exports.read_permit = function(req,res){
        SeattleBuildingPermit.findById(req.params.permitId, function(err,seattleBuildingPermit){
            if(err)
                res.send(err);
            res.json(seattleBuildingPermit);
        });
    };
    
    
    exports.update_permit = function(req,res){
        SeattleBuildingPermit.findOneAndUpdate({_id: req.params.permitId}, req.body, {new: true}, function(err, seattleBuildingPermit){
            if(err)
                res.send(err);
            res.json(seattleBuildingPermit);
          
            
        });
    };
    
    exports.delete_permit = function(req, res){
        SeattleBuildingPermit.remove({_id: req.params.permitId}, function(err, seattleBuildingPermit){
            if(err)
                res.send(err);
            res.json({ message: 'Building Permit deleted'});
        });
    };
  