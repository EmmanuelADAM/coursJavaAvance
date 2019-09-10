package reseau;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServeurUDP {
    public static void main(String arg[]) throws IOException {
        // creation d'une socket pour recevoir des paquets
        try (DatagramSocket socketServeur = new DatagramSocket(1234)) {
            
            //modele de paquet (taille de 1024 byte)
            DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
            
            int nbRecept = 0;
            while (nbRecept < 1000) {
                //attente bloquante d'un paquet
                socketServeur.receive(packet);
                byte[] b = packet.getData();
                String ch = new String(b);
                System.out.println("serveur -> j'ai reçu le paquet no " + nbRecept + ": " + ch);
                nbRecept++;
            }
        }
    }
}
