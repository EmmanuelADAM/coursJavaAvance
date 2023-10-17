package demoCours;

import java.io.File;
import java.util.Arrays;




public class Test {

    static void testException1() {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] b = {10, 9, 8, 7, 6, 5, 0, 3, 2, 1};

        int nb = Math.min(a.length, b.length);

        int[] c = new int[nb];

        for (int i = 0; i <= nb; i++) {
            try {
                c[i] = a[i] / b[i];
                System.out.println("c[" + i + "] = " + c[i]);
            }
            catch (ArithmeticException e) {
                System.out.println("division par 0 ...");
                c[i] = 0;
                System.out.println("dans ce cas c[" + i + "]=" + c[i]);
            }
            catch (IndexOutOfBoundsException e) {
                System.out.println("une erreur de depassement d'index !!! : " + e.getMessage());
            }
        }
        System.out.println("fin du calcul");
    }

    static void triEtudiant(int nb)
    {
        var tab = new Personne[nb];
        Arrays.setAll(tab, i->new Etudiant("n"+i, (int)(Math.random()*100)+1, "L3"));
        System.out.println(Arrays.toString(tab));
        System.out.println("-".repeat(30));
        Arrays.sort(tab, (p1,p2)->(p1.age-p2.age));
        System.out.println(Arrays.toString(tab));
    }


    static void testEtudiant(){
        Etudiant e = new Etudiant();
        System.out.println(e);
        e = new Etudiant("qsdf", 23, "M1");
        System.out.println(e);
    }

    static void testTabMelange()
    {
        Personne[] tab = new Personne[4];
        tab[0] = new ResponsableFormation("aze", 25, 0);
        tab[1] = new Etudiant("poi", 23, "M1");
        tab[2] = new ResponsableFormation("rty", 31, 1);
        tab[3] = new Etudiant("uyt", 24,"M2");
        System.out.println(Arrays.toString(tab));
        for(Personne p:tab)p.inscrire();
    }

    static void testRecord()
    {
        record MinMax ( int min , int max) {};

        MinMax tuple;
        int[] tab = {1,5,4,2,9};
        int maxi = tab[0];
        int mini = tab[0];
        for (int v : tab) {
            if (v<mini) mini=v;
            if (v>maxi) maxi=v;
        }
        tuple =  new MinMax(mini, maxi);
        System.out.println(tuple);
    }

    static void testRecordGenerique()
    {

    }



    static void testColis(){
        Colis c = Colis.TYPE2;
        System.out.println(c + " poids = " + c.getPoids());
    }

    static void testInterface()
    {
        EtreVivant e = new Voyageur();
        e.naitre();

        EtreVivant etreParticulier = new Voyageur() {
            @Override
            public void naitre() {
                System.out.println("je nais aussi mais je suis particulier");
            }
        };

        etreParticulier.naitre();
    }

    static void testProprietes()
    {
        var prop = System.getProperties();
        var cles = prop.keySet();
        for(var c:cles){
            System.out.println(c + " - " + prop.get(c));
        }

    }

/*    static void affiche(Etudiant[] tab)
    {
        for(var e:tab)System.out.println(e);
    }
    static void affiche(ResponsableFormation[] tab)
    {
        for(var e:tab)System.out.println(e);
    }
*/
    static<T> void affiche(T[] tab)
    {
        for(var e:tab)System.out.println(e);
    }

    static void testAffiche()
    {
        var tab = new ResponsableFormation[4];
        affiche(tab);
        Integer[] tabInt={4,2,5};
        affiche(tabInt);
    }


    static public void testCouple()
    {
        var c = new Couple<Integer, String>(3, "essai");
        System.out.println(c.getV2().toUpperCase());
    }

    static void testRandom(){
        var taux = 0.2;
        var cpt = 0;
        var nb = 100;
        for(int i=0; i<nb; i++)
            if (Math.random()<taux)
                cpt++;
        System.out.println("nb elt < %.2f pour cent = %d sur %d".formatted(taux, cpt, nb));
    }


    static void testFile(){
        File repCourant = new File(".");
        System.out.println("le repertoire courant est : " + repCourant.getAbsolutePath());
        String nomFichier = "demoCours/Test.java" ;
        File f = new File(nomFichier);
        if (f.exists()) {
//            String nomRep = f.getAbsolutePath();

//            nomRep = nomRep.substring(0, nomRep.lastIndexOf("\\"));
            System.out.println(nomFichier + " est dans le repertoire " + f.getParent());
            System.out.println(" droits en lecture, ecriture : " + f.canRead() + "," + f.canWrite());
        }
        else{
            System.out.println("ce fichier n'existe pas (encore?) ....");
        }
    }

    public static void main(String[] args)
    {
//        Personne p = new Personne("azerty", 23);
//        System.out.println(p.getNom());
//        p.age++;
//        System.out.println(p);
//        triPersonne(10);
//        testEtudiant();
//        testTabMelange();
//        testColis();
//        testRecord();
//        testInterface();
//        testProprietes();
//        testAffiche();
//        testCouple();
//        testException1();
//        testFile();
        for(int i=0; i<10; i++)  testRandom();
    }

}
