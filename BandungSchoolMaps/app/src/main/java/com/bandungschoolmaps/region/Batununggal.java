package com.bandungschoolmaps.region;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import com.bandungschoolmaps.other.PolylineHelper;
import com.bandungschoolmaps.raycasting.RayCastingHelper;

/**
 * Created by Niko Ahmad Fauzi on 15/10/2017.
 */

public class Batununggal {

    public static void regionBatununggal(ArrayList<LatLng> latLngsBatuunggal, GoogleMap googleMap, LatLng latLng) {
        latLngsBatuunggal.add(new LatLng(-6.945479, 107.641875));
        /**
         * ---
         * ---
         * ---
         */
        latLngsBatuunggal.add(new LatLng(-6.944689, 107.641945));

        if (RayCastingHelper.isPointPolyline(latLngsBatuunggal, latLng)) {
            PolylineHelper.drawPolylineWithLatLngs(latLngsBatuunggal, googleMap).setVisible(true);
        } else {
            PolylineHelper.drawPolylineWithLatLngs(latLngsBatuunggal, googleMap).setVisible(false);
        }
    }
}
