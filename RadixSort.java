import java.util.ArrayList;
import java.util.Arrays;

public class RadixSort{
    // RADIX SORT RESTRICTION: CAN ONLY SORT FROM 0 to N 

    public static Integer[] array; 
    public ArrayList<ArrayList<Integer>> snap_shots = new ArrayList<ArrayList<Integer>>();

    public RadixSort(Integer[] input_array){
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

    public void count_sort(int exponent){
        Integer[] temp = array.clone();
        int[] counter = new int[10];

        Arrays.fill(counter,0);

        for(int i=0; i<array.length; i++){
            counter[(array[i]/exponent)%10] += 1;
        }

        for(int i=1; i<10; i++){
            counter[i] += counter[i-1];
        }

        for(int i=array.length-1; i >= 0; i--){
            temp[counter[(array[i]/exponent)%10]-1] = array[i];
            snap_shots.add(new ArrayList<Integer>(Arrays.asList(temp)));
            counter[(array[i]/exponent)%10] -= 1;
        }

        array = temp;
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
        
        for (int exponent=1; max_range/exponent > 0; exponent*=10){
            count_sort(exponent);
        }
    }
}