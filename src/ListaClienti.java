import java.util.ArrayList;
/**
 * Classe che rappresenta la risorsa condivisa fra i due thread
 * da gestire con metodi "synchronized"
 * e con l'uso di wait() e notify()
 * @author frida
 * @version 1.0
 */
public class ListaClienti {
    private ArrayList<Integer> listaNumeri;
    private int ultimoArrivo;
    private int ultimoServito;
    private final int numeroMassimo = 5;
    /**
     * constructor
     * settaggio delle variabili di istanza
     */
    public ListaClienti() {
        listaNumeri = new ArrayList<Integer>();
        ultimoArrivo = 0;
        ultimoServito = 0;
    }

    /*synchronized parola chiave che gestisce il meccanismo del lock ovvero
    * 1) impedisce ad un altro thread l'esecuzione di tale
    * metodo, se un precedente thread lo sta già eseguendo
    * 2) senza di lui wait e notify non possono essere usati
    * si genera l'eccezione : IllegalMonitorStateException,*/

    /**
     * metodo eseguito da un thread della Classe Sportello
     * si chiede se è in coda un'altro cliente dopo l'ultimo che ha servito
     * se c'è lo serve (incrementa di 1 ultimoServito)
     * else resta in attesa che arrivi...(in attesa di un notify)
     * @return Integer ultimoServito
     *
     */
    public synchronized Integer rimuoviCliente() throws
            InterruptedException {
        while (ultimoServito >= ultimoArrivo) {
            System.out.println("non ci sono arrivi dopo l'ultimo servito");
            wait();
        }
        ultimoServito++;
        return ultimoServito;
    }

    /**
     * metodo eseguito da un thread della Classe GestioneArrivi
     * che produce un nuovo int aggiungendo 1 all'UltimoArrivo
     * e inserisce tale nuovo numero / ticket nella lista numeri
     * @return Integer: ultimoArrivo o null se gli arrivi saturano
     */
    public synchronized Integer addCliente() {
        if (ultimoArrivo < numeroMassimo) {
            ultimoArrivo++;
            listaNumeri.add(ultimoArrivo);
            notify();
            return ultimoArrivo;
        }
        return null;
    }
}
