
package Comunication;
import java.util.PriorityQueue;

import Structures.Carro;

public class TestRunner {

	public static void main(String[] args) {
		PriorityQueue<Carro> colaPrioridad = new PriorityQueue<>();
		Carro carro1 = new Carro(99, "Toyota");
		Carro carro2 = new Carro(999, "test1");
		Carro carro3 = new Carro(9999, "test2");
		colaPrioridad.add(carro1);
		colaPrioridad.add(carro3);
		colaPrioridad.add(carro2);
		
		while (colaPrioridad.size() > 0) {
			System.out.println(colaPrioridad.poll().getPlaca());
		}

	}

}
