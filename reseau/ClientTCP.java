package reseau;

import java.io.*;
import java.net.*;
import java.util.Scanner;

class ClientTCP  {

   public static void main(String[] args) throws IOException 
   {
      // récupère l'adresse internet du host
      // null permet de tester les applis sur une machine unique 
      InetAddress addr = InetAddress.getByName(null);
      Socket socket = new Socket(addr, 8888); // utilisation du m�me no de port�
      try (// récupération du flux d'entrée
            Scanner  in = new Scanner( socket.getInputStream());
            // récupération du flux de sortie
            PrintWriter out = new PrintWriter( socket.getOutputStream(),true);)
      {
         System.out.println("chez le client,  socket = " + socket);
         Scanner scanKeyboard = new Scanner(System.in); 
         String ligne = ""; 
         int no = 1;
         while (!ligne.equalsIgnoreCase("FIN")) {
            System.out.println("client -> entrez une chaine : "  );
            ligne = scanKeyboard.nextLine();
            out.println(""+no+" "+ ligne);
            String str = in.nextLine();
            System.out.println("client -> reçu du serveur  :" + str);
            no++;
         }
         scanKeyboard.close();
      } 
      finally 
      {
         System.out.println("client-> fermeture...");
         socket.close();
      }
   }

}
