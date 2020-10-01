package util;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * quelques fonctions illustrant le comportement des classes java.util.*<br>
 *     gestion des tableaux, des listes
 * @author emmanuel adam
 */
public class TestUtil {

    /**exemple d'utilisation de Arrays<br>
     * creation de tableau par setAll
     * tri et  affichage*/
    static void triTabObjets(int taille) {
        Personne[] tab = new Personne[taille];
        Random hasard = new Random();
        //remplir chaque case avec une personne de prénom croissant et d'age aleatoires
        Arrays.setAll(tab, i -> new Personne("p"+i, 15+hasard.nextInt(taille)));

        //demander l'affichage
        System.out.println(" tab initial =" + Arrays.toString(tab));

        //tri par le comparateur de la classe personne (tri par age decroissant)
        Arrays.sort(tab);

        //demander l'affichage
        System.out.println(" tab trie =" + Arrays.toString(tab));
    }

    /**exemple d'utilisation de Collections<br>
     * tri et  affichage d'une liste d'objet*/
    static void triListeObjets(int taille) {
        ArrayList<Personne> liste = new ArrayList<>();
        Random hasard = new Random();
        for(int i=0; i<taille; i++)
            liste.add(new Personne("p"+hasard.nextInt(taille), 15+hasard.nextInt(taille)));

        //demander l'affichage
        liste.forEach(System.out::print);
        System.out.println("------");

        //tri par le comparateur de la classe personne (tri par age decroissant)
        Collections.sort(liste);

        System.out.println("------");
        //demander l'affichage
        liste.forEach(System.out::print);
        System.out.println("------");
    }


    /**exemple d'utilisation de Collections<br>
     * tris et  affichages d'une liste d'objet*/
    static void autresTrisListeObjets(int taille) {
        ArrayList<Personne> liste = new ArrayList<>();
        Random hasard = new Random();
        for(int i=0; i<taille; i++)
            liste.add(new Personne("p"+hasard.nextInt(taille), 15+hasard.nextInt(taille)));

        //demander l'affichage
        System.out.println("liste non triee");
        liste.forEach(System.out::print);
        System.out.println("------");

        //comparateur base sur le prenom
        Comparator<Personne> c1 = Comparator.comparing(Personne::getPrenom);
        //comparateur base sur l'age
        Comparator<Personne> c2 = Comparator.comparing(Personne::getAge);
        //tri sur le prenom puis sur l'age
        System.out.println("tri sur le prenom puis sur l'age");
        liste.sort(c1.thenComparing(c2));

        //demander l'affichage
        liste.forEach(System.out::print);
        System.out.println("------");

        //tri par prenom decrossant
        liste.sort(c1.reversed());

        //demander l'affichage
        System.out.println("tri sur le prenom decroissant");
        liste.forEach(System.out::print);
        System.out.println("------");
    }

    /**exemple d'utilisation de Collections<br>
     * extractions et filrage de liste*/
    static void filtrerListeObjets(int taille) {
        ArrayList<Personne> liste = new ArrayList<>();
        Random hasard = new Random();
        for(int i=0; i<taille; i++)
            liste.add(new Personne("p"+hasard.nextInt(taille), 15+hasard.nextInt(taille)));

        //demander l'affichage
        liste.forEach(System.out::print);
        System.out.println("------");

        //recherche de la personne la plus jeune
        Personne plusJeune = Collections.min(liste, Comparator.comparingInt(Personne::getAge));
        System.out.println("la personne la plus jeune est : " + plusJeune);
        //recherche de prenom le plus loin dans l'alphabet
        Personne dernier = Collections.max(liste, Comparator.comparing(Personne::getPrenom));
        System.out.println("la derniere personne par ordre alphabetique  est : " + dernier);

        // oter les mineures
        liste.removeIf(p->p.getAge()<18);

        System.out.println("les personnes majeures :");
        liste.forEach(System.out::print);
        System.out.println("------");

        // faire viellir de 10 ans
        liste.forEach(p->p.setAge(p.getAge()+10));

        System.out.println("les personnes viellies :");
        liste.forEach(System.out::print);
        System.out.println("------");
    }

