/**
 * 根据指定数据的索引 索引前后元素 调换顺序 但是原有的顺序不变
 * {1,2,3,4,5,6,7} 指定索引2
 * 转完后
 * {4,5,6,7, 3, 1,2}
 */
public class ArrayReverse {
    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1,2,3,4,5,6,7};
        ArrayReverse.reverseOfIndex(arr,3);
        for (int i = 0; i <arr.length ; i++) {
            System.out.printf(arr[i]+",");
        }
    }

    public static  void reverseOfIndex(Integer[] arr,int index){
        if(index<0 || index>arr.length-1) return;
        //索引前数据反转
        for (int i = 0; i < index/2; i++) {
            int temp = arr[i];
            arr[i] = arr[index-i-1];
            arr[index-i-1] = temp;

        }
        //索引后数据反转
        for (int i = index+1,j=1,len = arr.length; i < (len+index+1)/2; i++,j++) {
            int temp = arr[i];
            arr[i] = arr[len-j];
            arr[len-j] = temp;

        }

        //整体反转
        for (int i = 0,len=arr.length; i < len/2; i++) {
            int temp = arr[i];
            arr[i] = arr[len-i-1];
            arr[len-i-1] = temp;

        }
    }
}

