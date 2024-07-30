package dev.marianof.gestionmultiservicios.View;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Timer;

import dev.marianof.gestionmultiservicios.Controller.GpsController;
import dev.marianof.gestionmultiservicios.Controller.ScheduledTask.PositionTask;
import dev.marianof.gestionmultiservicios.Model.Coordenadas;
import dev.marianof.gestionmultiservicios.R;

public class GpsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private FrameLayout map;
    private LatLng mapaPos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        GpsController.getSingleton().setMyActivity(this);

        map = findViewById(R.id.map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().
                findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        this.activateTask();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;
        mapaPos = new LatLng(20.5937, 78.9629);
        this.googleMap.addMarker(new MarkerOptions().position(mapaPos).title("Posición del vehículo"));
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(mapaPos));
    }

    private void activateTask() {
        Timer tiempo = new Timer();
        tiempo.schedule(new PositionTask(), 1000, 5000);
    }

    public void setPos() {
        Coordenadas cor = GpsController.getSingleton().getCoordenadas();
        mapaPos = new LatLng(cor.getLatitud(), cor.getLongitud());
        this.googleMap.clear();
        this.googleMap.addMarker(new MarkerOptions().position(mapaPos).title(cor.getNombreVeh()));
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(mapaPos));
        this.googleMap.setMinZoomPreference(10f);
    }
}
