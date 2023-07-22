package com.bandungschoolmaps.region;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import com.bandungschoolmaps.other.PolylineHelper;
import com.bandungschoolmaps.raycasting.RayCastingHelper;

/**
 * Created by Niko Ahmad Fauzi on 15/10/2017.
 */

public class BabakanCiparay {

    public static void regionBabakanCiparay(ArrayList<LatLng> latLngsBabakanCiparay, GoogleMap googleMap, LatLng latLng) {
        latLngsBabakanCiparay.add(new LatLng(-6.918032, 107.581159));
        /**
         * ---
         * ---
         * ---
         */
        latLngsBabakanCiparay.add(new LatLng(-6.918688, 107.586397));

        if (RayCastingHelper.isPointPolyline(latLngsBabakanCiparay, latLng)) {
            PolylineHelper.drawPolylineWithLatLngs(latLngsBabakanCiparay, googleMap).setVisible(true);
        } else {
            PolylineHelper.drawPolylineWithLatLngs(latLngsBabakanCiparay, googleMap).setVisible(false);
        }
    }
}
