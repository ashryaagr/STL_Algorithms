public class QuickSortArray {
    public static void qsortmed(int[] arr,int l,int r){
        if(r-l>0){
            int pivot = (l+r)/2,low = l,high = r;
            while(low<pivot && high > pivot){
                while(arr[low]<arr[pivot])
                    low++;
                while(arr[high]>arr[pivot])
                    high--;
                if(arr[high]<arr[low]){
                    int temp = arr[high];
                    arr[high] = arr[low];
                    arr[low] = temp;
                }

            }
            if(arr[low]>arr[pivot]){
                int temp = arr[pivot];
                arr[pivot] = arr[low];
                arr[low] = temp;
            }
            if(arr[high]<arr[pivot]){
                int temp = arr[pivot];
                arr[pivot] = arr[high];
                arr[high] = temp;
            }
            qsortmed(arr,l,pivot);
            qsortmed(arr,pivot+1,r);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,2,9,10,7,4,2,76,34};
        qsortmed(arr,0,arr.length-1);
        for(int i:arr){
            System.out.println(i);
        }
    }
}
