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
        
        if(req.body.batch){
            SeattleBuildingPermit.create(req.body.batch,function(err){
                if(err)
                    res.send(err);
                else
                    res.json(req.body);
            });
        }
        else{
            var new_permit = new SeattleBuildingPermit(req.body);
            new_permit.save(function(err, seattleBuildingPermit){
                if(err)
                    res.send(err);
                else
                    res.json(seattleBuildingPermit);
            });
        }
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
    
    exports.delete_all_permits = function(req, res){
        SeattleBuildingPermit.remove({},function(err){
            if(err)
                res.send(err);
            res.json({message: 'Building Permits Deleted'});
        });
    };
   