package servidortipo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorTipo {

    public static void main(String[] args) {
        // Puerto del servidor
        int port = 12345;
        // Variables
        int numeroAleatorio = numeroAleatorio(1,100);
        String answer;
        boolean adivinado = false;
        
        System.out.println("Numero aleatorio: "+numeroAleatorio);
        
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Servidor iniciado en el puerto "+port+".");
            while(true){
                //Aceptar una conexión
                Socket client = server.accept();
                System.out.println("Cliente conectado: "+client.getInetAddress());
                //Leer y responde al cliente
                BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter output = new PrintWriter(client.getOutputStream(),true);
                
                int numeroSaludo = 0;
                while(adivinado == false){
                
                    String message = input.readLine();
                    System.out.println("Cliente: "+message);
                    int messageInt = Integer.parseInt(message);
                    if(messageInt == numeroAleatorio){
                        answer = "¡Correcto!";
                        adivinado = true;
                        System.out.println("Servidor: ¡Correcto!");
                    } else if (messageInt > numeroAleatorio) {
                        answer = "Es menor";
                        System.out.println("Servidor: Es menor");
                    } else{
                       answer = "Es mayor";
                       System.out.println("Servidor: Es mayor");
                    }
                    output.println(answer);
                }
                client.close();
                System.out.println("El cliente se desconectó");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static int numeroAleatorio(int valorMinimo, int valorMaximo){
        Random r = new Random();
        return valorMinimo + r.nextInt(valorMaximo - valorMinimo +1);
    }
    
}
