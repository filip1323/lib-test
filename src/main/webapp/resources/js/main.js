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

    postRequest("/rest/get-logged-user", [], function(data){$scope.user=data; console.log($scope.user)}, function(){console.log("fail")});

}).controller('EditBookCtrl', function($rootScope, $scope, $http, $location, $timeout) {
      //TODO accept null vars
      var postRequest = function(url, dataToSend, onSucces, onError){
          $http.post(url, dataToSend).success(onSucces).error(onError);
      }
      var getRequest = function(url, dataToSend, onSucces, onError){
          $http.get(url, dataToSend).success(onSucces).error(onError);
      }

      $scope.books = [];

      refreshBooks = function(){
          postRequest("/rest/get-books", [], function(data){$scope.books=data}, function(){console.log("fail")});
      }

      refreshBooks();

      this.addBook = function(){
          //TODO data validation
          console.log($scope.book);
          postRequest("/rest/add-book", $scope.book, function(data){$scope.book.id = data; $scope.books.push($scope.book); $scope.book = {}}, function(){} );

      };

      this.removeBook = function(index){
          var bookid = $scope.books[index].id;
          getRequest("/rest/remove-book/" + bookid, null, function(){}, function(){});
          $scope.books.splice(index, 1);
      }

      this.editBook = function(index){
          $scope.book = JSON.parse(JSON.stringify($scope.books[index])); //cloning
          $scope.book.index = index;
      }

      this.stopEdit = function(){
          $scope.book = {};
      }

      this.updateBook = function(){
            postRequest("/rest/update-book", $scope.book, function(){$scope.book = {}}, function(){});
            $scope.books[$scope.book.index] = $scope.book;
      }

}).controller('BookServiceCtrl', function($rootScope, $scope, $http, $location, $timeout) {
        //TODO accept null vars
        var postRequest = function(url, dataToSend, onSucces, onError){
            $http.post(url, dataToSend).success(onSucces).error(onError);
        }
        var getRequest = function(url, dataToSend, onSucces, onError){
            $http.get(url, dataToSend).success(onSucces).error(onError);
        }

        $scope.books = [];

        refreshBooks = function(){
            postRequest("/rest/get-available-books", [], function(data){$scope.books=data}, function(){console.log("fail")});
        }

        refreshBooks();


        this.borrowBook = function(index){
            var book = $scope.books[index];
            postRequest("/rest/borrow-book", book, function(){}, function(){});
            $scope.books.splice(index, 1);
        }

}).controller('ReturnBookCtrl', function($rootScope, $scope, $http, $location, $timeout) {
        //TODO accept null vars
        var postRequest = function(url, dataToSend, onSucces, onError){
            $http.post(url, dataToSend).success(onSucces).error(onError);
        }
        var getRequest = function(url, dataToSend, onSucces, onError){
            $http.get(url, dataToSend).success(onSucces).error(onError);
        }

        $scope.books = [];

        refreshBooks = function(){
            getRequest("/rest/get-borrowed-books", [], function(data){$scope.books=data}, function(){console.log("fail")});
        }

        refreshBooks();


        this.returnBook = function(index){
            var book = $scope.books[index];
            postRequest("/rest/return-book", book, function(){}, function(){});
            $scope.books.splice(index, 1);
        }

    });