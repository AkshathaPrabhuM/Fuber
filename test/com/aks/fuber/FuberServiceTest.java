package com.aks.fuber;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import com.aks.fuber.IFuberService;
import com.aks.fuber.exception.CabNotFoundException;
import com.aks.fuber.exception.IncorrectLicenseNumberException;
import com.aks.fuber.exception.TripNotFoundException;
import com.aks.fuber.impl.CabManager;
import com.aks.fuber.impl.FuberService;
import com.aks.fuber.impl.TaxiServiceManager;
import com.aks.fuber.impl.cab.Cab;
import com.aks.fuber.impl.cab.Color;
import com.aks.fuber.impl.cab.Location;
import com.aks.fuber.impl.cab.Status;

/*
 * Class: FuberServiceTest
 *
 * Created on May 6, 2019
 *
 */

public class FuberServiceTest
{
    private static final String LICENSENUMBER = "KA03JA6680";

    @After
    public void afterEachTest() throws Exception
    {

        TaxiServiceManager.getInstance().removeCab(FuberServiceTest.LICENSENUMBER);
    }

    @Test
    public void testCabManagerWithIncorrectCarRegNumber()
    {
        Cab cab = CabManager.getInstance().getCab("WrongCab");
        Assert.assertNull(cab);
    }

    @Test
    public void testCabRegistration()
    {
        IFuberService fuberService = new FuberService();
        fuberService.registerCab(FuberServiceTest.LICENSENUMBER, Color.RED, "Honda City", 2015, 4);

        Cab cab = CabManager.getInstance().getCab(FuberServiceTest.LICENSENUMBER);
        Assert.assertNotNull(cab);
    }

    @Test
    public void testInValidStopTripRequest()
    {
        IFuberService fuberService = new FuberService();
        try
        {
            fuberService.stopTrip(FuberServiceTest.LICENSENUMBER, 0, 0);
            Assert.fail("Expected to throw exception");
        }
        catch (Exception e)
        {
            Assert.assertEquals("Invalid license Number, Trip not located!", e.getMessage());
        }

    }

    @Test
    public void testMakeCabOffline() throws Exception
    {
        IFuberService fuberService = new FuberService();
        fuberService.registerCab(FuberServiceTest.LICENSENUMBER, Color.RED, "abc", 2019, 5);

        fuberService.makeCabOnline(FuberServiceTest.LICENSENUMBER, 100, 150);

        Assert.assertEquals(Status.ONLINE, CabManager.getInstance().getCab(FuberServiceTest.LICENSENUMBER).getStatus());

        fuberService.makeCabOffline(FuberServiceTest.LICENSENUMBER);

        Assert
            .assertEquals(Status.OFFLINE, CabManager.getInstance().getCab(FuberServiceTest.LICENSENUMBER).getStatus());

    }

    @Test
    public void testMakeCabOnline() throws Exception
    {
        IFuberService fuberService = new FuberService();
        fuberService.registerCab(FuberServiceTest.LICENSENUMBER, Color.RED, "abc", 2019, 5);

        fuberService.makeCabOnline(FuberServiceTest.LICENSENUMBER, 100, 150);

        Assert.assertEquals(Status.ONLINE, CabManager.getInstance().getCab(FuberServiceTest.LICENSENUMBER).getStatus());
    }

    @Test
    public void testPinkTrip() throws Exception
    {
        IFuberService fuberService = new FuberService();
        fuberService.registerCab(FuberServiceTest.LICENSENUMBER, Color.PINK, "Honda City", 2015, 4);
        fuberService.makeCabOnline(FuberServiceTest.LICENSENUMBER, 100, 100);

        String licenseNumber = fuberService.requestCab(120, 120, Color.PINK);

        fuberService.startTrip(licenseNumber);

        double cost = fuberService.stopTrip(FuberServiceTest.LICENSENUMBER, 100, 100);

        Assert.assertEquals(61.56, cost, 0.5);
    }

    @Test
    public void testRemoveCabWhichisNotPresent()
    {
        try
        {
            TaxiServiceManager.getInstance().removeCab("WrongCab");
        }
        catch (CabNotFoundException e)
        {
            Assert.assertEquals("Cab not found for given license number", e.getMessage());
        }
    }

