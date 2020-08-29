import java.util.*;

/**
 * Klasse Lager für den Zweck der einfachen Bestandsführung
 * 
 * @author Kiran & Stelz
 * @version 1.0
 */
public class Lager {
    /**
     * Klassenkonstanten
     */
    private static final int STANDARD_LAGER_GROESSE 	= 10;
    private static final int MIN_LAGER_GROESSE 			= 1;

    private static final String LAGER_GROESSE_UNGUELTIG =
        "Die Kapazität des Lagers muss mind. 1 sein!";
    private static final String ARTIKEL_SCHON_ANGELEGT =
        "Dieser Artikel wurde bereits angelegt!";
    private static final String LAGER_VOLL =
        "Der Lager ist bereits voll!";
    private static final String ARTIKEL_NICHT_VORHANDEN =
        "Gesuchter Artikel ist nicht im Lager!";
    private static final String INDEX_UNGUELTIG =
        "Der Index ist zu gross oder zu klein!";

    /**
     * Attribute
     */
    private Artikel[] lager;
    int letzterBesetzterIndex;

    /**
     * Konstruktor zum Initialisieren der Kapazitaet
     * 
     * @param dimension
     */
    public Lager(int dimension) {
        if(dimension < MIN_LAGER_GROESSE) {
            throw new IllegalArgumentException(LAGER_GROESSE_UNGUELTIG);
        }

        lager = new Artikel[dimension];
        letzterBesetzterIndex = -1;

        for(int i = 0; i < dimension; i++) {
            lager[i] = null;
        }
    }

    /**
     * Konstruktor mit der Standardlagergroesse
     */
    public Lager() {
        this(STANDARD_LAGER_GROESSE);
    }

    /**
     * Legt einen neuen Artikel im Lager an
     * 
     * @param artikel Es darf keinen neuer Artikel angelegt werden, 
     * wenn die selbe Artikelnummer schon vergeben ist und 
     * wenn der Lager schon voll ist
     */
    public void legeAnArtikel(Artikel artikel) {
        if(sucheArtikel(artikel.getArtikelNr()) != -1) {
            throw new IllegalArgumentException(ARTIKEL_SCHON_ANGELEGT);
        }

        if(letzterBesetzterIndex >= lager.length - 1) {
            throw new IllegalArgumentException(LAGER_VOLL);
        }

        lager[++letzterBesetzterIndex] = artikel;
    }

    /**
     * Entfernt einen bereits angelegten Artikel
     * 
     * @param artikelNr Es muss mind. ein Artikel schon angelegt sein
     */
    public void entferneArtikel(int artikelNr) {
        int artikelOrt;
        int schieber;

        artikelOrt = sucheArtikel(artikelNr);

        if(artikelOrt == -1) {
            throw new IllegalArgumentException(ARTIKEL_NICHT_VORHANDEN);
        }

        lager[artikelOrt] = null;
        letzterBesetzterIndex--;

        for(schieber = artikelOrt; schieber <= letzterBesetzterIndex; schieber++) {
            lager[schieber] = lager[schieber + 1];
        }

        if(schieber + 1 < lager.length) {
            lager[schieber + 1] = null;
        }
    }

    /**
     * Bucht Zugang der Menge eines im Lager angelegten Artikels
     * 
     * @param artikelNr Es muss mind. ein Artikel schon angelegt sein
     * @param menge Die Menge muss mind. 1 sein
     */
    public void bucheZugang(int artikelNr, int menge) {
        int artikelIndex = sucheArtikel(artikelNr);

        if(artikelIndex == -1) {
            throw new IllegalArgumentException(ARTIKEL_NICHT_VORHANDEN);
        }

        lager[artikelIndex].bucheZugang(menge);
    }

