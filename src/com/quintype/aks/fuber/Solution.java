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

import com.quintype.aks.fuber.exception.CabNotFoundException;

public class Solution
{

    public static void main(String[] args)
    {
        Solution solution = new Solution();

        String liceseNumber = "KA03JA6680";

        solution.addCab(liceseNumber, 34, 35);

        try
        {
            String licenseNumber = solution.requestCab(34, 35, 90, 97);
        }
        catch (CabNotFoundException e)
        {
            e.printStackTrace();
        }

    }

    public void addCab(String licenseNumber, double latitude, double longitude)
    {
        Location sourceLocation = new Location(latitude, longitude);
        Car cab = new Car(licenseNumber, sourceLocation);

        TaxiServiceManager.getInstance().addCars(cab);
    }

    public String requestCab(double sourceX, double sourceY, double destinationX, double destinationY)
            throws CabNotFoundException
    {
        return TaxiServiceManager.getInstance().assignCars(sourceX, sourceY);
    }

}
