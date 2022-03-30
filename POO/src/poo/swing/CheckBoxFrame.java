package poo.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class CheckBoxFrame extends JFrame
	{
	private JLabel label;
	private JCheckBox bold;
	private JCheckBox italic;
	private Pannello pan;
	private static final int FONTSIZE = 24;
	
	/*private class ActionEventListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		}
	}*/

	public CheckBoxFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		label = new JLabel("The quick brown fox jumps over the lazy dog.");
		label.setFont(new Font("Serif", Font.PLAIN, FONTSIZE));
		add(label, BorderLayout.CENTER);
		ActionListener listener = event-> {
			 int mode = 0;
			 if (bold.isSelected()) 
				 mode += Font.BOLD;
			 if (italic.isSelected()) 
				 mode += Font.ITALIC;
			 label.setFont(new Font("Serif", mode, FONTSIZE));
		};
		//var buttonPanel = new JPanel();
		 pan=new Pannello();
		 bold = new JCheckBox("Bold");
		 bold.addActionListener(listener);
		 pan.agg(bold,0);
		
		 italic = new JCheckBox("Italic");
		 italic.addActionListener(listener);
		 pan.agg(italic,1);
		 add(pan, BorderLayout.SOUTH);
		 pack();
		 
		
		 }//costruttore
	
	 	private class Pannello extends JPanel{
	 		JList lis;
	 		JCheckBox[] a;
	 		public Pannello() {
	 			a= new JCheckBox[10];
	 			lis=new JList(a);
	 			lis.setBackground(Color.BLACK);
	 		}
	 		public void agg(JCheckBox cb, int i) {
	 			a[i]=cb;
	 			this.repaint();
	 			System.out.println(lis.getComponentCount());
	 			System.out.println("aggiunto");
	 		}
	 	}
	public static void main(String [] args) {
		JFrame f= new CheckBoxFrame();
		f.setVisible(true);
	}
	
}
