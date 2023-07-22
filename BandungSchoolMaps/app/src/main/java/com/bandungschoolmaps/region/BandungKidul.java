package com.bandungschoolmaps.region;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import com.bandungschoolmaps.other.PolylineHelper;
import com.bandungschoolmaps.raycasting.RayCastingHelper;

/**
 * Created by Niko Ahmad Fauzi on 15/10/2017.
 */

public class BandungKidul {

    public static void regionBandungKidul(ArrayList<LatLng> latLngsBandungKidul, GoogleMap googleMap, LatLng latLng) {
        latLngsBandungKidul.add(new LatLng(-6.945864, 107.640362));
        /**
         * ---
         * ---
         * ---
         */
        latLngsBandungKidul.add(new LatLng(-6.946237, 107.640395));

        if (RayCastingHelper.isPointPolyline(latLngsBandungKidul, latLng)) {
            PolylineHelper.drawPolylineWithLatLngs(latLngsBandungKidul, googleMap).setVisible(true);
        } else {
            PolylineHelper.drawPolylineWithLatLngs(latLngsBandungKidul, googleMap).setVisible(false);
        }
    }
}
