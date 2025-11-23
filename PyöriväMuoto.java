import javax.swing.*;

public class PyöriväMuoto extends SwingWorker<Void, String> {
    PiirtoAlusta alusta;
    
    public PyöriväMuoto(PiirtoAlusta alusta) {
        this.alusta = alusta;
    }
    
    @Override
    protected Void doInBackground() {
        this.alusta.alustaMuoto();
        return null;
    }
}
