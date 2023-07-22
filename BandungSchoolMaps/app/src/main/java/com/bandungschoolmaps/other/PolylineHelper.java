package com.bandungschoolmaps.other;

import android.graphics.Color;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

/**
 * Created by Niko Ahmad Fauzi on 15/10/2017.
 */

public class PolylineHelper {

    @NonNull
    public static Polyline drawPolylineWithLatLngs(ArrayList<LatLng> latLngs, GoogleMap googleMap) {
        PolylineOptions polylineOptions = new PolylineOptions().width(2).color(Color.BLUE).geodesic(true);

        for (int i = 0; i < latLngs.size(); i++) {
            polylineOptions.add(latLngs.get(i));
        }

        polylineOptions.add(latLngs.get(0));
        return googleMap.addPolyline(polylineOptions);
    }
}
