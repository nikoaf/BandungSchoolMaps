package com.bandungschoolmaps.other;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import com.bandungschoolmaps.region.Andir;
import com.bandungschoolmaps.region.Antapani;
import com.bandungschoolmaps.region.Arcamanik;
import com.bandungschoolmaps.region.Astanaanyar;
import com.bandungschoolmaps.region.BabakanCiparay;
import com.bandungschoolmaps.region.BandungKidul;
import com.bandungschoolmaps.region.BandungKulon;
import com.bandungschoolmaps.region.BandungWetan;
import com.bandungschoolmaps.region.Batununggal;
import com.bandungschoolmaps.region.BojongloaKaler;
import com.bandungschoolmaps.region.BojongloaKidul;
import com.bandungschoolmaps.region.Buahbatu;
import com.bandungschoolmaps.region.CibeunyingKaler;
import com.bandungschoolmaps.region.CibeunyingKidul;
import com.bandungschoolmaps.region.Cibiru;
import com.bandungschoolmaps.region.Cicendo;
import com.bandungschoolmaps.region.Cidadap;
import com.bandungschoolmaps.region.Cinambo;
import com.bandungschoolmaps.region.Coblong;
import com.bandungschoolmaps.region.Gedebage;
import com.bandungschoolmaps.region.Kiaracondong;
import com.bandungschoolmaps.region.KotaBandung;
import com.bandungschoolmaps.region.Lengkong;
import com.bandungschoolmaps.region.Mandalajati;
import com.bandungschoolmaps.region.Panyileukan;
import com.bandungschoolmaps.region.Rancasari;
import com.bandungschoolmaps.region.Regol;
import com.bandungschoolmaps.region.Sukajadi;
import com.bandungschoolmaps.region.Sukasari;
import com.bandungschoolmaps.region.SumurBandung;
import com.bandungschoolmaps.region.UjungBerung;

/**
 * Created by Niko Ahmad Fauzi on 15/10/2017.
 */

public class RegionHelper {

    public static void regionHelper(GoogleMap googleMap, LatLng latLng) {
        andir(googleMap, latLng);antapani(googleMap, latLng);arcamanik(googleMap, latLng);astanaanyar(googleMap, latLng);
        babakanCiparay(googleMap, latLng);bandungKidul(googleMap, latLng);bandungKulon(googleMap, latLng);
        bandungWetan(googleMap, latLng);batununggal(googleMap, latLng);bojongloaKaler(googleMap, latLng);
        bojongloaKidul(googleMap, latLng);buahBatu(googleMap, latLng);cibeunyingKaler(googleMap, latLng);
        cibeunyingKidul(googleMap, latLng);cibiru(googleMap, latLng);cicendo(googleMap, latLng);cidadap(googleMap, latLng);
        cinambo(googleMap, latLng);coblong(googleMap, latLng);gedebage(googleMap, latLng);kiaracondong(googleMap, latLng);
        lengkong(googleMap, latLng);mandalajati(googleMap, latLng);panyileukan(googleMap, latLng);rancasari(googleMap, latLng);
        regol(googleMap, latLng);sukajadi(googleMap, latLng);sukasari(googleMap, latLng);sumurBandung(googleMap, latLng);
        ujungBerung(googleMap, latLng);
    }

    public static void kotaBandung(ArrayList<LatLng> kotaBandung) {
        KotaBandung.regionKotaBandung(kotaBandung);
    }

    private static void andir(GoogleMap googleMap, LatLng latLng) {
        ArrayList<LatLng> andir = new ArrayList<>();
        Andir.regionAndir(andir, googleMap, latLng);
    }

    private static void antapani(GoogleMap googleMap, LatLng latLng) {
        ArrayList<LatLng> antapani = new ArrayList<>();
        Antapani.regionAntapani(antapani, googleMap, latLng);
    }

    private static void arcamanik(GoogleMap googleMap, LatLng latLng) {
        ArrayList<LatLng> arcamanik = new ArrayList<>();
        Arcamanik.regionArcamanik(arcamanik, googleMap, latLng);
    }

    private static void astanaanyar(GoogleMap googleMap, LatLng latLng) {
        ArrayList<LatLng> astanaanyar = new ArrayList<>();
        Astanaanyar.regionAstanaanyar(astanaanyar, googleMap, latLng);
    }

    private static void babakanCiparay(GoogleMap googleMap, LatLng latLng) {
        ArrayList<LatLng> babakanciparay = new ArrayList<>();
        BabakanCiparay.regionBabakanCiparay(babakanciparay, googleMap, latLng);
    }

    private static void bandungKidul(GoogleMap googleMap, LatLng latLng) {
        ArrayList<LatLng> bandungkidul = new ArrayList<>();
        BandungKidul.regionBandungKidul(bandungkidul, googleMap, latLng);
    }

    private static void bandungKulon(GoogleMap googleMap, LatLng latLng) {
        ArrayList<LatLng> bandungkulon = new ArrayList<>();
        BandungKulon.regionBandungKulon(bandungkulon, googleMap, latLng);
    }

    private static void bandungWetan(GoogleMap googleMap, LatLng latLng) {
        ArrayList<LatLng> bandungwetan = new ArrayList<>();
        BandungWetan.regionBandungWetan(bandungwetan, googleMap, latLng);
    }

