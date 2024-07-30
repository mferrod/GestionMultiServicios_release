package dev.marianof.gms2_app.Controller.ScheduledTasks;

import dev.marianof.gms2_app.Controller.GmsVehLocationController;

import java.util.TimerTask;

public class PositionPetition extends TimerTask {
    @Override
    public void run() {
        GmsVehLocationController.getSingleton().makePetition();
    }
}
