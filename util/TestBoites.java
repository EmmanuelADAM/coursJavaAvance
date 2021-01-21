package util;

import java.util.Random;
import java.util.Arrays;
import java.util.function.Function;

/**fonction qui illustre la recherche d'elements par programmation fonctionnelle.
 * Ici, on va rechercher des boites selon les champs hauteurs, largeurs, ....*/
public class TestBoites {

    TestBoites(){}

    /**simple fonction qui retourne la boite la plus haute
     * @param tab un tableau de boites*/
    Boite getPlusHaute(Boite[] tab)
    {
        Boite max = tab[0];
        for(Boite b:tab)
        {
            if (b.getHauteur() > max.getHauteur())
                max = b;
        }
        return max;
    }

    /**cree et retourne un tableau de nb Boites initialisees au hasard*/
    Boite[] creerTab(int nb)
    {
        Boite[] tab = new Boite[nb];
        Random hasard = new Random();
        //on suppose une largeur entre 10 et 40 max, une longeur entre 40 et 80 max, une hauteur entre 10 et 60, un poids entre 0.1 et 2kg
        Arrays.setAll(tab, i->new Boite(hasard.nextDouble()*30+10, hasard.nextDouble()*40+40,
                hasard.nextDouble()*50+10, hasard.nextDouble()*1.9+0.1));
        return tab;
    }

    /**fonction qui retourne la boite dont la valeur retournee par la fonction f est la plus grande
     * @param tab un tableau de boites
     * @param get une fonction prenant en entree une boite et sortant un reel Double*/
    Boite getBoiteMax(Boite[] tab, Function<Boite, Double> get)
    {
        Boite max = tab[0];
        for(Boite b:tab)
        {
            // on decompose pour bien comprendre (on peut ecrire de maniere plus synthetique et gagner des tests)
            //on applique la fonction a b pour recuperer sa valeur
            double valb = get.apply(b);
            //on applique la fonction a max pour recuperer sa valeur
            double valMax = get.apply(max);
            if (valb > valMax)
                max = b;
        }
        return max;
    }


    public static void main(String[] args)
    {
        TestBoites tb = new TestBoites();
        Boite[] boites = tb.creerTab(10);
        System.out.println("voici les boites");
        for(Boite b:boites) System.out.println(b);
        System.out.println("-".repeat(10));
        // RECHERCHE CLASSIQUE
        Boite haute = tb.getPlusHaute(boites);
        System.out.println("la plus haute est " + haute);
        System.out.println("-".repeat(10));
        // RECHERCHE EN PROGRAMMATION FONCTIONNELLE : ECRITURE 1
        Boite bMax = tb.getBoiteMax(boites, b->b.getHauteur());
        System.out.println("la plus haute est " + bMax);
        bMax = tb.getBoiteMax(boites, b->b.getLargeur());
        System.out.println("la plus large est " + bMax);
        bMax = tb.getBoiteMax(boites, b->b.getLongueur());
        System.out.println("la plus longue est " + bMax);
        bMax = tb.getBoiteMax(boites, b->b.getPoids());
        System.out.println("la plus lourde est " + bMax);
        System.out.println("-".repeat(10));
        System.out.println("-".repeat(10));
        // RECHERCHE EN PROGRAMMATION FONCTIONNELLE : ECRITURE 2
         bMax = tb.getBoiteMax(boites, Boite::getHauteur);
        System.out.println("la plus haute est " + bMax);
        bMax = tb.getBoiteMax(boites, Boite::getLargeur);
        System.out.println("la plus large est " + bMax);
        bMax = tb.getBoiteMax(boites, Boite::getLongueur);
        System.out.println("la plus longue est " + bMax);
        bMax = tb.getBoiteMax(boites, Boite::getPoids);
        System.out.println("la plus lourde est " + bMax);
        System.out.println("-".repeat(10));

    }
}
