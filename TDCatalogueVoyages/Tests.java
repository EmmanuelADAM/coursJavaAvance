package TDCatalogueVoyages;

import java.io.*;
import java.time.LocalTime;
import java.util.*;

public class Tests {

    static void testMoyens() {
        Moyen m = Moyen.Bus;
        System.out.println(m);
        System.out.println(m.getCout());
        System.out.println("-".repeat(20));
        for (Moyen moy : Moyen.values())
            System.out.printf("moyen no %d, %s, cout=%.2f, vitesse=%.2f\n", moy.ordinal(), moy, moy.getCout(), moy.getVitesse());
        System.out.println("-".repeat(20));

        try {
            var mTest = Moyen.valueOf("Train");
            System.out.printf("moyen no %d, %s, cout=%.2f, vitesse=%.2f\n", mTest.ordinal(), mTest, mTest.getCout(), mTest.getVitesse());
        } catch (Exception e) {
            System.err.println("erreur de recuperation du moyen : " + e.getMessage());
        }

        System.out.println("-".repeat(20));
    }

    static void testVilles() {
        Ville[] lesVilles = Ville.values();
        int nb = lesVilles.length;
        for (int i = 0; i < nb - 1; i++) {
            var start = lesVilles[i];
            for (int j = i + 1; j < nb; j++) {
                var end = lesVilles[j];
                if (start.existeChemin(end))
                    System.out.println("il existe un chemin entre " + start + " et " + end);
                else
                    System.out.println("il n'existe pas de chemin entre " + start + " et " + end);
            }
            System.out.println("-".repeat(20));
        }
    }


    static void testTrajetSimple() {
        var trajetsSimples = new ArrayList<TrajetSimple>(40);
        Ville[] lesVilles = Ville.values();
        int nb = lesVilles.length;

        for (int i = 0; i < nb - 1; i++) {
            var start = lesVilles[i];
            for (int j = i + 1; j < nb; j++) {
                var end = lesVilles[j];
                if (start.existeChemin(end)) {
                    Moyen m = Moyen.Bus;
                    if (start == Ville.A && end == Ville.F || start == Ville.F && end == Ville.A) m = Moyen.Train;
                    if (start == Ville.A && end == Ville.D || start == Ville.D && end == Ville.A) m = Moyen.Tram;
                    if (start == Ville.F && end == Ville.D || start == Ville.D && end == Ville.F) m = Moyen.Tram;
                    trajetsSimples.add(new TrajetSimple(start, end, m, LocalTime.of(6, 0)));
                    trajetsSimples.add(new TrajetSimple(end, start, m, LocalTime.of(6, 0)));
                }
            }
        }

        trajetsSimples.forEach(System.out::println);
    }

    static Catalogue testCatalogue() {
        Catalogue cata = new Catalogue();
        cata.creerCatalogue();
        cata.tableDepart.forEach((k, v) -> System.out.printf("de %s on a ces trajets %s\n", k, v));
        Ville ville = Ville.D;
        List<TrajetSimple> lesTrajets = cata.tableDepart.get(ville);
        Optional<TrajetSimple> trajet = lesTrajets.stream().min(Comparator.comparingDouble(TrajetSimple::getCout));
        trajet.ifPresent(t -> System.out.println("Le trajet le moins cher partant de " + ville + " est " + t));
        trajet = lesTrajets.stream().max(Comparator.comparingDouble(TrajetSimple::getDuree));
        trajet.ifPresent(t -> System.out.println("Le trajet le plus long partant de " + ville + " est " + t));
        System.out.println("-".repeat(30));
        System.out.println("Trajets de D vers A entre 7h et 9h : ");
        var l = cata.trouveCheminsDirects(Ville.D, Ville.A, LocalTime.of(7,0), 120);
        System.out.println(l);
        return cata;
    }

    static void testTrajetCompose(){
        TrajetCompose tc = new TrajetCompose();
        tc.add(new TrajetSimple(Ville.A, Ville.B, Moyen.Bus, LocalTime.now()));
        tc.add(new TrajetSimple(Ville.B, Ville.C, Moyen.Bus, LocalTime.now().plusMinutes(30)));
        tc.add(new TrajetSimple(Ville.C, Ville.F, Moyen.Train, LocalTime.now().plusMinutes(60)));
        System.out.println(tc);
        System.out.println(tc.detailToString());
    }

    public static void main(String[] args) {
//        testMoyens();
//                testVilles();
//        testTrajetSimple();
//        var catalog = testCatalogue();
        testTrajetCompose();
    }
}
