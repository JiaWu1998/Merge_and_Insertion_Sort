import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class Drawer extends JPanel{
    public ArrayList<Integer> current_snap_shot;
    public int width, height, bound;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        //draw snap shot
        g.setColor(Color.BLACK);

        int w = width/current_snap_shot.size();
        for(int i = 0; i < current_snap_shot.size(); i ++){
            int temp = (int) (((float) current_snap_shot.get(i)/ (float) bound) * (float) height);
            g.fillRect(i*w, height-(temp), 10, temp);
        }
    }

    public void setSnap(ArrayList<Integer> s){
        //getting the current snap 
        current_snap_shot = s;
    }

    public void setDimension(int w, int h, int b){
        //set the dimensions of the frame
        //used for calculation
        width = w; 
        height = h;
        bound = b;    
    }
}


