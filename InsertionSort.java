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
    ArrayList<Integer> temp;

    public InsertionSort(Integer[] input_array){
        //initialize the integer array
        array = input_array;
    }

    public Integer[] getArray(){
        //returns the integer array
        return array;
    }

    public void swap(int a, int b){
        //swaps two integer's position
        Integer temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public void insertion_sort(){
        //does inplace insertion sort on array
        int i = 1;
        int j;
        
        temp = new ArrayList<Integer>(Arrays.asList(array));
        snap_shots.add(temp);

        while(i<array.length){
            j = i;
            while ((j>0) && (array[j] < array[j-1])){
                //swap
                temp = new ArrayList<Integer>(Arrays.asList(array));
                snap_shots.add(temp);
                swap(j,j-1);
                j--; 
            }
            i++;
        }
    }

    public ArrayList<ArrayList<Integer>> show_snaps(){
        //returns all snap shots of the array
        return snap_shots;
    }
}