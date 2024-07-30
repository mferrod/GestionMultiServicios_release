package dev.marianof.gestionmultiservicios.Model;

public class User {
    private final Integer idUser;
    private final String mail;
    private final String pass;
    private final boolean admin;
    private final boolean login;
    private final String nombre;

    public User(Integer idUser, String mail, String pass, String nombre, boolean admin, boolean login) {
        this.idUser = idUser;
        this.mail = mail;
        this.pass = pass;
        this.admin = admin;
        this.login = login;
        this.nombre = nombre;
    }

    public String getUsername() {
        return mail;
    }

    public boolean isAdmin() {
        return admin;
    }

    public boolean isLogged() {
        return login;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public String getNombre() {
        return nombre;
    }
}
