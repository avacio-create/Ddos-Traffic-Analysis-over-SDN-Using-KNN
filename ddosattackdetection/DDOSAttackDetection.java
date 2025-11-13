/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ddosattackdetection;

import com.birosoft.liquid.LiquidLookAndFeel;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DDOSAttackDetection {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
           try {
            LiquidLookAndFeel.setLiquidDecorations(true, "mac");
              //LiquidLookAndFeel.setStipples(false);
              LiquidLookAndFeel.setPanelTransparency(true);
              javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
 Host ho=new Host();
 ho.show();
 
  Agents ag=new Agents();
 ag.show();
 
  OVSwitch ov=new OVSwitch();
 ov.show();
 
  TrafficGeneration tg=new TrafficGeneration();
 tg.show();
 
   }
        });
          } 
                catch (Exception ex) {
            Logger.getLogger(Host.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
}
