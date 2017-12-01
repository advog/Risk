import java.util.ArrayList;

public class AITerritory {
	private ArrayList<territoryButton> instanceFront = new ArrayList();
	private ArrayList<territoryButton> instanceFFront = new ArrayList();
	public territoryButton territory;
	territoryButton currentTarget;
	int attackPriority;
	int defencePriority;
	int totalPriority;
	int continentPriority;
	int adjacentTroops=0;
	int adjacentFTroops=0;
	territoryButton[] continent;
	VogelAI ai;
	
	
	public AITerritory(territoryButton t,VogelAI a){
		territory = t;
		ai = a;
		initFront();
	}
	
	public void initFront() {
		for(territoryButton x: territory.getAdjacent()) {
			if(x.getPlayer() != ai.player)
				instanceFront.add(x);
			else
				instanceFFront.add(x);
		}
	}
	
	public void calcDPriority(){
		adjacentTroops = 0;
		for(territoryButton x: instanceFront) {
			adjacentTroops += x.getTroops();
		}
		defencePriority = (int)Math.pow(adjacentTroops - territory.getTroops(), 2)/territory.getTroops();
	}
	
	public void calcAPriority(){
		territoryButton weakest = instanceFront.get(0);
		for(territoryButton x: instanceFront) {
			if(weakest.getTroops() > x.getTroops())
				weakest = x;
		}
		adjacentFTroops = 0;
		for(territoryButton x: instanceFFront) {
			adjacentFTroops += x.getTroops();
		}
		
		currentTarget = weakest;
		attackPriority = (int)Math.pow(territory.getTroops()-currentTarget.getTroops(),2)/(int)Math.pow(currentTarget.getTroops(),2);
	}

	public void calcCPriority(){
		continentPriority=0;
	}
	
	public void calcTPriority() {
		calcDPriority();
		calcAPriority();
		calcCPriority();
		totalPriority = 3*attackPriority + defencePriority
				//+ continentPriority
				;
	}
	
	public int getPriority() {
		calcTPriority();
		return totalPriority;
	}

	public void Attack() {
		adjacentFTroops = 0;
		for(territoryButton x: instanceFFront) {
			adjacentFTroops += x.getTroops();
		}
		if(
				//territory.getTroops() >= adjacentTroops && 
				currentTarget.getTroops()*2 < adjacentFTroops/2 + territory.getTroops() && territory.getTroops() > 3){
			new Battle(territory,currentTarget,ai.RFrame,territory.getTroops()/3*2,true);
			System.out.println("resolved");
		}
	}
}
