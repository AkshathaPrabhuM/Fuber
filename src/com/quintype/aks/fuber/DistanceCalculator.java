/*
 * Class: CabFinder
 * 
 * Created on May 1, 2019
 * 
 */
package com.quintype.aks.fuber;

public class DistanceCalculator
{
    private static DistanceCalculator INSTANCE;

    private DistanceCalculator()
    {
        // Do nothing here
    }

    public static DistanceCalculator getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new DistanceCalculator();
        }
        return INSTANCE;
    }

    public double calculateDistance(double sourceX, double sourceY, double destX, double destY)
    {
        double X = sourceX - sourceY;

        double Y = destX - destY;

        return Math.sqrt(X * X - Y * Y);
    }
}
