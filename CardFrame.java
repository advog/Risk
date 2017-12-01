import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class CardFrame extends javax.swing.JFrame {
	private Player player;
	private JButton[] button;
	private ArrayList<JButton> selectedcards= new ArrayList();
	private javax.swing.JLabel jLabel1;
	private RiskFrame RFrame;
	private int bonus = 10;
	
	public CardFrame(Player p,RiskFrame r,int b) {
		player = p;
		button = new JButton[player.getCards().size()];
		initComponents();
        setVisible(true);
        setSize(280,400);
        RFrame = r;
        bonus = b;
	}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("select 3");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 280, 20);

        
        for (int i = 0; i < player.getCards().size(); i++) {
    	   
    	   button[i]=new JButton();
    	   button[i].setText(""+player.getCards().get(i));
    	   getContentPane().add(button[i]);
    	   button[i].setBounds(0, (i+1)*30, 73, 23);
    	   button[i].setBorder(BorderFactory.createLineBorder(Color.blue,2)); // Simple Line Border
    	   final JButton tmp = button[i];
    	   button[i].addActionListener(new ActionListener() {
    		   @Override
   		    	public void actionPerformed(ActionEvent e) {
    			   if(selectedcards.contains(tmp))
    				   selectedcards.remove(tmp);
    			   else
    				   selectedcards.add(tmp);
    			   update();
   		    	}
   			});
        }
        
        JButton CHECK = new JButton();
        CHECK.setText("check");
        getContentPane().add(CHECK);
        CHECK.setBounds(80, 30, 160,60 );
        CHECK.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	check();
		    }
		});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void update(){
    	for(JButton x: button)
    		if(selectedcards.contains(x))
    			x.setBorderPainted(true);
    		else
    			x.setBorderPainted(false);
    }
    
    private void check(){
    	int a=0,b=0,c=0,d=0;

    	for(JButton x: selectedcards) {
    		if(x.getText().equals("1"))
    			a++;
    		if(x.getText().equals("2"))
    			b++;
    		if(x.getText().equals("3"))
    			c++;
    		if(x.getText().equals("4"))
    			d++;
    	}
    	System.out.println(a+" "+b+" "+c+" "+d);
    	if(a+d >= 3 || b+d >= 3 || c+d >= 3 || (a>=1 && b>=1 && d>=1) || (a>=1 && c>=1 && d>=1) || (c>=1 && b>=1 && d>=1) || (a>=1 && b>=1 && c>=1)) {
    		player.addTroops(bonus);
    		RFrame.update();
    		RFrame.addb();
    		dispose();
    	}
    }
}
