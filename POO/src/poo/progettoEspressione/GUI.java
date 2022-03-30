package poo.progettoEspressione;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
class Finestra extends JFrame{
	private String title="Progetto valutazione espressione";
	private JMenuItem about;
	private JButton calcola;
	private JTextField text;
	private JPanel pan;
	public Finestra() {
		setTitle(title);
		setLocation(300,250);
		setSize(350,350);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener( new WindowAdapter() {
		       public void windowClosing(WindowEvent e){
		    	   if( uscita() ) 
		    		 System.exit(0);
		       }
		});
		ActionEventListener listener= new ActionEventListener();
		JMenuBar menubar= new JMenuBar();
		setJMenuBar(menubar);
		about= new JMenuItem("About");
		menubar.add(about);
		about.addActionListener(listener);
		calcola= new JButton("Calcola");
		calcola.addActionListener(listener);
		text=new JTextField("", 20);
		pan=new JPanel();
		pan.add(text);
		pan.add(calcola);
		this.add(pan);
	}
	
	
	private class ActionEventListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==about)
				JOptionPane.showMessageDialog(Finestra.this, "Progetto valuta espressione di Matteo Orlando, matricola:213430","About", JOptionPane.INFORMATION_MESSAGE);
			else if(e.getSource()==calcola) {
				String espressione=text.getText();
				try {
				text.setText(""+Util.valutaEspressione(espressione));
				}
				catch(Exception exc){
					JOptionPane.showMessageDialog(null,"Espressione Malformata");
				}
			}
		}
	}
	
	private boolean uscita() {
		int option=JOptionPane.showConfirmDialog(null, "Sei sicuro di voler uscire?", "Uscita", JOptionPane.YES_NO_OPTION);
		return option==JOptionPane.YES_OPTION;
	}
	
}


public class GUI {
	public static void main(String[] args) {
		JFrame f= new Finestra();
		f.setVisible(true);
	}
}
