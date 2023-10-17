package demoCours;

public class Couple<T1, T2> {
    T1 v1;
    T2 v2;
    Couple(){}
    Couple(T1 v1, T2 v2){this.v1 = v1; this.v2 = v2;}
    public T1 getV1(){return v1;}
    public T2 getV2(){return v2;}
    public void setV1(T1 v1){this.v1 = v1;}
    public void setV2(T2 v2){this.v2 = v2;}

    public String toString() {
        return("("+ v1.toString() + "||" + v2.toString() + ")"); }
}