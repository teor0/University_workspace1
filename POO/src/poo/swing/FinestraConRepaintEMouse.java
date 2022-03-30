//
//  Altro esempio di finestra creabile con Java swing
//  che risponde all'evento di chiusura (si clicca
//  sul pulsante X o si digita ALT-F4 etc.) ed
//  ammette un pannello per la visualizzazione grafica.
//  Ad ogni ri-visualizzazione della
//  finestra (es. massimizzazione dopo una minimizzazione), 
//  il metodo paintComponent
//  sopperisce al fatto che Java AWT/Swing non memorizza
//  i pixel di una finestra e ri-crea la grafica di interesse.
//  Nell'esempio, la ri-visualizzazione e' anche invocata
//  da mouse click sul pannello.
//*********************************************************
package poo.swing;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class FinestraRepaintMouse extends JFrame{

   private Pannello p=null;
   private Font f=new Font("Helvetica", Font.BOLD, 20);
   private Color col=new Color( /*red*/57, /*green*/128, /*blu*/110 );
   private MouseList ml=null;
   private int x, y;
   
   public FinestraRepaintMouse(){
      setTitle("Finestra con Repaint");
      setSize(400,200);
      setLocation(50,200);
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      add( p=new Pannello(), BorderLayout.CENTER );
      ml=new MouseList();
      addMouseListener(ml);
   }

    private class Pannello extends JPanel{ //esempio di JComponent
      public Pannello(){
          addMouseListener(ml);
      }
      public void acheck(boolean b) {
    	  if(b) {
    		  setBackground(Color.BLUE);
    		  add(new JCheckBox("ciao"));
    	  }
    	  System.out.println("si");
    	  repaint();
      }
   }//Pannello

   private class MouseList extends MouseAdapter{
	   public void mouseClicked(MouseEvent e){//callback
		   FinestraRepaintMouse.this.x=e.getX();
		   FinestraRepaintMouse.this.y=e.getY();
		   FinestraRepaintMouse.this.p.acheck(true);
		   //FinestraRepaintMouse.this.p.repaint();//effetto asincrono su paintComponent
	   }
   }//MouseList

}//FinestraRepaintMouse

public class FinestraConRepaintEMouse{
   public static void main( String []args ){
	   JFrame f=new FinestraRepaintMouse();
	   f.setVisible(true);			  	   
   }//main
}//FinestraConRepaint
