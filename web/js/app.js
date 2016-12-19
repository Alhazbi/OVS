var app = angular.module('seniorapp', ['ngRoute', 'ngAnimate']);

//This configures the routes and associates each route with a view and a controller
app.config(function ($routeProvider) {
    $routeProvider
            .when('/',
                    {
                        templateUrl: '/SeniorFrontEnd/partials/services.html'
                    })
            .when('/login',
                    {   
                        controller: 'LoginController',
                        templateUrl: '/SeniorFrontEnd/partials/login.html'
                    })
            .when('/logout',
                    {   
                        controller: 'LoginController'
                    })        
            .when('/scan',
                    {
                        controller: 'ScanController',
                        templateUrl: '/SeniorFrontEnd/partials/scan_req.html'
                    })
            .when('/scanres',
                    {
                        controller: 'ScanController',
                        templateUrl: '/SeniorFrontEnd/partials/scan.html'
                    })
            .when('/history',
                    {
                        controller: 'HistoryController',
                        templateUrl: '/SeniorFrontEnd/partials/history.html'
                    })
            .when('/nmapscan',
                    {
                        controller: 'NmapScanController',
                        templateUrl: '/SeniorFrontEnd/partials/nmap_scan_req.html'
                    })
            .when('/nmapscanres',
                    {
                        controller: 'NmapScanController',
                        templateUrl: '/SeniorFrontEnd/partials/nmap_scan.html'
                    })
             .when('/manageusers',
                    {
                        controller: 'ManageUsersController',
                        templateUrl: '/SeniorFrontEnd/partials/manageusers.html'
                    })
                  .when('/user',
                    {
                        controller: 'ManageUsersController',
                        templateUrl: '/SeniorFrontEnd/partials/user.html'
                    })
            .otherwise({redirectTo: '/'});
    
   
});

  app.run(function($rootScope, $location) {
    $rootScope.isLoggedIn = function() {
        return $rootScope.user != null;
    };
    
    $rootScope.$on( "$routeChangeStart", function(event, next, current) {
      if (!$rootScope.isLoggedIn()) {
          $location.path("/login");
      }
    });
 });