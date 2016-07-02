(function() {
    'use strict';

    angular
        .module('app', [
            'nvd3',
            'gridster',
            'ui.bootstrap',
            'app.routes',
            'app.main',
            'app.grower',
            'app.crop'
        ])
        .factory('httpInterceptor', HttpInterceptor)
        .config(Config);


    HttpInterceptor.$inject = ['$q', '$location'];
    function HttpInterceptor($q, $location){
        return{

            request: function(config){
                if(!config.url.startsWith('http') && !config.url.startsWith('uib')) {
                    config.url = _contextPath + config.url;
                }
                return config || $q.when(config);
            }

        }
    }

    Config.$inject = ['$httpProvider'];
    function Config($httpProvider){
        $httpProvider.interceptors.push('httpInterceptor');
    }


})();