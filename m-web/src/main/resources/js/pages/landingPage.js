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
        scope: {
            streetName: '@',
            cssClass: '@',
            numberOfLayers: '@',
            price: '@'
        },
        link: function (scope, elem, attrs) {
        }
    };
});


main.directive('leftPlain', function () {
    return {
        restrict: 'AE',
        replace: 'true',
        templateUrl: 'directives/electric.html',
        scope: {
            streetName: '@',
            cssClass: '@',
            numberOfLayers: '@',
            price: '@'
        },
        link: function (scope, elem, attrs) {
        }
    };
});


main.directive('rightPlain', function () {
    return {
        restrict: 'AE',
        replace: 'true',
        templateUrl: 'directives/rightPlain.html',
        scope: {
            streetName: '@',
            cssClass: '@',
            numberOfLayers: '@',
            price: '@'
        },
        link: function (scope, elem, attrs) {
        }
    };
});


main.directive('conner', function () {
    return {
        restrict: 'AE',
        replace: 'true',
        templateUrl: 'directives/conner.html',
        scope: {
            cssClass: '@'
        },
        link: function (scope, elem, attrs) {
        }
    };
});


main.directive('rightSimple', function () {
    return {
        restrict: 'AE',
        replace: 'true',
        templateUrl: 'directives/rightSimple.html',
        scope: {
            cssClass: '@',
            displayText: '@'
        },
        link: function (scope, elem, attrs) {
        }
    };
});


main.directive('southLandDirective', function () {
    return {
        restrict: 'AE',
        replace: 'true',
        templateUrl: 'directives/southLandDirective.html',
        scope: {
            streetName: '@',
            cssClass: '@',
            numberOfLayers: '@',
            price: '@'
        },
        link: function (scope, elem, attrs) {
        }
    };
});


main.directive('northLandDirective', function () {
    return {
        restrict: 'AE',
        replace: 'true',
        templateUrl: 'directives/northLandDirective.html',
        scope: {
            streetName: '@',
            cssClass: '@',
            numberOfLayers: '@',
            price: '@'
        },
        link: function (scope, elem, attrs) {
        }
    };
});


main.directive('incomeTaxDirective', function () {
    return {
        restrict: 'AE',
        replace: 'true',
        templateUrl: 'directives/southLandDirective.html',
        scope: {
            cssClass: '@',
            price: '@'
        },
        link: function (scope, elem, attrs) {
        }
    };
});


main.directive('plainDirective', function () {
    return {
        restrict: 'AE',
        replace: 'true',
        templateUrl: 'directives/plianDirective.html',
        scope: {
            cssClass: '@',
            price: '@'
        },
        link: function (scope, elem, attrs) {
        }
    };
});

main.directive('chance', function () {
    return {
        restrict: 'AE',
        replace: 'true',
        templateUrl: 'directives/chance.html',
        scope: {
            cssClass: '@'
        },
        link: function (scope, elem, attrs) {
        }
    };
});

main.directive('rightLandDirective', function () {
    return {
        restrict: 'AE',
        replace: 'true',
        templateUrl: 'directives/rightDirective.html',
        scope: {
            streetName: '@',
            cssClass: '@',
            numberOfLayers: '@',
            price: '@'
        },
        link: function (scope, elem, attrs) {
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
            amountTitle: '@'
        },
        link: function (scope, elem, attrs) {
        }
    };
});


main.directive('ranking', function () {
    return {
        restrict: 'AE',
        replace: 'true',
        templateUrl: 'directives/rankingDirective.html',
        scope: {
            rank: '@',
            rankingStars: '@'
        },
        link: function (scope, elem, attrs) {
        }
    };
});



main.directive('chipDirective', function () {
    return {
        restrict: 'AE',
        replace: 'true',
        templateUrl: 'directives/chipDirective.html',
        scope: {
            chipId: '@',
            cssClass: '@'
        },
        link: function (scope, elem, attrs) {
        }
    };
});


main.directive('dice', function () {
    return {
        restrict: 'AE',
        replace: 'true',
        templateUrl: 'directives/dice.html',
        scope: {

        },
        link: function (scope, elem, attrs) {
            $(function (){
                var dice = $("#dice");
                dice.click(function () {
                    $(".wrap").append("<div id='dice_mask'></div>");//add mask
                    dice.attr("class", "dice");//After clearing the last points animation
                    dice.css('cursor', 'default');
                    var num = Math.floor(Math.random() * 6 + 1);//random num 1-6
                    dice.animate({left: '+2px'}, 100, function () {
                        dice.addClass("dice_t");
                    }).delay(200).animate({top: '-2px'}, 100, function () {
                        dice.removeClass("dice_t").addClass("dice_s");
                    }).delay(200).animate({opacity: 'show'}, 600, function () {
                        dice.removeClass("dice_s").addClass("dice_e");
                    }).delay(100).animate({left: '-2px', top: '2px'}, 100, function () {
                        dice.removeClass("dice_e").addClass("dice_" + num);
                        $("#result").html("Your throwing points are<span>" + num + "</span>");
                        dice.css('cursor', 'pointer');
                        $("#dice_mask").remove();//remove mask
                    });
                });
            });
        }
    };
});
