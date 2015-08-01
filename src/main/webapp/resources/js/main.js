var app = angular.module('libApp', []);
app.controller('MainCtrl', function($rootScope, $scope, $http, $location) {

      var authenticate = function(credentials, callback) {

        var headers = credentials ? {authorization : "Basic "
            + btoa(credentials.username + ":" + credentials.password)
        } : {};


        $http.post('/user', {headers : headers}).success(function(data) {
        console.log(data);
          if (data.name) {
            $rootScope.authenticated = true;
              console.log("authenticated");
          } else {
            $rootScope.authenticated = false;
              console.log(":(");
          }
          callback && callback();
        }).error(function() {
          $rootScope.authenticated = false;
          callback && callback();
        });

      }

      authenticate();
      $scope.credentials = {};
      $scope.login = function() {
          authenticate($scope.credentials, function() {
            if ($rootScope.authenticated) {
              $location.path("/");
              $scope.error = false;
            } else {
              $location.path("/");
              $scope.error = true;
            }
          });
      };
      $scope.logout = function() {
        $http.post('logout', {}).success(function() {
          $rootScope.authenticated = false;
          $location.path("/");
        }).error(function(data) {
          $rootScope.authenticated = false;
        });
      }
}).controller('UserCtrl', function($rootScope, $scope, $http, $location) {

    $scope.user = {}
    var postRequest = function(url, dataToSend, onSucces, onError){
        $http.post(url, dataToSend).success(onSucces).error(onError);
    }
    var getRequest = function(url, dataToSend, onSucces, onError){
        $http.get(url, dataToSend).success(onSucces).error(onError);
    }

    postRequest("/rest/get-logged-user", [], function(data){$scope.user=data}, function(){console.log("fail")});

}).controller('EditBookCtrl', function($rootScope, $scope, $http, $location) {

    var postRequest = function(url, dataToSend, onSucces, onError){
        $http.post(url, dataToSend).success(onSucces).error(onError);
    }
    var getRequest = function(url, dataToSend, onSucces, onError){
        $http.get(url, dataToSend).success(onSucces).error(onError);
    }

    $scope.books = [];
    postRequest("/rest/get-books", [], function(data){$scope.books=data}, function(){console.log("fail")});

    this.addBook = function(){
        //TODO data validation
        console.log($scope.book);
        postRequest("/rest/add-book", $scope.book, function(){console.log("succes")},function(){console.log("fail")} )
    };

});