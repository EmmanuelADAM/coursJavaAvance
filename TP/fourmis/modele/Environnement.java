package modele;

import application.SimuFourmis;

import java.util.ArrayList;


/**
 * matrice des cellules du jeu de la vie
 *
 * @author Emmanuel Adam
 */
public class Environnement {
    /**grille des parcelles de terrain*/
    Parcelle[][] grille;
    /** taille de la grille */
    int taille;

    /**les fourmis presentes*/
    ArrayList<Fourmi> lesFourmis;

    /**lien vers l'application graphiquue*/
    SimuFourmis application;


    /**
     * constructeur initialisant l'application et la taille,
     * la grille et la liste des fourmis
     */
    public Environnement(SimuFourmis application, int taille) {
        this.application = application;
        this.taille = taille;
        grille = new Parcelle[taille][taille];
        lesFourmis = new ArrayList<>();
        init();
    }


    /**
     * remplit la grille de parcelles de type terrain
     */
    void init() {
        for (int i = 0; i < taille; i++)
            for (int j = 0; j < taille; j++) {
                grille[i][j] = new Parcelle(grille, i, j, TypeParcelle.Terrain);
            }
    }

    /**
     * place un nid au centre de la grille
     */
    public void initNid() {
        //position du nid au centre
        int poxXNid = taille/2;
        int poxYNid = taille/2;
        //diffuser une odeur de nid qui decroit de 1 à partir du centre, vers env. 0 aux coins
        for(int x=0; x<taille; x++)
            for(int y=0; y<taille; y++)
                //TODO: COMPLETER (changer le 0 par la bonne valeur)
                grille[x][y].setOdeurNid(0);
        //
        for (int i=1; i<5; i++)
            for (int j=0; j<360; j++)
            {
                int x = (int)(i*Math.cos(360*Math.PI / j)) + poxXNid;
                int y = (int)(i*Math.sin(360*Math.PI / j)) + poxYNid;
                grille[x][y].setType(TypeParcelle.Nid);
            }

    }

    /**ajoute une zone de nourriture aux point nx,ny*/
    public void addNourriture(int nx, int ny) {
        for (int i=1; i<4; i++)
            for (int j=0; j<360; j++)
            {
                int x = (int)(i*Math.cos(360*Math.PI / j)) + nx;
                int y = (int)(i*Math.sin(360*Math.PI / j)) + ny;
                grille[x][y].setType(TypeParcelle.Nourriture);
            }
    }

    /**demande l'evaporation dans chaque parcelle*/
    public void evaporer()
    {
        for(Parcelle[] ligne:grille)
            for(Parcelle cell:ligne)
                cell.evaporer();
    }

    /**demande la diffusion de pheromone a chaque parcelle*/
    public void diffuser()
    {
        for(Parcelle[] ligne:grille)
            for(Parcelle cell:ligne)
                cell.diffuser();
    }

    /**demande au cerle lie a la fourmi de se deplacer dans le point identifie par la parcelle
     * @param f fourmi qui se deplace*/
    public void bouger(Fourmi f)
    {
        application.move(f.circle, f.parcelle.x, f.parcelle.y);
    }

    /**
     * demande a chaque fourmi d'evoluer
     */
    public void avancer() {
        lesFourmis.forEach(Fourmi::evoluer);
    }

    /**demande si possible a la parcelle  de retirer un dose de nourriture
     * */
    void manger(Parcelle p)
    {
        if(p.getNbNourriture()>0)  p.takeNourriture();
    }


    /**cree et ajoute une fourmi initialisee dans la case x,y
     * @return la fourmis creee*/
    public Fourmi addFourmi(int x, int y)
    {
        Fourmi f = new Fourmi(this, x, y);
        lesFourmis.add(f);
        return f;
    }

    /**
     * @return la grille des parcelles
     */
    public Parcelle[][] getGrille() {
        return grille;
    }


}
