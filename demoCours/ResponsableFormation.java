package demoCours;

public class ResponsableFormation extends Personne{
    int id;

    public ResponsableFormation(String nom, int age, int id) {
        super(nom, age);
        this.id = id;
    }

    void inscrire()
    {
        System.out.println("je m'inscris et définis une clé");
    }

}
