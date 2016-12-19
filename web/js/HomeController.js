//This controller handles login, retrieves the user object from the UsersService 
// and associates it with the $scope. The $scope is  bound to the view
app.controller('HomeController', function ($scope, $http, $location, $rootScope) {
  
    
    $scope.logout = function () {
        delete $rootScope.user;
        $location.path("/login#partialsDiv");
    };
});