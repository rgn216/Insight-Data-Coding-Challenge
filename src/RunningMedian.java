import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.io.IOException;

class MaxHeap{
    
    // A heap is represented by an array (heap) and a size (number of elements in the array)
    // In order to facilitate the access to children and parents, we ignore the index 0 in the heap array.
    // It follows that the root of the heap is heap[1]
    // for a node k, children are 2k and 2k +1, parent is k/2
    
    final static int CAPACITY = 2;
    private int size;
    private int[] heap;
       
    public MaxHeap(){ 
        clear();
    }
    public void clear(){
        size = 0;
        heap = new int[CAPACITY];
    }
   public int getMax(){
        return heap[1];
    }
    
    public int getSize(){
        return size;
    }
    
    // percolatingDown reorganizes the data into a heap, assuming that the subtrees below node k are heaps    
    private void percolatingDown(int k){         
        int tmp = heap[k];
        int child;
        for(; 2*k <= size ; k = child){
            child = 2 * k;
            
            //identify the highest child
            if (child != size && heap[child] < heap[child+1]){
                child ++;
            }
            
            //if the parent (k) and child are not correctly ordered we swap them
            if (tmp < heap[child]){
                heap[k] = heap[child];
            }
            else{ 
                break;
            }
            
        }
        heap[k] = tmp;  
    }
    
    private void doubleSize(){  
        //copy the previous heap array
        int[] old = heap;
        // double the size of the heap array
        heap = new int[ heap.length * 2];
        // copy old into heap
        System.arraycopy(old , 1 , heap , 1 , size);  
    }
      
    public void insert(int elem){
        if (size == heap.length -1 ){
            // no more room in the heap array, need to double its size 
            doubleSize();
        } 
        
        //Percolate up :    
        // insert elem as one of the leaves
        // swap with its parent as long as the order relationship is not satisfied.    
        
        int pos = ++size;
        while( (pos >1) && (elem > heap[pos/2]) ){
            heap[pos] = heap[pos/2];
            pos = pos/2;
        }
        heap[pos] = elem;    
    }
    
     public int deleteMax() throws RuntimeException    
     {
      if (size == 0) throw new RuntimeException();
      int max = heap[1];
      heap[1] = heap[size--];
      percolatingDown(1);
      return max;
     }
}

class MinHeap{
    
    // A heap is represented by an array (heap) and a size (number of elements in the array)
    // In order to facilitate the access to children and parents, we ignore the index 0 in the heap array.
    // It follows that the root of the heap is heap[1]
    // for a node k, children are 2k and 2k +1, parent is k/2
    
    final static int CAPACITY = 2;
    private int size;
    private int[] heap;
       
    public MinHeap(){       
        clear();
        //size = 0;
        //heap = new int[CAPACITY];
    }
    
    public int getMin(){
        return heap[1];
    }
    
    public int getSize(){
        return size;
    }
    // percolatingDown reorganizes the data into a heap, assuming that the subtrees below node k are heaps    
    private void percolatingDown(int k){         
        int tmp = heap[k];
        int child;
        for(; 2*k <= size ; k = child){
            child = 2 * k;
            
            //identify the lowest child
            if (child != size && heap[child] > heap[child+1]){
                child ++;
            }
            
            //if the parent (k) and child are not correctly ordered we swap them
            if (tmp > heap[child]){
                heap[k] = heap[child];
            }
            else{ 
                break;
            }
            
        }
        heap[k] = tmp;
   
    }
    
    private void doubleSize(){
        int[] old = heap;
        heap = new int[ heap.length * 2];
        System.arraycopy(old , 1 , heap , 1 , size);  
    }
     
    public void insert(int elem){
        if (size == heap.length -1 ){
        doubleSize();
        } 
        
        // Percolate up :    
        // insert elem as one of the leaves
        // swap with its parent as long as the order relationship is not satisfied.    
        
        int pos = ++size;
        while( (pos >1) && (elem < heap[pos/2]) ){
            heap[pos] = heap[pos/2];
            pos = pos/2;
        }        
        heap[pos] = elem;    
    }
    
    public int deleteMin() throws RuntimeException    
     {
      if (size == 0) throw new RuntimeException();
      int min = heap[1];
      heap[1] = heap[size--];
      percolatingDown(1);
      return min;
     }
    
    public void clear(){
        size = 0;
        heap = new int[CAPACITY];   
    }
}

public class RunningMedian{

    static MaxHeap maxHeap = new MaxHeap();
    static MinHeap minHeap = new MinHeap();
    
    private static void insert(int element){
        // decide on the heap for insertion
        if (element < maxHeap.getMax()){
            maxHeap.insert(element);
        }
        else{
            minHeap.insert(element);
        }
        
        // regularize the sizes of the heaps if they differ by more than 1
        if (maxHeap.getSize() - minHeap.getSize() > 1 ){
            int tmp = maxHeap.deleteMax();
            minHeap.insert(tmp);
        // remove the root of maxHeap and insert it in minHeap   
        }
        else if (minHeap.getSize() - maxHeap.getSize() > 1 ){
        // remove the root of minHeap and insert it in maxHeap
            int tmp = minHeap.deleteMin();
            maxHeap.insert(tmp);
        }
        
    }
    
    private static double getMedian(){    
        if (maxHeap.getSize() > minHeap.getSize()){
            return maxHeap.getMax();
        }
        else if (maxHeap.getSize() < minHeap.getSize()){
           return minHeap.getMin(); 
        }
        else{
           return 0.5 * (double)(maxHeap.getMax() + minHeap.getMin()); 
        }       
    }
    
    public static void main(String[] args) throws FileNotFoundException,IOException{ 
        
        File folder = new File("../wc_input");
        File[] files = folder.listFiles();
        FileWriter fileWriter = new FileWriter(new File("../wc_output/med_result.txt"));
        maxHeap.clear();  
        minHeap.clear();          
        // Ordering the files in alphabetical order
        Arrays.sort(files);
        for (File f : files ){
                        
            FileReader fileReader = new FileReader(f);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            String Line = bufferedReader.readLine();
            int nwords;
            while( Line != null ){                
                if ( Line.length() == 0) {
                    nwords =0;
                }
                else{
                    String[] words = Line.split(" ");
                    nwords = words.length;
                }
                
                insert(nwords);
                fileWriter.write(getMedian() + "\n");
                Line = bufferedReader.readLine();               
            }
        }
        fileWriter.close();
        
    }
}