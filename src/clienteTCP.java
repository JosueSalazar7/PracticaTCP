import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class clienteTCP {
    public static void main(String[] args) {
        try {
            Socket socketCliente = new Socket("192.168.1.8", 5000);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            PrintWriter salida = new PrintWriter(socketCliente.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                // Leer pregunta del servidor
                while (!entrada.ready()) {
                    // Esperar hasta que haya datos disponibles
                }
                String pregunta = entrada.readLine();
            
                // Verificar si el juego ha terminado
                if (pregunta.startsWith("Tu puntaje final es:")) {
                    System.out.println(pregunta);
                    break;
                }
            
                // Mostrar la pregunta al usuario
                System.out.println("Pregunta: " + pregunta);
            
                // Leer opciones de respuesta del servidor
                for (int i = 0; i < 3; i++) {
                    String opcion = entrada.readLine();
                    System.out.println(opcion);
                }
            
                // Obtener la respuesta del usuario
                System.out.print("Elija su respuesta (1, 2, o 3): ");
                String respuestaUsuario = scanner.nextLine();
            
                // Enviar la respuesta al servidor
                salida.println(respuestaUsuario);
            
                // Recibir y mostrar el resultado
                String resultado = entrada.readLine();
                System.out.println("Resultado: " + resultado);
            }
            
            // Cerrar el socket
            socketCliente.close();
            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

