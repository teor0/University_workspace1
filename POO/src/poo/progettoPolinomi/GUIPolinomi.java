package poo.progettoPolinomi;
import java.io.*;
import java.util.*;
import poo.polinomi.*;
import poo.util.PoliUtil;
import javax.swing.*;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.*;
class FinestraGUI extends JFrame{
	private String title= "Progetto polinomi";
	private String impl=null;
	private File saveFile=null;
	private JMenuItem implLL;
	private JMenuItem implAL;
	private JMenuItem implSet;
	private JMenuItem save, saveAs, open, close, about, add, remove, removeAll, clear, list, addToColl;
	private JMenuItem derivata, valutazione, sum, mul;
	private Collection<Polinomio> collezione=null;	
	private JPanel pan;
		
	public FinestraGUI() {
		setTitle(title);
		setLocation(300,200);
		setSize(500,400);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener( new WindowAdapter() {
		       public void windowClosing(WindowEvent e){
		    	   if( consensoUscita() ) 
		    		 System.exit(0);
		       }
		});
		
		ActionEventListener listener= new ActionEventListener();
			
		//Creazione menu ed implentazione per creare il polinomio
		JMenuBar menuBar= new JMenuBar();
		this.setJMenuBar(menuBar);
		JMenu fileMenu= new JMenu("File");
		menuBar.add(fileMenu);
		JMenu comandi=new JMenu("Comandi");
		menuBar.add(comandi);
		JMenu operazioni= new JMenu("Operazioni");
		menuBar.add(operazioni);
		about= new JMenuItem("About");
		menuBar.add(about);
		about.addActionListener(listener);
		
		JMenu implType= new JMenu("Nuova");
		//sottomenu di nuova
		fileMenu.add(implType);
		implLL= new JMenuItem("Collezione su LinkedList");
		implType.add(implLL);
		implLL.addActionListener(listener);
		implSet= new JMenuItem("Collezione su Set");
		implType.add(implSet);
		implSet.addActionListener(listener);
		implAL= new JMenuItem("Collezione su ArrayList");
		implType.add(implAL);
		implAL.addActionListener(listener);
		fileMenu.addSeparator();
		
		//sottomenu di file
		open= new JMenuItem("Apri file");
		fileMenu.add(open);
		open.addActionListener(listener);
		save= new JMenuItem("Salva");
		fileMenu.add(save);
		save.addActionListener(listener);
		saveAs= new JMenuItem("Salva con nome");
		fileMenu.add(saveAs);
		saveAs.addActionListener(listener);
		close= new JMenuItem("Esci");
		close.addActionListener(listener);
		fileMenu.add(close);
		
		//sottomenu di comandi
		add= new JMenuItem("Inserisci");
		comandi.add(add);
		add.addActionListener(listener);
		remove= new JMenuItem("Rimuovi");
		comandi.add(remove);
		remove.addActionListener(listener);
		removeAll= new JMenuItem("Rimuovi tutti");
		comandi.add(removeAll);
		removeAll.addActionListener(listener);
		comandi.addSeparator();
		clear=  new JMenuItem("Svuota collezione");
		comandi.add(clear);
		clear.addActionListener(listener);
		list= new JMenuItem("Mostra collezione");
		comandi.add(list);
		list.addActionListener(listener);
		addToColl=new JMenuItem("Aggiungi alla collezione");
		comandi.add(addToColl);
		addToColl.addActionListener(listener);
		//sottomenu di operazioni
		derivata=new JMenuItem("Derivata");
		operazioni.add(derivata);
		derivata.addActionListener(listener);
		mul=new JMenuItem("Moltiplicazione");
		operazioni.add(mul);
		mul.addActionListener(listener);
		sum=new JMenuItem("Somma");
		operazioni.add(sum);
		sum.addActionListener(listener);
		valutazione=new JMenuItem("Valuta");
		operazioni.add(valutazione);
		valutazione.addActionListener(listener);
		
		//settaggio operazioni possibili
		save.setEnabled(false);
		saveAs.setEnabled(false);
		add.setEnabled(false);
		remove.setEnabled(false);
		clear.setEnabled(false);
		list.setEnabled(false);
		addToColl.setEnabled(false);
		derivata.setEnabled(false);
		sum.setEnabled(false);
		mul.setEnabled(false);
		valutazione.setEnabled(false);
		pan= new JPanel();
		this.add(pan);
	}//costruttore
		
