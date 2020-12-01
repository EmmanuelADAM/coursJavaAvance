package donnees;

import gui.PersonneImg;

import java.awt.*;
import java.util.Objects;

/**represente une personne : sa position , son etat, ...
 * @author emmanueladam
 * */
public class Personne {
    /**position actuelle*/
    Point pos;
    /**position domicile*/
    Point posDomicile;
    /**malade x*/
    boolean malade;
    /**contagieux x*/
    boolean contagieux;
    /** representation graphique*/
    PersonneImg img;
    /**Environnement */
    Environnement env;
    /**duree de contact*/
    int dureeContact;
    /**id*/
    int id = 0;
    /**nb*/
    static int nb = 0;

    public Personne(Point pos)
    {
        this.pos = this.posDomicile = pos;
        id = ++nb;
    }

    public Personne(Point pos, Environnement env)
    {
        this(pos);
        this.env = env;
    }

    public Point getPos() {
        return pos;
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }

    public void moveTo(int x, int y)
    {
        pos.x = x;
        pos.y = y;
    }


    /**choix d'une position au hasard, en informe l'environnement*/
    public void wander()
    {
        int x = (int) (Math.random() * env.dimx);
        int y = (int) (Math.random() * env.dimy);
        moveTo(x,y);
        env.updatePos(this);
    }



    /**incremente la duree de contact si un malade se trouve dans la meme place, ou la met à 0 sinon*/
    public void detecteContagion()
    {
        boolean cas = env.personnes.stream().anyMatch(p-> Objects.equals(p.pos, pos) && p.contagieux);
        if(cas) dureeContact++;
        else dureeContact = 0;
        if(dureeContact>3)
        {
         setContagieux(true);
         img.setInfected();
        }
    }

    public Point getPosDomicile() {
        return posDomicile;
    }

    public void setPosDomicile(Point posDomicile) {
        this.posDomicile = posDomicile;
    }

    public boolean isMalade() {
        return malade;
    }

    public void setMalade(boolean malade) {
        this.malade = malade;
    }

    public boolean isContagieux() {
        return contagieux;
    }

    public void setContagieux(boolean contagieux) {
//        if(contagieux)  System.out.println("moi " + this + " je suis contagieux !!");
//        else System.out.println("moi " + this + " je ne suis plus contagieux !!");
        this.contagieux = contagieux;
    }

    public PersonneImg getImg() {
        return img;
    }

    public void setImg(PersonneImg img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Personne{" +
                "pos=" + pos +
                ", posDomicile=" + posDomicile +
                ", malade=" + malade +
                ", contagieux=" + contagieux +
                ", id=" + id +
                '}';
    }
}
