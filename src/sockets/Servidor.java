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
			System.out.println("Server running! Listening on port [ " + PORT + " ]");
			
			// 5. Acepto la conexión entrante del cliente
			s = server.accept();
			System.out.println("Connection established succesfully from [ " + s.getInetAddress() + " ] on port [ " + PORT + " ]");
			
			// 6. Abro canales de comunicación
			input = new DataInputStream(s.getInputStream());
			output = new DataOutputStream(s.getOutputStream());
			
			// 7. Recibo los datos procedentes del cliente: operadores y codigo de operacion
			// - 0: Suma
			// - 1: Resta
			// - 2: Multiplicacion
			// - 3: Division
			double num1 = input.readDouble();
			double num2 = input.readDouble();
			int op = input.readInt();
			
			// Elijo la operación en función del codigo de la operacion
			Calculadora calc = new Calculadora();
			
			switch (op) {
				case 0:
					output.writeUTF(num1 + " + " + num2 + " = " + calc.Suma(num1, num2));
					break;
				case 1:
					output.writeUTF(num1 + " - " + num2 + " = " + calc.Resta(num1, num2));
					break;
				case 2:
					output.writeUTF(num1 + " x " + num2 + " = " + calc.Multiplicacion(num1, num2));
					break;
				case 3:
					try {
						output.writeUTF(num1 + " % " + num2 + " = " + calc.Division(num1, num2));
					} catch (ArithmeticException e)
					{
						output.writeUTF(e.getMessage());
					}
					break;
				default:
					// No debería entrar aquí nunca, pero lo incluyo por respetar la sintaxis de la estructura de control
					output.writeUTF("Unknown option..."); 
					break;
			}
			
			// 8. Acuse de recibo al cliente
			output.writeUTF("Got " + num1 + " and " + num2 + " and operation " + op);
			
			// 9. Cierro socket y flujos
			s.close();
			System.out.println("Client disconnected. Have a nice day!");
			
			input.close();
			output.close();
			
			
		} catch (IOException e) {
			System.err.println("ERROR: server can't receve connections!\n" + e.getMessage());
		}
	
	}

}
