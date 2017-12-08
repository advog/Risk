import java.io.*;
import java.util.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

public class RiskFrame extends javax.swing.JFrame {
    public int phase = 0;
    public int players;
    public int turn = 1;
    private int bonus = 10;
    territoryButton selected;
    private ArrayList<territoryButton> terr= new ArrayList();
    public ArrayList<Player> parray= new ArrayList();
    public territoryButton[][] continents = new territoryButton[6][];
    private int[] cb = {5,2,2,7,5,3};
    public boolean newT;
    
    public RiskFrame(int p) {
        initComponents();
        players = p;
        set();
    }

    public RiskFrame(String s) {
    	initComponents();
    	readFile(s);
    }
    
    private void initComponents() {
        inVars();

        Phase = new JLabel(); 
        Troops = new JLabel();        
        NEXTPHASE = new JButton();
        BONUSTROOPS = new JButton();
        SAVE = new JButton();
        LOAD = new JButton();
        TURN = new JLabel();
        BACKGROUND = new JLabel();
        PAUSE = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        for(int x=0;x<terr.size();x++) {
        	getContentPane().add(terr.get(x));
        	terr.get(x).setText("0");
        	terr.get(x).setBorder(BorderFactory.createLineBorder(Color.blue,3)); // Simple Line Border
        	terr.get(x).setBorderPainted(false);
        	terr.get(x).ID=x;
        }
        
        Troops.setText("Troops");
        getContentPane().add(Troops);
        Troops.setBounds(900, 0, 100, 20);
        
        Phase.setText("Phase");
        getContentPane().add(Phase);
        Phase.setBounds(800, 0, 100, 20);
 
        NEXTPHASE.setText("Next Phase");
        getContentPane().add(NEXTPHASE);
        NEXTPHASE.setBounds(580, 720, 420, 60);
        NEXTPHASE.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    		next();
		    }
		});
        
        SAVE.setText("Save");
        getContentPane().add(SAVE);
        SAVE.setBounds(20, 20, 100, 30);
        SAVE.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if(phase == 1) 
		    	try {
		             writeFile();
		         } catch ( IOException ex) {
		         }finally {}
		        else
		        	JOptionPane.showConfirmDialog(null, "can only save on phase 1");
		        
		    }
		});
        
        LOAD.setText("Load");
        getContentPane().add(LOAD);
        LOAD.setBounds(120, 20, 100, 30);
        LOAD.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		            String path = JOptionPane.showInputDialog("Save Path");
		    		if(path != null && !path.equals("")) {
		    			new RiskFrame(path);
		    			dispose();
		    		}
		    }
		});
        
        
        BONUSTROOPS.setText("Bonus");
        getContentPane().add(BONUSTROOPS);
        BONUSTROOPS.setBounds(20, 720, 440, 60);
        BONUSTROOPS.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		       bonus();
		    }
		    
		});
        
        TURN.setFont(new java.awt.Font("Tahoma", 0, 36));
        TURN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TURN.setText("1");
        getContentPane().add(TURN);
        TURN.setBounds(400, 10, 190, 60);
        TURN.setOpaque(true);

        BACKGROUND.setIcon(new javax.swing.ImageIcon(getClass().getResource("/risk.png")));
        getContentPane().add(BACKGROUND);
        BACKGROUND.setBounds(0, 0, 1020, 790);
        BACKGROUND.setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
        
        //this.setUndecorated(true);
        pack();
        this.setSize(1040,840);
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        
        
        
        
    }// </editor-fold>//GEN-END:initComponents
    
    public void update(){
    	Troops.setText(""+parray.get(turn-1).getTroopsLeft());	
    	TURN.setBackground(parray.get(turn-1).getColor());
	    TURN.setText(turn+"");
	    Phase.setText(phase+"");
    }
    
    public ArrayList<territoryButton> getTerr() {
		return terr;
	}
    
    public void updateButtons(){
    	for(territoryButton x: terr){
    		x.update();
    	}
    }
    
    public void resetButtons(){
    	for(territoryButton x: terr){
    		x.reset();
    	}
    }
    
    public void addb() {
    	bonus+=5;
    }
    
    public void bonus() {
    	if(phase == 1)
	    	new CardFrame(parray.get(turn-1),this,bonus);
    }

    public void inVars() {
    	NA2 = new territoryButton(this);
        NA1 = new territoryButton(this);
        NA3 = new territoryButton(this);
        NA4 = new territoryButton(this);
        NA9 = new territoryButton(this);
        NA7 = new territoryButton(this);
        NA8 = new territoryButton(this);
        NA6 = new territoryButton(this);
        NA5 = new territoryButton(this);
        SA1 = new territoryButton(this);
        SA2 = new territoryButton(this);
        SA3 = new territoryButton(this);
        A1 = new territoryButton(this);
        SA4 = new territoryButton(this);
        A2 = new territoryButton(this);
        A3 = new territoryButton(this);
        A5 = new territoryButton(this);
        A6 = new territoryButton(this);
        A4 = new territoryButton(this);
        AS1 = new territoryButton(this);
        AS2 = new territoryButton(this);
        AS11 = new territoryButton(this);
        AS3 = new territoryButton(this);
        AS4 = new territoryButton(this);
        AS5 = new territoryButton(this);
        AS6 = new territoryButton(this);
        AS7 = new territoryButton(this);
        AS8 = new territoryButton(this);
        AS9 = new territoryButton(this);
        AS10 = new territoryButton(this);
        AS12 = new territoryButton(this);
        AU1 = new territoryButton(this);
        AU2 = new territoryButton(this);
        AU3 = new territoryButton(this);
        AU4 = new territoryButton(this);
        E1 = new territoryButton(this);
        E2 = new territoryButton(this);
        E3 = new territoryButton(this);
        E5 = new territoryButton(this);
        E6 = new territoryButton(this);
        E4 = new territoryButton(this);
        E7 = new territoryButton(this);
        
		terr.add(NA2);//0
		terr.add(NA1);
		terr.add(NA3);
		terr.add(NA4);
		terr.add(NA9);
		terr.add(NA7);
		terr.add(NA8);
		terr.add(NA6);
		terr.add(NA5);//8
		
		terr.add(SA1);//9
		terr.add(SA2);
		terr.add(SA3);
		terr.add(SA4);//12
		
		terr.add(A1);//13
		terr.add(A2);
		terr.add(A3);
		terr.add(A5);
		terr.add(A6);
		terr.add(A4);//18
		
		terr.add(AS1);//19
		terr.add(AS2);
		terr.add(AS11);
		terr.add(AS3);
		terr.add(AS4);
		terr.add(AS5);
		terr.add(AS6);
		terr.add(AS7);
		terr.add(AS8);
		terr.add(AS9);
		terr.add(AS10);
		terr.add(AS12);//30
		
		terr.add(AU1);//31
		terr.add(AU2);
		terr.add(AU3);
		terr.add(AU4);//34
		
		terr.add(E1);//35
		terr.add(E2);
		terr.add(E3);
		terr.add(E5);
		terr.add(E6);
		terr.add(E4);
		terr.add(E7);//41
		
		NA2.setBounds(100, 220, 50, 23);
        NA1.setBounds(50, 190, 50, 23);
        NA3.setBounds(90, 340, 50, 23);
        NA4.setBounds(160, 280, 50, 23);
        NA9.setBounds(90, 270, 50, 23);
        NA7.setBounds(170, 230, 50, 23);
        NA8.setBounds(240, 230, 50, 23);
        NA6.setBounds(130, 190, 50, 23);
        NA5.setBounds(330, 160, 80, 23);
        SA1.setBounds(220, 550, 50, 23);
        SA2.setBounds(270, 460, 50, 23);
        SA3.setBounds(210, 490, 50, 23);
        A1.setBounds(500, 420, 50, 23);
        SA4.setBounds(200, 400, 50, 23);
        A2.setBounds(560, 390, 50, 23);
        A3.setBounds(490, 320, 50, 23);
        A5.setBounds(430, 360, 50, 23);
        A6.setBounds(490, 500, 50, 23);
        A4.setBounds(590, 490, 50, 23);
        AS1.setBounds(630, 260, 50, 23);
        AS2.setBounds(760, 300, 50, 23);
        AS11.setBounds(610, 210, 50, 23);
        AS3.setBounds(690, 340, 50, 23);
        AS4.setBounds(760, 230, 50, 23);
        AS5.setBounds(880, 290, 50, 23);
        AS6.setBounds(870, 190, 50, 23);
        AS7.setBounds(560, 300, 50, 23);
        AS8.setBounds(770, 260, 50, 23);
        AS9.setBounds(780, 360, 50, 23);
        AS10.setBounds(690, 190, 50, 23);
        AS12.setBounds(760, 190, 50, 23);
        AU1.setBounds(900, 520, 50, 23);
        AU2.setBounds(800, 430, 50, 23);
        AU3.setBounds(900, 450, 50, 23);
        AU4.setBounds(830, 520, 50, 23);
        E1.setBounds(400, 230, 50, 23);
        E2.setBounds(390, 200, 50, 23);
        E3.setBounds(460, 240, 50, 23);
        E5.setBounds(530, 230, 50, 23);
        E6.setBounds(500, 270, 50, 23);
        E4.setBounds(480, 200, 50, 23);
        E7.setBounds(430, 270, 50, 23);
        
        NA2.name="NA2";
        NA1.name="NA1";
        NA3.name="NA3";
        NA4.name="NA4";
        NA9.name="NA9";
        NA7.name="NA7";
        NA8.name="NA8";
        NA6.name="NA6";
        NA5.name="NA5";
        SA1.name="SA1";
        SA2.name="SA2";
        SA3.name="SA3";
        A1.name="A1";
        SA4.name="SA4";
        A2.name="A2";
        A3.name="A3";
        A5.name="A5";
        A6.name="A6";
        A4.name="A4";
        AS1.name="AS1";
        AS2.name="AS2";
        AS11.name="AS11";
        AS3.name="AS3";
        AS4.name="AS4";
        AS5.name="AS5";
        AS6.name="AS6";
        AS7.name="AS7";
        AS8.name="AS8";
        AS9.name="AS9";
        AS10.name="AS10";
        AS12.name="AS12";
        AU1.name="AU1";
        AU2.name="AU2";
        AU3.name="AU3";
        AU4.name="AU4";
        E1.name="E1";
        E2.name="E2";
        E3.name="E3";
        E5.name="E5";
        E6.name="E6";
        E4.name="E4";
        E7.name="E7";
        
        NA1.adjacent.add(NA2);
        NA1.adjacent.add(NA6);
        NA1.adjacent.add(AS6);
        
        NA2.adjacent.add(NA1);
        NA2.adjacent.add(NA6);
        NA2.adjacent.add(NA7);
        NA2.adjacent.add(NA9);
        
        NA3.adjacent.add(NA9);
        NA3.adjacent.add(NA4);
        NA3.adjacent.add(SA4);
        
        NA4.adjacent.add(NA8);
        NA4.adjacent.add(NA7);
        NA4.adjacent.add(NA9);
        NA4.adjacent.add(NA3);
        
        NA5.adjacent.add(E2);
        NA5.adjacent.add(NA8);
        NA5.adjacent.add(NA7);
        NA5.adjacent.add(NA6);
        
        NA6.adjacent.add(NA1);
        NA6.adjacent.add(NA2);
        NA6.adjacent.add(NA7);
        NA6.adjacent.add(NA5);
        
        NA7.adjacent.add(NA5);
        NA7.adjacent.add(NA6);
        NA7.adjacent.add(NA2);
        NA7.adjacent.add(NA9);
        NA7.adjacent.add(NA4);
        NA7.adjacent.add(NA8);
        
        NA8.adjacent.add(NA5);
        NA8.adjacent.add(NA7);
        NA8.adjacent.add(NA4);
        
        NA9.adjacent.add(NA2);
        NA9.adjacent.add(NA7);
        NA9.adjacent.add(NA4);
        NA9.adjacent.add(NA3);
        
        
        SA1.adjacent.add(SA3);
        SA1.adjacent.add(SA2);
        
        SA2.adjacent.add(SA1);
        SA2.adjacent.add(SA3);
        SA2.adjacent.add(SA4);
        SA2.adjacent.add(A5);
        
        SA3.adjacent.add(SA1);
        SA3.adjacent.add(SA2);
        SA3.adjacent.add(SA4);
        
        SA4.adjacent.add(SA3);
        SA4.adjacent.add(SA2);
        SA4.adjacent.add(NA3);
        
        E1.adjacent.add(E2);
        E1.adjacent.add(E7);
        E1.adjacent.add(E4);
        E1.adjacent.add(E3);
        
        E2.adjacent.add(NA5);
        E2.adjacent.add(E1);
        E2.adjacent.add(E4);
        
        E3.adjacent.add(E1);
        E3.adjacent.add(E7);
        E3.adjacent.add(E6);
        E3.adjacent.add(E5);
        E3.adjacent.add(E4);
        
        E4.adjacent.add(E2);
        E4.adjacent.add(E1);
        E4.adjacent.add(E3);
        E4.adjacent.add(E5);
        
        E5.adjacent.add(AS11);
        E5.adjacent.add(AS1);
        E5.adjacent.add(AS7);
        E5.adjacent.add(E6);
        E5.adjacent.add(E3);
        E5.adjacent.add(E4);
        
        E6.adjacent.add(E5);
        E6.adjacent.add(AS7);
        E6.adjacent.add(A3);
        E6.adjacent.add(A5);
        E6.adjacent.add(E7);
        E6.adjacent.add(E3);
        
        E7.adjacent.add(E1);
        E7.adjacent.add(E3);
        E7.adjacent.add(E6);
        E7.adjacent.add(A5);
        
        A1.adjacent.add(A2);
        A1.adjacent.add(A5);
        A1.adjacent.add(A6);
       
        A2.adjacent.add(AS7);
        A2.adjacent.add(A4);
        A2.adjacent.add(A6);
        A2.adjacent.add(A1);
        A2.adjacent.add(A5);
        A2.adjacent.add(A3);
        
        A3.adjacent.add(E6);
        A3.adjacent.add(AS7);
        A3.adjacent.add(A2);
        A3.adjacent.add(A5);
        
        A4.adjacent.add(A2);
        A4.adjacent.add(A6);
        
        A5.adjacent.add(E7);
        A5.adjacent.add(A3);
        A5.adjacent.add(A2);
        A5.adjacent.add(A1);
        A5.adjacent.add(SA2);
        
        A6.adjacent.add(A1);
        A6.adjacent.add(A2);
        A6.adjacent.add(A4);
        
        AS1.adjacent.add(E5);
        AS1.adjacent.add(AS11);
        AS1.adjacent.add(AS2);
        AS1.adjacent.add(AS3);
        AS1.adjacent.add(AS7);
        
        AS2.adjacent.add(AS1);
        AS2.adjacent.add(AS11);
        AS2.adjacent.add(AS10);
        AS2.adjacent.add(AS8);
        AS2.adjacent.add(AS9);
        AS2.adjacent.add(AS3);
        
        AS3.adjacent.add(AS7);
        AS3.adjacent.add(AS1);
        AS3.adjacent.add(AS2);
        AS3.adjacent.add(AS9);
        
        AS4.adjacent.add(AS10);
        AS4.adjacent.add(AS12);
        AS4.adjacent.add(AS6);
        AS4.adjacent.add(AS8);
        
        AS5.adjacent.add(AS6);
        AS5.adjacent.add(AS8);
        
        AS6.adjacent.add(AS12);
        AS6.adjacent.add(AS4);
        AS6.adjacent.add(AS8);
        AS6.adjacent.add(AS5);
        AS6.adjacent.add(NA1);
        
        AS7.adjacent.add(E6);
        AS7.adjacent.add(E5);
        AS7.adjacent.add(AS1);
        AS7.adjacent.add(AS3);
        AS7.adjacent.add(A2);
        AS7.adjacent.add(A3);
        
        AS8.adjacent.add(AS2);
        AS8.adjacent.add(AS10);
        AS8.adjacent.add(AS4);
        AS8.adjacent.add(AS6);
        AS8.adjacent.add(AS5);
        
        AS9.adjacent.add(AS3);
        AS9.adjacent.add(AS2);
        AS9.adjacent.add(AU2);
        
        AS10.adjacent.add(AS11);
        AS10.adjacent.add(AS2);
        AS10.adjacent.add(AS8);
        AS10.adjacent.add(AS4);
        AS10.adjacent.add(AS12);
        
        AS11.adjacent.add(E5);
        AS11.adjacent.add(AS1);
        AS11.adjacent.add(AS2);
        AS11.adjacent.add(AS10);
        
        AS12.adjacent.add(AS10);
        AS12.adjacent.add(AS4);
        AS12.adjacent.add(AS6);
        
        AU1.adjacent.add(AU3);
        AU1.adjacent.add(AU4);
        
        AU2.adjacent.add(AS9);
        AU2.adjacent.add(AU3);
        AU2.adjacent.add(AU4);
        
        AU3.adjacent.add(AU2);
        AU3.adjacent.add(AU4);
        AU3.adjacent.add(AU1);
        
        AU4.adjacent.add(AU2);
        AU4.adjacent.add(AU3);
        AU4.adjacent.add(AU1);
        
        territoryButton[]  NA = {NA1, NA2, NA3, NA4,NA4,NA5,NA6,NA7,NA8,NA9};
        territoryButton[]  SA = {SA1, SA2, SA3, SA4};
        territoryButton[]  AU = {AU1, AU2, AU3, AU4};
        territoryButton[]  AS = {AS1, AS2, AS3, AS4,AS4,AS5,AS6,AS7,AS8,AS9,AS10,AS11,AS12};
        territoryButton[]  E = {E1, E2, E3, E4,E4,E5,E6,E7};
        territoryButton[]  A = {A1, A2, A3, A4,A4,A5,A6};
        
        continents[0] = NA;
        continents[1] = SA;
        continents[2] = AU;
        continents[3] = AS;
        continents[4] = E;
        continents[5] = A;
    }
    
    public territoryButton[][] getCont(){
    	return continents;
    }
    
    public boolean checkLoss(Player p,Player a){
    	if(p.getPterr().size()==0){
    		a.getCards().addAll(p.getCards());
    		p.dead = true;
    		System.out.println("death");
    		return true;
    	}
    	return false;
    }
    
    private void set(){
    	 int startarmies = 25;
    	 Integer[] arr = new Integer[42];
    	 Player[] par = new Player[6];
    	 par[0]=new Player(Color.LIGHT_GRAY,1);
    	 par[1]=new Player(Color.GREEN,2);
    	 par[2]=new Player(Color.ORANGE,3);
    	 par[3]=new Player(Color.CYAN,4);
    	 par[4]=new Player(Color.PINK,5);
    	 par[5]=new Player(Color.YELLOW,6);
    	 
    	 for(int x = 0; x<players; x++) {
    		 parray.add(par[x]);
    		 int reply = JOptionPane.showConfirmDialog(null,"make player "+ x + " an AI?" , "AI", JOptionPane.YES_NO_OPTION);
    	     if (reply == JOptionPane.YES_OPTION){
    	       par[x].createAI(this);
    	     }
    	     else {}
    	 }
    	 
    	 for(Player x: parray) {
        	x.setTroopsLeft(x.getTroopsLeft()+startarmies);
         }
    	
         
         
        String[] options = new String[] {"Random", "Select"};
    	int response = JOptionPane.showOptionDialog(null, "Message", "Title",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null, options, options[0]);
    	 
        if(response == 0) {    
	        for (int i = 0; i < arr.length; i++) {
	        	        arr[i] = i;
	        	    }
	        Collections.shuffle(Arrays.asList(arr));
	        for(int x = 0;x<42;x++) {
	        	terr.get(arr[x]).setPlayer(parray.get(x%parray.size()));
	        	terr.get(arr[x]).addTroops(1);
	        }
        }
        else {
        	phase = -1;
        }
       
    }
    
    public void next() {

    	if (checkWin())
    		return;

    	 else if( phase == 1) {
    		 phase++;
    	 }
    	 
    	 else if(phase == 2 ) {
	        phase++;
	        unhighlight();
	     }
	     
    	 else if(phase == 3) {
    		 if(newT)
 	    		parray.get(turn-1).getCards().add((int)(Math.random()*4+1));
 	    	  newT=false;
    		 
    		 resetButtons();
	    	 if(turn == parray.size()) {
	    		turn = 1;
	    		phase = 1;	
	    	}
	    	else {
	    		turn++;
	    		phase = 1;
	    	}
	    	unhighlight();
    	}

    	else if(phase == -1) {
    		boolean found = false;
    		for(territoryButton x: terr)
    			if(x.getPlayer()==null) {
    				found = true;
    			}
    		if(turn == parray.size()) {
	    		turn = 1;
	    	}
	    	else{
	    		turn++;
	    		phase = -1;
	    	}
    		if(!found) {
	    		phase = 0;
	    		turn = 1;
	    		update();
	    		updateButtons();
    		}
    	}
    	
    	else if(phase == 0) {

    		if(turn == parray.size()) {
	    		turn = 1;
	    	}
	    	else{
	    		turn++;
	    	}
    		
    		if(parray.get(turn-1).getTroopsLeft()==0) {
	    		phase = 1;
	    		turn = 1;
	    		update();
	    		updateButtons();
    		}
    		
	     }
    	
    	
    	if(phase == 1) {
	    	parray.get(turn-1).setTroopsLeft(
	    			parray.get(turn-1).getTroopsLeft()+
	    			(parray.get(turn-1).getPterr().size())/6+
	    			3+
	    			cBonus());
    	}
    	
    	
    	if(parray.get(turn-1).dead)
    		return;
    	else{
	    	update();
	    	updateButtons();
	    	System.out.println("runAI");
	    	parray.get(turn-1).runAI();
    	}
    }

    public int cBonus(){
    	int total = 0;
    	for(int x =0;x<6;x++){
    		boolean t = true;
    		for(territoryButton y: continents[x]){
    			if(parray.get(turn-1)!=y.getPlayer())
    				t = false;
    		}
    		if(t)
    			total += cb[x];
    	}
    	System.out.println("total");
    	return total;
    }
    
    public void highlight() {
    	for(territoryButton x: terr) {
    		if(x == selected) {
    			x.setBorderPainted(true);
    		}
    		else
    			x.setBorderPainted(false);
    	}
    }
    
    public void unhighlight() {
    	selected = null;
    	highlight();
    }
    
    public boolean checkMove(territoryButton btn){
    	if(selected.adjacent(btn) && selected.getTroops() > 1){
    			new Battle(selected,btn,this);
    			return true;
    	}
    	else{
    		return false;	
    	}
    }

    public static boolean foundpath = false ;
    public static ArrayList<territoryButton> found = new ArrayList();
    
    public boolean pathPrime(territoryButton start, territoryButton stop){
    	foundpath = false;
    	pathFind(start);
    	if(found.contains(stop))
    		return true;
    	else
    		return false;
    }
    
    public void pathFind(territoryButton start){
    	for(territoryButton x:	start.adjacent){
    		if(start.getPlayer()== x.getPlayer() && !found.contains(x)){
	    		found.add(x);
    			pathFind(x);
    		}
    	}
    }
    
    public void move(territoryButton target){
    	System.out.println("move");
    	if(pathPrime(selected,target) && selected.getTroops() > 1){
	    	int stay=1;
			if(selected.usedTroops>0 && selected.usedTroops != selected.getTroops())
				stay = 0;
			String confirmed=null;
			int fin=0;
			while((fin > selected.getTroops()-selected.usedTroops-stay)|| fin <= 0) {
				confirmed = null;
				confirmed = JOptionPane.showInputDialog("X troops of from "+ (selected.getTroops()-selected.usedTroops-stay) + " available in " + selected.name);
				if(confirmed!=null) {
					fin = Integer.parseInt(confirmed);
					break;
				}
				else
					break;
			}
			if(confirmed == null )
				return;
			
			selected.addTroops(-fin);
			target.addTroops(fin);
			updateButtons();
			next();
    	}	
    }
    
    public boolean AImove(territoryButton selected2,territoryButton target,int troops){
    	if(pathPrime(selected2,target) && selected2.getTroops() > 1){
			selected2.addTroops(-troops);
			target.addTroops(troops);
			updateButtons();
			System.out.println("moved");
			return true;
    	}	
    	return false;
    }
    
    

    public void readFile(String s){
    	try {
    		File file = new File(s);
    		BufferedReader in = new BufferedReader(new FileReader(file));
        	
    		turn = Integer.parseInt(in.readLine());
        	phase = Integer.parseInt(in.readLine());
            bonus = Integer.parseInt(in.readLine());
            players = Integer.parseInt(in.readLine());
            
            Player[] par = new Player[6];
       	 	par[0]=new Player(Color.LIGHT_GRAY,1);
       	 	par[1]=new Player(Color.GREEN,2);
       	 	par[2]=new Player(Color.ORANGE,3);
       	 	par[3]=new Player(Color.CYAN,4);
       	 	par[4]=new Player(Color.PINK,5);
       	 	par[5]=new Player(Color.YELLOW,6);
       	 
	       	for(int x = 0; x<players; x++) {
	       		parray.add(par[x]);
	       		int terrcount = Integer.parseInt(in.readLine());
	       		for(int i=0;i < terrcount;i++) {
	       			int terrID = Integer.parseInt(in.readLine());
	       			System.out.println(terr.get(terrID).name);
	       			terr.get(terrID).setPlayer(parray.get(x));
	       			terr.get(terrID).setTroops(Integer.parseInt(in.readLine()));
	       		}
	       		int cardcount = Integer.parseInt(in.readLine());
	       		for(int i=0;i < cardcount;i++) {
	       			parray.get(x).getCards().add(Integer.parseInt(in.readLine()));
	       		}
	       	}
    		in.close();
    		update();
           	updateButtons();
    	} catch (IOException e) {
			JOptionPane.showInputDialog("file not Found");
			System.exit(0);
		}
    }

    public void writeFile() throws IOException{
    	File file = new File(JOptionPane.showInputDialog("Save Name"));
    	//file.mkdirs();
    	file.createNewFile();
    	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
    	
    	
		out.println(turn);
		out.println(phase);
		out.println(bonus); 
		out.println(players); 
   	 
       	for(int x = 0; x<players; x++) {
       		Player p = parray.get(x);
       		int terrcount = p.getPterr().size();
       		out.println(terrcount);
       		for(int i=0;i < terrcount;i++) {
       			out.println(p.getPterr().get(i).ID);
       			out.println(p.getPterr().get(i).getTroops());
       		}
       		int cardcount = p.getCards().size();
       		out.println(cardcount);
       		for(int i=0;i < cardcount;i++) {
       			out.println(p.getCards().get(i));
       		}
       	}
       	out.close();
       	
    }
    
    public boolean checkWin() {
    	Player win = terr.get(1).getPlayer();
    	for(territoryButton x: terr)
    		if(x.getPlayer()!= win)
    			return false;
 
    	return true;
    }
    
    private territoryButton A1;
    private territoryButton A2;
    private territoryButton A3;
    private territoryButton A4;
    private territoryButton A5;
    private territoryButton A6;
    private territoryButton AS1;
    private territoryButton AS10;
    private territoryButton AS11;
    private territoryButton AS12;
    private territoryButton AS2;
    private territoryButton AS3;
    private territoryButton AS4;
    private territoryButton AS5;
    private territoryButton AS6;
    private territoryButton AS7;
    private territoryButton AS8;
    private territoryButton AS9;
    private territoryButton AU1;
    private territoryButton AU2;
    private territoryButton AU3;
    private territoryButton AU4;
    private territoryButton E1;
    private territoryButton E2;
    private territoryButton E3;
    private territoryButton E4;
    private territoryButton E5;
    private territoryButton E6;
    private territoryButton E7;
    private territoryButton NA1;
    private territoryButton NA2;
    private territoryButton NA3;
    private territoryButton NA4;
    private territoryButton NA5;
    private territoryButton NA6;
    private territoryButton NA7;
    private territoryButton NA8;
    private territoryButton NA9;
    private territoryButton SA1;
    private territoryButton SA2;
    private territoryButton SA3;
    private territoryButton SA4;
    
    public JLabel Troops;
    public JLabel Phase;

    private javax.swing.JLabel BACKGROUND;
    private javax.swing.JButton BONUSTROOPS;
    private javax.swing.JButton PAUSE;
    private javax.swing.JButton SAVE;
    private javax.swing.JButton LOAD;
    public javax.swing.JButton NEXTPHASE;
    public javax.swing.JLabel TURN;
    

}


