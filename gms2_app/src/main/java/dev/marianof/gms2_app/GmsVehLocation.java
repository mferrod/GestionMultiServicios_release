package dev.marianof.gms2_app;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;
import dev.marianof.gms2_app.Controller.GmsVehLocationController;
import dev.marianof.gms2_app.Controller.Navigator;
import dev.marianof.gms2_app.Controller.ScheduledTasks.PositionPetition;
import dev.marianof.gms2_app.Model.Posicion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Timer;

public class GmsVehLocation implements Initializable {
    @FXML
    public Button salirBtn;
    private MapView mapView;
    public VBox vbox;
    private MapLayer mapLayer;
    private MapPoint mapPoint;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GmsVehLocationController.getSingleton().setMyActivity(this);
        this.setMapPoint(37.91297,-3.00268);
        mapView = createMap();
        mapView.setVisible(false);
        vbox.getChildren().add(mapView);
        VBox.setVgrow(mapView, Priority.ALWAYS);
        this.setUpTimerTask();
        mapView.setVisible(true);
    }
    @FXML
    public void salir(ActionEvent actionEvent) {
        Navigator.getSingleton().goMainMenu(actionEvent, this);
    }
    public void setUpTimerTask() {
        Timer tiempo = new Timer();
        tiempo.schedule(new PositionPetition(), 1000, 5000);
    }
    public void setMapPoint(double lat, double lon) {
        mapPoint = new MapPoint(lat, lon);
    }
    private MapView createMap() {
        MapView mv = new MapView();
        mv.setPrefSize(400, 400);
        mv.setZoom(20);
        mv.setCenter(mapPoint);
        //mv.flyTo(0, mapPoint, 1);
        return mv;
    }
    public void setPos() {
        Posicion posicion = GmsVehLocationController.getSingleton().getPosicion();
        this.setMapPoint(
                posicion.getLat(),
                posicion.getLon()
        );
        //if (mapLayer != null)
        //    mapView.removeLayer(mapLayer);
        //mapView.flyTo(0, mapPoint, 0);
        mapView.setCenter(mapPoint);
        mapLayer = new CustomLayer();
        mapView.addLayer(mapLayer);
    }

    private class CustomLayer extends MapLayer {
        private final Node marker;
        public CustomLayer() {
            marker = new Circle(5, Color.RED);
            getChildren().add(marker);
        }

        @Override
        protected void layoutLayer() {
            super.layoutLayer();
            Point2D point2D = getMapPoint(mapPoint.getLatitude(), mapPoint.getLongitude());
            marker.setTranslateX(point2D.getX());
            marker.setTranslateY(point2D.getY());
        }
    }
}
