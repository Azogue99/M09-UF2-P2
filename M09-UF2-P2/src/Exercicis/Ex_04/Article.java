package Exercicis.Ex_04;

public class Article {
    private final String title;
    private final StringBuilder content;

    // Control concurrència (lectors-escriptors)
    private int readersCount = 0;    // Quants lectors hi ha llegint ara mateix
    private boolean isWriting = false; 
    private int writersWaiting = 0;  // Quants escriptors estan esperant

    public Article(String title, String initialContent) {
        this.title = title;
        this.content = new StringBuilder(initialContent);
    }

    // --- MÈTODES PER LECTORS ---
    public synchronized void startReading(String readerName) throws InterruptedException {
        // Esperem si hi ha algú escrivint o si hi ha escriptors esperant
        // (donem prioritat als escriptors per evitar starvation)
        while (isWriting || writersWaiting > 0) {
            wait();
        }
        readersCount++;
        System.out.println(readerName + " COMENÇA a llegir article: " + title 
                           + " (lectors actuals: " + readersCount + ")");
    }

    public synchronized void endReading(String readerName) {
        readersCount--;
        System.out.println(readerName + " ACABA de llegir article: " + title 
                           + " (lectors restants: " + readersCount + ")");
        // Si no hi ha més lectors, despertem els que puguin estar esperant (p. ex. un escriptor)
        if (readersCount == 0) {
            notifyAll();
        }
    }

    /**
     * Mètode per llegir el contingut (és bo que també sigui synchronized si
     * hi ha més lògica, però aquí suposem que ja hem fet startReading abans).
     */
    public synchronized String readContent() {
        return content.toString();
    }

    // --- MÈTODES PER ESCRIPTORS ---
    public synchronized void startWriting(String writerName) throws InterruptedException {
        writersWaiting++;
        // Esperem mentre hi hagi algú escrivint o algú llegint
        while (isWriting || readersCount > 0) {
            wait();
        }
        writersWaiting--;
        isWriting = true;
        System.out.println(writerName + " COMENÇA a escriure article: " + title);
    }

    public synchronized void endWriting(String writerName) {
        isWriting = false;
        System.out.println(writerName + " ACABA d'escriure article: " + title);
        // Notifiquem a tothom: potser hi ha lectors i/o escriptors esperant
        notifyAll();
    }

    /**
     * Mètode per afegir text al contingut (cridat durant l'escriptura).
     */
    public synchronized void writeContent(String newText) {
        content.append(" ").append(newText);
    }

    // Nom de l'article (informatiu)
    public String getTitle() {
        return title;
    }
}
