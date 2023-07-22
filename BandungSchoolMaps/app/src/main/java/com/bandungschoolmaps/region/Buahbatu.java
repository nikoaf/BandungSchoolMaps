package com.bandungschoolmaps.region;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import com.bandungschoolmaps.other.PolylineHelper;
import com.bandungschoolmaps.raycasting.RayCastingHelper;

/**
 * Created by Niko Ahmad Fauzi on 15/10/2017.
 */

public class Buahbatu {

    public static void regionBuahbatu(ArrayList<LatLng> latLngsBuahBatu, GoogleMap googleMap, LatLng latLng) {
        latLngsBuahBatu.add(new LatLng(-6.945864, 107.640362));
        /**
         * ---
         * ---
         * ---
         */
        latLngsBuahBatu.add(new LatLng(-6.946237, 107.640395));

        if (RayCastingHelper.isPointPolyline(latLngsBuahBatu, latLng)) {
            PolylineHelper.drawPolylineWithLatLngs(latLngsBuahBatu, googleMap).setVisible(true);
        } else {
            PolylineHelper.drawPolylineWithLatLngs(latLngsBuahBatu, googleMap).setVisible(false);
        }
    }
}
