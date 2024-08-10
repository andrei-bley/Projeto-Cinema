import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Lista implements Iterable<Filmes> {
    private ArrayList<Filmes> lista;
    public String getDataFormatada;
    public Lista(String name) {
        lista = new ArrayList<Filmes>();
    }

    public void append(Filmes filmes) {
        lista.add(filmes);
    }

    public void delete(int position) {
        lista.remove(position);
    }

    public void sortAZ() {
        Collections.sort(lista);
    }

    public void sortZA() {
        Collections.sort(lista, Collections.reverseOrder());
    }

    @Override
    public Iterator<Filmes> iterator() {
        return lista.iterator();
    }

}
