package com.bandungschoolmaps.region;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import com.bandungschoolmaps.other.PolylineHelper;
import com.bandungschoolmaps.raycasting.RayCastingHelper;

/**
 * Created by Niko Ahmad Fauzi on 15/10/2017.
 */

public class Panyileukan {

    public static void regionPanyileukan(ArrayList<LatLng> latLngsPanyileukan, GoogleMap googleMap, LatLng latLng) {
        latLngsPanyileukan.add(new LatLng(-6.915466, 107.704174));
        /**
         * ---
         * ---
         * ---
         */
        latLngsPanyileukan.add(new LatLng(-6.916985, 107.705684));

        if (RayCastingHelper.isPointPolyline(latLngsPanyileukan, latLng)) {
            PolylineHelper.drawPolylineWithLatLngs(latLngsPanyileukan, googleMap).setVisible(true);
        } else {
            PolylineHelper.drawPolylineWithLatLngs(latLngsPanyileukan, googleMap).setVisible(false);
        }
    }
}