	private boolean consensoUscita(){
		int option=JOptionPane.showConfirmDialog(null, "Continuare ?", "Uscendo si perderanno tutti i dati!", JOptionPane.YES_NO_OPTION);
		return option==JOptionPane.YES_OPTION;
	}//consensoUscita
	
	private void collCreata() {
		 save.setEnabled(true);
		 saveAs.setEnabled(true);
		 add.setEnabled(true);
		 clear.setEnabled(true);
		 list.setEnabled(true);
		 sum.setEnabled(true);
		 mul.setEnabled(true);
	 }//collcreata
	
	private class FrameAL extends JFrame implements ActionListener{
		 private JButton ok;
		 private JTextField cap;
		 
		 public FrameAL() {
			 setTitle("Capacita' array");
			 setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	     		addWindowListener( new WindowAdapter() {
	     			public void windowClosing(WindowEvent e) {
	     				cap.setText("");
	     				FrameAL.this.setVisible(false);
	     			}
	     		});
	     	JPanel p= new JPanel();
	     	p.add(new JLabel("Capacita' array", JLabel.RIGHT));
	     	p.add(cap=new JTextField("",10));
	     	p.add(ok=new JButton("OK"));
	     	ok.addActionListener(this);
	     	add(p);
	     	setLocation(250,350);
  	     	setSize(300,200);
		 }//costruttore
		 
		 public void actionPerformed( ActionEvent e ){
  	    	 if(e.getSource()==ok) {
  	    		 String c=cap.getText();
  	    		 collezione=new ArrayList<Polinomio>(Integer.parseInt(c));
  	    		 this.setVisible(false);
  	    		 cap.setText("");
  	    	 }
     	} 
	 }//FrameAL
	
	 private class FrameNuovoPolinomio extends JFrame implements ActionListener{
		 private JButton ok;
		 private JTextField inserimento;
		
		 public FrameNuovoPolinomio() {
			 setTitle("Nuovo polinomio");
			 setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
   	     		addWindowListener( new WindowAdapter() {
   	     			public void windowClosing(WindowEvent e) {
   	     				inserimento.setText("");
   	     				FrameNuovoPolinomio.this.setVisible(false);
   	     			}
   	     		});
   	     	 JPanel p=new JPanel();
   	     	 p.add(new JLabel("Polinomio",JLabel.RIGHT));
   	     	 p.add(inserimento=new JTextField("",20));
   	     	 p.add(ok=new JButton("OK"));
   	     	 ok.addActionListener(this);
   	     	 add(p);
   	     	 setLocation(250,340);
   	     	 setSize(400,150);
		 }//costruttore
		
		 public void actionPerformed( ActionEvent e ){
   	    	 if(e.getSource()==ok) {
   	    		 inserisci(inserimento.getText());
   	    		 this.setVisible(false);
   	    		 inserimento.setText("");
   	    	 }
      	}
	 }//framenuovopolinomio
	
	private void inserisci(String poli) {
		Polinomio n=null;
			try {
				n=PoliUtil.validazione(poli);
				JCheckBox cb= new JCheckBox(n.toString());
				pan.add(cb);
				cb.addActionListener(ActionListener -> {;
				if(cb.isSelected() ) {
					remove.setEnabled(true);
					addToColl.setEnabled(true);
					derivata.setEnabled(true);
					valutazione.setEnabled(true);
				}
				else {
					remove.setEnabled(false);
					addToColl.setEnabled(false);
					derivata.setEnabled(false);
					valutazione.setEnabled(false);
				}
				});
				pan.revalidate();
			}//try
			catch(IllegalArgumentException e){
				JOptionPane.showMessageDialog(this, "Polinomio impossibile");
			}		
	}//insert
	
	private void ripristinaPolinomi(File nomeFile)throws IOException {
		BufferedReader br= new BufferedReader(new FileReader(nomeFile));
		for(;;) {
			String poli=br.readLine();
			if(poli==null)
				break;
			inserisci(poli);
		}
		br.close();
	}//ripristinaPolinomi
	
		
	private class FrameElenco extends JFrame{
   	 private JTextArea area;
   	 public FrameElenco(){
   		 setTitle("Elenco polinomi");
   		 setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   	     JPanel p=new JPanel();
   	     p.setLayout( new FlowLayout() ); 
   	     area=new JTextArea(10, 25);
   	     area.setEditable(false);
   	 	 JScrollPane sp=new JScrollPane( area );
   	     p.add( sp ); 
   	     add(p);
   	     if( collezione.size()!=0 )
   	    	 for(Polinomio poli:collezione)
   	    		 area.append(poli.toString()+ "\n");
   	     else
   	    	 area.append("Collezione vuota!");
   		 setLocation(250,340);
   		 setSize(400,150);
   	 }//costruttore
    }//FrameElenco
	 	 
