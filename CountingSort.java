import java.util.ArrayList;
import java.util.Arrays;

public class CountingSort{
    public static Integer[] array; 
    public ArrayList<ArrayList<Integer>> snap_shots = new ArrayList<ArrayList<Integer>>();

    public CountingSort(Integer[] input_array){
        array = input_array;
    }

    public void set_array(Integer[] input_array){
        array = input_array;
    }

    public Integer[] get_array(){
        return array;
    }

    public ArrayList<ArrayList<Integer>> show_snaps(){
        return snap_shots;
    }

    public void clear_history(){
        snap_shots = new ArrayList<ArrayList<Integer>>();
    }

    
}