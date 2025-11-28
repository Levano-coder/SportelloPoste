/**
 * Classe che implementa il thread Sportello,
 * che serve i clienti delle
 * poste che arrivano via via, ovvero è il thread
 * consumatore delle risorse condivise
 * listaNumeri, ultimoArrivo, ultimoServito
 * @author frida
 * @version 1.0
 */
public class Sportello implements Runnable {
    /**
     * risorse condivise fra i due thread
     */
    private ListaClienti listaClienti;
    private final int minTempoServizio = 2000;
    private final int maxTempoServizio = 4000;
    private String nome;
    /**
     * constructor
     * @param listaClienti
     */
    public Sportello(ListaClienti listaClienti, String nome) {
        this.listaClienti = listaClienti;
        this.nome = nome;
    }

    /**
     * Questo metodo si occupa di determinare il tempo che uno sportello offre per il servizio
     * di un singolo cliente ed il suo tempo di riposo (sleep) tra un cliente e l'altro.
     * Può essere interrotto e aiuta
     * @see Runnable
     */
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Integer clienteServito = listaClienti.rimuoviCliente();
                //tempo di servizio variabile nel range [1,4] secondi
                int tempoServizio = (int) (Math.random() * (maxTempoServizio
                        - minTempoServizio + 1) + minTempoServizio);
                Thread.sleep(tempoServizio);
                //Thread.sleep(1000); //tempo di servizio fisso
                System.out.println("Servito Cliente Numero\t " + clienteServito+
                        " dallo sportello di " + nome);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread interrotto durante lo sleep");
        } finally {
            System.out.println("Sportello Chiuso");
        }
    }
}