    /**
     * Bucht Abgang der Menge eines im Lager angelegten Artikels
     * 
     * @param artikelNr Es muss mind. ein Artikel schon angelegt sein
     * @param menge Die Menge muss mind. 1 sein und nicht groesser als der Bestand sein
     */
    public void bucheAbgang(int artikelNr, int menge) {
        int artikelIndex = sucheArtikel(artikelNr);

        if(artikelIndex == -1) {
            throw new IllegalArgumentException(ARTIKEL_NICHT_VORHANDEN);
        }

        lager[artikelIndex].bucheAbgang(menge);
    }

    /**
     * Aendert die Preise aller angelegten Artikel auf einen
     * eingegebenen Prozentwert
     * 
     * @param prozent Darf mind. 0,00% sein
     */
    public void aenderePreisAllerArtikel(double prozent) {
        for(int i = 0; i <= letzterBesetzterIndex; i++) {
            lager[i].aenderePreis(prozent);
        }
    }

    /**
     * Hilfsmethode zum Suchen eines Artikels im Lager
     * 
     * @param sucheArtikelNr Der gesuchte Artikel muss existieren
     * @return Der Platz des Artikels im Lager
     */
    public int sucheArtikel(int sucheArtikelNr) {
        int i;
        int gefunden;

        for(i = 0, gefunden = -1; i <= letzterBesetzterIndex && gefunden == -1; i++) {
            if(lager[i].getArtikelNr() == sucheArtikelNr) {
                gefunden = i;
            }
        }

        return gefunden;
    }

    /**
     * Gibt die Anzahl der bereits angelegten Artikel zurück
     * 
     * @return Artikelindex
     */
    public int getArtikelAnzahl() {
        return letzterBesetzterIndex + 1;
    }

    /**
     * 
     * Gibt die Groesse des Lagers zurück
     * 
     * @return Lagergroesse
     */
    public int getLagerGroesse() {
        return lager.length;
    }

    /**
     * Teilt einem Artikel einen Indexwert zu
     * 
     * @param index Der Index eines Artikels muss groesser als 0 sein
     * @return Indexwert des Artikel im Lager
     */
    public Artikel getArtikel(int index) {
        if(index >= getLagerGroesse() || index < 0) {
            throw new IllegalArgumentException(INDEX_UNGUELTIG);
        }

        return lager[index];
    }

    /**
     * Erzeugt einen String mit allen Details
     * 
     * @return String des Lagers
     */
    public String toString() {
        StringBuilder lagerString = new StringBuilder("Im Lager sind von " + getLagerGroesse() +
                " Lagerplaetzen " + (letzterBesetzterIndex + 1) +
                " belegt, mit den folgenden Artikeln:\n");

        for(int i = 0; i <= letzterBesetzterIndex; i++) {
            lagerString.append(String.format("\n %3d %s", i, lager[i]));
        }

        return lagerString.toString();
    }

    /**
     * 
     * 
     * @return
     */
    public String ausgebenBestandsListe() {
        double zeilenWert;
        double gesamtWert = 0;

        StringBuilder bestandsListe = new StringBuilder();
        Formatter formatierer = new Formatter(bestandsListe);

        formatierer.format("\nLagerort: Alt-Saarbruecken\n\n" +
            "%6s %-25s %10s %10s %10s" +
            "\n----------------------------------------------------------------",
            "ArtNr", "Beschreibung", "Preis", "Bestand", "Gesamt");

        for(int i = 0; i <= letzterBesetzterIndex; i++) {
            zeilenWert = lager[i].getPreis() * lager[i].getBestand();
            gesamtWert += zeilenWert;

            formatierer.format("\n%6d %-25s %10.2f %10d %10.2f",
                lager[i].getArtikelNr(),
                lager[i].getBezeichnung(),
                lager[i].getPreis(),
                lager[i].getBestand(),
                zeilenWert);
        }

        formatierer.format("\n----------------------------------------------------------------\n" +
            "Gesamtwert:\t\t\t\t\t\t" + gesamtWert);

        return bestandsListe.toString();
    }
}