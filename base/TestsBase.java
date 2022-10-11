package base;

import java.util.*;
import java.util.function.BiFunction;

import static java.lang.System.out;

/**
 * quelques fonctions illustrant le comportement de java<br>
 *     cout des objets, gestion des tableaux, des listes
 * @author emmanuel adam 
 */
public class TestsBase {

    /**fonction pour illustrer le cout de la creation d'objets <br>
     * realise une somme de 1 a nb : <br>
     * - en utilisant des primitives <br>
     * - en utilisant des Integer
     * @param nb seuil pour le calcul de la somme de 1 a nb
     * */
    public  static void coutObjet(int nb)
    {
        int somme = 0;

        long tailleDebut = Runtime.getRuntime().freeMemory();
        long tpsDebut = System.nanoTime();

        for(int i=0; i<nb; i++)
            somme = somme + i;

        long tpsFin = System.nanoTime();
        long tailleFin = Runtime.getRuntime().freeMemory();
        out.println("somme de 0 à " + nb + " = " + somme);
        System.out.println("-- tps de calcul en utilisant les primitives =  " + (double)(tpsFin - tpsDebut)/1000000 + " ms");
        out.println("-- memoire utilisee =  " + (tailleDebut - tailleFin)/8 + " o");

        Integer objSomme = 0;
        tailleDebut = Runtime.getRuntime().freeMemory();
        tpsDebut = System.nanoTime();

        for(int i=0; i<nb; i++)
            objSomme = objSomme + i;

        tpsFin = System.nanoTime();
        tailleFin = Runtime.getRuntime().freeMemory();
        out.println("somme de 0 à " + nb + " = " + objSomme);
        System.out.println("-- tps de calcul en utilisant les objets =  " + (double)(tpsFin - tpsDebut)/1000000 + " ms");
        out.println("-- memoire utilisee =  " + (tailleDebut - tailleFin)/8 + " o");
    }


    /**
     * fonction qui compare les couts en tps de l'utilisation de String, StringBuilder et StringBuffer<br>
     * affiche la chaine "0,1,..., 59999"
     * */
    public static void testSB()
    {
        int taille = 60000;
        long momentDebut = System.nanoTime();
        //creation d’une chaine ch = 0, 1, …., 59999
        String ch = "";
        for(int i=0; i<taille; i++)  ch += i + ", ";
        long momentFin  = System.nanoTime();
        System.out.println("Concaténation de "+taille+" String par +, temps écoulé = "+  (double)(momentFin - momentDebut)/1000000 + " ms");

        String sep = ", ";
        momentDebut = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<taille; i++) sb.append(i).append(sep);
        momentFin  = System.nanoTime();
        System.out.println(sb.substring(0, 30));
        System.out.println("Concaténation de "+taille+" String par StringBuilder, temps écoulé = "+ (double)(momentFin - momentDebut)/1000000 + " ms");

