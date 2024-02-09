package sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) {
		
		// 1. Los objetos que necesitaré para recibir conexiones
		ServerSocket server = null;
		Socket s = null;
		
		// 2. Los objetos que necesitaré para comunicarme con el Cliente 
		DataInputStream input;
		DataOutputStream output;
		
		// 3. Elijo un puerto (espero que esté libre)
		final int PORT = 1984;
		
		// 4. Trato de abrir Socket en el puerto indicado
		try {
			server = new ServerSocket(PORT);
			System.out.println("Server running! Listening on port [" + PORT + "]");
			
			// 5. Acepto la conexión entrante del cliente
			s = server.accept();
			System.out.println("Connection established succesfully from [" + s.getInetAddress() + "] on port [" + PORT + "]");
			
			// 6. Abro canales de comunicación
			input = new DataInputStream(s.getInputStream());
			output = new DataOutputStream(s.getOutputStream());
			
			// 7. Recibo los datos procedentes del cliente
			double num1 = input.readDouble();
			double num2 = input.readDouble();
			// TODO: leer código de la operación
			
			System.out.println("Client sent numbers " + num1 + " and " + num2);
			
			// 8. Acuse de recibo al cliente
			output.writeUTF("Got " + num1 + " and " + num2);
			
			// 9. Cierro socket
			s.close();
			System.out.println("Client disconnected. Have a nice day!");
			
			
		} catch (IOException e) {
			System.err.println("ERROR: server can't receve connections!\n" + e.getMessage());
		}
	
	}

}
