package util;

import java.io.Serializable;
import java.util.Objects;

public class Personne implements Cloneable, Comparable<Personne>, Serializable {
	String prenom;
	int age;
	Region region;
	
	Personne(){prenom="";}
	
	Personne( String _prenom){
		prenom=_prenom;
	}

	Personne(String _prenom, int _age){
		this(_prenom);
		age = _age;
	}


	Personne(String _prenom, int _age, Region _region){
		this( _prenom, _age);
		region = _region;
	}


	@Override
	protected Personne clone()  {
		Object theClone = null;
		try { theClone = super.clone();	 }
		catch(Exception e) {e.printStackTrace();  }
		return (Personne)theClone;
	}
	
	public String toString()
	{
		StringBuilder retour = new StringBuilder("(");
		if(prenom != null) retour.append(prenom).append(" ");
		if(age>=0) retour.append("| age = ").append(age);
		if(region != null) retour.append("| region = ").append(region);
		retour.append(")");
		return retour.toString();
	}

	/**ici deux personnes sont egales si elles ont le meme prenom*/
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Personne personne = (Personne) o;
		return Objects.equals(prenom, personne.prenom);
	}

	/**ici comparaison sur l'age, tri par age decroissant
	 * @return <0 si o.age<age, >0 si o.age>age, 0 sinon*/
	@Override
	public int compareTo(Personne o) {
		return o.age - age;
	}

	public String getPrenom() {  return prenom; }
	public void setPrenom(String prenom) { this.prenom = prenom; }

	public int getAge() { return age; }
	public void setAge(int age) { this.age = age; }

	public Region getRegion() { return region; }
	public void setRegion(Region region) { this.region = region; }


}