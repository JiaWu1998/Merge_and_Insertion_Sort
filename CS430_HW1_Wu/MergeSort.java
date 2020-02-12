import java.util.ArrayList;
import java.util.Arrays;
public class MergeSort {
    public Integer[] array;
    public ArrayList<ArrayList<Integer>> snap_shots = new ArrayList<ArrayList<Integer>>();
    public ArrayList<Integer> temp;

    public MergeSort(Integer[] input_array){
        array = input_array;
    }

    public Integer[] getArray(){
        return array;
    }

    public void setArray(Integer[] input_array){
        array = input_array;
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
            temp = new ArrayList<Integer>(Arrays.asList(array));
            snap_shots.add(temp);
            j++;
        }

        //if there is anything left on the left array, add it on the end of the original array 
        if(k < left_array.length){
            for(int i=k; i<left_array.length;i++){
                array[j] = left_array[k];
                j++;
                k++;
                temp = new ArrayList<Integer>(Arrays.asList(array));
                snap_shots.add(temp);
            }
        }
        //if there is anything left on the right array, add it on the end of the original array 
        if(l < right_array.length){
            for(int i=l; i<right_array.length;i++){
                array[j] = right_array[l];
                j++;
                l++;
                temp = new ArrayList<Integer>(Arrays.asList(array));
                snap_shots.add(temp);
            }
        }

    }

    public void sort(int left, int right){
        if (left < right){
            int mid = (left + right) / 2;

            sort(left, mid);
            sort(mid+1,right);

            merge(left,mid,right);
        }
    }

    public void merge_sort(){
        temp = new ArrayList<Integer>(Arrays.asList(array));
        snap_shots.add(temp);
        sort(0,array.length-1);
    }

    public ArrayList<ArrayList<Integer>> show_snaps(){
        return snap_shots;
    }

}