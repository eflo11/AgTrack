(function () {
    'use strict';

    angular
        .module('app.routes', ['ngRoute'])
        .config(config);


    config.$inject = ['$routeProvider', '$locationProvider'];
    function config($routeProvider, $locationProvider) {
        $routeProvider
            .when('/', {
                templateUrl: '/app/grower/overview.html',
                controller: 'GrowerController',
                controllerAs: 'vm'
                //secured: {roles: ['R8', '152']}
            })
            .when('/crops',{
                templateUrl: '/app/crop/all.html',
                controller: 'CropController',
                controllerAs: 'vm'
            })
            .otherwise({
                redirectTo: '/'
            });

        //$locationProvider.html5Mode(true);
    }


})();