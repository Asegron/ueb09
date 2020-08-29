/**
 * Klasse CD
 *
 * @author Kiran & Stelz
 * @version 1.0
 */
public class CD extends Artikel {
    /**
     * Konstanten
     */
    private static final String INTERPRET_UNGUELTIG =
        "Eingegebener Interpret ist ungueltig!";
    private static final String TITEL_UNGUELTIG =
        "Eingegebener Titel ist ungueltig!";
    private static final String ANZAHLTITEL_UNGUELTIG =
        "Eingegebene Anzahl der Musiktitel ist ungueltig!";

    /**
     * Attribute
     */
    private String 	interpret;
    private String 	titel;
    private int 	anzahlTitel;

    public CD(int artikelNr, String bezeichnung, int bestand, double preis,
    String interpret, String titel, int anzahlTitel) {
        super(artikelNr, bezeichnung, bestand, preis);
        setCDAttribute(interpret, titel, anzahlTitel);
    }

    private void setCDAttribute(String interpret, String titel, int anzahlTitel) {
        setInterpret(interpret);
        setTitel(titel);
        setAnzahlTitel(anzahlTitel);
    }

    public String getInterpret() {
        return interpret;
    }

    public void setInterpret(String interpret) {
        if(interpret == null || interpret.trim().length() <= 0) {
            throw new IllegalArgumentException(INTERPRET_UNGUELTIG);
        }

        this.interpret = interpret;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        if(titel == null || titel.trim().length() <= 0) {
            throw new IllegalArgumentException(TITEL_UNGUELTIG);
        }

        this.titel = titel;
    }

    public int getAnzahlTitel() {
        return anzahlTitel;
    }

    public void setAnzahlTitel(int anzahlTitel) {
        if(anzahlTitel <= 0) {
            throw new IllegalArgumentException(ANZAHLTITEL_UNGUELTIG);
        }

        this.anzahlTitel = anzahlTitel;
    }

    public String getBeschreibung() {
        return String.format("%20s : %20s", interpret, titel);
    }

    public String toString() {
        return String.format("%s -> %12s %20s %5s", super.toString(), interpret, titel, anzahlTitel);
    }
}