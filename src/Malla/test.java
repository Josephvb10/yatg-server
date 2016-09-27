package Malla;

import Structures.*;
import Troncycle.Troncycle;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		LinkedMatrix malla = new LinkedMatrix(10,10);
		Item item1 = new Item(ItemType.bomb, 1, 0);
		Item item2 = new Item(ItemType.tronTrail, 0, 1, true, Player.player3);
		Item item3 = new Item(ItemType.tronTrail, 0, 2, false, Player.player3);
		Item item4 = new Item(ItemType.tronTrail, 1, 1, true, Player.player2);
		Item item5 = new Item(ItemType.tronTrail, 1, 2, false, Player.player2);
		Item item6 = new Item(ItemType.fuel, 0, 3);
		Item item7 = new Item(ItemType.increaseTail, 1, 3);
		/*
		malla.setNodeItem(item1);
		malla.setNodeItem(item2);
		malla.setNodeItem(item3);
		malla.setNodeItem(item4);
		malla.setNodeItem(item5);
		malla.setNodeItem(item6);
		malla.setNodeItem(item7);*/

		
		//malla.resetNodeItem(item6);
		//System.out.println(malla.getNodo(0, 3).getItem());
		Troncycle player1 = new	Troncycle(Player.player1, 0, 0);
		player1.setCurrentDirection(Direction.right);
		malla.updatePlayer(player1);
		System.out.println(malla.getNodo(0, 0).getItem());
		System.out.println(malla.getNodo(0, 1).getItem());
		System.out.println(malla.getNodo(0, 2).getItem());
		System.out.println(malla.getNodo(0, 3).getItem());
		malla.updatePlayer(player1);
		System.out.println(malla.getNodo(0, 0).getItem());
		System.out.println(malla.getNodo(0, 1).getItem());
		System.out.println(malla.getNodo(0, 2).getItem());
		System.out.println(malla.getNodo(0, 3).getItem());
		malla.updatePlayer(player1);
		System.out.println(malla.getNodo(0, 0).getItem());
		System.out.println(malla.getNodo(0, 1).getItem());
		System.out.println(malla.getNodo(0, 2).getItem());
		System.out.println(malla.getNodo(0, 3).getItem());
		malla.updatePlayer(player1);
		System.out.println(malla.getNodo(0, 0).getItem());
		System.out.println(malla.getNodo(0, 1).getItem());
		System.out.println(malla.getNodo(0, 2).getItem());
		System.out.println(malla.getNodo(0, 3).getItem());
		malla.updatePlayer(player1);
		System.out.println(malla.getNodo(0, 0).getItem());
		System.out.println(malla.getNodo(0, 1).getItem());
		System.out.println(malla.getNodo(0, 2).getItem());
		System.out.println(malla.getNodo(0, 3).getItem());
		malla.updatePlayer(player1);
		System.out.println(malla.getNodo(0, 0).getItem());
		System.out.println(malla.getNodo(0, 1).getItem());
		System.out.println(malla.getNodo(0, 2).getItem());
		System.out.println(malla.getNodo(0, 3).getItem());


		
		
	}

}
