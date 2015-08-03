var app = angular.module('libApp', [
                                     'ngRoute',
                                     'appControllers'
                                   ]);

app.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/book-editor', {
        templateUrl: 'templates/bookeditor.html',
        //controller: 'MainCtrl'
      }).
      when('/service-book', {
        templateUrl: 'templates/servicebook.html',
        //controller: 'MainCtrl'
      }).
      when('/return-book', {
        templateUrl: 'templates/returnbook.html',
        //controller: 'MainCtrl'
      }).
      otherwise({
        redirectTo: '/'
      });
  }]);
