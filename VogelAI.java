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
		
		//RFrame.repaint();
		//oneSec();
		
		if(RFrame.phase == 0){phaseZero();}
		if(RFrame.phase == 1){phaseOneA();}
		if(RFrame.phase == 2){phaseTwoA();}	
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
		AITerritory highest = frontLine.get(0);
		for(AITerritory x: frontLine){
			if(x.getPriority()>highest.getPriority())
				highest = x;
		}
		highest.territory.Clicked();	
		RFrame.next();
	}
	
	public void phaseOneA(){
		AITerritory target = frontLine.get(0);
		for(AITerritory x: frontLine) {
			if(x.getPriority() > target.getPriority())
				target = x;
		}
		
		int cp=target.getPriority();
		int cpv = target.getPriority()/3;
		if( cpv == 0)
			cpv =1;
		while(player.getTroopsLeft()>0 && cp>0){
			resetFront();
			for(AITerritory x: frontLine){
				if(x.getPriority()>cp)
					x.territory.Clicked();
			}
			cp-=cpv;
		}
		
		while(player.getTroopsLeft()>0){
			target.territory.Clicked();
			System.out.println("stuck c");
			System.out.println(player.getPterr().size());
		}
		RFrame.next();
	}
	
	public void phaseTwoA(){
		resetFront();
		for (int i = 0; i < 7; i++) { 
			for(int x=0;x < frontLine.size();x++){
				resetFront();
				if(x<frontLine.size())
					frontLine.get(x).Attack();
			}
		}
		RFrame.next();
	}
	
	public void phaseThree(){
		RFrame.updateButtons();
		//RFrame.NEXTPHASE.doClick();
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
