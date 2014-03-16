package mx.itson.iToquines.entidades;

import mx.itson.iToquines.enumeradores.Genero;

/**
 * Created with Itson.
 * User: ADMIN
 * Date: 25/11/13
 * Time: 01:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class Artista {
    private int id;
    private String nombre;
    private Genero genero;
    private String origen;
    private String ultimoDisco;
    private String img_perfil;
    private String img_ultimoDisco;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getUltimoDisco() {
        return ultimoDisco;
    }

    public void setUltimoDisco(String ultimoDisco) {
        this.ultimoDisco = ultimoDisco;
    }

    public String getImg_perfil() {
        return img_perfil;
    }

    public void setImg_perfil(String img_perfil) {
        this.img_perfil = img_perfil;
    }

    public String getImg_ultimoDisco() {
        return img_ultimoDisco;
    }

    public void setImg_ultimoDisco(String img_ultimoDisco) {
        this.img_ultimoDisco = img_ultimoDisco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
