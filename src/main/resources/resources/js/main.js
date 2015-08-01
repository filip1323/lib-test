var app = angular.module('libApp', []);
app.controller('mainCtrl', function($scope) {
    this.user = {isLogged: false};
    this.refresh = function(){
        $scope.$apply();
    }
});