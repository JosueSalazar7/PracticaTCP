import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class servidorTCP {
    public static void main(String[] args) {
        //Crear socket del servidor
        try {
            ServerSocket socket_servidor = new ServerSocket(5000);
            System.out.println("Eperando conexion");
            while (true){
                //Esperar y aceptar conexiones de clientes
                Socket socket_cliente = socket_servidor.accept();
                System.out.println("Cliente conectado: "+socket_cliente.getInetAddress().getHostAddress());

                //Crear un hilo para manejar la conexion con el cliente
                hiloCliente hilo = new hiloCliente(socket_cliente);
                hilo.start();
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
