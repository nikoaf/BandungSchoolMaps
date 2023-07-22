package com.bandungschoolmaps.region;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import com.bandungschoolmaps.other.PolylineHelper;
import com.bandungschoolmaps.raycasting.RayCastingHelper;

/**
 * Created by Niko Ahmad Fauzi on 15/10/2017.
 */

public class UjungBerung {

    public static void regionUjungBerung(ArrayList<LatLng> latLngsUjungBerung, GoogleMap googleMap, LatLng latLng) {
        latLngsUjungBerung.add(new LatLng(-6.907241, 107.686036));
        /**
         * ---
         * ---
         * ---
         */
        latLngsUjungBerung.add(new LatLng(-6.906327, 107.685975));

        if (RayCastingHelper.isPointPolyline(latLngsUjungBerung, latLng)) {
            PolylineHelper.drawPolylineWithLatLngs(latLngsUjungBerung, googleMap).setVisible(true);
        } else {
            PolylineHelper.drawPolylineWithLatLngs(latLngsUjungBerung, googleMap).setVisible(false);
        }
    }
}
