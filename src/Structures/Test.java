package Structures;

public class Test {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		GenericLinkedList<String> nueva_lista = new GenericLinkedList<>();
		nueva_lista.add("hola1");
		nueva_lista.add("hola2");
		nueva_lista.add("hola3");
		nueva_lista.insertAtBeggining("hola0");
		System.out.println(nueva_lista.getAtPosition(2));
		System.out.println(nueva_lista.getAtPosition(0));		
		
	}

}
