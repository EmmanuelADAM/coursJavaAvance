package reseau;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientUDP {
    public static void main(String arg[]) throws Exception {
        //null correspond à l'adresse du pc local
        InetAddress addr = InetAddress.getByName(null);
        //creation d'une socket pour emission de paquets
        try (DatagramSocket socketLocale = new DatagramSocket()) {
            System.out.println("client -> j'envoie 1000 paquets à " + addr.getHostName());
            for (int i = 0; i < 1000; i++) {
                String chaine = i + ": ceci est un long un message pour verifier la stabilite des chaines de caracteres par UDP";
                byte[] data = chaine.getBytes();
                //creation d'un paquet, precision du contenu, de l'adresse et du port
                DatagramPacket packet = new DatagramPacket(data, data.length, addr, 1234);
                //envoi du paquet
                socketLocale.send(packet);
                Thread.sleep(1);
            }
            System.out.println("client -> j'ai fini");
        }
    }
}
