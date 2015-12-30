/**
 * Created by chris on 2015/12/18.
 */
var main = angular.module('multipolyApp', ['ngRoute']);
main.config(['$routeProvider',
    function ($routeProvider) {
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


main.controller('DashboardViewController', function ($scope) {
    $scope.message = 'This is Add new order screen';
});

main.controller('GameViewController', function ($scope) {
    $scope.message = 'This is Show orders screen';

});


/*This is where the directives will live*/

main.directive('leftLandDirective', function () {
    return {
        restrict: 'AE',
        replace: 'true',
        templateUrl: 'directives/leftLandDirective.html',
        link: function (scope, elem, attrs) {
            elem.bind('click', function () {
                elem.css('background-color', 'white');
                scope.$apply(function () {
                    scope.color = "white";
                });
            });
            /*This should show the assets that are in that spot ??*/
            elem.bind('mouseover', function () {
                elem.css('cursor', 'pointer');
            });
        }
    };
});


main.directive('score', function () {
    return {
        restrict: 'AE',
        replace: 'true',
        templateUrl: 'directives/scoreDirective.html',
        scope: {
            amount: '@',
            amountTitle:'@'
        },
        link: function (scope, elem, attrs) {
        }
    };
});