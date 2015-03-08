# Insight-Data-Coding-Challenge

Please make sure to remove all hidden files in the wc_input folder, you might otherwise see unexpected results. (For example, get rid of the .DS_Store automatically created in OSX) 

##WordCount

`WordCount.java` uses a traditional hash table structure to represent the data. I relied on the SorteMap which is essentially a sorted hashtable. Each word is a key to which we associate a number of counts. I am aware of other more powerful techniques (the Map/Reduce paradigm), that could have been tested as well, which could be an interesting problem to solve next.

##RunningMedian
###Overview of RunningMedian 
The class `RunningMedian` has two class variables :

* `maxHeap` of type MaxHeap, reprensenting the numbers below the median.
* `minHeap`, of type MinHeap, representing the numbers above the median.

Such data structure is optimal compared to a simpler representation of the data (for example storing all the data in an unstructured aray). Because both heaps are constantly updated so as to be of similar size, accessing the median is a very simple function of the roots. The main method collects the stream of data from wc_input and calls for each data input `getMedian()` and `insert()`.

###MaxHeap and MinHeap

The program contains two private classes which define our heap data structure : `MaxHeap` and `MinHeap`.

####Two instance variables :

* an Array of integers `heap` 
* an int `size`, which represents the number of elements in the heap.

####Method Summary :   

* constructors `MaxHeap()`,`MinHeap()`
* some accessor methods `int getMax()`,`int getSize()`,and`int getMin()`
* `doubleSize()` : necessary when size exceeds the length of the heap array.
* `void percolatingDown(int k)` : when an anomaly to the heap property has been introduced at node k, this method converts an unordered array (a "quasi-heap") into a heap by swapping recursively one node and its child.  
* `void insert(int element)`: inserts an element at the bottom of heap, then swaps it with its parents recursively in order to maintain a heap structure
* `int deleteMax()`,`int deleteMin()` : returns the root from a heap,and removes it.

Please note that all four methods `void percolatingDown(int k)`,`void insert(int element)` ,`int deleteMax()`and `int deleteMin()`maintain the heap structure.

