package Exercicis;

public class Utilities {
    // Codis ANSI per a colors de text en la consola.
    public static final String RESET   = "\u001B[0m";  // Restaura el color per defecte.
    public static final String BLACK   = "\u001B[30m"; // Negre.
    public static final String RED     = "\u001B[31m"; // Vermell.
    public static final String GREEN   = "\u001B[32m"; // Verd.
    public static final String YELLOW  = "\u001B[33m"; // Groc.
    public static final String BLUE    = "\u001B[34m"; // Blau.
    public static final String PURPLE  = "\u001B[35m"; // Lila.
    public static final String CYAN    = "\u001B[36m"; // Cian.
    public static final String WHITE   = "\u001B[97m"; // Blanc.

    // Codis ANSI per a colors de fons (opcional).
    public static final String BG_RED    = "\u001B[41m"; // Fons vermell.
    public static final String BG_GREEN  = "\u001B[42m"; // Fons verd.
    public static final String BG_YELLOW = "\u001B[43m"; // Fons groc.
    public static final String BG_BLUE   = "\u001B[44m"; // Fons blau.
    public static final String BG_PURPLE = "\u001B[45m"; // Fons lila.
    public static final String BG_CYAN   = "\u001B[46m"; // Fons cian.
    public static final String BG_WHITE  = "\u001B[47m"; // Fons blanc.

    // Imprimeix un missatge amb el color indicat.
    public static void printColored(String msg, String color) {
        System.out.println(color + msg + RESET);
    }

    // Missatges informatius en blau.
    public static void info(String msg) {
        System.out.println(BLUE + "[INFO] " + msg + RESET);
    }

    // Missatges d'advertència en groc.
    public static void warning(String msg) {
        System.out.println(YELLOW + "[WARNING] " + msg + RESET);
    }

    // Missatges d'error en vermell.
    public static void error(String msg) {
        System.out.println(RED + "[ERROR] " + msg + RESET);
    }

    // Missatges d'èxit en verd.
    public static void success(String msg) {
        System.out.println(GREEN + "[SUCCESS] " + msg + RESET);
    }

    // Formata un número afegint zeros a l'esquerra fins a arribar a la longitud indicada.
    public static String formatNumber(int number, int width) {
        return String.format("%0" + width + "d", number);
    }

    // Imprimeix un separador per dividir seccions en la sortida.
    public static void printDivider() {
        System.out.println(CYAN + "----------------------------------------" + RESET);
    }

    // Imprimeix un títol destacat amb fons blau i text blanc.
    public static void printHeader(String title) {
        System.out.println(BG_BLUE + WHITE + " " + title + " " + RESET);
    }

    /**
     * Construeix una línia composta per diverses seccions amb diferents colors.
     * Cal passar un nombre PARELL d'arguments en parelles:
     *   buildColoredLine(color1, text1, color2, text2, ..., colorN, textN)
     *
     * Exemple:
     *   String linia = buildColoredLine(RED, "Error: ", YELLOW, "Falla en la connexió ", GREEN, "[Reintentant]");
     *   System.out.println(linia);
     */
    public static String buildColoredLine(String... parts) {
        if (parts.length % 2 != 0) {
            throw new IllegalArgumentException("S'han de passar parelles d'arguments: color i text.");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parts.length; i += 2) {
            sb.append(parts[i]);      // Codi de color.
            sb.append(parts[i+1]);    // Text.
        }
        sb.append(RESET);
        return sb.toString();
    }

    /**
     * Imprimeix a la consola una línia composta de diverses seccions amb diferents colors.
     * Funciona igual que buildColoredLine(), però directament imprimeix la línia.
     */
    public static void printMultiColored(String... parts) {
        System.out.println(buildColoredLine(parts));
    }
}
