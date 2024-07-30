package dev.marianof.gms2_app.Controller.Interface;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

public interface PeticionInterfaz {
    public void makePeticion(OkHttpClient okHttpClient, Request request);
}