    @Test
    public void testRequestCabsWhenNoCarsAvailable() throws Exception
    {
        IFuberService fuberService = new FuberService();
        try
        {
            fuberService.requestCab(100, 100);
            Assert.fail("Expected to throw exception");
        }
        catch (CabNotFoundException e)
        {
            Assert.assertEquals("Cabs not available at the moment! Please try again later", e.getMessage());
        }
    }

    @Test
    public void testRequestPinkCabs() throws Exception
    {
        IFuberService fuberService = new FuberService();
        fuberService.registerCab(FuberServiceTest.LICENSENUMBER, Color.PINK, "Honda City", 2015, 4);
        fuberService.makeCabOnline(FuberServiceTest.LICENSENUMBER, 150, 150);

        fuberService.registerCab("KA03JP3455", Color.RED, "Brezza", 2017, 4);
        fuberService.makeCabOnline("KA03JP3455", 340, 60);

        fuberService.requestCab(170, 170, Color.PINK);

        Assert
            .assertEquals(Status.ASSIGNED, CabManager.getInstance().getCab(FuberServiceTest.LICENSENUMBER).getStatus());

        TaxiServiceManager.getInstance().removeCab("KA03JP3455");

    }

    @Test
    public void testRequestPinkCabsWhenNoCarsAvailable() throws Exception
    {
        IFuberService fuberService1 = new FuberService();
        try
        {
            fuberService1.requestCab(120, 145, Color.PINK);
            Assert.fail("Expected to throw exception");
        }
        catch (CabNotFoundException e)
        {
            Assert.assertEquals("Cabs not available at the moment! Please try again later", e.getMessage());
        }
    }

    @Test
    public void testRequestPinkCabsWhenNotAvailable() throws Exception
    {
        IFuberService fuberService = new FuberService();
        fuberService.registerCab(FuberServiceTest.LICENSENUMBER, Color.RED, "Honda City", 2015, 4);

        fuberService.makeCabOnline(FuberServiceTest.LICENSENUMBER, 150, 150);

        try
        {
            fuberService.requestCab(170, 170, Color.PINK);
        }
        catch (CabNotFoundException e)
        {
            Assert.assertEquals("PINK cabs not available now! Please try again later!", e.getMessage());
        }

        Assert.assertEquals(Status.ONLINE, CabManager.getInstance().getCab(FuberServiceTest.LICENSENUMBER).getStatus());
    }

    @Test
    public void testRequestRegularCabs() throws Exception
    {
        IFuberService fuberService = new FuberService();
        fuberService.registerCab(FuberServiceTest.LICENSENUMBER, Color.RED, "Honda City", 2015, 4);

        fuberService.makeCabOnline(FuberServiceTest.LICENSENUMBER, 150, 150);

        fuberService.requestCab(170, 170);

        Assert
            .assertEquals(Status.ASSIGNED, CabManager.getInstance().getCab(FuberServiceTest.LICENSENUMBER).getStatus());

    }

    @Test
    public void testReturnTheClosestCab() throws Exception
    {
        IFuberService fuberService = new FuberService();
        fuberService.registerCab(FuberServiceTest.LICENSENUMBER, Color.BLACK, "Honda City", 2015, 4);
        fuberService.makeCabOnline(FuberServiceTest.LICENSENUMBER, 100, 100);

        fuberService.registerCab("KA03JP3455", Color.RED, "Brezza", 2017, 4);
        fuberService.makeCabOnline("KA03JP3455", 340, 60);

        fuberService.requestCab(120, 120);

        Assert
            .assertEquals(Status.ASSIGNED, CabManager.getInstance().getCab(FuberServiceTest.LICENSENUMBER).getStatus());
        Assert.assertEquals(Status.ONLINE, CabManager.getInstance().getCab("KA03JP3455").getStatus());

        TaxiServiceManager.getInstance().removeCab("KA03JP3455");
    }

    @Test
    public void testStartTrip() throws Exception
    {
        IFuberService fuberService = new FuberService();
        fuberService.registerCab(FuberServiceTest.LICENSENUMBER, Color.RED, "Honda City", 2015, 4);
        fuberService.makeCabOnline(FuberServiceTest.LICENSENUMBER, 100, 100);

        String licenseNumber = fuberService.requestCab(120, 120);

        fuberService.startTrip(licenseNumber);

        Cab cab = CabManager.getInstance().getCab(FuberServiceTest.LICENSENUMBER);

        Assert.assertEquals(120, cab.getLocation().getLatitude(), 0);
        Assert.assertEquals(120, cab.getLocation().getLongititude(), 0);

        fuberService.stopTrip(licenseNumber, 0, 0);

    }

