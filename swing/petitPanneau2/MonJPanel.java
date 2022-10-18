package swing.petitPanneau2;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

class MonJPanel extends JPanel
{
    private JFenetre fen = null;
    JTextArea zoneTexte;
    public MonJPanel(JFenetre _fen)
    {
        fen = _fen;
        setLayout(new BorderLayout());

        JButton bOk = new JButton("OK ...");
        add(bOk, BorderLayout.SOUTH);

        zoneTexte = new JTextArea();
        add(zoneTexte, BorderLayout.CENTER);
        zoneTexte.append("Coucou !!!!\n");
        zoneTexte.setEditable(false);
        //écoute événement de type action
        bOk.addActionListener(e -> fen.dispose());

        //écoute événement de type souris
        zoneTexte.addMouseListener(new MouseInputAdapter() {
            public void mouseEntered(MouseEvent e) { addLigne("la souris est sur la zone de texte");}
            public void mouseExited(MouseEvent e)  { addLigne("la souris est sortie de la zone de texte");}
        });

        //écoute événement de type touche de clavier
        zoneTexte.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.err.println("passe par ici");
                addLigne("touche captee = " + e.getKeyChar());
            }
        });
    }


    public void addLigne(String ligne){
        if(zoneTexte!=null) zoneTexte.append(ligne+"\n");
    }

}