    private static void batununggal(GoogleMap googleMap, LatLng latLng) {
        ArrayList<LatLng> batununggal = new ArrayList<>();
        Batununggal.regionBatununggal(batununggal, googleMap, latLng);
    }

    private static void bojongloaKaler(GoogleMap googleMap, LatLng latLng) {
        ArrayList<LatLng> bojongloakaler = new ArrayList<>();
        BojongloaKaler.regionBojongloaKaler(bojongloakaler, googleMap, latLng);
    }

    private static void bojongloaKidul(GoogleMap googleMap, LatLng latLng) {
        ArrayList<LatLng> bojongloakidul = new ArrayList<>();
        BojongloaKidul.regionBojongloaKidul(bojongloakidul, googleMap, latLng);
    }

    private static void buahBatu(GoogleMap googleMap, LatLng latLng) {
        ArrayList<LatLng> buahbatu = new ArrayList<>();
        Buahbatu.regionBuahbatu(buahbatu, googleMap, latLng);
    }

    private static void cibeunyingKaler(GoogleMap googleMap, LatLng latLng) {
        ArrayList<LatLng> cibeunyingkaler = new ArrayList<>();
        CibeunyingKaler.regionCibeunyingKaler(cibeunyingkaler, googleMap, latLng);
    }

    private static void cibeunyingKidul(GoogleMap googleMap, LatLng latLng) {
        ArrayList<LatLng> cibeunyingkidul = new ArrayList<>();
        CibeunyingKidul.regionCibeunyingKidul(cibeunyingkidul, googleMap, latLng);
    }

    private static void cibiru(GoogleMap googleMap, LatLng latLng) {
        ArrayList<LatLng> cibiru = new ArrayList<>();
        Cibiru.regionCibiru(cibiru, googleMap, latLng);
    }

    private static void cicendo(GoogleMap googleMap, LatLng latLng) {
        ArrayList<LatLng> cicendo = new ArrayList<>();
        Cicendo.regionCicendo(cicendo, googleMap, latLng);
    }

    private static void cidadap(GoogleMap googleMap, LatLng latLng) {
        ArrayList<LatLng> cidadap = new ArrayList<>();
        Cidadap.regionCidadap(cidadap, googleMap, latLng);
    }

    private static void cinambo(GoogleMap googleMap, LatLng latLng) {
        ArrayList<LatLng> cinambo = new ArrayList<>();
        Cinambo.regionCinambo(cinambo, googleMap, latLng);
    }

    private static void coblong(GoogleMap googleMap, LatLng latLng) {
        ArrayList<LatLng> coblong = new ArrayList<>();
        Coblong.regionCoblong(coblong, googleMap, latLng);
    }

    private static void gedebage(GoogleMap googleMap, LatLng latLng) {
        ArrayList<LatLng> gedebage = new ArrayList<>();
        Gedebage.regionGedebage(gedebage, googleMap, latLng);
    }

    private static void kiaracondong(GoogleMap googleMap, LatLng latLng) {
        ArrayList<LatLng> kiaracondong = new ArrayList<>();
        Kiaracondong.regionKiaracondong(kiaracondong, googleMap, latLng);
    }

    private static void lengkong(GoogleMap googleMap, LatLng latLng) {
        ArrayList<LatLng> lengkong = new ArrayList<>();
        Lengkong.regionLengkong(lengkong, googleMap, latLng);
    }

    private static void mandalajati(GoogleMap googleMap, LatLng latLng) {
        ArrayList<LatLng> mandalajati = new ArrayList<>();
        Mandalajati.regionMandalajati(mandalajati, googleMap, latLng);
    }

    private static void panyileukan(GoogleMap googleMap, LatLng latLng) {
        ArrayList<LatLng> panyileukan = new ArrayList<>();
        Panyileukan.regionPanyileukan(panyileukan, googleMap, latLng);
    }

    private static void rancasari(GoogleMap googleMap, LatLng latLng) {
        ArrayList<LatLng> rancasari = new ArrayList<>();
        Rancasari.regionRancasari(rancasari, googleMap, latLng);
    }

    private static void regol(GoogleMap googleMap, LatLng latLng) {
        ArrayList<LatLng> regol = new ArrayList<>();
        Regol.regionRegol(regol, googleMap, latLng);
    }

    private static void sukajadi(GoogleMap googleMap, LatLng latLng) {
        ArrayList<LatLng> sukajadi = new ArrayList<>();
        Sukajadi.regionSukajadi(sukajadi, googleMap, latLng);
    }

    private static void sukasari(GoogleMap googleMap, LatLng latLng) {
        ArrayList<LatLng> sukasari = new ArrayList<>();
        Sukasari.regionSukasari(sukasari, googleMap, latLng);
    }

    private static void sumurBandung(GoogleMap googleMap, LatLng latLng) {
        ArrayList<LatLng> sumurbandung = new ArrayList<>();
        SumurBandung.regionSumurBandung(sumurbandung, googleMap, latLng);
    }

    private static void ujungBerung(GoogleMap googleMap, LatLng latLng) {
        ArrayList<LatLng> ujungberung = new ArrayList<>();
        UjungBerung.regionUjungBerung(ujungberung, googleMap, latLng);
    }
}
