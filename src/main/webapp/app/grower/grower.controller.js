(function () {

    angular
        .module('app.grower', ['app.grower.service'])
        .controller('GrowerController', GrowerController)
        .controller('GrowerEditController', GrowerEditController);

    GrowerController.$inject = ['$scope','$location', '$window', 'GrowerService'];
    function GrowerController($scope, $location, $window, GrowerService) {

        var vm = this;

        vm.weather = {};

        vm.grower = {};

        var profile = $window.localStorage.getItem('profile');

        GrowerService.get(profile.email)
           .success(function(grower){
              vm.grower = grower;
           });
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

    GrowerEditController.$inject = ['GrowerService'];
    function GrowerEditController(GrowerService){

    }

})();