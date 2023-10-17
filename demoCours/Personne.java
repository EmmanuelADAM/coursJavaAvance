package demoCours;

abstract public class Personne {
    String nom;
    int age;

    public Personne() {
        age = 18;
        nom="";
    }

    public Personne(String nom, int age) {
        this.nom = nom;
        this.age = age;
    }

    abstract void inscrire();

    public String getNom() {
        return nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Personne{" +
                 nom + ", " +  age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Personne personne = (Personne) o;

        if (age != personne.age) return false;
        return nom.equals(personne.nom);
    }


}
