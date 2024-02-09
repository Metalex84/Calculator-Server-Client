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
		
		JOptionPane.showMessageDialog(null, "Welcome to JCalculator v1.0!");

		// 4. Trato de establecer Socket con el servidor
		try {
			sc = new Socket(HOST, PORT);
			
			// 5. Abro los canales de comunicación con el servidor
			input = new DataInputStream(sc.getInputStream());
			output = new DataOutputStream(sc.getOutputStream());
			
			// 6. Capturo los datos del usuario, controlando que no entran valores nulos
			String value1 = null;
			String value2 = null;
			do {
				value1 = JOptionPane.showInputDialog("Give me number #1: ");	
			} while (value1 == null || value1.trim().isEmpty());
			
			do {
				value2 = JOptionPane.showInputDialog("Give me number #2: ");
			} while (value2 == null || value2.trim().isEmpty());
			
			// y los guardo como numeros para enviarlos al servidor
			double n1 = Double.parseDouble(value1);
			double n2 = Double.parseDouble(value2);
			
			final Object[] OPERATIONS = { "+", "-", "x", "%" };
			Object operation = JOptionPane.showOptionDialog(null, "Choose an operation", "Warning", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, OPERATIONS, OPERATIONS[0]);
			
			// 7. Mando información del cliente al servidor
			output.writeDouble(n1);
			output.writeDouble(n2);
			output.writeInt((int)operation);
			// 8. Leo la respuesta desde el servidor
			JOptionPane.showMessageDialog(null, input.readUTF());
			
			// 9. Cierro socket y flujos
			sc.close();
			input.close();
			output.close();
			
			// 10. Y me despido :)
			JOptionPane.showMessageDialog(null, "Goodbye!");
			
		} catch (UnknownHostException e) {
			System.err.println("Cannot reach server on port [" + PORT + "]\n" + e.getMessage());
		} catch (IOException e) {
			System.err.println("Cannot send or receive data from server :-(");
		}
		
	}

}
