import java.util.ArrayList;
import java.util.Arrays;

public class InsertionSort{
    /* The InsertionSort class allows you to store an integer arraylist and sort them
    by calling a sorting function. The sorting function will record all steps of the 
    sorting and store them as snap shots. There is a function is can return all these
    snap shots. There is also a functon that can return the array list in this object.
     */
    public static Integer[] array;
    public ArrayList<ArrayList<Integer>> snap_shots = new ArrayList<ArrayList<Integer>>();

    public InsertionSort(Integer[] input_array){
        //initialize the integer array
        array = input_array;
    }

    public void set_array(Integer[] input_array){
        array = input_array;
    }

    public Integer[] get_array(){
        //returns the integer array
        return array;
    }

    public ArrayList<ArrayList<Integer>> show_snaps(){
        //returns all snap shots of the array
        return snap_shots;
    }

    public void clear_history(){
        snap_shots = new ArrayList<ArrayList<Integer>>();
    }

    public void swap(int a, int b){
        //swaps two integer's position
        Integer temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public void sort(){
        //does inplace insertion sort on array
        int i = 1;
        int j;
        
        snap_shots.add(new ArrayList<Integer>(Arrays.asList(array)));

        while(i<array.length){
            j = i;
            while ((j>0) && (array[j] < array[j-1])){
                //swap
                snap_shots.add(new ArrayList<Integer>(Arrays.asList(array)));
                swap(j,j-1);
                j--; 
            }
            i++;
        }
    }

}