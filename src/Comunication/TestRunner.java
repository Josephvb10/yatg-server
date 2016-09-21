package Comunication;

public class TestRunner {

	public static void main(String[] args) {
		Carro carro1 = new Carro(909090, "Toyota");
		String carroJson = JsonConverter.objectToJson(carro1);
		System.out.println(carroJson);
		
	}
	 

		
	}
 
