package dev.marianof.gms2_app.Controller;

import dev.marianof.gms2_app.GmsFacturacionMain;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigator {
    private static Navigator controller;

    private Navigator() {

    }

    public static Navigator getSingleton() {
        if (controller == null)
            controller = new Navigator();
        return controller;
    }

    public void goLoginMain(ActionEvent actionEvent, Object view) {
        try {
            FXMLLoader loader = new FXMLLoader(view.getClass().getResource("gms_login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goFacturacionMain(ActionEvent actionEvent, Object view) {
        try {
            FXMLLoader loader = new FXMLLoader(view.getClass().getResource("gms_facturacion_main.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void goGestionMain(ActionEvent actionEvent, Object view) {
        try {
            FXMLLoader loader = new FXMLLoader(view.getClass().getResource("gms_gestion_main.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void goGestionModificar(ActionEvent actionEvent, Object view) {
        try {
            FXMLLoader loader = new FXMLLoader(view.getClass().getResource("gms_gestion_modificar.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void goVehLocation(ActionEvent actionEvent, Object view) {
        try {
            FXMLLoader loader = new FXMLLoader(view.getClass().getResource("gms_vehLocation.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void goMainMenu(ActionEvent actionEvent, Object view) {
        try {
            FXMLLoader loader = new FXMLLoader(view.getClass().getResource("gms_main_menu.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void goGestionHacerPedido(ActionEvent actionEvent, Object view) {
        try {
            FXMLLoader loader = new FXMLLoader(view.getClass().getResource("gms_gestion_hacer_pedido.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void goFacturacionCrearFactura(ActionEvent actionEvent, Object view) {
        try {
            FXMLLoader loader = new FXMLLoader(view.getClass().getResource("gms_facturacion_crear_factura.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void goFacturacionVerFactura(ActionEvent actionEvent, Object view) {
        try {
            FXMLLoader loader = new FXMLLoader(view.getClass().getResource("gms_facturacion_ver_factura.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void goClientesMain(ActionEvent actionEvent, Object view) {
        try {
            FXMLLoader loader = new FXMLLoader(view.getClass().getResource("gms_clientes_main.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void goClientesModyadd(ActionEvent actionEvent, Object view) {
        try {
            FXMLLoader loader = new FXMLLoader(view.getClass().getResource("gms_clientes_modiadd.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
