package com.bandungschoolmaps.region;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import com.bandungschoolmaps.other.PolylineHelper;
import com.bandungschoolmaps.raycasting.RayCastingHelper;

/**
 * Created by Niko Ahmad Fauzi on 15/10/2017.
 */

public class BojongloaKidul {

    public static void regionBojongloaKidul(ArrayList<LatLng> latLngsBojongloaKidul, GoogleMap googleMap, LatLng latLng) {
        latLngsBojongloaKidul.add(new LatLng(-6.961284, 107.608798));
        /**
         * ---
         * ---
         * ---
         */
        latLngsBojongloaKidul.add(new LatLng(-6.960747, 107.608444));

        if (RayCastingHelper.isPointPolyline(latLngsBojongloaKidul, latLng)) {
            PolylineHelper.drawPolylineWithLatLngs(latLngsBojongloaKidul, googleMap).setVisible(true);
        } else {
            PolylineHelper.drawPolylineWithLatLngs(latLngsBojongloaKidul, googleMap).setVisible(false);
        }
    }
}
