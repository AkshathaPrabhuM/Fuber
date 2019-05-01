/*
 * Class: Car
 * 
 * Created on Apr 30, 2019
 * 
 * (c) Copyright Lam Research Corporation, unpublished work, created 2019
 * All use, disclosure, and/or reproduction of this material is prohibited
 * unless authorized in writing.  All Rights Reserved.
 * Rights in this program belong to:
 * Lam Research Corporation
 * 4000 N. First Street
 * San Jose, CA
 */
package com.quintype.aks.fuber;

public class Car extends Vehicle
{

    private Status status;

    private Location location;

    public Car(String licenseNo)
    {
        super(licenseNo);
        status = Status.ASSIGNED;
    }

    public Status getStatus()
    {
        return status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }

    public void setLocation(Location sourceLocation)
    {
        this.location = sourceLocation;
    }

    public Location getLocation()
    {
        return this.location;
    }

}
