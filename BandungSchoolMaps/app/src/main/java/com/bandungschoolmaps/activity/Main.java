package com.bandungschoolmaps.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bandungschoolmaps.R;
import com.bandungschoolmaps.other.JSONHelper;
import com.bandungschoolmaps.other.PolylineHelper;
import com.bandungschoolmaps.other.RegionHelper;
import com.bandungschoolmaps.other.ToastHelper;
import com.bandungschoolmaps.raycasting.RayCastingHelper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niko Ahmad Fauzi on 15/10/2017.
 */

public class Main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, SeekBar.OnSeekBarChangeListener {

    boolean doubleBackToExitPressedOnce = false;

    private AlertDialog alertDialog;
    private AlertDialog.Builder aBuilder, pBuilder;
    private DrawerLayout drawer;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private GoogleMap googleMap;
    private GoogleApiClient googleApiClient;
    private int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private int seekbar;
    private Location myLocation;
    private MenuItem menuItemLocation, menuItemPencarian, menuItemTypeMap;
    private NavigationView navigationView;
    private RadioGroup rgJenjang;
    private RadioButton rbJenjang;
    private String jenjang, jarak, mapType;
    private TextView txtSeekbar;
    private ToastHelper toastHelper = new ToastHelper(this);
    private View view;

    private LocationRequest locationRequest = LocationRequest.create().setSmallestDisplacement(0)
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY).setInterval(30 * 1000).setFastestInterval(5 * 1000);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigationDrawerOpen, R.string.navigationDrawerClose);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        setUpGoogleApiClientIfNeeded();

        jenjang = "";
        jarak = "";
        mapType = getString(R.string.normal);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
        } else {
            this.doubleBackToExitPressedOnce = true;
            toastHelper.showToast(getString(R.string.onBackPressed));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(gpsLocationReceiver, new IntentFilter(getString(R.string.broadcastAction)));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (gpsLocationReceiver != null) {
            unregisterReceiver(gpsLocationReceiver);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setZoomGesturesEnabled(true);
        googleMap.getUiSettings().setRotateGesturesEnabled(true);
        googleMap.getUiSettings().setMapToolbarEnabled(true);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                showSettingDialog();
                googleMap.setMyLocationEnabled(true);
            } else {
                requestLocationPermissions();
            }
        } else {
            showSettingDialog();
            googleMap.setMyLocationEnabled(true);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        seekbar = progress;
        txtSeekbar.setText(String.valueOf(seekbar) + " km");
        jarak = String.valueOf(seekbar);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        seekBar.setSecondaryProgress(seekBar.getProgress());
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        aBuilder = new AlertDialog.Builder(Main.this);
        view = getLayoutInflater().inflate(R.layout.progressbar_prosessdata, null);
        TextView txtProgressBar = view.findViewById(R.id.progressBarText);
        aBuilder.setView(view).setCancelable(false);
        alertDialog = aBuilder.create();

        if (ContextCompat.checkSelfPermission(Main.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            txtProgressBar.setText(getString(R.string.progressBarLocation));
            alertDialog.show();

            if (ContextCompat.checkSelfPermission(Main.this, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                alertDialog.show();
                fusedLocationProviderClient.getLastLocation().addOnSuccessListener(Main.this,
                        new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                if (location != null) {
                                    myLocation = location;
                                    LatLng latLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
                                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13f));
                                    alertDialog.dismiss();
                                    ArrayList<LatLng> kotaBandung = new ArrayList<>();
                                    RegionHelper.kotaBandung(kotaBandung);

                                    if (RayCastingHelper.isPointPolyline(kotaBandung, latLng)) {
                                        PolylineHelper.drawPolylineWithLatLngs(kotaBandung, googleMap).setVisible(true);
                                    } else {
                                        Menu menu = navigationView.getMenu();
                                        menuItemLocation = menu.findItem(R.id.nav_location);
                                        menuItemPencarian = menu.findItem(R.id.nav_search);
                                        menuItemTypeMap = menu.findItem(R.id.nav_maptype);
                                        menuItemLocation.setEnabled(false);
                                        menuItemPencarian.setEnabled(false);
                                        menuItemTypeMap.setEnabled(false);

                                        pBuilder = new AlertDialog.Builder(Main.this);
                                        pBuilder.setIcon(android.R.drawable.ic_dialog_alert).setTitle(R.string.titlePeringatan)
                                                .setMessage(R.string.messagePeringatan).setCancelable(false)
                                                .setNeutralButton(R.string.btnOk, new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.dismiss();
                                                    }
                                                }).show();

                                        PolylineHelper.drawPolylineWithLatLngs(kotaBandung, googleMap).setVisible(false);
                                    }
                                }
                            }
                        });
            } else {
                alertDialog.dismiss();
            }
        } else {
            alertDialog.dismiss();
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            Log.i(getString(R.string.app_name), connectionResult.toString());
            try {
                connectionResult.startResolutionForResult(this, 9000);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        } else {
            Log.i(getString(R.string.app_name), getString(R.string.onConnectionFailed)
                    + connectionResult.getErrorMessage());
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        switch (menuItem.getItemId()) {
            case R.id.nav_location :
                showInfoLocation();
                break;
            case R.id.nav_search :
                showSearchData();
                break;
            case R.id.nav_maptype :
                showChangeMaps();
                break;
            case R.id.nav_about :
                showAbout();
                break;
            default :
                break;
        }

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 99 : {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        if (googleApiClient == null) {
                            setUpGoogleApiClientIfNeeded();
                            showSettingDialog();
                        } else {
                            showSettingDialog();
                        }
                        googleMap.setMyLocationEnabled(true);
                    }
                } else {
                    toastHelper.showToast(getString(R.string.permission));
                }
            }
        }
    }

    LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            List<Location> locationList = locationResult.getLocations();
            if (locationList.size() > 0) {
                myLocation = locationList.get(locationList.size() - 1);
            }

        }
    };

    protected synchronized void setUpGoogleApiClientIfNeeded() {
        googleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
        googleApiClient.connect();
    }

    private void requestLocationPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                new AlertDialog.Builder(this).setTitle(R.string.titlePermission)
                        .setMessage(R.string.messagePermission)
                        .setPositiveButton(R.string.btnOk, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(Main.this, new String[] {
                                        Manifest.permission.ACCESS_FINE_LOCATION
                                }, MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        }).create().show();
            } else {
                ActivityCompat.requestPermissions(this, new String[] {
                        Manifest.permission.ACCESS_FINE_LOCATION
                }, MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
    }

    private void showSettingDialog() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> locationSettingsResultPendingResult =
                LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        locationSettingsResultPendingResult.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
                Status status = locationSettingsResult.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS :
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED :
                        try {
                            status.startResolutionForResult(Main.this, 0x1);
                        } catch (IntentSender.SendIntentException e) {
                            e.printStackTrace();
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE :
                        break;
                }
            }
        });
    }

    private Runnable sendUpdatesToUI = new Runnable() {
        @Override
        public void run() {
            showSettingDialog();
        }
    };

    private BroadcastReceiver gpsLocationReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().matches(getString(R.string.broadcastAction))) {
                LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    new Handler().postDelayed(sendUpdatesToUI, 10);
                }
            }
        }
    };

    @SuppressLint({"InflateParams", "SetTextI18n"})
    public void showSearchData() {
        aBuilder = new AlertDialog.Builder(this);
        final View view = getLayoutInflater().inflate(R.layout.radiogroup_searchdata, null);
        aBuilder.setTitle(getString(R.string.titleSilahkanMemilih)).setCancelable(false);

        SeekBar seekBar = view.findViewById(R.id.seekbar);
        txtSeekbar = view.findViewById(R.id.txtSeekbar);
        seekBar.setOnSeekBarChangeListener(this);

        rgJenjang = view.findViewById(R.id.rg_jenjang);

        RadioButton rb_sd = view.findViewById(R.id.rb_sd);
        RadioButton rb_smp = view.findViewById(R.id.rb_smp);

        if (jenjang.contains(getString(R.string.sd))) {
            rb_sd.setChecked(true);
        } else if (jenjang.contains(getString(R.string.smp))) {
            rb_smp.setChecked(true);
        }

        seekBar.setProgress(seekbar);
        txtSeekbar.setText(String.valueOf(seekbar) + " km");

        aBuilder.setPositiveButton(getString(R.string.btnOk), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (rgJenjang.getCheckedRadioButtonId() == -1 || jarak == null) {
                    new ToastHelper(Main.this).showToast(getString(R.string.wrongData));
                    dialog.dismiss();
                } else {
                    googleMap.clear();
                    int idJenjang = rgJenjang.getCheckedRadioButtonId();

                    rbJenjang = view.findViewById(idJenjang);

                    if (rbJenjang.getText().toString().contains(getString(R.string.sd))) {
                        jenjang = getString(R.string.sd);
                    } else if (rbJenjang.getText().toString().contains(getString(R.string.smp))) {
                        jenjang = getString(R.string.smp);
                    }

                    LatLng latLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
                    JSONHelper.jsonRequestData(Main.this, googleMap, latLng, jenjang, jarak);
                    RegionHelper.regionHelper(googleMap, latLng);

                    CoordinatorLayout coordinatorLayout = findViewById(R.id.coordinatorLayout);
                    Snackbar snackbar = Snackbar.make(coordinatorLayout, getString(R.string.andaMemilih)
                            + " " + jenjang, Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
            }
        });

        aBuilder.setView(view);
        AlertDialog alertDialog = aBuilder.create();
        alertDialog.show();
    }

    @SuppressLint("InflateParams")
    private void showChangeMaps() {
        aBuilder = new AlertDialog.Builder(this);
        view = getLayoutInflater().inflate(R.layout.radiogroup_maptype, null);
        aBuilder.setTitle(getString(R.string.titleSilahkanMemilih)).setCancelable(false);

        final RadioGroup rgMaptype = view.findViewById(R.id.rgMaptype);

        RadioButton rbNone = view.findViewById(R.id.rbNone);
        RadioButton rbNormal = view.findViewById(R.id.rbNormal);
        RadioButton rbSatellite = view.findViewById(R.id.rbSatellite);
        RadioButton rbTerrain = view.findViewById(R.id.rbTerrain);
        RadioButton rbHybrid = view.findViewById(R.id.rbHybrid);

        if (mapType.contains(getString(R.string.none))) {
            rbNone.setChecked(true);
        } else if (mapType.contains(getString(R.string.normal))) {
            rbNormal.setChecked(true);
        } else if (mapType.contains(getString(R.string.satellite))) {
            rbSatellite.setChecked(true);
        } else if (mapType.contains(getString(R.string.terrain))) {
            rbTerrain.setChecked(true);
        } else if (mapType.contains(getString(R.string.hybrid))) {
            rbHybrid.setChecked(true);
        }

        aBuilder.setPositiveButton(R.string.btnOk, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int idMapType = rgMaptype.getCheckedRadioButtonId();
                final RadioButton rbMaptype = view.findViewById(idMapType);

                if (rbMaptype.getText().toString().contains(getString(R.string.none))) {
                    mapType = getString(R.string.none);
                    googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);
                } else if (rbMaptype.getText().toString().contains(getString(R.string.normal))) {
                    mapType = getString(R.string.normal);
                    googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                } else if (rbMaptype.getText().toString().contains(getString(R.string.satellite))) {
                    mapType = getString(R.string.satellite);
                    googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                } else if (rbMaptype.getText().toString().contains(getString(R.string.terrain))) {
                    mapType = getString(R.string.terrain);
                    googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                } else if (rbMaptype.getText().toString().contains(getString(R.string.hybrid))) {
                    mapType = getString(R.string.hybrid);
                    googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                }
            }
        });

        aBuilder.setView(view);
        alertDialog = aBuilder.create();
        alertDialog.show();
    }

    @SuppressLint({"InflateParams", "SetTextI18n"})
    private void showInfoLocation() {
        aBuilder = new AlertDialog.Builder(this);
        view = getLayoutInflater().inflate(R.layout.alertdialog_infolocation, null);

        TextView txtLat = view.findViewById(R.id.latInfo);
        TextView txtLng = view.findViewById(R.id.lngInfo);
        TextView txtAkurasi = view.findViewById(R.id.akurasi);

        DecimalFormat decimalFormatDuaTiga = new DecimalFormat(getString(R.string.decimalFormatDuaTiga));
        DecimalFormat decimalFormatEmpatEnam = new DecimalFormat(getString(R.string.decimalFormatEmpatEnam));
        DecimalFormatSymbols decimalFormatSymbols = DecimalFormatSymbols.getInstance();
        decimalFormatSymbols.setDecimalSeparator('.');
        decimalFormatDuaTiga.setDecimalFormatSymbols(decimalFormatSymbols);
        decimalFormatEmpatEnam.setDecimalFormatSymbols(decimalFormatSymbols);

        txtLat.setText(String.valueOf(decimalFormatEmpatEnam.format(myLocation.getLatitude() / 1)));
        txtLng.setText(String.valueOf(decimalFormatEmpatEnam.format(myLocation.getLongitude() / 1)));
        txtAkurasi.setText(String.valueOf(decimalFormatDuaTiga.format(myLocation.getAccuracy() / 1)) + " m");

        aBuilder.setView(view);
        alertDialog = aBuilder.create();
        alertDialog.show();
    }

    @SuppressLint({"InflateParams", "SetTextI18n"})
    private void showAbout() {
        aBuilder = new AlertDialog.Builder(this);
        view = getLayoutInflater().inflate(R.layout.about_app, null);
        aBuilder.setCancelable(true);

        TextView txtVersion = view.findViewById(R.id.txtVersion);

        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = packageInfo.versionName;
            txtVersion.setText(getString(R.string.versiAplikasi) + " " + version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        aBuilder.setView(view);
        alertDialog = aBuilder.create();
        alertDialog.show();
    }
}
