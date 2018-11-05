package reseau;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServeurUDP{
   public static void main(String arg[])
   {
      try(DatagramSocket socketServeur = new DatagramSocket(1234))
      {
         // Serveur
         DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);

         int nbRecept = 0;
         while(nbRecept<1000)
         {
            socketServeur.receive(packet);
            byte[] b = packet.getData();
            String ch = new String(b);
            System.out.println("serveur -> j'ai reçu le paquet no " + nbRecept + ": " + ch);
            nbRecept++;
         }
      }
      catch(Exception e){System.err.println(e.getMessage());}
   }
}