    /**gestion de donnees via des streams :
     * recherche, extraction, conversion en tableau, ...*/
    static void testStream()
    {
        ArrayList<Personne> liste = new ArrayList<>(
                List.of(new Personne("anton",15, Region.RE),new Personne("yvan",18,Region.ARA),new Personne("boris",20,Region.BFC)));
        liste.addAll(List.of(new Personne("rebecca",19,Region.RE),new Personne("pola",17,Region.ARA),new Personne("yohanna",19,Region.BFC)));
        liste.addAll(List.of(new Personne("sacha",17, Region.HDF),new Personne("sonia",16, Region.COR),new Personne("david",18, Region.IDF )));
        liste.addAll(List.of(new Personne("dimitri",16, Region.HDF),new Personne("yani",15, Region.COR),new Personne("natacha",17, Region.IDF)));

        //CREER UNE LISTE DE VALEURS EXTRAITES
        Stream<Personne> flux = liste.stream();
        IntStream fluxAge = flux.mapToInt(Personne::getAge);
        OptionalInt vieilAge = fluxAge.max();
        vieilAge.ifPresent(age->System.out.println("-age le plus avancé = " + age));

        //FILTRER LES ELEMENTS
        //(attention, recréer le flux !!!)
        flux = liste.stream();
        Stream<Personne> adultes =  flux.filter(p->p.getAge()>=18);
        System.out.println("-personne adultes : ");
        adultes.forEach(System.out::println);
        //ATTENTION RECREER LE FLUX !!!
        flux = liste.stream().filter(p->p.getAge()>=18);
        Optional<Personne> jeune =  flux.min(Comparator.comparingInt(Personne::getAge));
        if(jeune.isPresent())
            System.out.println("-personne majeure la plus jeune : " + jeune.get());
        //ou
        jeune.ifPresent(personne -> System.out.println("-personne majeure la plus jeune : " + personne));


        //CREER UNE LISTE A PARTIR D'UN  FLUX
        //(attention, recréer le flux !!!)
        flux = liste.stream();
        Stream<Personne> enfantStream =  flux.filter(p->p.getAge()<18);
        List<Personne> mineures = enfantStream.collect( Collectors.toList() );
        System.out.println("les personnes mineures");
        mineures.forEach(System.out::print);
        System.out.println();

        //CREER UN TABLEAU A PARTIR D'UN  FLUX
        //(attention, recréer le flux !!!)
        flux = liste.stream();
        Stream<Personne> adoStream =  flux.filter(p->(p.getAge()==18));
        Personne[] tabMajeures = adoStream.toArray(Personne[]::new);
        System.out.println("-personne nouvellement majeures  : ");
        System.out.println(Arrays.toString(tabMajeures));

        // CALCUL DE MOYENNE, DE SOMME
        //(attention, recréer le flux !!!)
        flux = liste.stream();
        OptionalDouble ageMoyen = flux.mapToInt(Personne::getAge).average();
        ageMoyen.ifPresent(age-> System.out.printf("age moyen = %.1f\n", age));

        //(attention, recréer le flux !!!)
        flux = liste.stream();
        int sommeAge = flux.mapToInt(Personne::getAge).sum();
        System.out.println("somme des ages  = " +sommeAge);

        //(attention, recréer le flux !!!)
        flux = liste.stream();
        OptionalDouble moyenne = flux.filter(p->p.getRegion()==Region.HDF).mapToInt(Personne::getAge).average();
        moyenne.ifPresent(age->System.out.println("moyenne d'age des personnes de Hauts-De-France  : " + age));

        //RECHERCHE D'ELEMENTS
        System.out.println("Existe-t-il une personne de Corse ?");
        System.out.println("-> " + liste.stream().anyMatch(p->p.getRegion()==Region.COR));

        System.out.println("Est-ce-que toutes les personnes sont majeures  ?");
        System.out.println("-> " + liste.stream().allMatch(p->p.getAge()>=18));


        //REGROUPER DANS UNE MAP
        //(attention, recréer le flux !!!)
        flux = liste.stream();
        Map<Region, List<Personne>> parRegion =  flux.collect(Collectors.groupingBy(Personne::getRegion));
        System.out.println("liste des personnes classees par region : ");
        parRegion.forEach((k,v) -> System.out.println(k + "="+ v.toString()));

        //(attention, recréer le flux !!!)
        flux = liste.stream();
        //grouper selon prédicat
        Map<Boolean, List<Personne>> parStatut =
                flux.collect(Collectors.partitioningBy(p -> p.getAge() >= 18));
        System.out.println("liste des personnes partitionnees en mineures/majeures  : ");
        parStatut.forEach((k,v) -> System.out.println(k + "="+ v.toString()));

    }

    public static void main(String[] args)
    {
        triTabObjets(20);
//        triListeObjets(20);
//        autresTrisListeObjets(20);
//        filtrerListeObjets(20);
//        testStream();
    }


}
