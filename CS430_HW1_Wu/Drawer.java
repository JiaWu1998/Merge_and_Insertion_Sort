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
        
        //draw snap shot
        g.setColor(Color.BLACK);

        int w = getWidth()/current_snap_shot.size();
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
        bound = b;    
    }
}


