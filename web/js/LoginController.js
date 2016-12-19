//This controller handles login, retrieves the user object from the UsersService 
// and associates it with the $scope. The $scope is  bound to the view
app.controller('LoginController', function($scope, $http, $location,$rootScope) {
    //$scope.semester = user.currentSemester;
    //You can an init() for controllers that need to perform some initialization. Keeps things in
    //one place...not required though especially for a simple controller
//    init();

//    function init() {
//        $scope.email = 'sebti'; //just to make login easier
//        $scope.password = 'password'; //just to make login easier
//        $scope.user = null;
//        $scope.errorMessage = '';
//        //route to the login view
//        $location.url('/login');
//    }
//    ;

//    $scope.isLoggedIn = function() {
//        return !($scope.user === null || angular.isUndefined($scope.user));  //if null then isLoggedIn should be false
//    };
    $scope.styles = ["active","success", "warning" , "danger" , "info"];
    $scope.currentColor=0;
    $scope.email = "";
    $scope.password = "";

    
    $scope.getStyle = function() {
        return $scope.styles[  $scope.currentColor++%5];
    };
    $scope.login = function() {
        $scope.loading = true;  //this is used to show 
        console.log($scope.email);
        var requestParameters = {email: $scope.email, password: $scope.password};
        $http.post('http://10.20.150.78:8080/SeniorFrontEnd/rs/users/login', requestParameters)
                .success(function(response) {
                    console.log(response);
                    $scope.errorMessage = '';
                    $rootScope.user = response;
                    $scope.loading = false;
                    $location.url('/#partialsDiv'); //route to the home page
                })
                .error(function(response, status, headers, config) {
                    $scope.errorMessage = response;
                    $rootScope.user = null;
                    console.log(response, status, config);
                    $scope.loading = false;
                });
    };
    
    $scope.signup = function() {
        $scope.loading = true;  //this is used to show 
        var requestParameters = {email: $scope.email, password: $scope.password , firstName:$scope.firstName , lastName:$scope.lastName };
        $http.post('http://10.20.150.78:8080/SeniorFrontEnd/rs/users/signup', requestParameters)
                .success(function(response) {
                    console.log(response);
                    $scope.errorMessage = '';
                    $rootScope.user = response;
                    $scope.loading = false;
            $('#signupbox').hide();
            $('#loginbox').show();
               
                })
                .error(function(response, status, headers, config) {
                    $scope.errorMessage = response;
                    $rootScope.user = null;
                    console.log(response, status, config);
                    $scope.loading = false;
                });
    };

    $scope.logout = function() {
        init();
    };
});