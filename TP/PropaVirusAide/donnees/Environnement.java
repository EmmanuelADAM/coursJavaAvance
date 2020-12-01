package donnees;

import application.SimuVirus;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
/**
 * environnement de la simulation
 * contient la liste de personnes, plus tard des lieux
 * @author emmanueladam
 * */
public class Environnement {

    int dimx;
    int dimy;

    /**lien vers le controle graphique*/
    SimuVirus simu;

    /**les personnes se deplacant*/
    List<Personne> personnes;

    public Environnement()
    {
        personnes = new ArrayList<>();
    }

    public Environnement(int dimx, int dimy)
    {
        this();
        this.dimx = dimx;
        this.dimy = dimy;
    }

    public Environnement(int dimx, int dimy, SimuVirus simu) {
        this(dimx, dimy);
        this.simu = simu;
    }


    /**demande à chaque personne de 'errer'*/
    public void bouger()
    {
        personnes.forEach(Personne::wander);
    }

    /**demande à chaque personne de faire une pause et de détecter si presence de contagieux*/
    public void avancerTemps()
    {
        personnes.forEach(Personne::detecteContagion);
    }

    public void addPersonne(Personne p)
    {
        personnes.add(p);
    }

    /**alterne l'etat de la personne en x,y. fonction lancée par l'application*/
    public void switchContagieux(int x, int y)
    {
        Point pos= new Point(x,y);
        personnes.stream().filter(p->p.pos.equals(pos)).findFirst().ifPresent(p->p.setContagieux(!p.isContagieux()));
    }

    /**propage l'info sur le changement de position d'une personne au controleur graphique*/
    public void updatePos(Personne p)
    {
        simu.updatePos(p.getImg(),  p.pos);
    }

    public List<Personne> getPersonnes() {
        return personnes;
    }

    public  int getDimx() {
        return dimx;
    }
    public  int getDimy() {
        return dimy;
    }

    public int getNbInfectees()
    {
        return (int) personnes.stream().filter(Personne::isContagieux).count();
    }
}
