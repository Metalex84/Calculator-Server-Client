package sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Cliente {
	
	public static void main(String[] args) {
		
		// 1. Declaro desde dónde se conecta el cliente y a través de qué puerto
		final String HOST = "127.0.0.1";
		final int PORT = 1984;
		
		// 2. El objeto Socket me permite conectarme al servidor
		Socket sc = null;
		
		// 3. Estos son los canales de comunicación con el servidor
		DataInputStream input;
		DataOutputStream output;
		
		// 4. Trato de establecer Socket con el servidor
		try {
			sc = new Socket(HOST, PORT);
			JOptionPane.showMessageDialog(null, "Connection established through port [" + PORT + "]");
			
			//  Abro los canales de comunicación con el servidor
			input = new DataInputStream(sc.getInputStream());
			output = new DataOutputStream(sc.getOutputStream());
			
			// TODO: 6. Capturo la información del usuario
			
			// 7. Mando información del cliente al servidor
			output.writeDouble(3.14159);
			output.writeDouble(2.74258);
			
			// 8. Leo la respuesta desde el servidor
			JOptionPane.showMessageDialog(null, "Server replied: [" + input.readUTF() + "]");
			
			// 9. Cierro el socket
			sc.close();
			
		} catch (UnknownHostException e) {
			System.err.println("Cannot reach server on port [" + PORT + "]\n" + e.getMessage());
		} catch (IOException e) {
			System.err.println("Cannot send or receive data from server :-(");
		}
		
	}

}
