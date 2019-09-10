package reseau;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

class ClientTCP  {

   public static void main(String[] args) throws IOException 
   {
        // récupère l'adresse internet du host
        // null permet de tester les applis sur une machine unique
        InetAddress addr = InetAddress.getByName(null);
      //la syntaxe try-with suivante va clore tout ce qui a été ouvert en sortie de bloc
        try (
                Socket socket = new Socket(addr, 8888);
                // récupération du flux d'entrée
                Scanner  in = new Scanner( socket.getInputStream());
                // récupération du flux de sortie
                PrintWriter out = new PrintWriter( socket.getOutputStream(),true);
                Scanner scanKeyboard = new Scanner(System.in);)
        {
            System.out.println("chez le client,  socket = " + socket);
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
        }
    }

}
