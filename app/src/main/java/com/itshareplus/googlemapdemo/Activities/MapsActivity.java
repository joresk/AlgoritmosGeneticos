package com.itshareplus.googlemapdemo.Activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;
import com.google.maps.android.SphericalUtil;
//import com.google.maps.android.clustering.algo.Algorithm;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.itshareplus.googlemapdemo.Modelo.*;
//import com.itshareplus.googlemapdemo.Modelo.DirectionFinderListener;
//import com.itshareplus.googlemapdemo.Modelo.GPSTracker;
//import com.itshareplus.googlemapdemo.Modelo.Route;
import com.itshareplus.googlemapdemo.AlgoritmoGenetico.*;
import com.itshareplus.googlemapdemo.AlgoritmoGenetico.Algorithm;
import com.itshareplus.googlemapdemo.R;
//import com.bradleybossard.androidprogramabdemo.Modelo.Route;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, DirectionFinderListener {

    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    private GoogleMap mMap;
    private Button btnFindPath;
    private List<Marker> originMarkers = new ArrayList<>();
    private List<Marker> destinationMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();
    private ProgressDialog progressDialog;
    private LatLng miPos;

    //creo un String estatico con la ruta que quiero que haga encriptada
    private final static String line = "xutbDh`}lKfqA|RrAeB";
    private final static String line1 = "xutbDh`}lKz]vFaDrY~aBtX";
    private final static String line2 = "xutbDh`}lKxnDzh@pEoQtHwFr@kE~PwJ";
    private final static String line3 = "xutbDh`}lKid@}GsDj[oO}B";
    private final static String line4 = "xutbDh`}lK`LbBmQr}AuGcA_Gre@";
    private final static String line5 = "xutbDh`}lK`LbByc@d|Dh^~IwCnZ`KxA";
    private final static String line6 = "xutbDh`}lKpsFdz@k@`FnfAlQaF`e@hAN";
    private final static String lineViolencia1 = "xutbDh`}lKfLvBaRxeBvo@fLkI~m@x@R";
    private final static String lineOvd = "xutbDh`}lKfdC`_@}AjMy@M";

    LatLng c1 = new LatLng(-26.817090, -65.198281);
    LatLng c2= new LatLng(-26.8306657, -65.2009692);
    LatLng c3 = new LatLng(-26.807574, -65.200768);
    LatLng c4 = new LatLng(-26.850956, -65.197886);
    LatLng c5 = new LatLng(-26.837053, -65.207890);
    LatLng c6 = new LatLng(-26.813558, -65.219766);
    LatLng c7 = new LatLng(-26.819470, -65.235667);
    LatLng c8 = new LatLng(-26.866653, -65.218146);
    LatLng c9 = new LatLng(-26.835836, -65.181256);
    LatLng c10 = new LatLng(-26.856681, -65.224723);
    LatLng c11 = new LatLng(-26.822592, -65.225076);
    LatLng c12 = new LatLng(-26.837643, -65.205640);

    // List<LatLng> decodedPath1 = PolyUtil.decode(line);
    //PolylineOptions POLILINEA1 = new PolylineOptions().addAll(decodedPath1);
    List<LatLng> decodedPath2 = PolyUtil.decode(line3);
    PolylineOptions POLILINEA2 = new PolylineOptions().addAll(decodedPath2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_maps);
        mMap = ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map)).getMap();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setTiltGesturesEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);

       cargarMarcadores();
  setMarker(new LatLng(-26.837053, -65.207890), "Comisaria 2", "Bs As 479,4000 SM Tucumán-Tel.:+54 381 424-8006");
        setMarker(new LatLng(-26.850956, -65.197886), "Comisaría Seccional 4º", "Eugenio Méndez 369,SM Tucumán,Tucumán-0381 424-8021");
        setMarker( new LatLng(-26.807574, -65.200768), "Policia Seccional 5°", "Muñecas 1658-tel:0381 424-8021");
        setMarker(new LatLng(-26.813558, -65.219766), "Policía Seccional 6°", "España 1700-tel:0381 432-0558");
        setMarker(new LatLng(-26.819470, -65.235667), "Policía Seccional 7º", "Don Bosco 2600" + "\n" + "0381 433-0573");
        setMarker(new LatLng(-26.866653, -65.218146), "Policía Seccional 9º", "Ayacucho 2752");
        setMarker(new LatLng(-26.835836, -65.181256), "Policía Seccional 11°", "Av. Benjamín Aráoz 109" + "\n" + "tel: 0381 431-0337");
        setMarker(new LatLng(-26.856681, -65.224723), "Policía Seccional 13°", "Altura Alem 2100");
        setMarker(new LatLng(-26.822592, -65.225076), "Centro de Atención y Orientación en Violencia Familiar", "Don Bosco 1886-Tel.:+54 381 451-4912");
        setMarker(new LatLng(-26.837643, -65.205640), "Oficina de Violencia Domestica°", "Lamadrid 450 Planta Baja-0381-4248000");

        GPSTracker gpsTracker = new GPSTracker(MapsActivity.this);
        if (gpsTracker.canGetLocation()) {
            miPos = new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude());
            Log.i("Debug", "Mi posicion on create: " + miPos);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(miPos, 15));
        } else {
            gpsTracker.showSettingsAlert();
        }
        btnFindPath = (Button) findViewById(R.id.btnFindPath);
        btnFindPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String origen = miPos.toString().replace("lat/lng: ","").replace("(","").replace(")","");
                obtenerRutaconAG(origen);

            }
        });


