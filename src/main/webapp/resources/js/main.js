var app = angular.module('libApp', [
                                     'ngRoute',
                                     'appControllers'
                                   ]);

app.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/book-editor', {
        templateUrl: 'templates/bookeditor.html',
        controller: 'EditBookCtrl as editctrl'
      }).
      when('/service-book', {
        templateUrl: 'templates/servicebook.html',
        controller: 'BookServiceCtrl as servicectrl'
      }).
      when('/return-book', {
        templateUrl: 'templates/returnbook.html',
        controller: 'ReturnBookCtrl as returnctrl'
      }).
      when('/check-service', {
        templateUrl: 'templates/checkservice.html',
        controller: 'CheckServiceCtrl'
      }).
      otherwise({
        redirectTo: '/'
      });
  }]);
