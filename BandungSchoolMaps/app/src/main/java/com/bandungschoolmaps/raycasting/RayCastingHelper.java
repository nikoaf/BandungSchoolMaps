package com.bandungschoolmaps.raycasting;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by Niko Ahmad Fauzi on 16/02/2018.
 */

public class RayCastingHelper {

    public static boolean isPointPolyline(ArrayList<LatLng> latLngs, LatLng latLng) {
        int intersectCount = 0;

        for (int i = 0; i < latLngs.size() - 1; i++) {

            if (rayCastIntersect(latLng, latLngs.get(i), latLngs.get(i + 1))) {
                intersectCount++;
            }
        }

        return ((intersectCount % 2) == 1);
    }

    private static boolean rayCastIntersect(LatLng latLng, LatLng vertA, LatLng vertB) {
        double aY = vertA.latitude;
        double bY = vertB.latitude;
        double aX = vertA.longitude;
        double bX = vertB.longitude;
        double pY = latLng.latitude;
        double pX = latLng.longitude;

        if ((aY > pY && bY > pY) || (aY < pY && bY < pY) || (aX < pX && bX < pX)) {
            return false;
        }

        double m = (aY - bY) / (aX - bX);
        double n = (-aX) * m + aY;
        double x = (pY - n) / m;

        return x > pX;
    }
}
