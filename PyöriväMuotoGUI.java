import javax.swing.*;
import java.awt.*;

public class PyöriväMuotoGUI {
    private PyöriväMuoto pyörimisGeneraattori;
    private Thread generointiThread;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PyöriväMuotoGUI());
    }

    private PyöriväMuotoGUI() {
        JFrame ikkuna = new JFrame("Pyörivä Muoto");
        ikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ikkuna.setSize(600, 400);

        JButton pyöritäPainike = new JButton("Pyöritä");
        JButton pysäytäPainike = new JButton("Pysäytä");
        PiirtoAlusta alusta = new PiirtoAlusta();

        pyöritäPainike.addActionListener(e -> {
            pyörimisGeneraattori = new PyöriväMuoto(alusta);
            generointiThread = new Thread(pyörimisGeneraattori);
            generointiThread.start();
        });

                
        pysäytäPainike.addActionListener(e -> {
        	if (pyörimisGeneraattori != null && !pyörimisGeneraattori.isDone()) {
        		pyörimisGeneraattori.cancel(true);
        	}
        });

        JPanel painikeAlue = new JPanel();
        painikeAlue.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        painikeAlue.add(pyöritäPainike);
        painikeAlue.add(pysäytäPainike);    
        ikkuna.add(painikeAlue, BorderLayout.SOUTH);
        ikkuna.add(alusta, BorderLayout.CENTER);
        ikkuna.setVisible(true);
        System.out.println(alusta.getSize());
    }
}
