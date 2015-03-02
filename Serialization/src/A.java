import java.io.Serializable;

public class A extends B implements Serializable {

    private static final long serialVersionUID = 1L;

    int i;
    float f;

    public A(int i, float f, double d) {
	super(d);
	this.i = i;
	this.f = f;
    }
    
    public double getB(){
	return d;
    }
}
