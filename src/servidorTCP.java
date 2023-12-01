import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class servidorTCP {
    static Preguntas[] preguntas = new Preguntas[5];

    public static void main(String[] args) {
        // Crear preguntas y respuestas
        crearPreguntas();

        // Crear socket del servidor
        try {
            ServerSocket socket_servidor = new ServerSocket(5000);
            System.out.println("Esperando conexion");

            while (true) {
                // Esperar y aceptar conexiones de clientes
                Socket socket_cliente = socket_servidor.accept();
                System.out.println("Cliente conectado: " + socket_cliente.getInetAddress().getHostAddress());

                // Crear un hilo para manejar la conexión con el cliente
                hiloCliente hilo = new hiloCliente(socket_cliente, preguntas);
                hilo.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Preguntas[] crearPreguntas() {
        preguntas[0] = new Preguntas("¿Quién fue el último campeón del fútbol Ecuatoriano?",
                new String[]{"Barcelona", "Aucas", "Liga de Quito"}, 2);

        preguntas[1] = new Preguntas("¿Qué equipo tiene más campeonatos nacionales?",
                new String[]{"Liga de Quito", "Barcelona", "Emelec"}, 2);

        preguntas[2] = new Preguntas("¿Cuál es el primer campeón del fútbol Ecuatoriano?",
                new String[]{"Emelec", "Liga de Quito", "Barcelona"}, 1);

        preguntas[3] = new Preguntas("¿Cuál es el único equipo Ecuatoriano en ganar la Copa Libertadores de América?",
                new String[]{"Barcelona", "Emelec", "Liga de Quito"}, 3);

        preguntas[4] = new Preguntas("¿Qué equipo tiene más hinchada en el Ecuador?",
                new String[]{"Barcelona", "Liga de Quito", "Emelec"}, 1);

        return preguntas;
    }
}
