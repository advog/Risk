/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.*;
import java.awt.Color;
import java.util.*;
import java.awt.event.*;
/**
 *
 * @author avliv
 */

public class territoryButton extends JButton{
    private RiskFrame RFrame;
	private Player player;
	private int troops;
	public String name;
	public int usedTroops=0;
	public int ID;
	public ArrayList<territoryButton> adjacent= new ArrayList();
	
	territoryButton(RiskFrame r){
	RFrame = r;
	this.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        territoryButton btn =  (territoryButton) e.getSource();
		        btn.Clicked();
		    }
		});
	}
	
	public void Clicked() {
		System.out.println("clicked");
		if(RFrame.phase == -1 && player == null) {
        	setPlayer(RFrame.parray.get(RFrame.turn-1));
        	player.getPterr().add(this);
        	troops=1;
        	setText(name);
        	RFrame.next();
        }
		
		else if(RFrame.phase == 0 && this.player.getPlayerID() == RFrame.turn) {
			addTroops(1);
			player.setTroopsLeft(player.getTroopsLeft()-1);
        	RFrame.update();
        	RFrame.updateButtons();
        	RFrame.next();
        	
        }
		
		else if(player.getTroopsLeft()>0 && RFrame.phase==1 && player.getPlayerID() == RFrame.turn) {
			if(player.getTroopsLeft()>0){
				addTroops(1);
				player.setTroopsLeft(player.getTroopsLeft()-1);
        	}
        	
        	RFrame.update();
        	RFrame.updateButtons();
        	
        }
		
		
		else if(RFrame.phase == 2 && this.player.getPlayerID() == RFrame.turn) {
			if(RFrame.selected == this) {
    			RFrame.selected=null;
    		}
    		else {
    			RFrame.selected=this;
    		}
    		RFrame.highlight();
    	
		}
		else if(RFrame.phase == 2 && this.player.getPlayerID() != RFrame.turn && RFrame.selected != null) {
			RFrame.checkMove(this);
			RFrame.updateButtons();
		}
		else if(RFrame.phase == 3 && this.player.getPlayerID() == RFrame.turn && RFrame.selected == null ||RFrame.selected == this) {
			if(RFrame.selected == this) {
    			RFrame.selected=null;
    		}
    		else {
    			RFrame.selected=this;
    		}
    		RFrame.highlight();
		}
		else if(RFrame.phase == 3 && this.player.getPlayerID() == RFrame.turn) {
			RFrame.move(this);
			RFrame.updateButtons();
		}
	}
	
	public void update(){
		setText(""+troops);
		setBackground(player.getColor());
	}
	public void setPlayer(Player p){
		if(player != null )
			player.getPterr().remove(this);
		p.getPterr().add(this);
		player = p;
		update();
	}
	public void addTroops(int t) {
		troops += t;
		update();
	}
	public void setTroops(int t) {
		troops = t;
	}
	
	public boolean adjacent(territoryButton t) {
	    	territoryButton target = t;
	    	if(adjacent.contains(target))
	    		return true;
	    	else return false;
	   }
	
	public void reset(){
		usedTroops=0;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUsedTroops() {
		return usedTroops;
	}
	public void setUsedTroops(int usedTroops) {
		this.usedTroops = usedTroops;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public ArrayList<territoryButton> getAdjacent() {
		return adjacent;
	}
	public Player getPlayer() {
		return player;
	}
	public int getTroops() {
		return troops;
	}
}
