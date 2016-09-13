(function(){

    angular
        .module('app.crop.service', [])
        .factory('CropService', CropService);

    CropService.$inject = ['$http'];
    function CropService($http){

        var cropFactory = {};

        cropFactory.getAll = function(growerId) {
            return $http.get('/rest/crop/all/' + growerId);
            // return [
            //     {
            //         cropId: 1,
            //         growthTime: 6,
            //         latitude: 43.123,
            //         longitude: 128.123,
            //         name: 'Apple',
            //         type: 'Organic',
            //         variety: 'Fuji',
            //         growerId: 1,
            //         plantDate: '20160101',
            //         planned: 1210,
            //         actual: 1400,
            //         yield: 190
            //     },
            //     {
            //         cropId: 2,
            //         growthTime: 12,
            //         latitude: 43.123,
            //         longitude: 128.123,
            //         name: 'Apple',
            //         type: 'Conventional',
            //         variety: 'Pink Lady',
            //         growerId: 1,
            //         plantDate: '20160101',
            //         planned: 700,
            //         actual: 620,
            //         yield: -80
            //     },
            //     {
            //         cropId: 3,
            //         growthTime: 10,
            //         latitude: 43.123,
            //         longitude: 128.123,
            //         name: 'Pear',
            //         type: 'Organic',
            //         variety: 'Asian',
            //         growerId: 1,
            //         plantDate: '20160101',
            //         planned: 450,
            //         actual: 600,
            //         yield: 150
            //     },
            //     {
            //         cropId: 4,
            //         growthTime: 10,
            //         latitude: 43.123,
            //         longitude: 128.123,
            //         name: 'Pear',
            //         type: 'Organic',
            //         variety: 'Bosch',
            //         growerId: 1,
            //         plantDate: '20160101',
            //         planned: 275,
            //         actual: 300,
            //         yield: 25
            //     },
            //     {
            //         cropId: 5,
            //         growthTime: 10,
            //         latitude: 43.123,
            //         longitude: 128.123,
            //         name: 'Pear',
            //         type: 'Conventional',
            //         variety: 'Bosch',
            //         growerId: 1,
            //         plantDate: '20160101',
            //         planned: 320,
            //         actual: 275,
            //         yield: -45
            //     }
            // ];
        };

        cropFactory.get = function(cropId){
            return $http.get('/rest/crop/' + cropId);
        };

        return cropFactory;
    }

})();