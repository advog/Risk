import java.util.ArrayList;
import javax.swing.*;
import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author avliv
 */


import javax.swing.*;

public class Battle extends javax.swing.JFrame {
	private territoryButton target;
	private territoryButton selected;
	private int atroops;
	private int dtroops;
	private int[] A = new int[3];
	private int[] D = new int[2];
	private RiskFrame RFrame;
	private boolean resolve =false;
	
	public Battle(territoryButton s,territoryButton b,RiskFrame r){
        initComponents();
        RFrame = r;
        selected = s;
        target = b;
        setup();
        setSize(450,400);
        setVisible(true);
    }

	public Battle(territoryButton s,territoryButton b,RiskFrame r,int a,boolean re){
        initComponents();
        RFrame = r;
        selected = s;
        target = b;
        
        setSize(450,400);
        setVisible(true);
        atroops = a;
        resolve = re;
        aisetup();
    }

	private void initComponents() {

        RollA = new javax.swing.JButton();
        RollD = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        DEF1 = new javax.swing.JLabel();
        DEF2 = new javax.swing.JLabel();
        ATT3 = new javax.swing.JLabel();
        ATT1 = new javax.swing.JLabel();
        ATT2 = new javax.swing.JLabel();
        RETREAT = new javax.swing.JButton();

        RollA.setText("ROLL");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        RollD.setText("ROLL");
        RollD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RollDActionPerformed(evt);
            }
        });
        getContentPane().add(RollD);
        RollD.setBounds(290, 210, 100, 90);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setForeground(java.awt.Color.darkGray);
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("0");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(170, 100, 130, 110);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel2.setForeground(java.awt.Color.red);
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("0");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(90, 100, 120, 110);

        DEF1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DEF1.setText("0");
        getContentPane().add(DEF1);
        DEF1.setBounds(330, 10, 60, 60);

        DEF2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DEF2.setText("0");
        getContentPane().add(DEF2);
        DEF2.setBounds(330, 70, 60, 50);

        ATT3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ATT3.setText("0");
        getContentPane().add(ATT3);
        ATT3.setBounds(0, 250, 60, 40);

        ATT1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ATT1.setText("0");
        getContentPane().add(ATT1);
        ATT1.setBounds(10, 170, 40, 40);

        ATT2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ATT2.setText("0");
        getContentPane().add(ATT2);
        ATT2.setBounds(10, 210, 40, 40);

        RETREAT.setText("RETREAT");
        RETREAT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RETREATActionPerformed(evt);
            }
        });
        getContentPane().add(RETREAT);
        RETREAT.setBounds(0, 0, 100, 90);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RollDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RollDActionPerformed
       roll();
    }
    
    private void RETREATActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RollDActionPerformed
       retreat();
    }
   
    public void aisetup(){
		selected.addTroops(-atroops);
		dtroops = target.getTroops();
		
		update();
		roll();
    }
    
    public void setup(){
    		int stay=1;
    		
    		if(selected.usedTroops>0 && selected.usedTroops != selected.getTroops())
    			stay = 0;
    		
    		String confirmed=null;
    		int fin=0;
    		while((fin > selected.getTroops()-selected.usedTroops-stay)|| fin <= 0) {
				confirmed = null;
    			confirmed = JOptionPane.showInputDialog("X troops of from "+ (selected.getTroops()-selected.usedTroops-stay) + " available in " + selected.name);
				if(confirmed!=null)
					fin = Integer.parseInt(confirmed);
				else
					break;
    		}
    		if(confirmed == null )
    			dispose();
    		
    		atroops = fin;
    		selected.addTroops(-atroops);
    		dtroops = target.getTroops();
    		update();
    }
    
    public void update(){
    	jLabel2.setText(""+atroops);
    	jLabel1.setText(""+dtroops);
    	ATT1.setText(""+A[0]);
    	ATT2.setText(""+A[1]);
    	ATT3.setText(""+A[2]);
    	DEF1.setText(""+D[0]);
    	DEF2.setText(""+D[1]);
    }
    
    public void roll(){
    	
    	
    	A[0]=0;
    	A[1]=0;
    	A[2]=0;
    	D[0]=0;
    	D[1]=0;
    	
    	int attd=atroops;
    	int defd=dtroops;
    	if(attd>3)
    		attd=3;
    	if(defd>2)
    		defd=2;
    	
    	for (int i = 0; i < attd; i++) {
    		A[i]=(int)(Math.random()*6+1);
		}
    	for (int i = 0; i < defd; i++) {
    		D[i]=(int)(Math.random()*6+1);
		}
    	
    	Arrays.sort(A);
    	Collections.reverse(Arrays.asList(A));
    	Arrays.sort(D);
    	Collections.reverse(Arrays.asList(D));
    	
    	if(A[2]<=D[1])
    		atroops--;
    	else
    		dtroops--;
    	
    	if(D[0] != 0) {
    		if(A[1]<=D[0])
    			atroops--;
    		else
    			dtroops--;
    	}
    	
    	update();
    	
    	if(atroops < 1){
    		defeat();
    	}
    	if(dtroops < 1){
    		victory();
    	}
    		
    	if(resolve){
    		System.out.println("ran");
    		roll();
    	}
    		
    }
    
    public void retreat(){
    	target.setTroops(dtroops);
    	selected.usedTroops+=atroops;
    	selected.addTroops(atroops);
    	System.out.println(selected.usedTroops);
    	System.out.println(selected.getTroops());
    	RFrame.updateButtons();
    	resolve = false;
    	dispose();
    }
    
    public void victory(){
    	target.setPlayer(selected.getPlayer());
    	target.setTroops(atroops);
    	RFrame.updateButtons();
    	resolve = false;
    	RFrame.newT=true;
    	dispose();
    }
    
    public void defeat(){
    	target.setTroops(dtroops);
    	RFrame.updateButtons();
    	resolve = false;
    	dispose();
    }
    
    private javax.swing.JLabel ATT1;
    private javax.swing.JLabel ATT2;
    private javax.swing.JLabel ATT3;
    private javax.swing.JLabel DEF1;
    private javax.swing.JLabel DEF2;
    private javax.swing.JButton RollA;
    private javax.swing.JButton RollD;
    private javax.swing.JButton RETREAT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
}
