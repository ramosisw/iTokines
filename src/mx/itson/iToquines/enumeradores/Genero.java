package mx.itson.iToquines.enumeradores;

/**
 * Created Itson
 * User: ADMIN
 * Date: 25/11/13
 * Time: 01:16 AM
 * To change this template use File | Settings | File Templates.
 */
public enum Genero {
    ROCK(0), CABARET(1), ELECTRONICA(2),
    INFANTIL(3), OLDIE(4), POP(5), BANDA(6),
    RAP(7), URBANOS(8), REGGAE(9);

    private int valor;

    private Genero(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
