(function () {

    angular
        .module('app.grower', ['app.grower.service'])
        .controller('GrowerController', GrowerController);

    GrowerController.$inject = ['$scope','$location', '$routeParams', 'GrowerService'];
    function GrowerController($scope, $location, $routeParams, GrowerService) {

        var vm = this;

        vm.weather = {};

        vm.grower = {
            user: 'TGROWER',
            name: 'TEST GROWER',
            address: '123 Main St.',
            address2: '',
            city: 'Portland',
            state: 'OR',
            zip: 97002
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
        GrowerService.currentWeather(vm.grower.city, vm.grower.state)
            .success(function (conditions) {
                vm.weather.conditions = conditions.current_observation;
            });

        GrowerService.hourlyWeather(vm.grower.city, vm.grower.state)
            .success(function (hourly) {
                vm.weather.hourly = hourly.hourly_forecast;
            });

        GrowerService.forecast(vm.grower.city, vm.grower.state)
            .success(function (weather) {
                vm.weather.forecast = weather.forecast.simpleforecast.forecastday;
            });

        vm.widgets = GrowerService.widgets();
    }

})();