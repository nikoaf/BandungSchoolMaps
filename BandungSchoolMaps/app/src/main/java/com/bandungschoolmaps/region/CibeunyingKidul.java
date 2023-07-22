package com.bandungschoolmaps.region;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import com.bandungschoolmaps.other.PolylineHelper;
import com.bandungschoolmaps.raycasting.RayCastingHelper;

/**
 * Created by Niko Ahmad Fauzi on 15/10/2017.
 */

public class CibeunyingKidul {

    public static void regionCibeunyingKidul(ArrayList<LatLng> latLngsCibeunyingKidul, GoogleMap googleMap, LatLng latLng) {
        latLngsCibeunyingKidul.add(new LatLng(-6.902155, 107.657693));
        /**
         * ---
         * ---
         * ---
         */
        latLngsCibeunyingKidul.add(new LatLng(-6.901783, 107.657616));

        if (RayCastingHelper.isPointPolyline(latLngsCibeunyingKidul, latLng)) {
            PolylineHelper.drawPolylineWithLatLngs(latLngsCibeunyingKidul, googleMap).setVisible(true);
        } else {
            PolylineHelper.drawPolylineWithLatLngs(latLngsCibeunyingKidul, googleMap).setVisible(false);
        }
    }
}
