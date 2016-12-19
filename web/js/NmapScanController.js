//This controller handles login, retrieves the user object from the UsersService 
// and associates it with the $scope. The $scope is  bound to the view
app.controller('NmapScanController', function($scope, $http, $location ,$rootScope , $sce) {


    
    
   init();
$scope.loading = false; 
    function init() {

 if ($location.path() === "/scanres"){
            }
     
 } ;
 


    $scope.submitNmapScan = function() {
        $scope.loading = true;  //this is used to show 
       $('#submit').attr('disabled','disabled');
        var requestParameters = $scope.ip;
        $http.post('http://10.20.150.78:8080/SeniorFrontEnd/rs/users/networkscan', requestParameters)
                .success(function(response) {
                    console.log(response);
                    $scope.errorMessage = '';
       $rootScope.nampScan = response;
                    $scope.nampScan = response;
                    $scope.loading = false;
                    $location.url('/nmapscanres#partialsDiv'); //route to the home page
                })
                .error(function(response, status, headers, config) {
                    $scope.errorMessage = response;
                    $scope.user = null;
                    console.log(response, status, config);
                    $scope.loading = false;
                });
    };
    
    $scope.getScan = function (){
        return $rootScope.nampScan;
    };
    
    $scope.getString = function (text){
        return  $sce.trustAsHtml(text);
    };
    
    
    
    
        $scope.submitScan = function() {
         $scope.loading = true;  //this is used to show 
       $('#submit').attr('disabled','disabled');
      $( "input:checkbox.ip-check-box" ).each(function( index ) {
          
                  var requestParameters = {ip:  $( this ).val() , isNetworkScan: $scope.isNetworkScan, isWebScan:$scope.isWebScan , scanlevel:$scope.scanlevel};
        $http.post('http://10.20.150.78:8080/SeniorFrontEnd/rs/users/scan', requestParameters)
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

});

    };

    
});
