(function(){

    angular
        .module('app.crop', ['app.crop.service'])
        .controller('CropController', CropController);

    CropController.$inject = ['$location', '$routeParams', 'CropService'];
    function CropController($location, $routeParams, CropService){

        var vm = this;

        vm.crops = CropService.getAll();

    }

})();