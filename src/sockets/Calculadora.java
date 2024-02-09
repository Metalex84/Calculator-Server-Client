package sockets;

public class Calculadora {
	
	public double Suma(double a, double b) {
		return (a + b);
	}

	public double Resta(double a, double b) {
		return (a - b);
	}
	
	public double Multiplicacion(double a, double b) {
		return (a * b);
	}
	
	public double Division(double a, double b) throws ArithmeticException { // Manejo una posible división por cero
		return (a / b);
	}
}
