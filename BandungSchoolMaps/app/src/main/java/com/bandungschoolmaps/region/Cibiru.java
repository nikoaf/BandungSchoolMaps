package com.bandungschoolmaps.region;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import com.bandungschoolmaps.other.PolylineHelper;
import com.bandungschoolmaps.raycasting.RayCastingHelper;

/**
 * Created by Niko Ahmad Fauzi on 15/10/2017.
 */

public class Cibiru {

    public static void regionCibiru(ArrayList<LatLng> latLngsCibiru, GoogleMap googleMap, LatLng latLng) {
        latLngsCibiru.add(new LatLng(-6.934192, 107.721783));
        /**
         * ---
         * ---
         * ---
         */
        latLngsCibiru.add(new LatLng(-6.933995, 107.722076));

        if (RayCastingHelper.isPointPolyline(latLngsCibiru, latLng)) {
            PolylineHelper.drawPolylineWithLatLngs(latLngsCibiru, googleMap).setVisible(true);
        } else {
            PolylineHelper.drawPolylineWithLatLngs(latLngsCibiru, googleMap).setVisible(false);
        }
    }
}
