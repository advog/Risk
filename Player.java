import java.util.*;
import java.awt.Color;


public class Player {
	private Color color;
	private int PlayerID;
	private ArrayList<Integer> Cards= new ArrayList();
	private ArrayList<territoryButton> pterr= new ArrayList();
	private int troopsLeft=0;
	public VogelAI ai;
	public boolean dead= false;
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getPlayerID() {
		return PlayerID;
	}

	public void setPlayerID(int playerID) {
		PlayerID = playerID;
	}

	public ArrayList<Integer> getCards() {
		return Cards;
	}

	public ArrayList<territoryButton> getPterr() {
		return pterr;
	}

	public int getTroopsLeft() {
		return troopsLeft;
	}

	public void setTroopsLeft(int t) {
		troopsLeft = t;
	}

	Player(Color c,int i){
		color = c;
		PlayerID = i;
	}
	
	public void addTroops(int a) {
		troopsLeft += a;
	}

	public void createAI(RiskFrame r){
		ai = new VogelAI(this,r);
	}
	
	public void runAI() {
		if(ai  != null) 	
			ai.runAI();
	}
	
	
}
