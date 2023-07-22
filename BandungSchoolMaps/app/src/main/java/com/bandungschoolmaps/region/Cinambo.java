package com.bandungschoolmaps.region;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import com.bandungschoolmaps.other.PolylineHelper;
import com.bandungschoolmaps.raycasting.RayCastingHelper;

/**
 * Created by Niko Ahmad Fauzi on 15/10/2017.
 */

public class Cinambo {

    public static void regionCinambo(ArrayList<LatLng> latLngsCinambo, GoogleMap googleMap, LatLng latLng) {
        latLngsCinambo.add(new LatLng(-6.912326, 107.691527));
        /**
         * ---
         * ---
         * ---
         */
        latLngsCinambo.add(new LatLng(-6.912769, 107.691335));

        if (RayCastingHelper.isPointPolyline(latLngsCinambo, latLng)) {
            PolylineHelper.drawPolylineWithLatLngs(latLngsCinambo, googleMap).setVisible(true);
        } else {
            PolylineHelper.drawPolylineWithLatLngs(latLngsCinambo, googleMap).setVisible(false);
        }
    }
}
