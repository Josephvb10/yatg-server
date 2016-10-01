package Comunication;

public class inputSocketListener {
	private static inputSocketListener instance = null;
	
	
	protected inputSocketListener() {
		// TODO Auto-generated constructor stub
	}

	public static inputSocketListener getInstance() {
		if(instance == null){
		instance = new inputSocketListener();
		}
		return instance;

	}

	public static inputSocketListener resetInstance() {
		instance = null;
		return getInstance();
	}
	
	

}
