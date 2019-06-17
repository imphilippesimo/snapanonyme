package com.zerofiltre.snapanonyme.infrastructure.repository;

import com.zerofiltre.snapanonyme.domain.model.Location;

public class LocationUtils {

    public static double distanceAsMeters(Location origin, Location destination, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(destination.getLatitude() - origin.getLatitude());
        double lonDistance = Math.toRadians(destination.getLongitude() - origin.getLongitude());
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(origin.getLongitude())) * Math.cos(Math.toRadians(destination.getLatitude()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distanceAsMeters = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distanceAsMeters = Math.pow(distanceAsMeters, 2) + Math.pow(height, 2);

        return Math.sqrt(distanceAsMeters);
    }

    public static double distanceAsMiles(Location origin, Location destination, double el1, double el2) {
        return distanceAsMeters(origin, destination, el1, el2) / 1609.344;
    }
}
