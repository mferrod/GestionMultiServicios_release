package dev.marianof.gms2_app.Model;

public class User {
    private final Integer idUser;
    private final String username;
    private final boolean admin;
    private final boolean logged;

    public User(Integer idUser, String username, boolean admin, boolean logged) {
        this.idUser = idUser;
        this.username = username;
        this.admin = admin;
        this.logged = logged;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAdmin() {
        return admin;
    }

    public boolean isLogged() {
        return logged;
    }

    public Integer getIdUser() {
        return idUser;
    }
}
