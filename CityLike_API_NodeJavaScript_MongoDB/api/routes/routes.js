'use strict';
module.exports = function(app){
    var seattleBuildingPermits = require('../controllers/seattleBuildingPermitsController');

    app.route('/buildingPermits')
        .get(seattleBuildingPermits.list_permits)
        .delete(seattleBuildingPermits.delete_all_permits)
        .post(seattleBuildingPermits.create_permit);
    
    app.route('/createBuildingPermits')
        .post(seattleBuildingPermits.create_multiple_permits);
    
    
    app.route('/buildingPermits/:permitId')
        .get(seattleBuildingPermits.read_permit_by_permit_number)
        .put(seattleBuildingPermits.update_permit)
        .delete(seattleBuildingPermits.delete_permit)

        
        };
        