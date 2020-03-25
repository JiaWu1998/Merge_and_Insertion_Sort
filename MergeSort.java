import java.util.ArrayList;
import java.util.Arrays;

public class MergeSort {
    /* The MergeSort class allows you to store an integer arraylist and sort them
    by calling a sorting function. The sorting function will record all steps of the 
    sorting and store them as snap shots. There is a function is can return all these
    snap shots. There is also a functon that can return the array list in this object.
     */
    public static Integer[] array;
    public ArrayList<ArrayList<Integer>> snap_shots = new ArrayList<ArrayList<Integer>>();

    public MergeSort(Integer[] input_array){
        //initialize the integer array
        array = input_array;
    }

    public void set_array(Integer[] input_array){
        array = input_array;
    }

    public Integer[] get_array(){
        //returns the integar array
        return array;
    }

    public ArrayList<ArrayList<Integer>> show_snaps(){
        //returns all snap shots of the array
        return snap_shots;
    }

    public void clear_history(){
        snap_shots = new ArrayList<ArrayList<Integer>>();
    }
    
    public void merge(int left, int mid ,int right){
        //copy selected portion array to two auxiliary arrays
        int[] left_array = new int[mid-left+1];
        int[] right_array = new int[right-mid];

        for(int i=0; i<left_array.length; i++){
            left_array[i] = array[i+left];
        }
        for(int i=0; i<right_array.length; i++){
            right_array[i] = array[i+mid+1]; 
        }

        //sort two arrays and change orignial array 
        int j = left;
        int k = 0;
        int l = 0;

        while(k < left_array.length && l < right_array.length){
            if(left_array[k] < right_array[l]){
                array[j] = left_array[k];
                k++;
            }else{
                array[j] = right_array[l];
                l++;
            }
            //records a snap shot of the array
            snap_shots.add(new ArrayList<Integer>(Arrays.asList(array)));
            j++;
        }

        //if there is anything left on the left array, add it on the end of the original array 
        if(k < left_array.length){
            for(int i=k; i<left_array.length;i++){
                array[j] = left_array[k];
                j++;
                k++;
                //records a snap shot of the array
                snap_shots.add(new ArrayList<Integer>(Arrays.asList(array)));
            }
        }
        //if there is anything left on the right array, add it on the end of the original array 
        if(l < right_array.length){
            for(int i=l; i<right_array.length;i++){
                array[j] = right_array[l];
                j++;
                l++;
                //records a snap shot of the array
                snap_shots.add(new ArrayList<Integer>(Arrays.asList(array)));
            }
        }

    }

    public void merge_sort(int left, int right){
        //recursive calls to deconstruct the orignal array into singletons and merge back into a sorted array
        if (left < right){
            int mid = (left + right) / 2;

            merge_sort(left, mid);
            merge_sort(mid+1,right);

            merge(left,mid,right);
        }
    }

    public void sort(){
        //records a snap shot of the array
        snap_shots.add(new ArrayList<Integer>(Arrays.asList(array)));

        //calls the recursive sorting function
        merge_sort(0,array.length-1);
    }

}