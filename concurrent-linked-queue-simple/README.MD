# <img src="https://raw.githubusercontent.com/bobocode-projects/resources/master/image/logo_transparent_background.png" height=50/>Linked queue exercise :muscle:
Improve your Java Concurrent skills
### Task
`Queue` is an API that represents queue data structure that follows "first in, first out" rule (FIFO). Your job is to 
implement the *todo* section of the class `ConcurrentLinkedQueue`. Please note, that your implementation should be 
based on linked  queue, e.g. liked nodes. It means that you should create your own class `Node<T>` that will hold queue 
elements. In addition you should implement synchronization since this queue should be thread-safe, which means that all
operation should work fine in a concurrent mode. (When multiple threads try changing the queue).
  
To verify your implementation, run `ConcurrentLinkedQueueTest.java`
 
### Pre-conditions :heavy_exclamation_mark:
You're supposed to be familiar Queue data structure, Concurrency and be able to write Java code

### How to start :question:
* Just clone the repository and create a branch **exercise/your_username** if you want your code to be reviewed
* Start implementing the **todo** section and verify your changes by running tests
* If you don't have enough knowledge about this domain, check out the [links below](#related-materials-information_source)
* Don't worry if you got stuck, checkout the **exercise/completed** branch and see the final implementation
 
### Related materials :information_source:
 * [Queue (abstract data type)](https://en.wikipedia.org/wiki/Queue_(abstract_data_type))
 * [Thread synchronization in Java](https://docs.oracle.com/javase/tutorial/essential/concurrency/sync.html)
 * [Java Atomic variables](https://docs.oracle.com/javase/tutorial/essential/concurrency/atomicvars.html)

