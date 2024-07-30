package dev.marianof.gestionmultiservicios.Controller;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import dev.marianof.gestionmultiservicios.Model.Cliente;
import dev.marianof.gestionmultiservicios.Model.Factura;
import dev.marianof.gestionmultiservicios.Model.Producto;
import dev.marianof.gestionmultiservicios.Model.User;
import dev.marianof.gestionmultiservicios.View.ClientesMainActivity;
import dev.marianof.gestionmultiservicios.View.UserMainActivity;

public class Peticion {
    public void getLogin(String user, String pass) {
        String jsonInputString = "{\"mail\": \"" + user + "\", \"pass\": \"" + pass + "\"}";
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonInputString);
        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/json")
                .addHeader("ApiKey", "YOUR_OWN_API_KEY")
                .url("https://YOUR_IP_SERVER:9443/gms2api/api/login/getUser")
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String res = response.body().string();
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        LoginController.getSingleton().handleResponse(res);
                    }
                });
            }
        });
    }

    public void getPosition() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .addHeader("cache-control","no-cache")
                .addHeader("ApiKey", "YOUR_OWN_API_KEY")
                .url("https://YOUR_IP_SERVER:9443/gms2api/api/gps/getPosition")
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String res = response.body().string();
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        GpsController.getSingleton().handleResponse(res);
                    }
                });
            }
        });
    }
    public void getFacturas() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .addHeader("control-cache", "no-cache")
                .addHeader("ApiKey", "YOUR_OWN_API_KEY")
                .url("https://YOUR_IP_SERVER:9443/gms2api/api/facturacion/getFacturas")
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String res = response.body().string();
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        FacturacionMainController.getSingleton().handleResponse(res);
                    }
                });
            }
        });
    }

    public void getProductos() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .addHeader("control-cache", "no-cache")
                .addHeader("ApiKey", "YOUR_OWN_API_KEY")
                .url("https://YOUR_IP_SERVER:9443/gms2api/api/gestion/getProductos")
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String res = response.body().string();

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        VerProductosFacturaController.getSingleton().handleProductos(res);
                    }
                });
            }
        });
    }

    public void getClientes(int i) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .addHeader("control-cache", "no-cache")
                .addHeader("ApiKey", "YOUR_OWN_API_KEY")
                .url("https://YOUR_IP_SERVER:9443/gms2api/api/clientes/getClientes")
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String s = response.body().string();
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        switch (i) {
                            case 1:
                                CrearFacturaController.getSingleton().handleClientes(s);
                                break;
                            case 2:
                                ModificarFacturaController.getSingleton().handleClientes(s);
                                break;
                            default:
                                break;
                        }
                    }
                });
            }
        });
    }

    public void modifyProductos(LinkedList<Producto> productos) {
        String json = new Gson().toJson(productos);
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);
        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/json")
                .addHeader("ApiKey", "YOUR_OWN_API_KEY")
                .url("https://YOUR_IP_SERVER:9443/gms2api/api/gestion/modifyProductos")
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String s = response.body().string();

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        System.out.printf(s);
                    }
                });
            }
        });
    }

    public void addFactura(Factura factura) {
        String json = new Gson().toJson(factura);
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);
        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/json")
                .addHeader("ApiKey", "YOUR_OWN_API_KEY")
                .url("https://YOUR_IP_SERVER:9443/gms2api/api/facturacion/addFacturas")
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String s = response.body().string();

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(s);
                    }
                });
            }
        });
    }
    public void deleteFactura(Factura factura) {
        String json = new Gson().toJson(factura);
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);
        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/json")
                .addHeader("ApiKey", "YOUR_OWN_API_KEY")
                .url("https://YOUR_IP_SERVER:9443/gms2api/api/facturacion/deleteFactura")
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String s = response.body().string();

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(s);
                    }
                });
            }
        });
    }

    public void modifyFactura(Factura factura) {
        String json = new Gson().toJson(factura);
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);
        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/json")
                .addHeader("ApiKey", "YOUR_OWN_API_KEY")
                .url("https://YOUR_IP_SERVER:9443/gms2api/api/facturacion/modifyFactura")
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String s = response.body().string();

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(s);
                    }
                });
            }
        });
    }

    public void getUsuarios() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .addHeader("control-cache", "no-cache")
                .addHeader("ApiKey", "YOUR_OWN_API_KEY")
                .url("https://YOUR_IP_SERVER:9443/gms2api/api/users/getUsers")
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String res = response.body().string();

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        UsersMainController.getSingleton().handleUsuarios(res);
                    }
                });
            }
        });
    }

    public void getClientes() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .addHeader("control-cache", "no-cache")
                .addHeader("ApiKey", "YOUR_OWN_API_KEY")
                .url("https://YOUR_IP_SERVER:9443/gms2api/api/clientes/getClientes")
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String res = response.body().string();

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        ClientesMainController.getSingleton().handleClientes(res);
                    }
                });
            }
        });
    }

    public void addCliente(Cliente cliente) {
        String json = new Gson().toJson(cliente);
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);
        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/json")
                .addHeader("ApiKey", "YOUR_OWN_API_KEY")
                .url("https://YOUR_IP_SERVER:9443/gms2api/api/clientes/addCliente")
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String s = response.body().string();

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(s);
                    }
                });
            }
        });
    }
    public void deleteCliente(Cliente cliente) {
        String json = new Gson().toJson(cliente);
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);
        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/json")
                .addHeader("ApiKey", "YOUR_OWN_API_KEY")
                .url("https://YOUR_IP_SERVER:9443/gms2api/api/clientes/deleteCliente")
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String s = response.body().string();

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(s);
                    }
                });
            }
        });
    }
    public void modifyCliente(Cliente cliente) {
        String json = new Gson().toJson(cliente);
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);
        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/json")
                .addHeader("ApiKey", "YOUR_OWN_API_KEY")
                .url("https://YOUR_IP_SERVER:9443/gms2api/api/clientes/modifyCliente")
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String s = response.body().string();

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(s);
                    }
                });
            }
        });
    }

    public void addUser(User user) {
        String jsonString = "{\"admin\":\"" + (user.isAdmin() ? "Y" : "N") + "\","
                + "\"idUser\":" + -1 + ","
                + "\"login\":\"N\","  // Assuming "N" should be a string
                + "\"mail\":\"" + user.getUsername() + "\","
                + "\"nombre\":\"" + user.getNombre() + "\"}";
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonString);
        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/json")
                .addHeader("ApiKey", "YOUR_OWN_API_KEY")
                .url("https://YOUR_IP_SERVER:9443/gms2api/api/users/addUser")
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String s = response.body().string();

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(s);
                    }
                });
            }
        });
    }

    public void deleteUser(User user) {
        String json = new Gson().toJson(user);
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);
        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/json")
                .addHeader("ApiKey", "YOUR_OWN_API_KEY")
                .url("https://YOUR_IP_SERVER:9443/gms2api/api/users/deleteUser")
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String s = response.body().string();

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(s);
                    }
                });
            }
        });
    }
    public void modifyUser(User user) {
        String jsonString = "{\"admin\":\"" + (user.isAdmin() ? "YES" : "NO") + "\","
                + "\"idUser\":" + -1 + ","
                + "\"login\":\"N\","  // Assuming "N" should be a string
                + "\"mail\":\"" + user.getUsername() + "\","
                + "\"nombre\":\"" + user.getNombre() + "\"}";
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonString);
        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/json")
                .addHeader("ApiKey", "YOUR_OWN_API_KEY")
                .url("https://YOUR_IP_SERVER:9443/gms2api/api/users/modifyUser")
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String s = response.body().string();

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(s);
                    }
                });
            }
        });
    }
}
