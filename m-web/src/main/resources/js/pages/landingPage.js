/**
 * Created by chris on 2015/12/18.
 */
var main  = angular.module('multipolyApp', ['ngRoute']);
main.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
            when('/dashboardView', {
                templateUrl: 'dashboardView',
                controller: 'DashboardViewController'
            }).
            when('/gameView', {
                templateUrl: 'gameView',
                controller: 'GameViewController'
            }).
            otherwise({
                redirectTo: '/landingPage'
            });
    }]);

/*
-------------------------------------------------------------------------------------
Adding me controllers
-------------------------------------------------------------------------------------
*/



main.controller('DashboardViewController', function($scope) {
    $scope.message = 'This is Add new order screen';
});

main.controller('GameViewController', function($scope) {
    $scope.message = 'This is Show orders screen';

});

