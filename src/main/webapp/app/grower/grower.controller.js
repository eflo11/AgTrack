(function () {

    angular
        .module('app.grower', ['app.grower.service'])
        .controller('GrowerController', GrowerController);

    GrowerController.$inject = ['$scope','$location', '$routeParams', 'GrowerService'];
    function GrowerController($scope, $location, $routeParams, GrowerService) {

        var vm = this;

        vm.grower = {
            user: 'TGROWER',
            name: 'TEST GROWER',
            address: '123 Main St.',
            address2: '',
            city: 'Yakima',
            state: 'WA',
            zip: 98908
        };
        //GrowerService.get(1)
        //    .success(function(grower){
        //       vm.grower = grower;
        //
        //        GrowerService.weather(vm.grower.city, vm.grower.state)
        //            .success(function(weather){
        //                vm.weather = weather.forecast.simpleforecast.forecastday;
        //            });
        //    });

        GrowerService.weather(vm.grower.city, vm.grower.state)
            .success(function (weather) {
                vm.weather = weather.forecast.simpleforecast.forecastday;
            });

        vm.widgets = GrowerService.widgets();
    }

})();