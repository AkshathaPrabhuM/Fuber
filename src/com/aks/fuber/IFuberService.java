/*
 * Class: IFuberService
 *
 * Created on May 2, 2019
 *
 */
package com.aks.fuber;

import com.aks.fuber.impl.cab.Color;

public interface IFuberService
{
    /**
     * This API is used for making the cab offline. When the cab driver is done for the day he can change his status and go offline.
     *
     * @param licenseNumber License Number of the car
     * @throws Exception if the license number provided is incorrect
     */
    public void makeCabOffline(String licenseNumber) throws Exception;

    /**
     * This API is used by the Cab driver when he wants to start his day and serve the cab for passengers.
     * Calling this API will make sure customers can book this cab.
     *
     * @param licenseNumber License number of the car
     * @param latitude The location latitude of the car
     * @param longitude The location longitude of the car
     * @throws Exception , if the car is not already registered and present in the cab manager
     */
    public void makeCabOnline(String licenseNumber, double latitude, double longitude) throws Exception;

    /**
     * This API is for the cab driver for the first time when he wants to register his car under fuber service.
     *
     * @param licenseNumber License number of the car
     * @param color Car color
     * @param model Car model/make
     * @param year The year the car was procured
     * @param capacity the number of people that can be accommodated in the cab
     */
    public void registerCab(String licenseNumber, Color color, String model, int year, int capacity);

    /**
     * This API is exposed to the customer when the customer is requesting for a cab from a given location.
     *
     * @param sourceX Source location latitude.
     * @param sourceY Source location longitude
     * @return The license number of the cab allocated, null otherwise
     * @throws Exception If no cab is found throws an exception.
     */
    public String requestCab(double sourceX, double sourceY) throws Exception;

    /**
     * This API is exposed to the customer when the customer is requesting for a cab from a given location, but also has
     * a color preference and wants to request cabs of specific colors
     *
     * @param sourceX Source location latitude.
     * @param sourceY Source location longitude
     * @param colorPref Indicates the color of the cab
     * @return The license number of the cab allocated, null otherwise
     * @throws Exception If no cab is found throws an exception.
     */
    public String requestCab(double sourceX, double sourceY, Color colorPref) throws Exception;

    /**
     * Once the request cab API returns the license number of the cab allocated, we can invoke start trip on that cab.
     *
     * @param licenseNumber indicates the cab license number which is allocated to the customer
     * @throws Exception if incorrect license number of the cab is provided.
     */
    public void startTrip(String licenseNumber) throws Exception;

    /**
     * When the customer has reached the destination we can end the already started trip.
     *
     * @param licenseNumber License number of the cab allocated to the customer
     * @param destX Destination location latitude
     * @param destY Destination location longitude
     * @return The cost of the trip
     * @throws Exception If the provided license number is incorrect or trip hasn't begun for that cab.
     */
    public double stopTrip(String licenseNumber, double destX, double destY) throws Exception;
}
