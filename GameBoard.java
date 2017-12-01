import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GameBoard{
		
	public static void main(String[] args) {
		String[] options = new String[] {"New", "Load"};
	   	int response = JOptionPane.showOptionDialog(null, "Start Menu", "Start Menu",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null, options, options[0]);
	   	if(response == 0) {    		
	   		new RiskFrame(Integer.parseInt(JOptionPane.showInputDialog("How many pLayers?")));
        }
        else {
        	new RiskFrame(JOptionPane.showInputDialog("Save Path"));
        }
	}
}
