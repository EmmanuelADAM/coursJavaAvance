package swing.petitPanneau2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class JFenetre extends JFrame
{
    public JFenetre()
    {

        setTitle("Un exemple simple");
        setSize(400, 300);
        setLocationRelativeTo(null);

        MonJPanel lePanneau = new MonJPanel(this);
        Container support = getContentPane();
        support.add(lePanneau);


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
            @Override
            public void windowActivated(WindowEvent e){
                lePanneau.addLigne("fenetre en action");
            }
            @Override
            public void windowDeactivated(WindowEvent e){
                lePanneau.addLigne("la fenetre perd le focus... ");
            }
        });
        setVisible(true);


        var couleur = JColorChooser.showDialog(null, "Choisissez une couleur", Color.BLUE);
        System.err.println("couleur choisie = " + couleur);

    }

    public static void main(String[] args)
    {
        JFenetre fen = new JFenetre();
    }

}
