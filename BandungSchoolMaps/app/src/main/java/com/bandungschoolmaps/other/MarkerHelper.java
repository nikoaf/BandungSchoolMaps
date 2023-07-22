package com.bandungschoolmaps.other;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.bandungschoolmaps.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by Niko Ahmad Fauzi on 15/10/2017.
 */

public class MarkerHelper implements GoogleMap.InfoWindowAdapter, GoogleMap.OnMarkerClickListener,
        GoogleMap.OnInfoWindowClickListener {

    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private static GoogleMap googleMap;
    private static LayoutInflater layoutInflater;
    @SuppressLint("StaticFieldLeak")
    private static View view;

    MarkerHelper(Context context, GoogleMap googleMap) {
        MarkerHelper.context = context;
        MarkerHelper.googleMap = googleMap;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    @SuppressLint("InflateParams")
    public View getInfoContents(Marker marker) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.marker_infowindows, null);

        TextView namaSekolah = view.findViewById(R.id.txtNamaSekolah);
        namaSekolah.setText(marker.getTitle());
        return view;
    }

    @SuppressLint("InflateParams")
    @Override
    public void onInfoWindowClick(Marker marker) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        view = layoutInflater.inflate(R.layout.alertdialog_info, null);
        builder.setCancelable(true);

        TextView namaSekolah = view.findViewById(R.id.txtNamaSekolah);
        TextView npsn = view.findViewById(R.id.npsnInfo);
        TextView jenjang = view.findViewById(R.id.jenjangInfo);
        TextView status = view.findViewById(R.id.statusInfo);
        TextView alamat = view.findViewById(R.id.alamatInfo);
        TextView noTelepon = view.findViewById(R.id.noTeleponInfo);
        TextView kecamatan = view.findViewById(R.id.kecamatanInfo);
        TextView jarak = view.findViewById(R.id.jarakInfo);
        TextView akreditasi = view.findViewById(R.id.akreditasiInfo);

        InfoWindowHelper infoWindowHelper = (InfoWindowHelper) marker.getTag();
        namaSekolah.setText(infoWindowHelper.getNamaSekolah());
        npsn.setText(infoWindowHelper.getNpsn());
        jenjang.setText(infoWindowHelper.getJenjang());
        status.setText(infoWindowHelper.getStatus());
        alamat.setText(infoWindowHelper.getAlamat());
        noTelepon.setText(infoWindowHelper.getNoTelepon());
        kecamatan.setText(infoWindowHelper.getKecamatan());
        jarak.setText(infoWindowHelper.getJarak());
        akreditasi.setText(infoWindowHelper.getAkreditasi());

        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        marker.showInfoWindow();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.builder()
                .target(marker.getPosition()).zoom(googleMap.getCameraPosition().zoom).build()));
        return true;
    }
}
