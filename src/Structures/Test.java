package Structures;

import java.util.PriorityQueue;

public class Test {

	public static void main(String[] args) {
		PriorityQueue<Carro> colaPrioridad = new PriorityQueue<>();
		Carro carro1 = new Carro(9090, "toyota", Carro.Importancia.patrulla);
		Carro carro2 = new Carro(1212, "nissan", Carro.Importancia.taxi);
		Carro carro3 = new Carro(6767, "Hummer", Carro.Importancia.uber);
		Carro carro4 = new Carro(2323, "jeep", Carro.Importancia.patrulla);
		colaPrioridad.add(carro1);
		colaPrioridad.add(carro2);
		colaPrioridad.add(carro3);
		colaPrioridad.add(carro4);
		while(colaPrioridad.size()>0){
			System.out.println(colaPrioridad.poll().getMarca());
		}
		System.out.println(colaPrioridad);
	}

}
