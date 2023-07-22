package com.bandungschoolmaps.region;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import com.bandungschoolmaps.other.PolylineHelper;
import com.bandungschoolmaps.raycasting.RayCastingHelper;

/**
 * Created by Niko Ahmad Fauzi on 15/10/2017.
 */

public class BojongloaKaler {

    public static void regionBojongloaKaler(ArrayList<LatLng> latLngsBojongloaKaler, GoogleMap googleMap, LatLng latLng) {
        latLngsBojongloaKaler.add(new LatLng(-6.946902, 107.588810));
        /**
         * ---
         * ---
         * ---
         */
        latLngsBojongloaKaler.add(new LatLng(-6.946351, 107.589184));

        if (RayCastingHelper.isPointPolyline(latLngsBojongloaKaler, latLng)) {
            PolylineHelper.drawPolylineWithLatLngs(latLngsBojongloaKaler, googleMap).setVisible(true);
        } else {
            PolylineHelper.drawPolylineWithLatLngs(latLngsBojongloaKaler, googleMap).setVisible(false);
        }
    }
}
