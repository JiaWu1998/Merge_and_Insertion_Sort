import java.util.ArrayList;
import java.util.Arrays;

public class CountingSort{
    // COUNTING SORT RESTRICTION: CAN ONLY SORT FROM 0 to N 

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

    public void sort(){

        //Find the maximum integer
        int max_range = 0;
        for(int i=0; i<array.length; i++){
            if (array[i] > max_range){
                max_range = array[i];
            }
            if (array[i] < 0){
                System.out.print("Counting Sort Restriction: You cannot have a negative number.");
                return;
            }            
        }

        //Create a index array that is length of maximum integer
        Integer[] index = new Integer[max_range+1];

        //initialize index array with zeros
        for(int i=0; i<index.length; i++){
            index[i] = 0;
        }

        //For count each integer in the oringal array and store it in the index array 
        for(int i=0; i<array.length; i++){
            index[array[i]] += 1;
        }

        //For each count in the index array, add the previous count
        for(int i=1; i<index.length; i++){
            index[i] += index[i-1];
        }

        // Make a temp array. 
        Integer[] temp = array.clone();

        //For each integer in the original array, find the count of that integer in the index array.
        //That count will be the index of that integer in the temp array.
        for(int i=0; i<array.length; i++){
            temp[index[array[i]]-1] = array[i];
            snap_shots.add(new ArrayList<Integer>(Arrays.asList(array)));
            index[array[i]] -= 1;
        }

        //The temp array is the sorted version of the original array
        array = temp;
    }
}