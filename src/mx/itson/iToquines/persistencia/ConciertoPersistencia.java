package mx.itson.iToquines.persistencia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import mx.itson.iToquines.entidades.Artista;
import mx.itson.iToquines.entidades.Concierto;
import mx.itson.iToquines.enumeradores.Genero;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with Itson.
 * User: ADMIN
 * Date: 25/11/13
 * Time: 01:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class ConciertoPersistencia extends SQLiteOpenHelper {

    private static int version = 14;

    public ConciertoPersistencia(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE tbl_artista (" +
                "  id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "  nombre TEXT," +
                "  tipoGenero INTEGER," +
                "  origen TEXT," +
                "  ultimoDisco TEXT," +
                "  img_artista TEXT," +
                "  img_ultimoDisco TEXT" +
                ");");
        sqLiteDatabase.execSQL("CREATE TABLE tbl_concierto (" +
                "  id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "  lugar TEXT," +
                "  ciudad TEXT," +
                "  fechaHora DATETIME," +
                "  id_artista INTEGER" +
                ");");

        sqLiteDatabase.execSQL("INSERT INTO tbl_artista VALUES (null, 'Kreator', 0, 'Essen, Alemania', 'Phantom Antichrist', 'kreator.jpg', 'phantom_kreator.jpg')," +
                "(null, 'Steve Vai', 0, 'Estadounidense', 'Passion and Warfare', 'steve.jpg', 'passion_steve.jpg')," +
                "(null, 'Enrique Bunbury', 0, 'Español', 'Flamingos', 'enrique.jpg', 'flamingos_enrique.jpg')," +
                "(null, 'Café Tacvba', 0, 'México, DF', 'Re', 'cafe.jpg', 're_cafe.jpg')," +
                "(null, 'Alejandro Fernández', 0, 'Mexicano', 'Dos Mundos', 'alejandro.jpg', 'dos_alejandro.jpg');");


        sqLiteDatabase.execSQL("INSERT INTO tbl_concierto  VALUES" +
                "(null, 'Auditorio Nacional', 'México, DF', '2014-05-17 20:00:00', 5)," +
                "(null, 'Teatro Estudio Cavaret', 'Zapopan, JAL', '2013-11-28 20:30:00', 1)," +
                "(null, 'Circo Volador', 'México, DF', '2013-11-29 20:30:00', 1)," +
                "(null, 'Teatro Metropólitan', 'México, DF', '2013-11-26 21:00:00', 2)," +
                "(null, 'Auditorio Nacional', 'México, DF', '2014-01-20 20:30:00', 3)," +
                "(null, 'Auditorio Banamex', 'Monterrey, LN', '2014-01-23 21:00:00', 3)," +
                "(null, 'Auditorio Nacional', 'México, DF', '2014-01-29 20:30:00', 3)," +
                "(null, 'Auditorio Nacional', 'México, DF', '2014-02-01 20:00:00', 3)," +
                "(null, 'Palacio de los Deportes', 'México, DF', '2013-12-07 20:00:00', 4)," +
                "(null, 'Auditorio Nacional', 'México, DF', '2014-05-14 20:30:00', 5)," +
                "(null, 'Auditorio Nacional', 'México, DF', '2014-05-16 20:30:00', 5);");
        /*sqLiteDatabase.execSQL("ALTER TABLE 'tbl_concierto'" +
                "  ADD CONSTRAINT tbl_concierto_ibfk_1 FOREIGN KEY (id_artista) REFERENCES tbl_artista (id);");    */

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tbl_artista;");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tbl_concierto;");
        onCreate(sqLiteDatabase);
    }

    public List<Concierto> getConciertos() {
        Date now = new Date();
        now.setDate(1);
        now.setHours(0);
        now.setMinutes(0);
        now.setSeconds(0);
        Date m_now = new Date();
        m_now.setDate(1);
        m_now.setHours(0);
        m_now.setMinutes(0);
        m_now.setSeconds(0);
        m_now.setMonth(now.getMonth() + 1);
        SQLiteDatabase baseDatos = this.getReadableDatabase();

        String d1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now);
        String d2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(m_now);
        Cursor c = baseDatos.rawQuery("SELECT * FROM tbl_concierto c INNER JOIN tbl_artista a ON c.id_artista=a.id WHERE c.fechaHora BETWEEN ? AND ?", new String[]{d1, d2});
        //Cursor c = baseDatos.rawQuery(statement.simpleQueryForString(), null);
        if (c.moveToFirst()) {
            List<Concierto> conciertos = new ArrayList<Concierto>();
            do {
                Concierto concierto = new Concierto();
                concierto.setId(c.getInt(0));
                concierto.setLugar(c.getString(1));
                concierto.setCiudad(c.getString(2));
                /* String date = c.getString(3); */
                Date date = null;
                try {
                    date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(c.getString(3));
                } catch (ParseException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                concierto.setFechaHora(date);
                Artista artista = new Artista();
                artista.setId(c.getInt(4));
                artista.setNombre(c.getString(6));
                artista.setGenero(Genero.values()[c.getInt(7)]);
                artista.setOrigen(c.getString(8));
                artista.setUltimoDisco(c.getString(9));
                artista.setImg_perfil(c.getString(10));
                artista.setImg_ultimoDisco(c.getString(11));
                concierto.setArtista(artista);
                conciertos.add(concierto);
            } while (c.moveToNext());
            return conciertos;
        }
        return null;

    }

    public List<Concierto> search(String key) {
        SQLiteDatabase baseDatos = this.getReadableDatabase();
        Cursor c = baseDatos.rawQuery("SELECT * FROM tbl_concierto c INNER JOIN tbl_artista a ON c.id_artista=a.id " +
                "WHERE a.nombre LIKE ? OR c.ciudad LIKE ?;", new String[]{"%" + key.toLowerCase() + "%"});
        if (c.moveToFirst()) {
            List<Concierto> conciertos = new ArrayList<Concierto>();
            do {
                Concierto concierto = new Concierto();
                concierto.setId(c.getInt(0));
                concierto.setLugar(c.getString(1));
                concierto.setCiudad(c.getString(2));
                /* String date = c.getString(3); */
                Date date = null;
                try {
                    date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(c.getString(3));
                } catch (ParseException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                concierto.setFechaHora(date);
                Artista artista = new Artista();
                artista.setId(c.getInt(4));
                artista.setNombre(c.getString(6));
                artista.setGenero(Genero.values()[c.getInt(7)]);
                artista.setOrigen(c.getString(8));
                artista.setUltimoDisco(c.getString(9));
                artista.setImg_perfil(c.getString(10));
                artista.setImg_ultimoDisco(c.getString(11));
                concierto.setArtista(artista);
                conciertos.add(concierto);
            } while (c.moveToNext());
            return conciertos;
        }
        return null;
    }

    public Concierto getById(int id) {
        SQLiteDatabase baseDatos = this.getReadableDatabase();
        Cursor c = baseDatos.rawQuery("SELECT * FROM tbl_concierto c INNER JOIN tbl_artista a ON c.id_artista=a.id " +
                "WHERE c.id=?", new String[]{id + ""});
        if (c.moveToFirst()) {

            Concierto concierto = new Concierto();
            concierto.setId(c.getInt(0));
            concierto.setLugar(c.getString(1));
            concierto.setCiudad(c.getString(2));
                /* String date = c.getString(3); */
            Date date = null;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(c.getString(3));
            } catch (ParseException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            concierto.setFechaHora(date);
            Artista artista = new Artista();
            artista.setId(c.getInt(4));
            artista.setNombre(c.getString(6));
            artista.setGenero(Genero.values()[c.getInt(7)]);
            artista.setOrigen(c.getString(8));
            artista.setUltimoDisco(c.getString(9));
            artista.setImg_perfil(c.getString(10));
            artista.setImg_ultimoDisco(c.getString(11));
            concierto.setArtista(artista);


            return concierto;
        }
        return null;
    }


}