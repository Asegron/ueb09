/**
 * Klasse Video
 *
 * @author Kiran & Stelz
 * @version 1.0
 */
public class Video extends Artikel {
    /**
     * Konstanten
     */
    public static final int MIN_ERSCHEINUNGSJAHR = 1950;
    public static final int MAX_ERSCHEINUNGSJAHR = 2014;

    private static final String TITEL_UNGUELTIG =
        "Eingegebener Titel ist ungueltig!";
    private static final String DAUER_UNGUELTIG =
        "Eingegebener Dauer ist ungueltig!";
    private static final String ERSCHEINUNGSJAHR_UNGUELTIG =
        "Eingegebenes Erscheinungsjahr ist ungueltig!";

    /**
     * Attribute
     */
    private String	titel;
    private int		dauer;
    private int		erscheinungsjahr;

    public Video(int artikelNr, String bezeichnung, int bestand, double preis,
    String titel, int dauer, int erscheinungsjahr) {
        super(artikelNr, bezeichnung, bestand, preis);
        setVideoAttribute(titel, dauer, erscheinungsjahr);
    }

    private void setVideoAttribute(String titel, int dauer, int erscheinungsjahr) {
        setTitel(titel);
        setDauer(dauer);
        setErscheinungsjahr(erscheinungsjahr);
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

    public int getDauer() {
        return dauer;
    }

    public void setDauer(int dauer) {
        if(dauer <= 0) {
            throw new IllegalArgumentException(DAUER_UNGUELTIG);
        }

        this.dauer = dauer;
    }

    public int getErscheinungsjahr() {
        return erscheinungsjahr;
    }

    public void setErscheinungsjahr(int erscheinungsjahr) {
        if(erscheinungsjahr < MIN_ERSCHEINUNGSJAHR || erscheinungsjahr > MAX_ERSCHEINUNGSJAHR) {
            throw new IllegalArgumentException(ERSCHEINUNGSJAHR_UNGUELTIG);
        }

        this.erscheinungsjahr = erscheinungsjahr;
    }

    public String getBeschreibung() {
        return String.format("%20s", titel);
    }

    public String toString() {
        return String.format("%s -> %20s %10.2f %5d", super.toString(), titel, dauer, erscheinungsjahr);
    }
}