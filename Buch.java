/**
 * Klasse Buch
 *
 * @author Kiran & Stelz
 * @version 1.0
 */
public class Buch extends Artikel {
    /**
     * Konstanten
     */
    private static final String TITEL_UNGUELTIG =
        "Der Titel ist ungueltig!";
    private static final String AUTOR_UNGUELTIG =
        "Der Autor ist ungueltig!";
    private static final String VERLAG_UNGUELTIG =
        "Der Verlag ist ungueltig!";

    /**
     * Attribute
     */
    private String titel;
    private String autor;
    private String verlag;

    public Buch(int artikelNr, String bezeichnung, int bestand, double preis,
    String titel, String autor, String verlag) {
        super(artikelNr, bezeichnung, bestand, preis);
        setBuchAttribute(titel, autor, verlag);
    }

    private void setBuchAttribute(String titel, String autor, String verlag) {
        setTitel(titel);
        setAutor(autor);
        setVerlag(verlag);
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        if(titel == null || titel.trim().length() <= 0) {
            throw new RuntimeException(TITEL_UNGUELTIG);
        }

        this.titel = titel;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        if(autor == null || autor.trim().length() <= 0) {
            throw new RuntimeException(AUTOR_UNGUELTIG);
        }

        this.autor = autor.trim();
    }

    public String getVerlag() {
        return verlag;
    }

    public void setVerlag(String verlag) {
        if(verlag == null || verlag.trim().length() <= 0) {
            throw new RuntimeException(VERLAG_UNGUELTIG);
        }

        this.verlag = verlag;
    }

    public String getBeschreibung() {
        return String.format("%20s : %20s", autor, titel);
    }

    public String toString() {
        return String.format("%s -> %10s %10s %15s", super.toString(), autor, titel, verlag);
    }
}