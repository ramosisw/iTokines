package mx.itson.iToquines.entidades;

import java.util.Date;

/**
 * Created with Itson.
 * User: ADMIN
 * Date: 25/11/13
 * Time: 01:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class Concierto {
    private int id;
    private Date fechaHora;
    private String lugar;
    private String ciudad;
    private Artista artista;

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