	 private class ActionEventListener implements ActionListener{
		 public void actionPerformed(ActionEvent e) {	
			if(e.getSource()==close) {
				if(consensoUscita())
					System.exit(0);
			}
			else if(e.getSource()==implAL) {
				JFrame fal=new FrameAL();
				fal.setVisible(true);
				impl="ArrayList";
				setTitle(title+ " - "+ impl);
				collCreata();
			}	
			else if(e.getSource()==implSet) {
				collezione=new LinkedHashSet<Polinomio>();
				impl="LinkedHashSet";
				setTitle(title+ " - "+ impl);
				collCreata();
			}
			else if(e.getSource()==implLL) {
				collezione= new LinkedList<Polinomio>();
				impl="LinkedList";
				setTitle(title+ " - "+ impl);
				collCreata();
			}
			else if(e.getSource()==save) {
				  JFileChooser chooser=new JFileChooser();
	  			   try{
	  				   if( saveFile!=null ){
	  					   int ans=JOptionPane.showConfirmDialog(null,"Sovrascrivere "+saveFile.getAbsolutePath()+" ?");
						   if( ans==0) {
							  PoliUtil.salva(saveFile.getAbsolutePath(),collezione);
						   }
						   else {
							   try {
									if(chooser.showOpenDialog(FinestraGUI.this)==JFileChooser.APPROVE_OPTION) {
											saveFile=chooser.getSelectedFile();
											FinestraGUI.this.setTitle(title+" "+saveFile.getName());
									}
									if(saveFile!=null) {
										PoliUtil.salva(saveFile.getAbsolutePath(), collezione);
									}
									else
										JOptionPane.showMessageDialog(null, "Nessun salvataggio.", "Salvataggio", JOptionPane.INFORMATION_MESSAGE);
								}
								catch(Exception exc) {
									exc.printStackTrace();
								}		   
						   }//else
						   return;
					   }//try
	  				   if( chooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION ){
	  					   saveFile=chooser.getSelectedFile();
	  					   FinestraGUI.this.setTitle(title+ " - " + impl+ saveFile.getName());
	  				   }
	  				   if( saveFile!=null ){
	  					 PoliUtil.salva(saveFile.getAbsolutePath(),collezione);
	  				   }
	  				   else
	  					   JOptionPane.showMessageDialog(null,"Nessun Salvataggio!");  
	  			   }
	  			   catch( Exception exc ){
	  				   exc.printStackTrace();
	  			   }
			}//save
			
			else if(e.getSource()==open) {
				JFileChooser chooser=new JFileChooser();
				try {
					if(chooser.showOpenDialog(FinestraGUI.this)==JFileChooser.APPROVE_OPTION) {
						if(!chooser.getSelectedFile().exists()) {
							JOptionPane.showMessageDialog(null, "File non esistente!", "Errore", JOptionPane.ERROR_MESSAGE);
						}
						else {
							saveFile=chooser.getSelectedFile();
							FinestraGUI.this.setTitle(title+" "+saveFile.getAbsolutePath());
							try {
								collezione=PoliUtil.ripristina(saveFile);
								ripristinaPolinomi(saveFile);
								collCreata();
							}
							catch(IOException ioe){
								JOptionPane.showMessageDialog(null,"Fallimento apertura. File corrotto!", "Errore", JOptionPane.ERROR_MESSAGE);
							}
						}
					}//if
					else {
						 JOptionPane.showMessageDialog(null,"Nessuna apertura!","Apertura file",JOptionPane.ERROR_MESSAGE);
					}
				}//try
				catch(Exception ex){
					ex.printStackTrace();
				}
			}//open
			
			else if(e.getSource()==saveAs) {
				JFileChooser chooser=new JFileChooser();
				try {
					if(chooser.showOpenDialog(FinestraGUI.this)==JFileChooser.APPROVE_OPTION) {
							saveFile=chooser.getSelectedFile();
							FinestraGUI.this.setTitle(title+" "+saveFile.getName());
					}
					if(saveFile!=null)
						PoliUtil.salva(saveFile.getAbsolutePath(), collezione);
					else
						JOptionPane.showMessageDialog(null, "Nessun salvataggio.", "Salvataggio", JOptionPane.INFORMATION_MESSAGE);
				}//try
				catch(Exception exc) {
					exc.printStackTrace();
				}		   
			}//saveAs
			
			else if(e.getSource()==add) {
				JFrame fnp= new FrameNuovoPolinomio();
				fnp.setVisible(true);
			}
			
			else if(e.getSource()==remove) {
				Component[] com= pan.getComponents();
				for(int i=0; i<com.length; i++) {
					JCheckBox c=(JCheckBox) com[i];
					if(c.isSelected())
						pan.remove(c);
				}
				pan.revalidate();
				pan.repaint();
			}
			
			else if(e.getSource()==removeAll) {
				if(pan.getComponentCount()>=1){
					Component[] com= pan.getComponents();
					for(int i=0; i<com.length; i++)
						pan.remove(com[i]);
				}
				else {
					JOptionPane.showMessageDialog(FinestraGUI.this, "Nessun polinomio presente","Errore", JOptionPane.ERROR_MESSAGE);
				}
				pan.revalidate();
				pan.repaint();
			}
			
			else if(e.getSource()==clear) {
				collezione.clear();
				setTitle(title);
			}
			
			else if(e.getSource()==list) {
				JFrame ele= new FrameElenco();
				ele.setVisible(true);
			}
			
			else if(e.getSource()==about) {
				JOptionPane.showMessageDialog(FinestraGUI.this, "Progetto Polinomio di Matteo Orlando, matricola:213430","About", JOptionPane.INFORMATION_MESSAGE);
			}
			
			else if(e.getSource()==derivata) {
				Component[] com= pan.getComponents();
				for(int i=0; i<com.length; i++) {
					JCheckBox c=(JCheckBox) com[i];
					Polinomio p=PoliUtil.validazione(c.getText());
					if(c.isSelected())
						inserisci(p.derivata().toString());
				}
			}
			
			else if(e.getSource()==addToColl) {
				Component[] com= pan.getComponents();
				for(int i=0; i<com.length; i++) {
					JCheckBox c=(JCheckBox) com[i];
					Polinomio p=PoliUtil.validazione(c.getText());
					if(c.isSelected()) {
						if(!collezione.contains(p))
							collezione.add(p);
						else
							JOptionPane.showMessageDialog(null, "Polinomio gia' presente: "+ p, "Errore", JOptionPane.ERROR_MESSAGE);;
					}
				}
			}//addToColl
			
			else if(e.getSource()==sum) {
				if(pan.getComponentCount()>=2){
					ArrayList<Polinomio> addendi=new ArrayList<>();
					Component[] com=pan.getComponents();
					for(int i=0; i<com.length; i++) {
						JCheckBox c=(JCheckBox) com[i];
						if(c.isSelected()) {
							if(addendi.size()<2)
							addendi.add(PoliUtil.validazione(c.getText()));
						}
					}
					Polinomio p1=addendi.get(0);
					Polinomio p2=addendi.get(1);
					inserisci(p1.add(p2).toString());
				}
				else {
					JOptionPane.showMessageDialog(FinestraGUI.this, "Nessun polinomio presente/numero di polinomi insufficiente","Errore", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			else if(e.getSource()==valutazione) {
				Component[] com= pan.getComponents();
				for(int i=0; i<com.length; i++) {
					JCheckBox c=(JCheckBox) com[i];
					if(c.isSelected()) {
						Polinomio p=PoliUtil.validazione(c.getText());
						String s=JOptionPane.showInputDialog("Inserisci il valore con cui sostituire la x");
						JOptionPane.showMessageDialog(null, p.valore(Double.parseDouble(s)),"Risultato", JOptionPane.PLAIN_MESSAGE);
					}
				}
			}
			
			else if(e.getSource()==mul) {
				if(pan.getComponentCount()>=2){
					ArrayList<Polinomio> fattori=new ArrayList<>();
					Component[] com=pan.getComponents();
					for(int i=0; i<com.length; i++) {
						JCheckBox c=(JCheckBox) com[i];
						if(c.isSelected()) {
							if(fattori.size()<2)
							fattori.add(PoliUtil.validazione(c.getText()));
						}
					}
					Polinomio p1=fattori.get(0);
					Polinomio p2= fattori.get(1);
					inserisci(p1.mul(p2).toString());
				}
				else {
					JOptionPane.showMessageDialog(FinestraGUI.this, "Nessun polinomio presente/numero di polinomi insufficienti","Errore", JOptionPane.ERROR_MESSAGE);
				}
			}
		}//actionPerformed
	}//Listener
		
}//FinestraGUI

	public class GUIPolinomi {
		public static void main(String[] args) {
			FinestraGUI finestra= new FinestraGUI();
			finestra.setVisible(true);
		}
	}//GUI