        momentDebut = System.nanoTime();
        StringBuffer sb2 = new StringBuffer();
        for(int i=0; i<taille; i++) sb2.append(i).append(sep);
        momentFin  = System.nanoTime();
        System.out.println(sb2.substring(0, 30));
        System.out.println("Concaténation de "+taille+" String par StringBuffer, temps écoulé = "+ (double)(momentFin - momentDebut)/1000000 + " ms");
    }

    /**fonction illustrant la creation d'un tableau, son affichage, son tri par lambda expression et sans boucles*/
    static void testTabEtTri()
    {
        int taille = 100000000;
        int[] tab = new int [taille];
        Random hasard = new Random();
        //affecte à chaque case d'indice i un nb entre 0 et taille
        Arrays.setAll(tab, i->hasard.nextInt(taille));
        //affiche les 10 premiers
        for(int i=0; i<10; i++) out.print(tab[i] + ", ");
        out.println();
        //tri
        long momentDebut = System.nanoTime();
        Arrays.sort(tab);
        long momentFin = System.nanoTime();

        System.out.println("temps écoulé pour le tri de "+taille+" entiers = "+ (double)(momentFin - momentDebut)/1000000000d + " s");
        //affiche les 10 premiers
        for(int i=0; i<10; i++) out.print(tab[i] + ", ");
        out.println();

    }
    /**fonction pour illustrer le tri de 100 millions d'entiers en parallele
     * */
    static void testTriParallele()
    {
        int taille = 100000000;
        int[] tab = new int [taille];
        Random hasard = new Random();
        //affecte a chaque case d'indice i un nb entre 0 et 100 millions
        Arrays.setAll(tab, i->hasard.nextInt(taille));

        //affiche les 10 premiers
        for(int i=0; i<10; i++) out.print(tab[i] + ", ");
        out.println();

        //tri les 100 millions d'entiers (env. 6 secondes)
        long momentDebut = System.nanoTime();
        Arrays.parallelSort(tab);
        long momentFin = System.nanoTime();

        System.out.println("temps écoulé pour le tri de "+taille+" entiers en parallèle = "+ (double)(momentFin - momentDebut)/1000000000d + " s");

        //verificiations sur les 10 premiers
        for(int i=0; i<10; i++) out.print(tab[i] + ", ");
        out.println();
    }

    /**fonction qui illustre le fonctionnement de liste dynamiques et statiques*/
    static void testListe()
    {
        //listes statiques, ne peuvent varier
        List<Integer> liste1 = List.of(1, 2, 3, 4, 5);
        List<Integer> liste2 = List.of(4, 5, 6, 7, 8);
        //affichage des listes
        out.println("liste1 = " +liste1);
        out.println("liste2 = " +liste2);
        //Une liste modifiable, contenant une copie de la liste 1
        ArrayList<Integer> ajout = new ArrayList<>(liste1);
        //ajout de toute la liste 2
        ajout.addAll(liste2);
        out.println("concaténation = " +ajout);
        //Une liste modifiable, contenant une copie de la liste 1
        ArrayList<Integer> union = new ArrayList<>(liste1);
        //retrait des éléments communs avec la liste 2
        union.removeAll(liste2);
        //ajout des éléments de la liste 2
        union.addAll(liste2);
        out.println("union = " +union);
        //Une liste modifiable, contenant une copie de la liste 1
        ArrayList<Integer> intersection = new ArrayList<>(liste1);
        //ne garder que ce qui est commun avec la liste 2
        intersection.retainAll(liste2);
        out.println("intersection = " +intersection);
        //Une liste modifiable, contenant une copie de l'union
        ArrayList<Integer> unionDisjointe = new ArrayList<>(union);
        //retirer que ce qui est dans l'intersection
        unionDisjointe.removeAll(intersection);
        out.println("unionDisjointe = " +unionDisjointe);

        //n'afficher que les valeurs >5 dans l'union
        out.println("valeur > 5 dans l'union :");
        union.forEach(v->{if(v>5)out.print(v+", ");});
        out.println();

        //oter ce qui est < 5 dans l'union
        union.removeIf(v->v<5);
        out.println("union sans les valeurs < 5 :" + union);

    }

    /**
     * test de map String < - > String
     **/
    static void testMap()
    {
        HashMap<String, String> users = new HashMap<>();
        users.put("al1", "alain");
        users.put("krol", "carole");
        users.put("yan", "yanice");
        users.forEach((k,v)-> out.println(k + " correspond a " + v));

        String key = "krol";
        String val = users.get(key);
        if(val!=null)
            out.println("valeur associee à " + key + "=" + val);

        val = users.put("yan", "yann");
        out.println("valeur associee precedemment à yan =" + val);
        out.println("nouvelle valeur associee  à yan =" + users.get("yan"));
    }

        /**
     * exemple de fonction utilisant une fonction passee en parametre
     * initialise le contenu de tab3 avec l'application de f sur les elements de tab1 et tab2
     * @param tab1 tableau d'entree
     * @param tab2 tableau d'entree
     * @param tab3 tableau de sortie
     * @param f fonction (Integer, Integer)->Double
     * */
    static void opeSurTab(int[] tab1, int[] tab2, double[] tab3, BiFunction<Integer, Integer, Double> f)
    {
        //eviter de depasser les bornes
        int taille = Math.min(tab1.length, Math.min(tab2.length, tab3.length));
        for(int i=0; i<taille; i++)
            tab3[i] = f.apply(tab1[i], tab2[i]);
    }

    /**
     * exemple de programmation fonctionnelle
     */
    static void testFonctionnel()
    {
        int[]tabA = {2,5,4,3,1,7,8};
        int[]tabB = {5,4,8, 2};
        double[]tabC = new double[8];
        System.out.println("tabA="+Arrays.toString(tabA));
        System.out.println("tabB="+Arrays.toString(tabB));

        opeSurTab(tabA, tabB, tabC, (a,b)->(double)a*b);
        System.out.println("tabC=tabAxTabB ="+Arrays.toString(tabC));

        opeSurTab(tabA, tabB, tabC, (a,b)->(double)a/b);
        System.out.println("tabC=tabA/TabB ="+Arrays.toString(tabC));
    }


    public static void  main(String[] args) {
        //coutObjet(100000);
        //testSB();
        //testTabEtTri();
        //testTriParallele();
        testListe();
        //testMap();
        //testFonctionnel();
    }
}
