
app.controller('ManageUsersController', function($scope, $http, $location) {
  $scope.newuser = {"userType" : "N"};

$scope.addUser=function(newuser){
       console.log("I am in the add function");
        console.log($scope.newuser.password);
         
       // $scope.usersmenu.push(newuser);
            $http({
            method: 'POST',
            dataType: "json",
            data: $scope.newuser,
            url: 'http://10.20.150.78:8080/SeniorFrontEnd/rs/users/'
        }).success(function(response, status, headers) {
            console.log("the returned response is");
           console.log(response);
            $location.url('/manageusers');
            } ).error(function(response, status, headers, config) {
            console.log(response);
       
        });
    
    
};

 //private function not attached to the scope
    var deleteUser = function(userId) {
        var foundAt = $scope.usersmenu.indexOf('id', userId);
        if (userId >= 0) {
            $scope.usersmenu.splice(foundAt, 1);
        }
        
    };

    Array.prototype.indexOf = function (property, value) {
        for (var i = 0; i < this.length; i++) {
            if (this[i][property] === value)
                return i;
        }
        return -1;
    };

    $scope.getUsers = function() {
        $scope.alertMessage = null; //clear alertMessage
        $http({
            method: 'GET',
            dataType: "json",
            url: 'http://10.20.150.78:8080/SeniorFrontEnd/rs/users/'
        }).success(function(response) {
            console.log(response);
           
            $scope.usersmenu = response;
            console.log($scope.usersmenu[0].firstName);
           console.log($scope.usersmenu[0].id);
           console.log($scope.usersmenu[0].password);
            $scope.loading = false;
        }).error(function(response, status, headers, config) {
            $scope.alertMessage = response;
            console.log(response, status, config);
            $scope.loading = false;
        });
    };

    //Call the getUsers to initialiaze the users array
     $scope.getUsers();

    $scope.deleteUser = function(userId) {
        console.log('UserId to delete ' + userId);
       // if (!confirm("Are you sure you want to delete user# " + userId + "?")) {
         //   return;
        //}
        swal({
            title: "Are you sure?",
            text: "You will not be able to recover this user!",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Yes, delete it!",
            cancelButtonText: "No, cancel",
            closeOnConfirm: false,
            closeOnCancel: false
        },
        function (isConfirm) {
            if (isConfirm) {
                swal("Deleted!", "User has been deleted", "success");
            } else {
                swal("Cancelled", "User has failed to delete", "error");
            }
        });

        $http({
            method: 'DELETE',
            url: 'http://10.20.150.78:8080/SeniorFrontEnd/rs/users/' + userId
        }).success(function(response) {
            console.log(response);
            $scope.alertMessage = response;
            //Delete the user on the client side
            deleteUser(userId);
            $scope.loading = false;
        }).error(function(response, status, headers, config) {
            $scope.alertMessage = response;
            console.log(response, status, config);
            $scope.loading = false;
        });
    };

    $scope.clearAlert = function() {
        $scope.alertMessage = null;
    };

    $scope.goBack = function() {
        window.history.back();
    };

});

