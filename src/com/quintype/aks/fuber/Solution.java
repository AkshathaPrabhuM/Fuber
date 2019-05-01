/*
 * Class: Solution
 * 
 * Created on Apr 30, 2019
 * 
 * You are the proprietor of fuber, an on call taxi service.
 *      - You have a fleet of cabs at your disposal, and each cab has a location, determined by it's latitude and longitude.
 *      - A customer can call one of your taxis by providing their location, and you must assign the nearest taxi to the customer.
 *      - Some customers are particular that they only ride around in pink cars, for hipster reasons. You must support this ability.
 *      - When the cab is assigned to the customer, it can no longer pick up any other customers
 *      - If there are no taxis available, you reject the customer's request.
 *      - The customer ends the ride at some location. The cab waits around outside the customers house, 
 *        and is available to be assigned to another customer.
 * 
 */
package com.quintype.aks.fuber;

import java.util.ArrayList;
import java.util.List;

public class Solution
{

    public static void main(String[] args)
    {
        Solution solution = new Solution();

        String liceseNumber = "KA03JA6680";

        Location location = new Location(34, 35);

        solution.addCab(liceseNumber, 34, 35);

        String licenseNumber = requestCab(34, 35, 90, 97);

    }

    public void addCab(String licenseNumber, float latitude, float longitude)
    {
        Location sourceLocation = new Location(latitude, longitude);

        Car cab = new Car(licenseNumber);
        cab.setLocation(sourceLocation);

        TaxiServiceManager.getInstance().addCars(cab);
    }

}
