package com.bandungschoolmaps.region;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import com.bandungschoolmaps.other.PolylineHelper;
import com.bandungschoolmaps.raycasting.RayCastingHelper;

/**
 * Created by Niko Ahmad Fauzi on 15/10/2017.
 */

public class Rancasari {

    public static void regionRancasari(ArrayList<LatLng> latLngsRancasari, GoogleMap googleMap, LatLng latLng) {
        latLngsRancasari.add(new LatLng(-6.966482, 107.660453));
        /**
         * ---
         * ---
         * ---
         */
        latLngsRancasari.add(new LatLng(-6.966269, 107.660480));

        if (RayCastingHelper.isPointPolyline(latLngsRancasari, latLng)) {
            PolylineHelper.drawPolylineWithLatLngs(latLngsRancasari, googleMap).setVisible(true);
        } else {
            PolylineHelper.drawPolylineWithLatLngs(latLngsRancasari, googleMap).setVisible(false);
        }
    }
}
