package Exercicis.Ex_04;

public class Article {
    private final String title; // Títol de l'article.
    private final StringBuilder content; // Contingut de l'article (mutable).

    // Control de concurrència per a lectors i escriptors.
    private int readersCount = 0;    // Nombre de lectors actualment llegint.
    private boolean isWriting = false; // Indica si hi ha un escriptor escrivint.
    private int writersWaiting = 0;  // Nombre d'escriptors en espera.

    /**
     * Constructor de la classe Article.
     *
     * @param title Títol de l'article.
     * @param initialContent Contingut inicial de l'article.
     */
    public Article(String title, String initialContent) {
        this.title = title;
        this.content = new StringBuilder(initialContent);
    }

    // --- MÈTODES PER ALS LECTORS ---

    /**
     * Permet a un lector començar a llegir l'article.
     *
     * @param readerName Nom del lector.
     * @throws InterruptedException Si el fil és interromput mentre espera.
     */
    public synchronized void startReading(String readerName) throws InterruptedException {
        // Esperem si hi ha un escriptor treballant o escriptors en espera (prioritat als escriptors).
        while (isWriting || writersWaiting > 0) {
            wait();
        }
        readersCount++; // Incrementem el comptador de lectors.
        System.out.println(readerName + " COMENÇA a llegir l'article: " + title
                + " (lectors actuals: " + readersCount + ")");
    }

    /**
     * Permet a un lector finalitzar la seva lectura.
     *
     * @param readerName Nom del lector.
     */
    public synchronized void endReading(String readerName) {
        readersCount--; // Reduïm el nombre de lectors actius.
        System.out.println(readerName + " ACABA de llegir l'article: " + title
                + " (lectors restants: " + readersCount + ")");
        // Si ja no hi ha més lectors, notifiquem als escriptors en espera.
        if (readersCount == 0) {
            notifyAll();
        }
    }

    /**
     * Retorna el contingut actual de l'article.
     *
     * @return Contingut de l'article en format String.
     */
    public synchronized String readContent() {
        return content.toString();
    }

    // --- MÈTODES PER ALS ESCRIPTORS ---

    /**
     * Permet a un escriptor començar a escriure en l'article.
     *
     * @param writerName Nom de l'escriptor.
     * @throws InterruptedException Si el fil és interromput mentre espera.
     */
    public synchronized void startWriting(String writerName) throws InterruptedException {
        writersWaiting++; // Incrementem el comptador d'escriptors en espera.
        // Esperem mentre hi hagi un altre escriptor escrivint o lectors llegint.
        while (isWriting || readersCount > 0) {
            wait();
        }
        writersWaiting--; // Un cop comença a escriure, ja no està en espera.
        isWriting = true; // Marquem que l'escriptor està escrivint.
        System.out.println(writerName + " COMENÇA a escriure l'article: " + title);
    }

    /**
     * Permet a un escriptor finalitzar la seva escriptura.
     *
     * @param writerName Nom de l'escriptor.
     */
    public synchronized void endWriting(String writerName) {
        isWriting = false; // Marquem que l'article ja no està en ús per un escriptor.
        System.out.println(writerName + " ACABA d'escriure l'article: " + title);
        // Notifiquem a tothom (lectors i escriptors) que poden continuar.
        notifyAll();
    }

    /**
     * Afegeix nou contingut a l'article.
     *
     * @param newText Text a afegir a l'article.
     */
    public synchronized void writeContent(String newText) {
        content.append(" ").append(newText); // Afegim el text al contingut existent.
    }

    /**
     * Retorna el títol de l'article.
     *
     * @return Títol de l'article.
     */
    public String getTitle() {
        return title;
    }
}
