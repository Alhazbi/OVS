//This controller handles login, retrieves the user object from the UsersService 
// and associates it with the $scope. The $scope is  bound to the view
app.controller('HistoryController', function($scope, $http, $location , $rootScope) {
init();


  function init() {
        $scope.loading = true;  //this is used to show 
       
        $http.post('http://10.20.150.78:8080/SeniorFrontEnd/rs/users/history')
                .success(function(response) {
                    console.log(response);
                    $scope.errorMessage = '';
                    $scope.sites = response;
                    console.log(JSON.stringify($scope.sites));
                    $scope.loading = false;
                    $location.url('/history#partialsDiv'); //route to the home page
                })
                .error(function(response, status, headers, config) {
                    $scope.errorMessage = response;
                    $scope.user = null;
                    console.log(response, status, config);
                    $scope.loading = false;
                });
    };

  
       $scope.loadScan = function(scanId) {
        $http.get('http://10.20.150.78:8080/SeniorFrontEnd/rs/scan/'+scanId)
                .success(function(response) {
                    console.log(response);
                    $scope.errorMessage = '';
                    $rootScope.scan = response;
                    $scope.scan = response;
                    $scope.loading = false;
                    $location.url('/scanres#partialsDiv'); //route to the home page
                })
                .error(function(response, status, headers, config) {
                    $scope.errorMessage = response;
                    $scope.user = null;
                    console.log(response, status, config);
                    $scope.loading = false;
                });
    };
});
