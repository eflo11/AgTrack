(function () {

    angular
        .module('app.grower', ['app.grower.service','angular-storage'])
        .controller('GrowerController', GrowerController)
        .controller('GrowerEditController', GrowerEditController);

    GrowerController.$inject = ['store','$location', 'GrowerService'];
    function GrowerController(store, $location, GrowerService) {

        var vm = this;

        vm.weather = {};

        vm.grower = {};

        var profile = store.get('profile');

        GrowerService.get(profile.email)
           .success(function(grower){
              vm.grower = grower;
           });
        // GrowerService.currentWeather(vm.grower.city, vm.grower.state)
        //     .success(function (conditions) {
        //         vm.weather.conditions = conditions.current_observation;
        //     });
        //
        // GrowerService.hourlyWeather(vm.grower.city, vm.grower.state)
        //     .success(function (hourly) {
        //         vm.weather.hourly = hourly.hourly_forecast;
        //     });
        //
        // GrowerService.forecast(vm.grower.city, vm.grower.state)
        //     .success(function (weather) {
        //         vm.weather.forecast = weather.forecast.simpleforecast.forecastday;
        //     });

        vm.widgets = GrowerService.widgets();
    }

    GrowerEditController.$inject = ['store','GrowerService'];
    function GrowerEditController(store, GrowerService){
        var vm = this;
        
        vm.grower = {};

        var profile = store.get('profile');

        GrowerService.get(profile.email)
            .success(function(grower){
                vm.grower = grower;
            });

        vm.save = function(){
            GrowerService.save(vm.grower)
                .success(function(){
                    
                });
        }
    }

})();