import java.beans.PropertyChangeSupport;

public class Delegate extends PropertyChangeSupport {
    private WorkArea workArea;

    public Delegate(Object source, WorkArea workArea) {
        super(source);
        this.workArea = workArea;
    }
}
