import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class PiirtoAlusta extends JPanel {

    boolean piirtoAloitettu = false;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.MAGENTA);
        if (piirtoAloitettu) {
            int leveys = getWidth();
            int korkeus = getHeight();
            int marginaali = 10;
            int halkaisija = Math.min(leveys, korkeus) - marginaali * 2;
            if (halkaisija < 0) halkaisija = 0;
            int x = (leveys - halkaisija) / 2;
            int y = (korkeus - halkaisija) / 2;
            System.out.println("piirretään " + leveys + "x" + korkeus + " soikio " + x + "," + y + " koko " + halkaisija);
            g2d.drawOval(x, y, halkaisija, halkaisija);
            g2d.setStroke(new BasicStroke(2));

            double keskipiste_x = leveys / 2;
            double keskipiste_y = korkeus / 2;
            double kulma = 195 * (Math.PI / 180);
            double pisteYmpyrällä1_x = keskipiste_x + (halkaisija / 2) * Math.cos(kulma);
            double pisteYmpyrällä1_y = keskipiste_y + (halkaisija / 2) * Math.sin(kulma);

            for (int i = 0; i < 6; i++) {
                if (i == 0 || i == 3) {
                    kulma += Math.PI;

                }
                else {
                    if (i <= 2) {
                        kulma -= 105 * (Math.PI / 180);
                    }
                    else {
                        kulma += 105 * (Math.PI / 180);
                    }
                }

                double pisteYmpyrällä2_x = keskipiste_x + (halkaisija / 2) * Math.cos(kulma);
                double pisteYmpyrällä2_y = keskipiste_y + (halkaisija / 2) * Math.sin(kulma); 
                g2d.draw(new Line2D.Double(pisteYmpyrällä1_x, pisteYmpyrällä1_y, pisteYmpyrällä2_x, pisteYmpyrällä2_y));
                pisteYmpyrällä1_x = pisteYmpyrällä2_x;
                pisteYmpyrällä1_y = pisteYmpyrällä2_y;
            }
            
            // System.out.println(pisteYmpyrällä1_x + " " + pisteYmpyrällä1_y + " " + pisteYmpyrällä2_x + " " + pisteYmpyrällä2_y);

        }
    }

    public void alustaMuoto() {
        System.out.println("tulee alustaMuotoon");
        this.piirtoAloitettu = true;
        repaint();
    }

    // private void piirraViivat(double luku) {
    // }
    
}
