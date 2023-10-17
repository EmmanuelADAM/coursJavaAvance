package demoCours;

public class Etudiant extends Personne{
    String formation;

    public Etudiant() { formation = "L3";
    }

    public Etudiant(String nom, int age, String formation) {
        super(nom, age);
        this.formation = formation;
    }

    @Override
    void inscrire() {
        System.out.println("je m'inscris avec une clé");
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "formation='" + formation + '\'' +
                "nom='" + nom + '\'' +
                ", age=" + age +
                '}';
    }
}
