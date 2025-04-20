package array;

import java.util.List;

// [2,4,5,7,8,23,1]
public class LinearSearch {
    public static void main(String[] args) {
        LinearSearch lr=new LinearSearch();
        System.out.println(lr.search(List.of(2, 4, 5, 7, 8, 23, 1), 74));

    }

    private boolean search(List<Integer> list,int key){
        for (Integer i : list) {
            if(i==key){
                return true;

            }
        }
        return false;
    }
}
