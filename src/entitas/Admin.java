/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitas;

/**
 *
 * @author Fadli Hudaya
 */
public class Admin {

    private String id;
    private String nama_lengkap;
    private String username;
    private String password;
    private String level;

    public Admin(String id, String nama_lengkap, String username, String password, String level) {
        this.id = id;
        this.nama_lengkap = nama_lengkap;
        this.username = username;
        this.password = password;
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public String getNamaLengkap() {
        return nama_lengkap;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getLevel() {
        return level;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNamaLengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Object getObject(int index) {
        switch (index) {
            case 0:
                return id;
            case 1:
                return nama_lengkap;
            case 2:
                return username;
            case 3:
                return password;
            case 4:
                return level;
            default:
                return null;
        }
    }

    public Object getObjects(int index) {
        switch (index) {
            case 0:
                return id;
            case 1:
                return nama_lengkap;
            case 2:
                return username;
            case 3:
                return level;
            default:
                return null;
        }
    }
}
