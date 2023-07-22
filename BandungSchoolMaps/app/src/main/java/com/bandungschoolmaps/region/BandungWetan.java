package com.bandungschoolmaps.region;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import com.bandungschoolmaps.other.PolylineHelper;
import com.bandungschoolmaps.raycasting.RayCastingHelper;

/**
 * Created by Niko Ahmad Fauzi on 15/10/2017.
 */

public class BandungWetan {

    public static void regionBandungWetan(ArrayList<LatLng> latLngsBandungWetan, GoogleMap googleMap, LatLng latLng) {
        latLngsBandungWetan.add(new LatLng(-6.895618, 107.603950));
        /**
         * ---
         * ---
         * ---
         */
        latLngsBandungWetan.add(new LatLng(-6.895464, 107.604485));

        if (RayCastingHelper.isPointPolyline(latLngsBandungWetan, latLng)) {
            PolylineHelper.drawPolylineWithLatLngs(latLngsBandungWetan, googleMap).setVisible(true);
        } else {
            PolylineHelper.drawPolylineWithLatLngs(latLngsBandungWetan, googleMap).setVisible(false);
        }
    }
}
