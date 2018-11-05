package reseau;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientUDP {
   public static void main(String arg[])
   {
      try
      {
         //null correspond à l'adresse du pc local
         InetAddress addr = InetAddress.getByName(null);
         try(DatagramSocket socketLocale = new DatagramSocket())
         {
            System.out.println("client -> j'envoie 1000 paquets à " + addr.getHostName());
            for(int i=0; i<1000; i++) 
            {
               String chaine = i + ": ceci est un long un message pour verifier la stabilite des chaines de caracteres par UDP";
               byte[] data = chaine.getBytes();
               DatagramPacket packet = new DatagramPacket(data, data.length, addr, 1234);           
               socketLocale.send(packet);
               Thread.sleep(1);
            }
            System.out.println("client -> j'ai fini");

         }
      }
      catch(Exception e){System.err.println(e.getMessage());}
   }
}
