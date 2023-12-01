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
            Socket socketCliente = new Socket("localhost", 5000);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            PrintWriter salida = new PrintWriter(socketCliente.getOutputStream(), true);

            while (true) {
                // Leer pregunta del servidor
                String pregunta = entrada.readLine();
                if (pregunta == null || pregunta.equals("Fin")) {
                    // Si la pregunta es nula o igual a "Fin", significa que el juego ha terminado
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
                String respuestaUsuario = Scanner.nextLine();

                // Enviar la respuesta al servidor
                salida.println(respuestaUsuario);

                // Recibir y mostrar el resultado
                String resultado = entrada.readLine();
                System.out.println("Resultado: " + resultado);
            }

            // Recibir y mostrar el puntaje final
            String puntajeFinal = entrada.readLine();
            System.out.println("Puntaje final: " + puntajeFinal);

            // Cerrar el socket después de salir del bucle
            socketCliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
}
