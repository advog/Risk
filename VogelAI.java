import java.util.*;
public class VogelAI {
	public Player player;
	private ArrayList<AITerritory> frontLine= new ArrayList();
	public RiskFrame RFrame;
	

	public VogelAI(Player p,RiskFrame r){
		player = p;
		RFrame =r;
	}
	
	public void runAI() {
		resetFront();
		oneSec();
		RFrame.repaint();
		if(RFrame.phase == 0){phaseZero();}
		if(RFrame.phase == 1){phaseOne();}
		if(RFrame.phase == 2){phaseTwo();}	
		if(RFrame.phase == 3){phaseThree();}
	}
	
	public void resetFront(){
		frontLine.removeAll(frontLine);
		for(territoryButton x: RFrame.getTerr()){
			if(x.getPlayer() == player){
				boolean found = false;
				for(territoryButton y: x.getAdjacent()){
					if(y.getPlayer()!=player)
						found = true;
				}	
				if(found == true)
					frontLine.add(new AITerritory(x,this));
			}	
		}
		
	}

	public void phaseZero(){
		int cp=20;
		while(player.getTroopsLeft()>0){
			for(AITerritory x: frontLine){
				if(x.getPriority()>cp)
					x.territory.Clicked();
			}
			cp-=2;
		}
		RFrame.next();
	}
	
	public void phaseOne(){
		int cp=15;
		while(player.getTroopsLeft()>0){
			for(AITerritory x: frontLine){
				if(x.getPriority()>cp)
					x.territory.Clicked();
			}
			cp-=2;
		}
		//System.out.println("done");
		RFrame.next();
	}
	
	public void phaseTwo(){
			
		for (int i = 0; i < 4; i++) 
			for(AITerritory x: frontLine){
				x.getPriority();
				x.Attack();
			}		
			RFrame.next();
	}
	
	public void phaseThree(){
		RFrame.updateButtons();
		RFrame.next();
	}
	
	//public int StratValue(territoryButton btn) {
	//	
	//}
	
	
	public static void oneSec() {
		    try {
		      Thread.currentThread().sleep(1);
		       }
		     catch (InterruptedException e) {
		       e.printStackTrace();
		       }
		     }  
	
	public static void manySec(long s) {
		     try {
		       Thread.currentThread().sleep(s * 1000);
		       }
		     catch (InterruptedException e) {
		       e.printStackTrace();
		       }
		     }
		

}
