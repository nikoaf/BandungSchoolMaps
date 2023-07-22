package com.bandungschoolmaps.region;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import com.bandungschoolmaps.other.PolylineHelper;
import com.bandungschoolmaps.raycasting.RayCastingHelper;

/**
 * Created by Niko Ahmad Fauzi on 15/10/2017.
 */

public class Sukasari {

    public static void regionSukasari(ArrayList<LatLng> latLngsSukasari, GoogleMap googleMap, LatLng latLng) {
        latLngsSukasari.add(new LatLng(-6.879145, 107.598182));
        /**
         * ---
         * ---
         * ---
         */
        latLngsSukasari.add(new LatLng(-6.878689, 107.597982));

        if (RayCastingHelper.isPointPolyline(latLngsSukasari, latLng)) {
            PolylineHelper.drawPolylineWithLatLngs(latLngsSukasari, googleMap).setVisible(true);
        } else {
            PolylineHelper.drawPolylineWithLatLngs(latLngsSukasari, googleMap).setVisible(false);
        }
    }
}
