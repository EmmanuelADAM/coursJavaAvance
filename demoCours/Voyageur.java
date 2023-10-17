package demoCours;

public class Voyageur implements EtreVivant {
    @Override
    public void naitre() {
        System.out.println("je nais");
    }

    public void voyager() {
        System.out.println("je voyage");
    }
}
