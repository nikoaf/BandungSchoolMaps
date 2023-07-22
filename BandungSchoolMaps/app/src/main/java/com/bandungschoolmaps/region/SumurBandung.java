package com.bandungschoolmaps.region;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import com.bandungschoolmaps.other.PolylineHelper;
import com.bandungschoolmaps.raycasting.RayCastingHelper;

/**
 * Created by Niko Ahmad Fauzi on 18/02/2018.
 */

public class SumurBandung {

    public static void regionSumurBandung(ArrayList<LatLng> latLngsSumurBandung, GoogleMap googleMap, LatLng latLng) {
        latLngsSumurBandung.add(new LatLng(-6.915713, 107.630041));
        /**
         * ---
         * ---
         * ---
         */
        latLngsSumurBandung.add(new LatLng(-6.914405, 107.629469));

        if (RayCastingHelper.isPointPolyline(latLngsSumurBandung, latLng)) {
            PolylineHelper.drawPolylineWithLatLngs(latLngsSumurBandung, googleMap).setVisible(true);
        } else {
            PolylineHelper.drawPolylineWithLatLngs(latLngsSumurBandung, googleMap).setVisible(false);
        }
    }
}