/*
       btnFindPath.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view)
            {showPolilinea();
            }                                                   });
        btnFindPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDistance();
            }
        });
*/
    }



   private void setMarker(LatLng position, String titulo, String info) {
        // Agregamos marcadores para indicar sitios de interéses.
        Marker myMaker = mMap.addMarker(new MarkerOptions()
                .position(position)
                .title(titulo)  //Agrega un titulo al marcador
                .snippet(info)   //Agrega información detalle relacionada con el marcador
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))); //Color del marcador
    }



    private void obtenerRutaconAG(String origen) {
        LatLng destino = obtenerPuntoMasCercano();
        if (destino.equals(c1)) {
            FitnessCalc.setSolution("00000000000111110000000001000000000100000000010000000001");
            //Creamos una población
            Individual mejor = generarPoblacion();
            obtenerRuta(origen,destino.toString().replace("lat/lng: ","").replace("(","").replace(")",""));
        } else if (destino.equals(c2)) {
            FitnessCalc.setSolution("00000000000000011111000001000000000100000000010000000001");
            //Creamos una población
            Individual mejor = generarPoblacion();
            obtenerRuta(origen,destino.toString().replace("lat/lng: ","").replace("(","").replace(")",""));
        } else if (destino.equals(c3)) {
            FitnessCalc.setSolution("00000000000000000000000000000000000000000000000000000010000000001000000000100000000010000000111");
            //Creamos una población
            Individual mejor = generarPoblacion();
            obtenerRuta(origen,destino.toString().replace("lat/lng: ","").replace("(","").replace(")",""));
        }
    }


    //Hacer Mediante Libreria "Library", calculo de punto mas cercano, agregar funcion
    private LatLng obtenerPuntoMasCercano() {
        LatLng destino = null;
        float distancias[] = new float[3];
        Location origen = new Location("origen");
        origen.setLatitude(miPos.latitude);
        origen.setLongitude(miPos.longitude);
        Location locationA = new Location("puntocarga1");
        locationA.setLatitude(c1.latitude);
        locationA.setLongitude(c2.longitude);
        Location locationB = new Location("puntocarga2");
        locationB.setLatitude(c1.latitude);
        locationB.setLongitude(c2.longitude);
        Location locationC = new Location("puntocarga3");
        locationC.setLatitude(c1.latitude);
        locationC.setLongitude(c2.longitude);
        float distanceOA = origen.distanceTo(locationA);
        distancias[0]=distanceOA;
        float distanceOB = origen.distanceTo(locationB);
        distancias[1]=distanceOB;
        float distanceOC = origen.distanceTo(locationC);
        distancias[2]=distanceOC;
        float mayor = 0;
        int pos=0;
        for (int i=0;i<distancias.length;i++){
            if(distancias[i]>mayor){
                mayor=distancias[i];
                pos=i;
            }
        }
        switch (pos) {
            case 0:
                destino = new LatLng(c1.latitude,c1.longitude);
                break;
            case 1:
                destino = new LatLng(c2.latitude,c2.longitude);
                break;
            case 2:
                destino = new LatLng(c3.latitude,c3.longitude);
                break;
        }
        return destino;
    }

    private Individual generarPoblacion() {
        Population myPop = new Population(4, true);
        //Generamos y mutamos de acuerdo a la población inicial
        int generationCount = 0;
        System.out.println("Generando población..");
        while (myPop.getFittest().getFitness() < FitnessCalc.getMaxFitness()) {
            generationCount++;
            System.out.println("Generación n°: " + generationCount);
            myPop = Algorithm.evolvePopulation(myPop);
        }
        System.out.println("Solucion encontrada!!");
        System.out.println("El mejor individuo es de la generación n°: " + generationCount);
        System.out.println("Genes:");
        System.out.println(myPop.getFittest());
        return myPop.getFittest();
    }


    private void cargarMarcadores() {
         Marker m1 = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-26.8306657, -65.2009692))
                .title("Comisaria 1")
               .snippet("San Martín 224,4000 San Miguel de Tucumán - tel.:+54 381 431-0480"));
        m1.hideInfoWindow();
        Marker m2 = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-26.817090, -65.198281))
                .title("Facultad Regional Tucumán")
                .snippet("Rivadavia 1050"));
        m2.hideInfoWindow();
        /* setMarker(new LatLng(-26.837053, -65.207890), "Comisaria 2", "Bs As 479,4000 SM Tucumán-Tel.:+54 381 424-8006");
        setMarker(new LatLng(-26.850956, -65.197886), "Comisaría Seccional 4º", "Eugenio Méndez 369,SM Tucumán,Tucumán-0381 424-8021");
      setMarker( new LatLng(-26.807574, -65.200768), "Policia Seccional 5°", "Muñecas 1658-tel:0381 424-8021");
        setMarker(new LatLng(-26.813558, -65.219766), "Policía Seccional 6°", "España 1700-tel:0381 432-0558");
         setMarker(new LatLng(-26.819470, -65.235667), "Policía Seccional 7º", "Don Bosco 2600" + "\n" + "0381 433-0573");
         setMarker(new LatLng(-26.866653, -65.218146), "Policía Seccional 9º", "Ayacucho 2752");
         setMarker(new LatLng(-26.835836, -65.181256), "Policía Seccional 11°", "Av. Benjamín Aráoz 109" + "\n" + "tel: 0381 431-0337");
        setMarker(new LatLng(-26.856681, -65.224723), "Policía Seccional 13°", "Altura Alem 2100");
        setMarker(new LatLng(-26.822592, -65.225076), "Centro de Atención y Orientación en Violencia Familiar", "Don Bosco 1886-Tel.:+54 381 451-4912");
        setMarker(new LatLng(-26.837643, -65.205640), "Oficina de Violencia Domestica°", "Lamadrid 450 Planta Baja-0381-4248000");
        */
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng hcmus = new LatLng(10.762963, 106.682394);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hcmus, 18));
        originMarkers.add(mMap.addMarker(new MarkerOptions()
                .title("Đại học Khoa học tự nhiên")
                .position(hcmus)));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
    }


    public boolean onMarkerClick(Marker marker) {
        String origen = miPos.toString().replace("lat/lng: ","").replace("(","").replace(")","");
        String destino = marker.getPosition().toString().replace("lat/lng: ","").replace("(","").replace(")","");
        System.out.println("Las coordenadas de origen:" + origen + " Las coordenadas de destino:"+destino);
        obtenerRuta(origen,destino);
        return false;
    }
    @Override
    public void onDirectionFinderStart() {
        progressDialog = ProgressDialog.show(this, "Espera un Momento....",
                "Buscando Dirección..!", true);

        if (originMarkers != null) {
            for (Marker marker : originMarkers) {
                marker.remove();
            }
        }

        if (destinationMarkers != null) {
            for (Marker marker : destinationMarkers) {
                marker.remove();
            }
        }

        if (polylinePaths != null) {
            for (Polyline polyline:polylinePaths ) {
                polyline.remove();
            }
        }
    }

    @Override
    public void onDirectionFinderSuccess(List<Route> routes) {
        progressDialog.dismiss();
        polylinePaths = new ArrayList<>();
        originMarkers = new ArrayList<>();
        destinationMarkers = new ArrayList<>();

        for (Route route : routes) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route.startLocation, 16));
            ((TextView) findViewById(R.id.tvDuration)).setText(route.duration.text);
            ((TextView) findViewById(R.id.tvDistance)).setText(route.distance.text);

            originMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.start_blue))
                    .title(route.startAddress)
                    .position(route.startLocation)));
            destinationMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.end_green))
                    .title(route.endAddress)
                    .position(route.endLocation)));

            PolylineOptions polylineOptions = new PolylineOptions().
                    geodesic(true).
                    color(Color.BLUE).
                    width(10);

            for (int i = 0; i < route.points.size(); i++)
                polylineOptions.add(route.points.get(i));

            polylinePaths.add(mMap.addPolyline(polylineOptions));
        }
    }
    private void showPolilinea (){

        new CountDownTimer(3000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                drawPolilyne(POLILINEA2);
            }
        }.start();


    }
    private void drawPolilyne(PolylineOptions options) {
        Polyline polyline = mMap.addPolyline(options);
    }
    private void showDistance() {
        double distance1 = SphericalUtil.computeDistanceBetween(new LatLng(-26.817090, -65.198281), new LatLng(-26.8306657, -65.2009692));
        double distance2 = SphericalUtil.computeDistanceBetween(new LatLng(-26.817090, -65.198281), new LatLng(-26.807574, -65.200768));
        if (distance1>distance2){
            Toast notificacion=Toast.makeText(this,(formatNumber(distance2)),Toast.LENGTH_LONG);
            notificacion.show();
        }
        else {Toast notificacion=Toast.makeText(this,(formatNumber(distance1)),Toast.LENGTH_LONG);
            notificacion.show();
        }

    }
    private String formatNumber(double distance) {
        String unit = "m";
        if (distance >1000){
            distance = distance/1000;
            unit = "km";
        }
        return String.format("%4.4f%s", distance, unit);
    }
    public void obtenerRuta(String origen, String destino){
        try {
            new DirectionFinder(this, origen, destino).execute();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
