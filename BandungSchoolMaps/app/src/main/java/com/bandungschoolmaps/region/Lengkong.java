package com.bandungschoolmaps.region;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import com.bandungschoolmaps.other.PolylineHelper;
import com.bandungschoolmaps.raycasting.RayCastingHelper;

/**
 * Created by Niko Ahmad Fauzi on 15/10/2017.
 */

public class Lengkong {

    public static void regionLengkong(ArrayList<LatLng> latLngsLengkong, GoogleMap googleMap, LatLng latLng) {
        latLngsLengkong.add(new LatLng(-6.918773, 107.624222));
        /**
         * ---
         * ---
         * ---
         */
        latLngsLengkong.add(new LatLng(-6.919076, 107.624289));

        if (RayCastingHelper.isPointPolyline(latLngsLengkong, latLng)) {
            PolylineHelper.drawPolylineWithLatLngs(latLngsLengkong, googleMap).setVisible(true);
        } else {
            PolylineHelper.drawPolylineWithLatLngs(latLngsLengkong, googleMap).setVisible(false);
        }
    }
}
