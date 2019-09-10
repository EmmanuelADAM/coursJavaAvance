package reseau;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServeurTCP {

    public static void main(String args[]) throws IOException {
        // création d'une socket serveur
        try (ServerSocket serveur = new ServerSocket(8888);) {
            System.out.println("Socket lancee : " + serveur);
            try (
                    // Attendre une connection
                    Socket socket = serveur.accept();
                    // récupération du flux d'entrée
                    Scanner in = new Scanner(socket.getInputStream());
                    // récupération du flux de sortie
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);)
            {
                System.out.println("Connection acceptee: " + socket);
                // attente d'une donnee
                String str = "";
                while (!(str.equalsIgnoreCase("FIN"))) {
                    str = in.nextLine();
                    String strDecode[] = str.split(" ", 2);
                    int no = Integer.parseInt(strDecode[0]);
                    str = strDecode[1];
                    System.out.println("serveur -> message " + no + " reçu : " + str);
                    out.println("bien reçu le message " + no);
                }
            }
        }
    }
}

