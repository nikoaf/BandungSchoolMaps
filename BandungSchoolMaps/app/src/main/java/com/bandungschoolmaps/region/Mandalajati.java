package com.bandungschoolmaps.region;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import com.bandungschoolmaps.other.PolylineHelper;
import com.bandungschoolmaps.raycasting.RayCastingHelper;

/**
 * Created by Niko Ahmad Fauzi on 15/10/2017.
 */

public class Mandalajati {

    public static void regionMandalajati(ArrayList<LatLng> latLngsMandalajati, GoogleMap googleMap, LatLng latLng) {
        latLngsMandalajati.add(new LatLng(-6.885064, 107.665556));
        /**
         * ---
         * ---
         * ---
         */
        latLngsMandalajati.add(new LatLng(-6.885218, 107.665644));

        if (RayCastingHelper.isPointPolyline(latLngsMandalajati, latLng)) {
            PolylineHelper.drawPolylineWithLatLngs(latLngsMandalajati, googleMap).setVisible(true);
        } else {
            PolylineHelper.drawPolylineWithLatLngs(latLngsMandalajati, googleMap).setVisible(false);
        }
    }
}
