angular.module('appModule')
.component('userComponent', {
	templateUrl : 'app/appModule/user/user.component.html',
	controllerAs : 'vm',
	controller : function($routeParams, $location, userService, businessService){
		var vm = this;
		vm.user = null;
		vm.businesses = null; 
		//Init load
		userService.show($routeParams.id).then(function(res){
			vm.user = res.data; 
		}); 
		businessService.index().then(function (res) {
			vm.businesses = res.data;
		});  
		//
		vm.getVehicles = function(){
			$location.path("/user/" + $routeParams.id + "/vehicle"); 
		}
        vm.getSettings = function(){
        		console.log('hello'); 
    			$location.path("/user/" + $routeParams.id + '/settings'); 
        }
		vm.getRequests = function(){
			$location.path("/user/" + $routeParams.id + "/request"); 
		}	
		vm.getBusiness = function(business) {
			$location.path("/user/" + $routeParams.id + "/business/" + business.id)
		}
	}
})	
