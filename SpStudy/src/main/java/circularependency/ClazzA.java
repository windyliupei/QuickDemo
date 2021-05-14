package circularependency;

public class ClazzA {
    //private ClazzB b = new ClazzB();


    public ClazzB getB() {
        return b;
    }

    public void setB(ClazzB b) {
        this.b = b;
    }

    //
    private ClazzB b = null;
}
