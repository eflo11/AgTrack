(function () {
    'use strict';

    angular
        .module('app.main', [])
        .controller('MainController', MainController);


    MainController.$inject = ['$scope','$rootScope', '$timeout', '$window'];
    function MainController($scope, $rootScope, $timeout, $window) {

        var vm = this;

        // get info if a person is logged in
        vm.loggedIn = true;

        // check to see if a user is logged in on every request
        $rootScope.$on('$routeChangeStart', function () {
            vm.loggedIn = false;
        });

        // function to handle login form
        vm.doLogin = function () {

            //Auth.login(vm.loginData.username, vm.loginData.password)
            //    .success(function (data) {
            //        $location.path('/');
            //    });
        };

        // function to handle logging out
        vm.doLogout = function () {
            //Auth.logout();
            //$location.path('/login');
        };

        $scope.gridsterOptions = {
            margins: [20, 20],
            columns: 4,
            draggable: {
                handle: 'h3'
            },
            isMobile: false, // stacks the grid items if true
            mobileBreakPoint: 800, // if the screen is not wider that this, remove the grid layout and stack the items
            mobileModeEnabled: true,
            resizable: {
                enabled: true,
                handles: ['n', 'e', 's', 'w', 'ne', 'se', 'sw', 'nw'],

                // optional callback fired when resize is started
                start: function(event, $element, widget) {},

                // optional callback fired when item is resized,
                resize: function(event, $element, widget) {
                    //if (widget.chart.api) widget.chart.api.update();
                    $scope.$broadcast('resize');
                },

                // optional callback fired when item is finished resizing
                stop: function(event, $element, widget) {
                    $timeout(function(){
                        //if (widget.chart.api) widget.chart.api.update();
                        $scope.$broadcast('resize');
                    },400)
                }
            }
        };

        // We want to manually handle `window.resize` event in each directive.
        // So that we emulate `resize` event using $broadcast method and internally subscribe to this event in each directive
        // Define event handler
        $scope.events = {
            resize: function(e, scope){
                $timeout(function(){
                    scope.api.update()
                },200)
            }
        };
        angular.element(window).on('resize', function(e){
            $scope.$broadcast('resize');
        });

        // We want to hide the charts until the grid will be created and all widths and heights will be defined.
        // So that use `visible` property in config attribute
        $scope.config = {
            visible: false
        };
        $timeout(function(){
            $scope.config.visible = true;
        }, 200);



}
})();