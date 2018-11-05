package reseau;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class ServeurTCP {

   public static void main(String args[]) {
      // création d'une socket serveur
      try (ServerSocket serveur = new ServerSocket(8888); )
      {
         System.out.println("Socket lancee : " + serveur);
         try(Socket socket = serveur.accept();)	      
         {// récupération du flux d'entrée
            try(Scanner  in = new Scanner( socket.getInputStream());) 
            {
               // Attendre une connection
               System.out.println("Connection acceptee: " + socket);

               // récupération du flux de sortie
               PrintWriter out = new PrintWriter( socket.getOutputStream(),true);

               // attente d'une donnee
               String str="";
               while(!(str.equalsIgnoreCase("FIN")))
               {
                  str = in.nextLine();
                  String strDecode[] = str.split(" ", 2);
                  int no = Integer.parseInt(strDecode[0]);
                  str = strDecode[1];
                  System.out.println("serveur -> message " + no + " reçu : " + str);
                  out.println("bien reçu le message "+ no);
               }
            }
         }
      }
      catch (IOException e) { e.printStackTrace();}
   }
}

