package com.example.quickksilent;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

/**
 * Constants used in this sample.
 */

final class Constants {

    private Constants() {
    }

    private static final String PACKAGE_NAME = "com.google.android.gms.location.Geofence";

    static final String GEOFENCES_ADDED_KEY = PACKAGE_NAME + ".GEOFENCES_ADDED_KEY";

    /**
     * Used to set an expiration time for a geofence. After this amount of time Location Services
     * stops tracking the geofence.
     */
    private static final long GEOFENCE_EXPIRATION_IN_HOURS = 12;

    /**
     * For this sample, geofences expire after twelve hours.
     */
    static final long GEOFENCE_EXPIRATION_IN_MILLISECONDS =
            GEOFENCE_EXPIRATION_IN_HOURS * 60 * 60 * 1000;
    static final float GEOFENCE_RADIUS_IN_METERS = 1609; // 1 mile, 1.6 km

    /**
     * Map for storing information about airports in the San Francisco bay area.
     */
    //Check the Latlang type. Is it fine to put the same in the database?

    //Another thing to check is the constant is referenced bro?
    public static final HashMap<String, LatLng> GEOFENCE_PONTS = new HashMap<>();
    //Removed static shit from either of them

     static {
        //Check if you can modify this constants file instead. Will make it damn easier for you.

        // San Francisco International Airport.
        GEOFENCE_PONTS.put("SFO", new LatLng(37.621313, -122.378955));

        // Googleplex.
        GEOFENCE_PONTS.put("GOOGLE", new LatLng(37.422611,-122.0840577));

        //Call the fucking method or maybe not
        /**
         * Retrieve the fucker from a database.
         *
         */

    }
}