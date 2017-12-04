import java.util.ArrayList;

public class AITerritory {
	private ArrayList<territoryButton> instanceFront = new ArrayList();
	private ArrayList<territoryButton> instanceFFront = new ArrayList();
	public territoryButton territory;
	territoryButton currentTarget;
	int defencePriority;
	int totalPriority;
	int continentPriority;
	int adjacentTroops=0;
	int adjacentFTroops=0;
	private ArrayList<territoryButton> continent = new ArrayList();
	VogelAI ai;
	
	
	public AITerritory(territoryButton t,VogelAI a){
		territory = t;
		ai = a;
		initFront();
		initCont();
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
		if(adjacentTroops - territory.getTroops()<0) {
			defencePriority = 0;
			return;
		}
		defencePriority = (int)Math.pow(adjacentTroops - territory.getTroops(), 2)/(adjacentTroops);
	}
	
	public void getTarget(){
		territoryButton target = instanceFront.get(0);
		for(territoryButton x: instanceFront) {
			if(x.getTroops() < target.getTroops())
				target = x;
		}
		currentTarget = target;
	}
	
	public void initCont(){
		for(int x = 0;x < ai.RFrame.getCont().length;x++)
			for(int y=0;y < ai.RFrame.continents[x].length;y++)
				if(ai.RFrame.continents[x][y] == territory)
					for(int z=0;z < ai.RFrame.continents[x].length;z++)
						continent.add(ai.RFrame.continents[x][z]);
	}
	
	public void calcCPriority(){
		int count = 0;
		for(territoryButton x: continent)
			if(x.getPlayer() == ai.player)
				count++;
		continentPriority = (int)(count/(double)continent.size()+1*3);
	}
	
	public void calcTPriority() {
		calcDPriority();
		calcCPriority();
		totalPriority =  defencePriority*continentPriority;
		System.out.println(defencePriority+ "   "+continentPriority);
	}
	
	public int getPriority() {
		calcTPriority();
		return totalPriority;
	}

	public void Attack() {
		getTarget();
		adjacentFTroops = 0;
		for(territoryButton x: instanceFFront) {
			adjacentFTroops += x.getTroops();
		}
		if(currentTarget.getTroops() < adjacentFTroops/2 + territory.getTroops() && territory.getTroops() >= 4){
			new Battle(territory,currentTarget,ai.RFrame,territory.getTroops()-1,true);
			System.out.println("resolved");
		}
	}
}
