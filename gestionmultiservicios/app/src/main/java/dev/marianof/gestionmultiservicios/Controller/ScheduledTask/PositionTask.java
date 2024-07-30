package dev.marianof.gestionmultiservicios.Controller.ScheduledTask;

import java.util.TimerTask;

import dev.marianof.gestionmultiservicios.Controller.GpsController;

public class PositionTask extends TimerTask {
    @Override
    public void run() {
        GpsController.getSingleton().makePetition();
    }
}
