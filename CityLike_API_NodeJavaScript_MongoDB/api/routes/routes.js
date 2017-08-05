'use strict';
module.exports = function(app){
    var seattleBuildingPermits = require('../controllers/seattleBuildingPermitsController');

    app.route('/buildingPermits')
        .get(seattleBuildingPermits.list_permits)
        .post(seattleBuildingPermits.create_permit);
    
    
    
    app.route('buildingPermits/:permitId')
        .get(seattleBuildingPermits.read_permit)
        .put(seattleBuildingPermits.update_permit)
        .delete(seattleBuildingPermits.delete_permit);
        
        };
        