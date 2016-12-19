//This controller handles login, retrieves the user object from the UsersService 
// and associates it with the $scope. The $scope is  bound to the view
app.controller('ScanController', function($scope, $http, $location ,$rootScope , $sce) {


    
    
   init();

    function init() {

 if ($location.path() === "/scanres"){
            $('#networkscan').show();
            $('#webscan').show();
            if(!$rootScope.scan.isNetworkScam){
                   $('#networkscan').hide();
            }
            if(!$rootScope.scan.isWebScan){
                   $('#webscan').hide();
            }
     
 }
    }
    ;

    $scope.submitScan = function() {
        $scope.loading = true;  //this is used to show 
       $('#submit').attr('disabled','disabled');
        var requestParameters = {ip: $scope.ip, isNetworkScan: $scope.isNetworkScan, isWebScan:$scope.isWebScan , scanlevel:$scope.scanlevel};
        console.log(requestParameters.scanlevel);
        $http.post('http://10.20.150.78:8080/SeniorFrontEnd/rs/scan', requestParameters)
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
    
    $scope.getScan = function (){
        return $rootScope.scan;
    };
    
    $scope.getString = function (text){
        return  $sce.trustAsHtml(text);
    };

    
});
