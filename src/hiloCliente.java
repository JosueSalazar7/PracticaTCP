import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class hiloCliente extends Thread {
    private Socket socketCliente;
    private Preguntas[] preguntas;
    private int puntaje;

    public hiloCliente(Socket socketCliente, Preguntas[] preguntas) {
        this.socketCliente = socketCliente;
        this.preguntas = preguntas;
        this.puntaje = 0;
    }

    @Override
    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            PrintWriter salida = new PrintWriter(socketCliente.getOutputStream(), true);

            for (Preguntas pregunta : preguntas) {
                // Enviar la pregunta al cliente
                salida.println(pregunta.getPregunta());

                // Enviar opciones al cliente
                for (int i = 0; i < pregunta.getOpciones().length; i++) {
                    salida.println(i + 1 + ". " + pregunta.getOpciones()[i]);
                }

                // Leer la respuesta del cliente
                int respuestaCliente = Integer.parseInt(entrada.readLine());

                // Evaluar la respuesta y enviar resultado al cliente
                if (respuestaCliente == pregunta.getRespuestaCorrecta()) {
                    salida.println("Correcto");
                    puntaje += 100;  // Sumar 100 puntos por respuesta correcta
                } else {
                    salida.println("Incorrecto");
                }
            }

            // Enviar puntaje final al cliente
            salida.println("Tu puntaje final es: " + puntaje);
            // Enviar la señal de "Fin" al cliente
            salida.println("Fin");

            // Cerrar el socket
            socketCliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Cerrar conexión con el cliente
            try {
                socketCliente.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
