/**
 * Klasse LagerTest
 *
 * @author Kiran & Stelz
 * @version 1.0
 */
public class LagerTest {
    public static void main(String[] args) {
        try {
            Lager lager = new Lager(5);
            lager.legeAnArtikel(new Artikel(1111, "Auto", 50, 1000.0));
            lager.legeAnArtikel(new Artikel(1112, "Staubsauger", 3000, 10.0));
            lager.legeAnArtikel(new Artikel(9999, "Hose", 1000, 20.0));
            System.out.println(lager);
            System.out.println(lager.ausgebenBestandsListe());
        } catch(RuntimeException e) {
            System.out.println(e);
        }
    }
}