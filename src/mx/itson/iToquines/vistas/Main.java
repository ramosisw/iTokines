package mx.itson.iToquines.vistas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import mx.itson.iToquines.R;
import mx.itson.iToquines.entidades.Concierto;
import mx.itson.iToquines.persistencia.ConciertoPersistencia;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ADMIN
 * Date: 24/11/13
 * Time: 05:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main extends Activity {
    private LinearLayout lny_results;
    private ConciertoPersistencia cp;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //Inicializar componentes
        init();
        //Search Field
        ((EditText) findViewById(R.id.search)).setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //Lineas para ocultar el teclado virtual (Hide keyboard)
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    String key = v.getText().toString();
                    List<Concierto> conciertos = cp.search(key);
                    if (conciertos != null) {
                        showData(conciertos);
                    } else {
                        Toast.makeText(Main.this, "No encontramos nada relacionado", Toast.LENGTH_LONG).show();
                    }

                    return true;
                }
                return false;
            }
        });


    }

    private void init() {
        this.lny_results = (LinearLayout) findViewById(R.id.lny_results);
        cp = new ConciertoPersistencia(this, "ConciertoPersistencia", null);
        List<Concierto> conciertos = cp.getConciertos();
        showData(conciertos);


    }

    private void showData(List<Concierto> conciertos) {
        this.lny_results.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(Main.this);
        for (Concierto concierto : conciertos) {
            LinearLayout item = (LinearLayout) inflater.inflate(R.layout.item_search, null, false);
            ImageView mImage = (ImageView) item.findViewById(R.id.img_concierto);
            LinearLayout lny_descripcion = (LinearLayout) item.findViewById(R.id.lny_descripcion);
            ((TextView) lny_descripcion.findViewById(R.id.lbl_artista)).setText(concierto.getArtista().getNombre() + "");
            ((TextView) lny_descripcion.findViewById(R.id.lbl_fecha)).setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(concierto.getFechaHora()));
            ((TextView) lny_descripcion.findViewById(R.id.lbl_lugar)).setText(concierto.getLugar());
            ((TextView) lny_descripcion.findViewById(R.id.lbl_ciudad)).setText(concierto.getCiudad());

            try {
                // get input stream
                InputStream ims = getAssets().open("img/" + concierto.getArtista().getImg_perfil());
                // load image as Drawable
                Drawable d = Drawable.createFromStream(ims, null);
                // set image to ImageView
                mImage.setImageDrawable(d);
            } catch (IOException ex) {
                Drawable d = this.getResources().getDrawable(R.drawable.not_available);
                mImage.setImageDrawable(d);
            }
            ((TextView) item.findViewById(R.id.id_artista)).setText(concierto.getArtista().getId() + "");
            ((TextView) item.findViewById(R.id.lbl_artista)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View parent = (View) v.getParent();
                    //Toast.makeText(Main.this, ((TextView) parent.findViewById(R.id.id_artista)).getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
            ((TextView) item.findViewById(R.id.id_concierto)).setText(concierto.getId() + "");
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id_concierto = Integer.parseInt(((TextView) v.findViewById(R.id.id_concierto)).getText().toString());
                    Intent intent = new Intent(Main.this, mx.itson.iToquines.vistas.Concierto.class);
                    intent.putExtra("id_concierto", id_concierto);
                    startActivity(intent);
                    Toast.makeText(Main.this, ((TextView) v.findViewById(R.id.id_concierto)).getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });

            this.lny_results.addView(item);
        }
    }
}