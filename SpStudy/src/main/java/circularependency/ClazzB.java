package circularependency;

public class ClazzB {
    public ClazzA getA() {
        return a;
    }

    public void setA(ClazzA a) {
        this.a = a;
    }

    //private ClazzA a = new ClazzA();
    private ClazzA a = null;
}
