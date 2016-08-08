(function(){

    angular.module('app.login',['angular-storage'])
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$scope', 'auth', '$location', 'store']
    function LoginController($scope, auth, $location, store){
        $scope.login = function(){
            auth.signin({popup: true}, function(profile, token){
                store.set('profile', profile);
                store.set('token', idToken);
            }, function(err){
                // If anything goes wrong
            });
        };

        $scope.logout = function() {
            auth.signout();
            store.remove('profile');
            store.remove('token');
            $location.path('/login');
        }
    }

})();