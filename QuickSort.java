import java.util.ArrayList;
import java.util.Arrays;

public class QuickSort implements Sorts{
    public static Integer[] array; 
    public ArrayList<ArrayList<Integer>> snap_shots = new ArrayList<ArrayList<Integer>>();

    public QuickSort(Integer[] input){
        //set integer array as input
        array = input;
    }

    public void set_array(Integer[] input_array){
        //initialize the integer array
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
        //removes all snap shots of the array
        snap_shots = new ArrayList<ArrayList<Integer>>();
    } 

    public void swap(int a, int b){        
        //swaps two integer's position
        Integer temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public int partition(int start, int end){
        //create partition and swap if smaller than pivot
        int j = 0;
        for(int i = 0; i < end - 1; i++){
            if (array[j] <= array[end-1]) {
                swap(i,j);
                j++;
            }
        }
        swap(end-1,j);
        return j;
    }

    public void recursive_quick_sort(int s, int e){
        if (s < e) {
            snap_shots.add(new ArrayList<Integer>(Arrays.asList(array)));
            int sorted = partition(s,e);

            recursive_quick_sort(s,sorted);
            recursive_quick_sort(sorted+1,e);
        }
    }

    public void sort(){
        //runs sort if length is not zero
        if (array.length != 0){
            recursive_quick_sort(0,array.length);
        }
    }

}