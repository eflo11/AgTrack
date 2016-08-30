(function() {
    'use strict';

    angular
        .module('app', [
            'auth0',
            'angular-storage',
            'angular-jwt',
            'nvd3',
            'gridster',
            'ui.bootstrap',
            'app.routes',
            'app.main',
            'app.login',
            'app.grower',
            'app.crop'
        ])
        .factory('httpInterceptor', HttpInterceptor)
        .factory('authInterceptor', AuthInterceptor)
        .config(Config)
        .run(Run);


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
    };

    AuthInterceptor.$inject = ['store'];
    function AuthInterceptor(store) {
        return store.get('token');
    };

    Config.$inject = ['$httpProvider', 'authProvider'];
    function Config($httpProvider, authProvider){
        //Configure Auth0 with credentials
        authProvider.init({
            domain: 'eflory11.auth0.com',
            clientID: 'ODeTtDm5Ru7daOidI5TOclPwGWbcd0uz',
            loginUrl: '/login'
        });

        authProvider.on('loginSuccess', ['$location', 'profilePromise', 'idToken', 'store',
            function($location, profilePromise, idToken, store) {
            // Successfully log in
            // Access to user profile and token
            profilePromise.then(function(profile){
                // profile
                store.set('profile', profile);
                store.set('token', idToken);
            });
            $location.url('/');
        }]);

        authProvider.on('authenticated', function() {
            // if user is authenticated.
            // Useful in re-authentication
        });

        //Called when login fails
        authProvider.on('loginFailure', function() {
            // If anything goes wrong
        });

        $httpProvider.interceptors.push('httpInterceptor');
        //$httpProvider.interceptors.push('authInterceptor');
    };

    Run.$inject = ['$rootScope', 'auth', 'store', 'jwtHelper', '$location'];
    function Run($rootScope, auth, store, jwtHelper, $location){

        auth.hookEvents();

        // // Wrapper function to handle profile and toke storage
        // var saveUserInfo = function(profile, token) {
        //     store.set('profile', profile);
        //     store.set('token', token);
        // };
        //
        // // Called when authentication is successful
        // auth.lockOn("authenticated", function(authResult) {
        //     console.log(authResult);
        //     auth.getProfile(authResult.idToken).then(function (profile) {
        //
        //         console.log(profile);
        //         // Save user info to local storage
        //         saveUserInfo(profile, authResult.idToken);
        //     })
        // });
        // // Called when authentication fails
        // auth.lockOn("error", function(error) {
        //     console.log(error);
        // });
        // // Listen to a location change event
        $rootScope.$on('$locationChangeStart', function() {
            // Grab the user's token
            var token = store.get('token');
            // Check if token was actually stored
            if (token) {
                // Check if token is yet to expire
                if (!jwtHelper.isTokenExpired(token)) {
                    // Check if the user is not authenticated
                    if (!auth.isAuthenticated) {
                        // Re-authenticate with the user's profile
                        auth.authenticate(store.get('profile'), token);
                    }
                } else {
                    // Show the login page
                    $location.path('/login');
                }
            }

        });

    }


})();