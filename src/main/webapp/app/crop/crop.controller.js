(function(){

    angular
        .module('app.crop', ['app.crop.service'])
        .controller('CropController', CropController)
        .controller('CropEditController', CropEditController);

    CropController.$inject = ['store', '$location', '$routeParams', 'CropService'];
    function CropController(store, $location, $routeParams, CropService){

        var vm = this;

        var profile = store.get('profile');

        vm.crops = {};

            CropService.getAll(profile.id)
                .success(function(crops){
                    vm.crops = crops;
                });

        vm.edit = function(cropId){
            $location.path('/crop/' + cropId);
        }

    }

    CropEditController.$inject = ['store', '$location', '$routeParams', 'CropService'];
    function CropEditController(store, $location, $routeParams, CropService){

        var vm = this;

        vm.crop = {};

        CropService.get($routeParams.cropId)
            .success(function(crop){
                vm.crop = crop;
            });

    }

})();