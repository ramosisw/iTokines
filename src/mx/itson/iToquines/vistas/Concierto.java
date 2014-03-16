package mx.itson.iToquines.vistas;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import mx.itson.iToquines.R;
import mx.itson.iToquines.persistencia.ConciertoPersistencia;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

/**
 * Created with IntelliJ IDEA.
 * User: ADMIN
 * Date: 25/11/13
 * Time: 05:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class Concierto extends Activity {
    private TextView lbl_artista;
    private TextView lbl_fechaHora;
    private TextView lbl_genero;
    private TextView lbl_origen;
    private TextView lbl_ultimoDisco;
    private TextView lbl_lugar;
    private TextView lbl_ciudad;
    private ImageView img_artista;
    private ImageView img_ultimoDisco;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.concierto);
        init();
        Bundle bundle = getIntent().getExtras();
        int id_concierto = bundle.getInt("id_concierto");
        ConciertoPersistencia cp = new ConciertoPersistencia(this, "ConciertoPersistencia", null);
        mx.itson.iToquines.entidades.Concierto concierto = cp.getById(id_concierto);
        if (concierto != null) {
            //LayoutInflater inflater = LayoutInflater.from(Concierto.this);
            //LinearLayout item = (LinearLayout) inflater.inflate(R., null, false);
            try {
                // get input stream
                InputStream ims = getAssets().open("img/" + concierto.getArtista().getImg_perfil());
                InputStream ims2 = getAssets().open("img/" + concierto.getArtista().getImg_ultimoDisco());
                // load image as Drawable
                Drawable d = Drawable.createFromStream(ims, null);
                Drawable d2 = Drawable.createFromStream(ims2, null);
                // set image to ImageView
                this.img_artista.setImageDrawable(d);
                this.img_ultimoDisco.setImageDrawable(d2);
            } catch (IOException ex) {
                Drawable d = this.getResources().getDrawable(R.drawable.not_available);
                this.img_artista.setImageDrawable(d);
                this.img_ultimoDisco.setImageDrawable(d);
            }
            this.lbl_artista.setText(concierto.getArtista().getNombre());
            this.lbl_lugar.setText(concierto.getLugar());
            this.lbl_genero.setText(concierto.getArtista().getGenero() + "");
            this.lbl_ultimoDisco.setText(concierto.getArtista().getUltimoDisco());
            this.lbl_ciudad.setText(concierto.getCiudad());
            this.lbl_fechaHora.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(concierto.getFechaHora()));
            this.lbl_origen.setText(concierto.getArtista().getOrigen().toString() + "");
        } else {
            Toast.makeText(this, "Ocurrio un Problema", Toast.LENGTH_LONG).show();
            super.onBackPressed();
        }
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(Concierto.this);
        this.lbl_artista = (TextView) findViewById(R.id.lbl_artista);
        this.lbl_fechaHora = (TextView) findViewById(R.id.lbl_fechaHora);
        this.lbl_genero = (TextView) findViewById(R.id.lbl_tipo);
        this.lbl_ultimoDisco = (TextView) findViewById(R.id.lbl_ultimoDisco);
        this.lbl_lugar = (TextView) findViewById(R.id.lbl_lugar);
        this.lbl_ciudad = (TextView) findViewById(R.id.lbl_ciudad);
        this.img_artista = (ImageView) this.findViewById(R.id.img_artista);
        this.img_ultimoDisco = (ImageView) findViewById(R.id.img_ultimoDisco);
        this.lbl_origen = (TextView) findViewById(R.id.lbl_origen);
    }
}