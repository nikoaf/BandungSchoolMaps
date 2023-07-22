package com.bandungschoolmaps.other;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bandungschoolmaps.R;
import com.bandungschoolmaps.app.AppController;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Created by Niko Ahmad Fauzi on 15/10/2017.
 */

public class JSONHelper {

    private static Bitmap bitmap;
    private static double a, b, latA, lngA, latB, lngB, latJum, lngJum, result, batas;
    private static int earth = 6371;
    private static JSONArray jsonArray;
    private static JSONObject jsonObject;
    private static LatLng jsonLatLng;
    private static Marker marker;
    private static String npsn, namaSekolah, jsonJenjang, jsonStatus, alamat, kecamatan, latitude, longitude, noTelepon,
            akreditasi, km, url;

    public static void jsonRequestData(final Context context, final GoogleMap googleMap, final LatLng latLng,
                                       final String jenjang, final String jarak) {
        BitmapDrawable bitmapDrawable;
        if (jenjang.contains(context.getString(R.string.sd))) {
            url = context.getString(R.string.urlSdNegeri);
            bitmapDrawable = (BitmapDrawable) context.getResources().getDrawable(R.drawable.sd);
            bitmap = Bitmap.createScaledBitmap(bitmapDrawable.getBitmap(), 50, 58, false);
        } else if (jenjang.contains(context.getString(R.string.smp))) {
            url = context.getString(R.string.urlSmpNegeri);
            bitmapDrawable = (BitmapDrawable) context.getResources().getDrawable(R.drawable.smp);
            bitmap = Bitmap.createScaledBitmap(bitmapDrawable.getBitmap(), 50, 61, false);
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        VolleyLog.d(jenjang, response.toString());

                        try {
                            jsonArray = response.getJSONArray(context.getString(R.string.dbBsm));

                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                npsn = jsonObject.getString(context.getString(R.string.dbNpsn));
                                namaSekolah = jsonObject.getString(context.getString(R.string.dbNamaSekolah));
                                jsonJenjang = jsonObject.getString(context.getString(R.string.dbJenjang));
                                jsonStatus = jsonObject.getString(context.getString(R.string.dbStatus));
                                alamat = jsonObject.getString(context.getString(R.string.dbAlamat));
                                kecamatan = jsonObject.getString(context.getString(R.string.dbKecamatan));
                                latitude = jsonObject.getString(context.getString(R.string.dbLatitude));
                                longitude = jsonObject.getString(context.getString(R.string.dbLongitude));
                                noTelepon = jsonObject.getString(context.getString(R.string.dbTelepon));
                                akreditasi = jsonObject.getString(context.getString(R.string.dbAkreditasi));

                                InfoWindowHelper infoWindowHelper = new InfoWindowHelper();
                                infoWindowHelper.setNpsn(npsn);
                                infoWindowHelper.setNamaSekolah(namaSekolah);
                                infoWindowHelper.setJenjang(jsonJenjang);
                                infoWindowHelper.setStatus(jsonStatus);
                                infoWindowHelper.setAlamat(alamat);
                                infoWindowHelper.setKecamatan(kecamatan);
                                infoWindowHelper.setLatitude(latitude);
                                infoWindowHelper.setLongitude(longitude);
                                infoWindowHelper.setNoTelepon(noTelepon);
                                infoWindowHelper.setAkreditasi(akreditasi);

                                jsonLatLng = new LatLng(Double.parseDouble(infoWindowHelper.getLatitude()),
                                        Double.parseDouble(infoWindowHelper.getLongitude()));

                                latA = latLng.latitude;
                                lngA = latLng.longitude;
                                latB = jsonLatLng.latitude;
                                lngB = jsonLatLng.longitude;
                                latJum = Math.toRadians(latB - latA);
                                lngJum = Math.toRadians(lngB - lngA);
                                a = Math.sin(latJum / 2) * Math.sin(latJum / 2) + Math.cos(Math.toRadians(latA))
                                        * Math.cos(Math.toRadians(latB)) * Math.sin(lngJum / 2) * Math.sin(lngJum / 2);
                                b = 2 * Math.asin(Math.sqrt(a));
                                result = earth * b;

                                DecimalFormat decimalFormatDuaTiga = new DecimalFormat(context.getString(R.string.decimalFormatDuaTiga));
                                DecimalFormat decimalFormatEmpatEnam = new DecimalFormat(context.getString(R.string.decimalFormatEmpatEnam));
                                DecimalFormatSymbols decimalFormatSymbols = DecimalFormatSymbols.getInstance();
                                decimalFormatSymbols.setDecimalSeparator('.');
                                decimalFormatDuaTiga.setDecimalFormatSymbols(decimalFormatSymbols);
                                decimalFormatEmpatEnam.setDecimalFormatSymbols(decimalFormatSymbols);
                                km = String.valueOf(decimalFormatDuaTiga.format(result / 1));
                                batas = Double.parseDouble(jarak);

                                infoWindowHelper.setJarak(String.valueOf(km) + " " + context.getString(R.string.km));

                                MarkerHelper markerHelper = new MarkerHelper(context, googleMap);
                                googleMap.setInfoWindowAdapter(markerHelper);
                                googleMap.setOnMarkerClickListener(markerHelper);
                                googleMap.setOnInfoWindowClickListener(markerHelper);

                                marker = googleMap.addMarker(new MarkerOptions().title(infoWindowHelper.getNamaSekolah())
                                        .snippet(infoWindowHelper.getAlamat()).position(jsonLatLng)
                                        .icon(BitmapDescriptorFactory.fromBitmap(bitmap)));
                                marker.setTag(infoWindowHelper);

                                if (result < batas) {
                                    marker.isVisible();
                                } else {
                                    marker.remove();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                VolleyLog.d(jenjang, volleyError.getMessage());

                if (volleyError instanceof TimeoutError || volleyError instanceof NoConnectionError
                        || volleyError instanceof AuthFailureError || volleyError instanceof ServerError
                        || volleyError instanceof NetworkError || volleyError instanceof ParseError) {
                    new ToastHelper(context).showToastWhitDuration(5000, context.getString(R.string.errorData));
                }
            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }
}
