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
     * @param licenseNumber
     * @param latitude
     * @param longitude
     * @throws Exception
     */
    public void makeCabOffline(String licenseNumber) throws Exception;

    /**
     * @param licenseNumber
     * @param latitude
     * @param longitude
     * @throws Exception
     */
    public void makeCabOnline(String licenseNumber, double latitude, double longitude) throws Exception;

    /**
     *
     * @param licenseNumber
     * @param color
     * @param model
     * @param year
     * @param capacity
     */
    public void registerCab(String licenseNumber, Color color, String model, int year, int capacity);

    /**
     * @param sourceX
     * @param sourceY
     * @return
     * @throws Exception
     */
    public String requestCab(double sourceX, double sourceY) throws Exception;

    /**
     * @param sourceX
     * @param sourceY
     * @param colorPref
     * @return
     * @throws Exception
     */
    public String requestCab(double sourceX, double sourceY, Color colorPref) throws Exception;

    /**
     * @param licenseNumber
     * @throws Exception
     */
    public void startTrip(String licenseNumber) throws Exception;

    /**
     * @param licenseNumber
     * @param destX
     * @param destY
     * @return
     * @throws Exception
     */
    public double stopTrip(String licenseNumber, double destX, double destY) throws Exception;
}
