import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class Drawer extends JPanel{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public ArrayList<Integer> current_snap_shot;
    public int bound;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        //draws a snap shot
        g.setColor(Color.BLACK);
        int w = getWidth()/current_snap_shot.size();
        if(w == 0){
            g.drawString("Make Your Screen Bigger.", getWidth()/2, getHeight()/2);
        }
        for(int i = 0; i < current_snap_shot.size(); i ++){
            int temp = (int) (((float) current_snap_shot.get(i)/ (float) bound) * (float) getHeight());
            g.fillRect(i*w, getHeight()-(temp), w, temp);
        }
    }

    public void setSnap(ArrayList<Integer> s){
        //getting the current snap 
        current_snap_shot = s;
    }

    public void setMaxNum(int b){
        //gets the max random integer
        bound = b;    
    }
}


