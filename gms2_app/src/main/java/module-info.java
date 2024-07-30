module dev.marianof.gms2_app {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.net.http;
    requires com.google.gson;
    requires okhttp;
    requires java.desktop;
    requires javafx.web;
    requires com.gluonhq.maps;
    requires java.rmi;
    requires java.sql;
    requires layout;
    requires kernel;
    requires io;

    opens dev.marianof.gms2_app to javafx.fxml;
    exports dev.marianof.gms2_app;
    exports dev.marianof.gms2_app.Model;
    opens dev.marianof.gms2_app.Model to javafx.fxml, com.google.gson;
    exports dev.marianof.gms2_app.Controller;
    opens dev.marianof.gms2_app.Controller to javafx.fxml;
}