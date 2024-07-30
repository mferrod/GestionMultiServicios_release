package com.marianof.gms2api.Model;

public class Usuario {
    private Integer idUser;
    private String mail;
    private String pass;
    private String nombre;
    private String admin;
    private String login;

    public Usuario(Integer idUser, String mail, String pass, String nombre, String admin, String login) {
        this.idUser = idUser;
        this.mail = mail;
        this.pass = pass;
        this.nombre = nombre;
        this.admin = admin;
        this.login = login;
    }

    public String getMail() {
        return mail;
    }

    public String getPass() {
        return pass;
    }

    public String getAdmin() {
        return admin;
    }

    public String getLogin() {
        return login;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getIdUsario() {
        return idUser;
    }

    public void setIdUsario(Integer idUsario) {
        this.idUser = idUsario;
    }
}
