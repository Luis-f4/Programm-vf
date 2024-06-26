import java.awt.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class AnalogeUhr {

    public static void main(String[] args) throws InterruptedException {
        // Creating instance of JFrame
        JFrame frame = new JFrame();
        frame.setLayout(new GridBagLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Anordnen der Elemente
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding around components
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        //Text Label für Uhrzeit erstellen
        JLabel text = new JLabel("null 0:00:00");
        text.setFont(new Font("Vodafone", Font.BOLD, 48));
        frame.add(text, gbc);

        //Rahmen um die Uhr setzen
        gbc.gridy = 1;
        Uhr uhr = new Uhr();
        uhr.setBorder(new LineBorder(Color.BLACK, 5));
        uhr.setPreferredSize(new Dimension(500, 500));
        frame.add(uhr, gbc);

        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

        //Uhrzeit abfragen
        while (true) {
            ZonedDateTime uhrzeit = Instant.now().atZone(ZoneId.systemDefault());

            //Unterschiedliche Einheiten erhalten
            int sekunden = uhrzeit.get(ChronoField.SECOND_OF_MINUTE);
            int minuten = uhrzeit.get(ChronoField.MINUTE_OF_HOUR);
            int stunde = uhrzeit.get(ChronoField.HOUR_OF_DAY);

            //Text Label mit Uhrzeit befüllen
            text.setText(String.format("%s %02d:%02d:%02d", ZoneId.systemDefault().getId(), stunde, minuten, sekunden));
            uhr.set(stunde % 12, minuten, sekunden);

            Thread.sleep(1000);
        }
    }

    //Je ein Zeiger 
    static class Uhr extends JPanel {
        private final Zeiger sekundenZeiger;
        private final Zeiger minutenZeiger;
        private final Zeiger stundenZeiger;

        //Zeiger anpassen: Dicke, Farbe, Position
        public Uhr() {
            setLayout(null);

            sekundenZeiger = new Zeiger(Color.RED, 200, 5);
            minutenZeiger = new Zeiger(Color.BLUE, 180, 10);
            stundenZeiger = new Zeiger(Color.GREEN, 150, 15);

            sekundenZeiger.setBounds(0, 0, 500, 500);
            minutenZeiger.setBounds(0, 0, 500, 500);
            stundenZeiger.setBounds(0, 0, 500, 500);

            add(sekundenZeiger);
            add(minutenZeiger);
            add(stundenZeiger);
        }

        //Rotation mit Zeit berechnen
        public void set(int stunde, int minute, int sekunde) {
            sekundenZeiger.setRotation(TimeUnit.SECONDS, 60, sekunde);
            minutenZeiger.setRotation(TimeUnit.MINUTES, 60, minute * 60 + sekunde);
            stundenZeiger.setRotation(TimeUnit.HOURS, 12, stunde * 60 * 60 + minute * 60 + sekunde);
        }
    }


    public static class Zeiger extends JComponent {
        private double grad = 0;
        private final Color farbe;
        private final int laenge;
        private final int weite;

        public Zeiger(Color farbe, int laenge, int weite) {
            this.farbe = farbe;
            this.laenge = laenge;
            this.weite = weite;
        }

        //Rotatsionswinkel
        public void setRotation(TimeUnit timeUnit, int max, int wert) {
            grad = 360.0 / timeUnit.toSeconds(max) * wert - 90;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            double radiant = Math.toRadians(grad);
            int x1 = getWidth() / 2;
            int y1 = getHeight() / 2;

            int x2 = (int) Math.round(x1 + laenge * Math.cos(radiant));
            int y2 = (int) Math.round(y1 + laenge * Math.sin(radiant));

            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(farbe);
            g2d.setStroke(new BasicStroke(weite));
            g2d.drawLine(x1, y1, x2, y2);
        }
    }
}