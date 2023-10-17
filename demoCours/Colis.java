package demoCours;

public enum Colis {
    TYPE1(20) , TYPE2(30) , TYPE3(40) ;
    int poids ;

    Colis(int poids){this.poids = poids;}

    public int getPoids (){return poids ;}
}
