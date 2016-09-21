package Comunication;

public class Carro {
		private int placa;
		private String marca;
		
		public Carro(int placa, String marca) {
			super();
			this.placa = placa;
			this.marca = marca;
		}
		public int getPlaca() {
			return placa;
		}
		public void setPlaca(int placa) {
			this.placa = placa;
		}
		public String getMarca() {
			return marca;
		}
		public void setMarca(String marca) {
			this.marca = marca;
		}
}
