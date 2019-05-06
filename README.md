# Füber
## Modelling Problem:
You are the proprietor of füber, an on call taxi service.
* You have a fleet of cabs at your disposal, and each cab has a location, determined by it's latitude and longitude.
* A customer can call one of your taxis by providing their location, and you must assign the nearest taxi to the customer.
* Some customers are particular that they only ride around in pink cars, for hipster reasons. You must support this ability.
* When the cab is assigned to the customer, it can no longer pick up any other customers 
* If there are no taxis available, you reject the customer's request.
* The customer ends the ride at some location. The cab waits around outside the customer's house, and is available to be assigned to another customer

## Extra Credit:
* When the customer ends the ride, log the total amount the customer owes
* The price is 1 dogecoin per minute, and 2 dogecoin per kilometer. Pink cars cost an additional 5 dogecoin.
* HTML front end showing me all the cars available


## Instructions
1. Create a IFuberService instance. This class handles all the exposed API.
2. We will need to register the cab to the fuber service for the first time, so that all cabs are tracked.
3. Whenever the cab driver wishes to make the cab available for service he can come online by publishing his location to fuber service.
4. When the user wants to use the fuber, they can request for the cab by sending thier source location.
5. Fuber service will then try to locate the closest car available and send it the license number of the cab to the user.
6. User will then start the trip using the cab license number.
7. When the user reaches the destination, we can end the trip by publishing the end location. 
8. The stop API will itself calculate the cost for the trip and send back the results.

        IFuberService fuberService = new FuberService();
        
        // 1. Register cab for first time
        fuberService.registerCab(FuberServiceTest.LICENSENUMBER, Color.RED, "Honda City", 2015, 4);
        
        // 2. Make the cab online/servicable by publishing cab location.
        fuberService.makeCabOnline(FuberServiceTest.LICENSENUMBER, 100, 100);

        // 3. User requests for a cab from a given location
        // We will locate the closest cab and return the cab license number.
        String licenseNumber = fuberService.requestCab(120, 120);

        // 4. Start the trip for the licence number of the reqested cab.
        fuberService.startTrip(licenseNumber);

        // 5. When the trip is completed we will return the cost of the trip
        double cost = fuberService.stopTrip(FuberServiceTest.LICENSENUMBER, 160, 160);
