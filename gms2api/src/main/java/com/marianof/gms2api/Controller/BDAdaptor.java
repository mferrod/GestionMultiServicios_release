package com.marianof.gms2api.Controller;

import com.google.gson.*;
import com.marianof.gms2api.Model.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

@Component
public class BDAdaptor {

    @Value("${db.dbname}")
    private String dbname;

    @Value("${db.dbuser}")
    private String dbuser;

    @Value("${db.dbpass}")
    private String dbpass;

    private Connection initDatabase() {
        Connection connection = null;
        String dbDriver = "com.mysql.cj.jdbc.Driver";
        String dbURL = "jdbc:mysql://YOUR_IP_SERVER:27892/";
        String dbName = "gmsapi";
        String dbUser = "gmsapi_user";
        String dbPass = "8iLibixOQI4iwu7AtInAP81Ose7Oxo";

        try {
            Class.forName(dbDriver);
            connection = DriverManager.getConnection(dbURL + dbName, dbUser, dbPass);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return connection;
    }

    public String getUser(Usuario usuario) {
        Connection c = null;
        Usuario usu = null;
        PreparedStatement preparedStatement = null;
        try {
            c = this.initDatabase();
            preparedStatement = c.prepareStatement("SELECT * FROM usuarios WHERE mail='"+ usuario.getMail() +
                    "' AND password='"+ usuario.getPass() + "'");
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                usu = new Usuario(rs.getInt("id"),
                        rs.getString("mail"),
                                null,
                                rs.getString("nombre"),
                                rs.getString("admin"),
                        "Y");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                c.close();
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        assert usu != null;
        this.updateLoginDate(usu.getIdUsario());
        return new Gson().toJson(usu);
    }

    private ArrayList<Producto> parseArray(String s) {
        ArrayList<Producto> arrayList = new ArrayList<>();
        JsonElement j = JsonParser.parseString(s);
        JsonArray js = j.getAsJsonArray();
        for (int i = 0; i < js.size(); i++) {
            JsonObject object = js.get(i).getAsJsonObject();
            arrayList.add(
                    new Producto(
                            object.get("id").getAsString(),
                            object.get("nombre").getAsString(),
                            object.get("descripcion").getAsString(),
                            object.get("precio").getAsFloat(),
                            object.get("cantidad").getAsInt(),
                            object.get("idProveedor").toString(),
                            object.get("nombreProveedor").toString()
                    )
            );
        }
        return arrayList;
    }

    private String generateSecurePassword() {
        char[] possibleCharacters = ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~!@#$%^&*()-_=+[{]}|;:,<.>/?").toCharArray();
        return RandomStringUtils.random( 15 + new Random().nextInt(10, 20), 0, possibleCharacters.length-1, false, false, possibleCharacters, new SecureRandom() );
    }

    public String getFacturas() {
        Connection c = null;
        ArrayList<Factura> facturas = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            c = this.initDatabase();
            preparedStatement = c.prepareStatement("SELECT * FROM facturas");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                facturas.add(new Factura(
                        rs.getString("id"),
                        rs.getString("id_usuario"),
                        rs.getString("id_cliente"),
                        this.parseArray(rs.getString("lista_productos")),
                        rs.getFloat("monto"),
                        rs.getString("tipoFactura"),
                        new Date(rs.getDate("fecha_creacion").getTime()).toString(),
                        new Time(rs.getTime("hora_creacion").getTime()).toString()
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                c.close();
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return new Gson().toJson(facturas);
    }

    public String getProductos() {
        Connection c = null;
        ArrayList<Producto> productos = new ArrayList<Producto>();
        PreparedStatement preparedStatement = null;
        try {
            c = this.initDatabase();
            preparedStatement = c.prepareStatement("SELECT p.*, pd.nombre FROM productos p, proveedores pd WHERE p.idProveedor = pd.id");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                productos.add(
                        new Producto(
                                rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getFloat(4),
                            rs.getInt(5),
                            rs.getString(6),
                                rs.getString(7)
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                c.close();
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return new Gson().toJson(productos);
    }

    public String modifyProducto(Producto producto) {
        Connection c = null;
        PreparedStatement preparedStatement = null;

        try {
            c = this.initDatabase();
            preparedStatement = c.prepareStatement("UPDATE productos " +
                    "SET nombre='"+ producto.getNombre() +"', descripcion='"+ producto.getDescripcion()+"'" +
                    ", precio='" + producto.getPrecio() + "', cantidad='" + producto.getCantidad() + "'" +
                    ", idProveedor='" + producto.getIdProveedor() + "' WHERE id='" + producto.getId()+"'");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                preparedStatement.close();
                c.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return "ACTUALIZADOS LOS DATOS DEL PRODUCTO CORRECTAMENTE.";
    }

    public String getProveedores() {
        Connection c = null;
        ArrayList<Proveedor> productos = new ArrayList<Proveedor>();
        PreparedStatement preparedStatement = null;
        try {
            c = this.initDatabase();
            preparedStatement = c.prepareStatement("SELECT * FROM proveedores");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                productos.add(
                        new Proveedor(
                                rs.getString("id"),
                                rs.getString("nombre")
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                c.close();
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return new Gson().toJson(productos);
    }
    public String addFactura(Factura factura) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = this.initDatabase();
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO facturas (" +
                            "id_usuario,id_cliente,lista_productos,monto,tipoFactura,fecha_creacion,hora_creacion)"
            + "VALUES (?,?,?,?,?,?,?)");
            preparedStatement.setInt(1, Integer.parseInt(factura.getIdUsuario()));
            preparedStatement.setInt(2, Integer.parseInt(factura.getIdCliente()));
            preparedStatement.setString(3, new Gson().toJson(factura.getListaProductos()).toString());
            preparedStatement.setDouble(4, factura.getMontoFactura());
            preparedStatement.setString(5, factura.getTipoFactura());
            preparedStatement.setDate(6, new Date(Date.valueOf(factura.getFechaCreacion()).getTime()));
            preparedStatement.setTime(7, Time.valueOf(factura.getHoraCreacion()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                assert connection != null;
                connection.close();
                assert preparedStatement != null;
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return "Se ha completado la subida de la factura.";
    }

    public String getInfoFactura(InfoFactura infoFactura) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet rs = null;
        InfoFactura infoFactura1 = null;

        try {
            connection = this.initDatabase();
            preparedStatement = connection.prepareStatement("SELECT CONCAT(c.nombre, \" \", c.apellidos) AS nombreCliente, u.nombre AS nombreUsuario " +
                    "FROM facturas f " +
                    "JOIN clientes c ON f.id_cliente = c.id " +
                    "JOIN usuarios u ON f.id_usuario = u.id " +
                    "WHERE f.id_usuario = ? AND f.id_cliente = ?;");
            preparedStatement.setInt(1, infoFactura.getIdUsuario());
            preparedStatement.setInt(2, infoFactura.getIdCliente());
            rs = preparedStatement.executeQuery();
            if (rs.next())
                infoFactura1 = new InfoFactura(
                        infoFactura.getIdCliente(),
                        rs.getString("nombreCliente"),
                        infoFactura.getIdUsuario(),
                        rs.getString("nombreUsuario")
                );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                assert preparedStatement != null;
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return new Gson().toJson(infoFactura1);
    }

    public String deleteFactura(Factura factura) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = this.initDatabase();
            preparedStatement = connection.prepareStatement("DELETE FROM facturas WHERE id=?");
            preparedStatement.setInt(1, Integer.parseInt(factura.getIdFactura()));
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                assert preparedStatement != null;
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return "SE HA ELIMINADO LA FACTURA CORRECTAMENTE.";
    }
    public String modifyFactura(Factura factura) {
        Connection c = null;
        PreparedStatement preparedStatement = null;

        try {
            c = this.initDatabase();
            preparedStatement = c.prepareStatement("UPDATE facturas " +
                    "SET id_usuario=?, id_cliente=?, lista_productos=?, monto=?, tipoFactura=?, fecha_creacion=?, hora_creacion=? WHERE id=?");
            preparedStatement.setString(1, factura.getIdUsuario());
            preparedStatement.setString(2, factura.getIdCliente());
            preparedStatement.setString(3, new Gson().toJson(factura.getListaProductos()));
            preparedStatement.setDouble(4, factura.getMontoFactura());
            preparedStatement.setString(5, factura.getTipoFactura());
            preparedStatement.setDate(6, new Date(Date.valueOf(factura.getFechaCreacion()).getTime()));
            preparedStatement.setTime(7, Time.valueOf(factura.getHoraCreacion()));
            preparedStatement.setInt(8, Integer.parseInt(factura.getIdFactura()));
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                preparedStatement.close();
                c.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return "ACTUALIZADOS LOS DATOS DE LA FACTURA CORRECTAMENTE.";
    }

    public String getUsers() {
        Connection c = null;
        ArrayList<Usuario> usu = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            c = this.initDatabase();
            preparedStatement = c.prepareStatement("SELECT * FROM usuarios");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                usu.add(new Usuario(rs.getInt("id"),
                        rs.getString("mail"),
                        null,
                        rs.getString("nombre"),
                        rs.getString("admin"),
                        "Y")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                c.close();
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return new Gson().toJson(usu);
    }

    public String deleteUser(Usuario usuario) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = this.initDatabase();
            preparedStatement = connection.prepareStatement("DELETE FROM usuarios WHERE id=?");
            preparedStatement.setInt(1, usuario.getIdUsario());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                assert preparedStatement != null;
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return "SE HA ELIMINADO EL USUARIO CORRECTAMENTE.";
    }
    public String modifyUser(Usuario usuario) {
        Connection c = null;
        PreparedStatement preparedStatement = null;

        if (simplyCheckMailUsuarios(usuario.getMail()))
            return "YA EXISTE UN USUARIO CON ESE MAIL";

        try {
            c = this.initDatabase();
            preparedStatement = c.prepareStatement("UPDATE usuarios " +
                    "SET mail=?, nombre=?, admin=? WHERE id=?");
            preparedStatement.setString(1, usuario.getMail());
            preparedStatement.setString(2, usuario.getAdmin());
            preparedStatement.setDouble(3, usuario.getIdUsario());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                preparedStatement.close();
                c.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return "ACTUALIZADOS LOS DATOS DEL USUARIO CORRECTAMENTE.";
    }

    public String addUser(Usuario usuario) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        if (simplyCheckMailUsuarios(usuario.getMail()))
            return "YA EXISTE UN USUARIO CON ESE MAIL";

        try {
            connection = this.initDatabase();
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO usuarios (" +
                            "mail,nombre,admin,password,first_login)"
                            + "VALUES (?,?,?,?,?)");
            preparedStatement.setString(1, usuario.getMail());
            preparedStatement.setString(2, usuario.getNombre());
            preparedStatement.setString(3, usuario.getAdmin());
            preparedStatement.setString(4, generateSecurePassword());
            preparedStatement.setDate(5, Date.valueOf(LocalDate.now()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                assert connection != null;
                connection.close();
                assert preparedStatement != null;
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return usuario.getMail();
    }
    public String getClientes() {
        Connection c = null;
        ArrayList<Cliente> clientes = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            c = this.initDatabase();
            preparedStatement = c.prepareStatement("SELECT * FROM clientes");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                clientes.add(new Cliente(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("email"),
                        rs.getString("dni"),
                        rs.getString("direccion"),
                        rs.getString("telefono")
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                c.close();
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return new Gson().toJson(clientes);
    }

    public String deleteCliente(Cliente cliente) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = this.initDatabase();
            preparedStatement = connection.prepareStatement("DELETE FROM clientes WHERE id=?");
            preparedStatement.setInt(1, cliente.idCliente());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                assert preparedStatement != null;
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return "SE HA ELIMINADO EL CLIENTE CORRECTAMENTE.";
    }
    public String modifyCliente(Cliente cliente) {
        Connection c = null;
        PreparedStatement preparedStatement = null;

        if (simplyCheckMailClientes(cliente.emailCliente()))
            return "YA EXISTE UN CLIENTE CON ESE MAIL";

        try {
            c = this.initDatabase();
            preparedStatement = c.prepareStatement("UPDATE clientes " +
                    "SET nombre=?, apellidos=?, email=?, dni=?, direccion=?, telefono=? WHERE id=?");
            preparedStatement.setString(1, cliente.nombreCliente());
            preparedStatement.setString(2, cliente.apellidosCliente());
            preparedStatement.setString(3, cliente.emailCliente());
            preparedStatement.setString(4, cliente.dniCliente());
            preparedStatement.setString(5, cliente.direccionCliente());
            preparedStatement.setString(6, cliente.telefonoCliente());
            preparedStatement.setInt(7, cliente.idCliente());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                preparedStatement.close();
                c.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return "ACTUALIZADOS LOS DATOS DEL CLIENTE CORRECTAMENTE.";
    }



    public String addCliente(Cliente cliente) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        if (simplyCheckMailClientes(cliente.emailCliente()))
            return "YA EXISTE UN USUARIO QUE CON ESE MAIL";

        try {
            connection = this.initDatabase();
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO clientes (" +
                            "nombre,apellidos,email,dni,direccion,telefono)"
                            + "VALUES (?,?,?,?,?,?)");
            preparedStatement.setString(1, cliente.nombreCliente());
            preparedStatement.setString(2, cliente.apellidosCliente());
            preparedStatement.setString(3, cliente.emailCliente());
            preparedStatement.setString(4, cliente.dniCliente());
            preparedStatement.setString(5, cliente.direccionCliente());
            preparedStatement.setString(6, cliente.telefonoCliente());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                assert connection != null;
                connection.close();
                assert preparedStatement != null;
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return "Se ha completado la subida del cliente.";
    }

    public String getPassForMail(String mail) {
        Connection c = null;
        String res = null;
        PreparedStatement preparedStatement = null;
        try {
            c = this.initDatabase();
            preparedStatement = c.prepareStatement("SELECT * FROM usuarios WHERE mail='"+ mail +"'");
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
                res = rs.getString("password");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                c.close();
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return res;
    }

    private boolean simplyCheckMailClientes(String mail) {
        Connection c = null;
        int res = -1;
        PreparedStatement preparedStatement = null;
        try {
            c = this.initDatabase();
            preparedStatement = c.prepareStatement("SELECT COUNT(*) contada FROM clientes WHERE email='"+ mail +"'");
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
                res = rs.getInt("contada");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                c.close();
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return res > 0;
    }

    private boolean simplyCheckMailUsuarios(String mail) {
        Connection c = null;
        int res = -1;
        PreparedStatement preparedStatement = null;
        try {
            c = this.initDatabase();
            preparedStatement = c.prepareStatement("SELECT COUNT(*) contada FROM usuarios WHERE mail='"+ mail +"'");
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
                res = rs.getInt("contada");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                c.close();
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return res > 0;
    }

    private void updateLoginDate(Integer idUsario) {
        Connection c = null;
        PreparedStatement preparedStatement = null;

        try {
            c = this.initDatabase();
            preparedStatement = c.prepareStatement("UPDATE usuarios " +
                    "SET last_login=? WHERE id=?");
            preparedStatement.setDate(1, Date.valueOf(LocalDate.now()));
            preparedStatement.setInt(2, idUsario);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                preparedStatement.close();
                c.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}