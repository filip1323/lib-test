var appControllers = angular.module('appControllers', []);

appControllers.factory('myRestService', function($http) {
    return {
        postRequest: function(url, dataToSend, onSucces, onError){
            $http.post(url, dataToSend).success(onSucces).error(onError);
        },
        getRequest: function(url, dataToSend, onSucces, onError){
            $http.get(url, dataToSend).success(onSucces).error(onError);
        }
    };
});

appControllers.run(function($rootScope, myRestService) {
    $rootScope.appData = myRestService;
});

appControllers.controller('MainCtrl', function($rootScope, $scope, $http, $location) {

    var authenticate = function(credentials, callback) {

        var headers = credentials ? { authorization : "Basic " + btoa(credentials.username + ":" + credentials.password) } : {};


        $http.post('/user', {headers : headers}).success(function(data) {
            if (data.name) {
                $rootScope.authenticated = true;
            } else {
                $rootScope.authenticated = false;
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

    $scope.user = {};

    $scope.appData.postRequest("/rest/get-logged-user", [], function(data){$scope.user=data;}, function(){});

}).controller('EditBookCtrl', function($rootScope, $scope, $http, $location, $timeout) {

    $scope.books = [];

    refreshBooks = function(){
        $scope.appData.postRequest("/rest/get-available-books", [], function(data){$scope.books=data}, function(){});
    }

    refreshBooks();

    this.addBook = function(){
    //TODO data validation
        $scope.appData.postRequest("/rest/add-book", $scope.book, function(data){$scope.book.id = data; $scope.books.push($scope.book); $scope.book = {}}, function(){} );
    };

    this.removeBook = function(bookToRemove){
        $scope.book = {};
        $scope.appData.getRequest("/rest/remove-book/" + bookToRemove.id, null, function(){}, function(){});
        $scope.books.splice($scope.books.indexOf(bookToRemove), 1);
    }

    this.editBook = function(bookToEdit){
        $scope.book = JSON.parse(JSON.stringify(bookToEdit)); //cloning
    }

    this.stopEdit = function(){
        $scope.book = {};
    }

    this.updateBook = function(){
        //TODO FIX IT TO CLIENTSIDE
        $scope.appData.postRequest("/rest/update-book", $scope.book, function(){$scope.book = {}; refreshBooks();}, function(){});
    }

}).controller('BookServiceCtrl', function($rootScope, $scope, $http, $location, $timeout) {
    //TODO accept null vars

    $scope.books = [];

    refreshBooks = function(){
        $scope.appData.postRequest("/rest/get-available-books", [], function(data){$scope.books=data}, function(){});
    }

    refreshBooks();
    $scope.servicePeriod = 7;

    this.borrowBook = function(book){
        $scope.book = book;
    }

    this.confirmBorrowBook = function(){
        $scope.appData.postRequest("/rest/borrow-book/" + $scope.servicePeriod, $scope.book, function(){}, function(){});
        $scope.books.splice($scope.books.indexOf($scope.book), 1);
        $scope.book = null;
    }

    this.resign = function(){
        $scope.book = null;
    }

}).controller('ReturnBookCtrl', function($rootScope, $scope, $http, $location, $timeout) {

    $scope.books = [];

    refreshBooks = function(){
        $scope.appData.getRequest("/rest/get-borrowed-books", [], function(data){$scope.books=data; }, function(){});
    }

    this.parseTimeleft = function(timeleft){
        return Math.round(timeleft/(24*60*60*1000)) + (timeleft < 2 && timeleft >= 1 ? " day" : " days");
    }

    this.getReturnButtonClass = function(timeleft){
        return (timeleft/(24*60*60*1000) > 0) ? "success" : "danger";
    }

    refreshBooks();


    this.returnBook = function(book){
        $scope.appData.postRequest("/rest/return-book", book, function(){}, function(){});
        $scope.books.splice($scope.books.indexOf(book), 1);
    }
}).controller('CheckServiceCtrl', function($rootScope, $scope, $http, $location, $timeout) {
    $scope.services = [];

    refreshServices = function(){
        $scope.appData.getRequest("/rest/get-all-services", [], function(data){$scope.services=parseServices(data); console.log($scope.services)}, function(){});
    }

    Date.prototype.getMyDateFormat = function(){
        return this.getFullYear() + "-" + ((this.getMonth() < 9) ? "0" : "") + (this.getMonth()+1) + "-" + ((this.getDate() < 10) ? "0" : "") + this.getDate();
    }

    parseServices = function(data){
        var newData = [];
        for(var i = 0 ; i < data.length; i++){
            newData.push(new Service(data[i]));
        }
        return newData;
    }

    function Service(data) {
       this.user = data.user;
       this.book = data.book;
       this.startTime = new Date(data.startTime).getMyDateFormat();
       this.endTime = new Date(data.endTime).getMyDateFormat();
    }

    Service.prototype.fullName = function() {
       return this.user.firstname + " " + this.user.lastname;
    }

//    this.parseTimeleft = function(timeleft){
//        return Math.floor(timeleft/(24*60*60*1000)) + (timeleft < 2 && timeleft >= 1 ? " day" : " days");
//    }

    refreshServices();


});