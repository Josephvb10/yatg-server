
package tests;

import java.io.IOException;
import java.util.PriorityQueue;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import Comunication.JsonConverter;
import Structures.*;

public class TestRunner {

	public static void main(String[] args) {
		PriorityQueue<Carro> colaPrioridad = new PriorityQueue<>();
		Carro carro1 = new Carro(99, "Toyota", Carro.Importancia.particular);
		Carro carro2 = new Carro(999, "test1", Carro.Importancia.taxi);
		Carro carro3 = new Carro(9999, "test2", Carro.Importancia.uber);
		colaPrioridad.add(carro1);
		colaPrioridad.add(carro3);
		colaPrioridad.add(carro2);

		while (colaPrioridad.size() > 0) {
			System.out.println(colaPrioridad.poll().getPlaca());
		}
		String carroJson = JsonConverter.objectToJson(carro1);
		System.out.println(carroJson);

		int positionX = 1;
		int positionY = 2;
		Item item1 = new Item(ItemType.bomb, positionX, positionY);
		String itemJson = JsonConverter.objectToJson(item1);
		System.out.println(itemJson);

		Troncycle player1 = new Troncycle(Player.player1, 0, 0);
		player1.setCurrentDirection(Direction.right);
		String playerJson = JsonConverter.objectToJson(player1);
		System.out.println(playerJson);

		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		Troncycle playerClon= new Troncycle(Player.player1, 221, 233);
		// IMPORTANT
		// without this option set adding new fields breaks old code
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		String jsonStr = "{\"owner\":\"player1\",\"fuel\":100,\"speed\":1,\"currentDirection\":\"right\",\"extraTrail\":3,\"trail\":{\"head\":{\"data\":{\"type\":\"tronTrail\",\"indexI\":0,\"indexJ\":0,\"value\":0,\"owner\":\"player1\",\"head\":true},\"next\":null},\"empty\":false,\"size\":1},\"head\":{\"data\":{\"type\":\"tronTrail\",\"indexI\":0,\"indexJ\":0,\"value\":0,\"owner\":\"player1\",\"head\":true},\"next\":null},\"tail\":{\"type\":\"tronTrail\",\"indexI\":0,\"indexJ\":0,\"value\":0,\"owner\":\"player1\",\"head\":true}}";
		try {
			playerClon = mapper.readValue(jsonStr, Troncycle.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}

		System.out.println(playerClon.getCurrentDirection());
		
		
	}

}
