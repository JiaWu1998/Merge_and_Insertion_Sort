import java.util.ArrayList;
import java.util.Arrays;

public class InsertionSort{
    public static Integer[] array;
    public ArrayList<ArrayList<Integer>> snap_shots = new ArrayList<ArrayList<Integer>>();
    ArrayList<Integer> temp;

    public InsertionSort(Integer[] input_array){
        array = input_array;
    }

    public Integer[] getArray(){
        return array;
    }

    public void setArray(Integer[] input_array){
        array = input_array;
    }

    public void swap(int a, int b){
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
        return snap_shots;
    }
}