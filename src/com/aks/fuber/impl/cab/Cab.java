/*
 * Class: Cab
 *
 * Created on Apr 30, 2019
 *
 */
package com.aks.fuber.impl.cab;

public class Cab extends Vehicle
{

    private Location location;

    private Status status;

    public Cab(String licenseNo)
    {
        super(licenseNo);
        this.status = Status.OFFLINE;
    }

    public Location getLocation()
    {
        return this.location;
    }

    public Status getStatus()
    {
        return this.status;
    }

    public void setLocation(Location sourceLocation)
    {
        this.location = sourceLocation;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }

}
