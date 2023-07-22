package com.bandungschoolmaps.region;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import com.bandungschoolmaps.other.PolylineHelper;
import com.bandungschoolmaps.raycasting.RayCastingHelper;

/**
 * Created by Niko Ahmad Fauzi on 16/02/2018.
 */

public class Kiaracondong {

    public static void regionKiaracondong(ArrayList<LatLng> latLngsKiaracondong, GoogleMap googleMap, LatLng latLng) {
        latLngsKiaracondong.add(new LatLng(-6.945479, 107.641875));
        /**
         * ---
         * ---
         * ---
         */
        latLngsKiaracondong.add(new LatLng(-6.944689, 107.641945));

        if (RayCastingHelper.isPointPolyline(latLngsKiaracondong, latLng)) {
            PolylineHelper.drawPolylineWithLatLngs(latLngsKiaracondong, googleMap).setVisible(true);
        } else {
            PolylineHelper.drawPolylineWithLatLngs(latLngsKiaracondong, googleMap).setVisible(false);
        }
    }
}