    @Test
    public void testStartTripForIncorrectLicenseNumber() throws Exception
    {
        IFuberService fuberService = new FuberService();

        try
        {
            fuberService.startTrip("WrongCab");
            Assert.fail("Expected to throw exception");
        }
        catch (IncorrectLicenseNumberException e)
        {
            Assert.assertEquals("Cannot start the trip, Invalid License Number", e.getMessage());
        }
    }

    @Test
    public void testStartTripForWrongLicenseNumber() throws Exception
    {
        IFuberService fuberService = new FuberService();
        fuberService.registerCab(FuberServiceTest.LICENSENUMBER, Color.RED, "Honda City", 2015, 4);
        fuberService.makeCabOnline(FuberServiceTest.LICENSENUMBER, 100, 100);

        try
        {
            fuberService.startTrip(FuberServiceTest.LICENSENUMBER);
            Assert.fail("Expected to throw exception");
        }
        catch (IncorrectLicenseNumberException e)
        {
            Assert.assertEquals("Cannot start the trip, Invalid License Number", e.getMessage());
        }
    }

    @Test
    public void testStopTrip() throws Exception
    {
        IFuberService fuberService = new FuberService();
        fuberService.registerCab(FuberServiceTest.LICENSENUMBER, Color.RED, "Honda City", 2015, 4);
        fuberService.makeCabOnline(FuberServiceTest.LICENSENUMBER, 100, 100);

        String licenseNumber = fuberService.requestCab(120, 120);

        fuberService.startTrip(licenseNumber);

        double cost = fuberService.stopTrip(FuberServiceTest.LICENSENUMBER, 160, 160);

        Assert.assertEquals(113.13, cost, 0.5);
    }

    @Test
    public void testStopTripWhichIsAlreadyStopped() throws Exception
    {
        IFuberService fuberService = new FuberService();
        fuberService.registerCab(FuberServiceTest.LICENSENUMBER, Color.RED, "Honda City", 2015, 4);
        fuberService.makeCabOnline(FuberServiceTest.LICENSENUMBER, 100, 100);

        String licenseNumber = fuberService.requestCab(120, 120);

        fuberService.startTrip(licenseNumber);

        fuberService.stopTrip(FuberServiceTest.LICENSENUMBER, 160, 160);
        try
        {
            fuberService.stopTrip(FuberServiceTest.LICENSENUMBER, 160, 160);
            Assert.fail("Should throw exception");
        }
        catch (TripNotFoundException e)
        {
            Assert.assertEquals("Invalid license Number, Trip not located!", e.getMessage());
        }

    }

    @Test
    public void testTaxiManagerForValidCab() throws Exception
    {
        IFuberService fuberService = new FuberService();
        fuberService.registerCab(FuberServiceTest.LICENSENUMBER, Color.RED, "Honda City", 2015, 4);

        TaxiServiceManager.getInstance().addCab(FuberServiceTest.LICENSENUMBER);
        Assert
            .assertEquals(Status.OFFLINE, CabManager.getInstance().getCab(FuberServiceTest.LICENSENUMBER).getStatus());
    }

    @Test
    public void testTaxiMangerForInvalidCab()
    {
        try
        {
            TaxiServiceManager.getInstance().addCab("KA03JA6681");
            Assert.fail("Should have thrown exception");
        }
        catch (CabNotFoundException e)
        {
            Assert.assertEquals("Cab not found for given license number", e.getMessage());
        }
    }

    @Test
    public void testTryToReassignInValidCars()
    {
        try
        {
            TaxiServiceManager.getInstance().reassignCabs("InvlaidLicenseNmber", Location.create(0, 0));
            Assert.fail("Should fail here");
        }
        catch (CabNotFoundException e)
        {
            Assert.assertEquals("Incorrect Cab license number", e.getMessage());
        }
    }

    @Test
    public void testTryToReassignWithNullLicenseNumber()
    {
        try
        {
            TaxiServiceManager.getInstance().reassignCabs(null, Location.create(0, 0));
            Assert.fail("Should fail here");
        }
        catch (CabNotFoundException e)
        {
            Assert.assertEquals("Incorrect Cab license number", e.getMessage());
        }
    }
}
