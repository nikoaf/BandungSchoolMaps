package com.bandungschoolmaps.activity;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.bandungschoolmaps.R;
import com.bandungschoolmaps.app.AppController;
import com.bandungschoolmaps.other.ToastHelper;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Niko Ahmad Fauzi on 15/10/2017.
 */

public class SplashScreen extends AppCompatActivity {

    private AlertDialog.Builder builder;
    private GoogleApiClient googleApiClient;
    private Intent intent;
    private LocationRequest locationRequest;
    private String[] id, status;
    private ToastHelper toastHelper = new ToastHelper(this);

    private static final int ACCESS_FINE_LOCATION_INTENT_ID = 3;
    private static final int REQUEST_CHECK_SETTINGS = 0x1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        setUpGoogleApiClientIfNeeded();
        checkPermissions();
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case ACCESS_FINE_LOCATION_INTENT_ID : {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        if (googleApiClient == null) {
                            setUpGoogleApiClientIfNeeded();
                            showSettingDialog();
                        } else {
                            showSettingDialog();
                        }
                    }
                } else {
                    toastHelper.showToast(getString(R.string.permission));
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LocationSettingsStates locationSettingsStates = LocationSettingsStates.fromIntent(data);
        switch (requestCode) {
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                        connectionCek();
                        break;
                    case Activity.RESULT_CANCELED:
                        if (!locationSettingsStates.isGpsUsable()) {
                            finish();
                        }
                        break;
                    default:
                        break;
                }
                break;
        }
    }

    protected synchronized void setUpGoogleApiClientIfNeeded() {
        googleApiClient = new GoogleApiClient.Builder(this).addApi(LocationServices.API).build();
        googleApiClient.connect();
    }

    private void checkPermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                requestLocationPermissions();
            } else {
                showSettingDialog();
            }
        } else {
            showSettingDialog();
        }
    }

    private void requestLocationPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    ACCESS_FINE_LOCATION_INTENT_ID);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    ACCESS_FINE_LOCATION_INTENT_ID);
        }
    }

    private void showSettingDialog() {
        onPause();
        locationRequest = LocationRequest.create().setInterval(30 * 1000).setFastestInterval(5 * 1000);
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
                        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

                        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                            onResume();
                            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                            connectionCek();
                        }
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED :
                        try {
                            status.startResolutionForResult(SplashScreen.this, REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                            e.printStackTrace();
                        }
                        break;
                    case LocationSettingsStatusCodes.CANCELED :
                        finish();
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

    private boolean connectionCek() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        assert connectivityManager != null;
        if (connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isAvailable()
                && connectivityManager.getActiveNetworkInfo().isConnected()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    jsonMaintenance();
                }
            }, 2000);
        } else {
            builderConnectionError();
        }
        return connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isAvailable()
                && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    private void jsonMaintenance() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, getString(R.string.urlMaintenance),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        VolleyLog.d(getString(R.string.maintenance), response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray(getString(R.string.dbBsm));

                            id = new String[jsonArray.length()];
                            status = new String[jsonArray.length()];

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                id[i] = object.getString(getString(R.string.dbId));
                                status[i] = object.getString(getString(R.string.dbStatus));

                                if ((id[i].contains(String.valueOf(1)) && status[i].contains(getString(R.string.yBesar)))
                                        || (id[i].contains(String.valueOf(1)) && status[i].contains(getString(R.string.yKecil)))) {
                                    builderMaintenance();
                                } else {
                                    intent = new Intent(SplashScreen.this, Main.class);
                                    startActivity(intent);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                VolleyLog.d(getString(R.string.maintenance), volleyError.getMessage());
                builderError();
            }
        });
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    private void builderConnectionError() {
        builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_dialog_alert).setTitle(R.string.titleConnectionError)
                .setMessage(R.string.messageConnectionError).setCancelable(false)
                .setNegativeButton(R.string.btnKeluar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).show();
    }

    private void builderMaintenance() {
        builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_dialog_alert).setTitle(R.string.titleMaintenance)
                .setMessage(R.string.messageMaintenance).setCancelable(false)
                .setNeutralButton(R.string.btnOk, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).show();
    }

    private void builderError() {
        builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_dialog_alert).setTitle(R.string.titleError).setMessage(R.string.messageError)
                .setCancelable(false).setNeutralButton(R.string.btnOk, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).show();
    }
}
