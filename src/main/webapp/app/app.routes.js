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
                controllerAs: 'vm',
                requiresLogin: true
            })
            .when('/crops',{
                templateUrl: '/app/crop/all.html',
                controller: 'CropController',
                controllerAs: 'vm',
                requiresLogin: true
            })
            .when('/grower',{
                templateUrl: '/app/grower/edit.html',
                controller: 'GrowerEditController',
                controllerAs: 'vm',
                requiresLogin: true
            })
            .when('/login',{
                templateUrl: '/app/login/login.html',
                controller: 'LoginController',
                controllerAs: 'vm'
            })
            .otherwise({
                redirectTo: '/'
            });

        //$locationProvider.html5Mode(true);
    }


})();