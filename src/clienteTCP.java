import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Scanner;

public class clienteTCP {
    public static void main(String[] args) {
        Scanner Scanner = new Scanner(System.in);
        //Crear un socket para conectarse al servidor

        try {
            Socket socket_cliente = new Socket("172.31.118.80", 5000);
            while(true) {
                //Enviar datos al cliente
                String mensaje;
                System.out.print("Escriba el mensaje: ");


                //Crear buffers para escribir y enviar datos al cliente
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket_cliente.getInputStream()));
                PrintWriter salida = new PrintWriter(socket_cliente.getOutputStream(), true);

                mensaje = Scanner.nextLine();


                if (mensaje.equalsIgnoreCase("chao")) {
                    System.out.println("Desconectando...");
                    break;
                }
                salida.println(mensaje);
                //Leer datos recibidos desde el servidor
                String datos_recibidos = entrada.readLine();
                System.out.println("Mensaje recibido: " + datos_recibidos);

            }
            // Cerrar el socket después de salir del bucle
            socket_cliente.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        }
}
