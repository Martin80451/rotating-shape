import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class PiirtoAlusta extends JPanel {

    boolean piirtoAloitettu = false;
    double pisteYmpyrällä2_x;
    double pisteYmpyrällä2_y;
    List<Point2D.Double> pisteet;

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

            ActionListener piirronAnimoija = new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    g2d.setColor(Color.CYAN);
                }
            };
            Timer ajastin = new Timer(1000, piirronAnimoija);

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

                this.pisteYmpyrällä2_x = keskipiste_x + (halkaisija / 2) * Math.cos(kulma);
                this.pisteYmpyrällä2_y = keskipiste_y + (halkaisija / 2) * Math.sin(kulma); 
                g2d.draw(new Line2D.Double(pisteYmpyrällä1_x, pisteYmpyrällä1_y, pisteYmpyrällä2_x, pisteYmpyrällä2_y));
                int pituus = (int) Math.hypot(this.pisteYmpyrällä2_x - pisteYmpyrällä1_x, this.pisteYmpyrällä2_y - pisteYmpyrällä2_x);
                this.pisteet = pisteidenLaskenta(pisteYmpyrällä1_x, pisteYmpyrällä1_y, this.pisteYmpyrällä2_x, this.pisteYmpyrällä2_y, pituus);
                System.out.println("Jokaisen viivan pisteet jaettuna animointia varten: " + this.pisteet);
                pisteYmpyrällä1_x = this.pisteYmpyrällä2_x;
                pisteYmpyrällä1_y = this.pisteYmpyrällä2_y;
            }
            
            // System.out.println(pisteYmpyrällä1_x + " " + pisteYmpyrällä1_y + " " + pisteYmpyrällä2_x + " " + pisteYmpyrällä2_y);

        }
    }

    public void alustaMuoto() {
        System.out.println("tulee alustaMuotoon");
        this.piirtoAloitettu = true;
        repaint();
    }

    private List<Point2D.Double> pisteidenLaskenta(double x1, double y1, double x2, double y2, int n) {
        List<Point2D.Double> pisteet = new ArrayList<>(Math.max(0,n));
        if (n <= 1) {
            pisteet.add(new Point2D.Double(x1, y1));
            return pisteet;
        }
        for (int i = 0; i < n; i++) {
            double t = (double) i / (n - 1);
            double x = x1 + t * (x2 - x1);
            double y = y1 + t * (y2 - y1);
            pisteet.add(new Point2D.Double(x, y));
        }
        return pisteet;
    }

    // private void piirraViivat(double luku) {
    // }
    
}
