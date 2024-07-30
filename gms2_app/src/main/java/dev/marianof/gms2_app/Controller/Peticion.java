package dev.marianof.gms2_app.Controller;

import com.google.gson.Gson;
import com.squareup.okhttp.*;
import dev.marianof.gms2_app.Controller.Interface.PeticionInterfaz;
import dev.marianof.gms2_app.Model.Cliente;
import dev.marianof.gms2_app.Model.Factura;
import dev.marianof.gms2_app.Model.InfoFactura;
import dev.marianof.gms2_app.Model.Producto;
import javafx.application.Platform;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.ArrayList;

public class Peticion implements PeticionInterfaz {
    public void getUser(String user, String pass, ActionEvent actionEvent) {
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
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            GmsLoginController.getSingleton().setUserData(response.body().string(), actionEvent);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
        });
    }

    public void getProductos(int i) {
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
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            switch (i) {
                                case 1:
                                    GmsGestionMainController.getSingleton().setProductsData(response.body().string());
                                    break;
                                case 2:
                                    GmsGestionHacerPedidoController.getSingleton().setProductosData(response.body().string());
                                    break;
                                case 3:
                                    GmsFacturacionCrearFacturaController.getSingleton().handleProductos(response.body().string());
                                    break;
                                case 4:
                                    GmsFacturacionVerFacturaController.getSingleton().handleProductos(response.body().string());
                                    break;
                                default:
                                    break;
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
        });
    }

    public void getProveedores(int i) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .addHeader("control-cache", "no-cache")
                .addHeader("ApiKey", "YOUR_OWN_API_KEY")
                .url("https://YOUR_IP_SERVER:9443/gms2api/api/gestion/getProveedores")
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            switch (i) {
                                case 1:
                                    GmsGestionModificarController.getSingleton().setProveedoresData(response.body().string());
                                    break;
                                case 2:
                                    GmsGestionHacerPedidoController.getSingleton().setProveedoresData(response.body().string());
                                    break;
                                default:
                                    break;
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
        });
    }

    public void modifyProducto(Producto producto) {
        String jsonInputString = new Gson().toJson(producto);
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonInputString);
        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/json")
                .addHeader("ApiKey", "YOUR_OWN_API_KEY")
                .url("https://YOUR_IP_SERVER:9443/gms2api/api/gestion/updateProducto")
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                System.out.println(response.body().string());
            }
        });
    }

    public void makePedido(ArrayList<Producto> productosPedido) {//
        String jsonInputString = new Gson().toJson(productosPedido);
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonInputString);
        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/json")
                .addHeader("ApiKey", "YOUR_OWN_API_KEY")
                .url("https://YOUR_IP_SERVER:9443/gms2api/api/gestion/makePedido")
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                System.out.println(response.body().string());
            }
        });
    }

    public void getLocalizacion() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .addHeader("control-cache", "no-cache")
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
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            GmsVehLocationController.getSingleton().handleResponse(response.body().string());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
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
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            GmsFacturacionMainController.getSingleton().setFacturaData(response.body().string());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
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
        makePeticion(okHttpClient, request);
    }

    public void getInfoFactura(InfoFactura infoFactura) {
        String jsonInputString = new Gson().toJson(infoFactura);
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonInputString);
        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/json")
                .addHeader("ApiKey", "YOUR_OWN_API_KEY")
                .url("https://YOUR_IP_SERVER:9443/gms2api/api/facturacion/getInfoFactura")
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            GmsFacturacionMainController.getSingleton().setInfoFacturaData(response.body().string());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
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
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            switch (i) {
                                case 1:
                                    GmsFacturacionCrearFacturaController.getSingleton().handleClientes(response.body().string());
                                    break;
                                case 2:
                                    GmsFacturacionVerFacturaController.getSingleton().handleClientes(response.body().string());
                                    break;
                                case 3:
                                    GmsClientesMainController.getSingleton().handleClientes(response.body().string());
                                default:
                                    break;
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
        });
    }

    public void modifyProductos(ArrayList<Producto> productos) {
        String json = new Gson().toJson(productos);
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);
        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/json")
                .addHeader("ApiKey", "YOUR_OWN_API_KEY")
                .url("https://YOUR_IP_SERVER:9443/gms2api/api/gestion/modifyProductos")
                .post(requestBody).url("https://YOUR_IP_SERVER:9443/gms2api/api/gestion/modifyProductos")
                .build();
        makePeticion(okHttpClient, request);
    }

    public void deleteFactura(Factura factura) {
        String json = new Gson().toJson(factura);
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);
        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/json")
                .addHeader("ApiKey", "YOUR_OWN_API_KEY")
                .url("https://YOUR_IP_SERVER:9443/gms2api/api/facturacion/deleteFactura")
                .delete(requestBody)
                .build();
        makePeticion(okHttpClient, request);
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
        makePeticion(okHttpClient, request);
    }

    @Override
    public void makePeticion(OkHttpClient okHttpClient, Request request) {
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println(response.body().string());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
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
                .delete(requestBody)
                .build();
        makePeticion(okHttpClient, request);
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
        makePeticion(okHttpClient, request);
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
        makePeticion(okHttpClient, request);
    }
}
