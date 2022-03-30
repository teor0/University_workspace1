package poo.swing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import poo.regex.RegOp;
import poo.util.*;
import javax.swing.*;


	 class FrontEnd extends JFrame{
		private JButton sette, otto, nove, zero, diviso,
		cinque, due, uno, piu, meno, per,
		quattro, sei, tre, uguale, punto; 
		private JTextArea jtf;
		private JMenuItem reset;
		public FrontEnd(){
			setTitle("Calcolatrice");
			setSize(400,400);
			setLocation(500,200);
			ActionEventListener listener= new ActionEventListener();
			JMenuBar menuBar= new JMenuBar();
			this.setJMenuBar(menuBar);
			reset= new JMenuItem("Reset");
			reset.addActionListener(listener);
			menuBar.add(reset);
			JPanel q=new JPanel();
			JLabel l=new JLabel("Risultato:",JLabel.RIGHT);
			jtf=new JTextArea(1,20);
			q.add(l);
			q.add(jtf);			
			JPanel p=new JPanel();
			p.setLayout( new GridLayout(4,4,3,3) );
			 sette=new JButton("7");
			 sette.addActionListener(listener);
			 otto=new JButton("8");
			 otto.addActionListener(listener);
			 nove=new JButton("9");
			 nove.addActionListener(listener);
			 diviso=new JButton("/");
			 diviso.addActionListener(listener);
			 quattro=new JButton("4");
			 quattro.addActionListener(listener);
			 cinque=new JButton("5");
			 cinque.addActionListener(listener);
			 sei=new JButton("6");
			 sei.addActionListener(listener);
			 per=new JButton("*");
			 per.addActionListener(listener);
			 uno=new JButton("1");
			 uno.addActionListener(listener);
			 due=new JButton("2");
			 due.addActionListener(listener);
			 tre=new JButton("3");
			 tre.addActionListener(listener);
			 meno=new JButton("-");	
			 meno.addActionListener(listener);
			 zero=new JButton("0");
			 zero.addActionListener(listener);
			 punto=new JButton(".");
			 punto.addActionListener(listener);
			 uguale=new JButton("=");
			 uguale.addActionListener(listener);
			 piu=new JButton("+");
			 piu.addActionListener(listener);
			//...
			p.add(sette); p.add(otto); p.add(nove); p.add(diviso);
			p.add(quattro); p.add(cinque); p.add(sei); p.add(per);
			p.add(uno); p.add(due); p.add(tre); p.add(meno);	
			p.add(zero); p.add(punto); p.add(uguale); p.add(piu);
			//...
			add( q, BorderLayout.NORTH ); //aggiunta del pannello p alla JFrame
			add( p, BorderLayout.CENTER);	
		}
		 private class ActionEventListener implements ActionListener{
			 public void actionPerformed(ActionEvent e) {	
				 if(e.getSource()==piu) {
					 meno.setEnabled(false);
					 per.setEnabled(false);
					 diviso.setEnabled(false);
					 jtf.append("+");
				 }
				 else if(e.getSource()==uguale) {
					 piu.setEnabled(true);
					 meno.setEnabled(true);
					 per.setEnabled(true);
					 diviso.setEnabled(true);
					 String oper=jtf.getText();
					 jtf.setText("");
					 jtf.append( String.valueOf(RegOp.risultato(oper)));
				 }
				 else if(e.getSource()==meno) {
					 piu.setEnabled(false);
					 per.setEnabled(false);
					 diviso.setEnabled(false);
					 jtf.append("-");
				 }
				 else if(e.getSource()==per) {
					 piu.setEnabled(false);
					 meno.setEnabled(false);
					 diviso.setEnabled(false);
					 jtf.append("*");
				 }
				 else if(e.getSource()==diviso) {
					 piu.setEnabled(false);
					 meno.setEnabled(false);
					 per.setEnabled(false);
					 jtf.append("/");
				 }
				 else if(e.getSource()==uno) {
					 jtf.append("1");
				 }
				 
				 else if(e.getSource()==due) {
					 jtf.append("2");
				 }
				 
				 else if(e.getSource()==tre) {
					 jtf.append("3");
				 }
				 
				 else if(e.getSource()==quattro) {
					 jtf.append("4");
				 }
				 
				 else if(e.getSource()==cinque) {
					 jtf.append("5");
				 }
				 
				 else if(e.getSource()==sei) {
					 jtf.append("6");
				 }
				 
				 else if(e.getSource()==sette) {
					 jtf.append("7");
				 }
				 
				 else if(e.getSource()==otto) {
					 jtf.append("8");
				 }
				 
				 else if(e.getSource()==nove) {
					 jtf.append("9");
				 }
				 
				 else if(e.getSource()==zero) {
					 jtf.append("0");
				 }
				 
				 else if(e.getSource()==punto) {
					 jtf.append(".");
				 }	
				 else if(e.getSource()==reset) {
					 jtf.setText("");
				 }
			 }
		 }//ActionEventListener
	 }
	 public class Calcolatrice {
			public static void main( String[] args ){
				FrontEnd fe=new FrontEnd();
				fe.setVisible(true);
			}
	}//Calcolatrice
	

