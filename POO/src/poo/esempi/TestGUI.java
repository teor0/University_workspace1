package poo.esempi;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
	class FinestraButtons extends JFrame{
		private JButton bNero;
		private JButton bBianco, bRosso, bVerde, bBlu;
		private Color c;
		private Pannello panel;
		private Listener listener;
	
		public FinestraButtons() {
			setTitle("Finestra di prova");
			setSize(300,250);
			setLocation(600,250);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
			panel=new Pannello();
			listener= new Listener();
			bNero= new JButton("Nero");
			bNero.addActionListener(listener);
			bBianco= new JButton("Bianco");
			bBianco.addActionListener(listener);
			bRosso= new JButton("Rosso");
			bRosso.addActionListener(listener);
			bVerde= new JButton("Verde");
			bVerde.addActionListener(listener);
			bBlu= new JButton("Blu");
			bBlu.addActionListener(listener);
			panel.add(bBianco);
			panel.add(bNero);
			panel.add(bVerde);
			panel.add(bRosso);
			panel.add(bBlu);
			add(panel,BorderLayout.CENTER);
		}
	
	class Listener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==bBianco)
				c=Color.WHITE;
			else if(e.getSource()==bBlu)
				c=Color.BLUE;
			else if(e.getSource()==bNero)
				c=Color.BLACK;
			else if(e.getSource()==bRosso)
				c=Color.RED;
			else if(e.getSource()==bVerde)
				c=Color.GREEN;
			panel.repaint();
		}
	}
	
	class Pannello extends JPanel{
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			setBackground(c);
		}
	}
}
	public class TestGUI {
		public static void main(String[] args) {
			EventQueue.invokeLater(()->{
				JFrame f= new FinestraButtons();
				f.setVisible(true);
			});
		}
	}

	

