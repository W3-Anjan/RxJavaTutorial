# RxJava 2.x

### What is RxJava?
 > RxJava is an art and endless possibilities await those who can master it.
 
 **RxJava** is a Java VM implementation of Reactive Extensions.
 
 From the official doc, Reactive Extensions(ReactiveX) is as a library for composing **asynchronous** and **event-based** programs by using **observable sequences**.
 
 Now we have to learn these three terms - **asynchronous**, **event-based** and **observable sequences**.
 
 **Asynchronous:** Different parts of the program run simultaneously.
 
 **Event-Based:** The program executes the code based on the event generated.
 
 
 ### Flowable and Observable
 
 RxJava provides more types of event publishers:
 
 - **Flowable** Publisher that emits 0..N elements, and then completes successfully or with an error
 
 - **Observable** like Flowables but without a backpressure strategy. They were introduced in RxJava 1.x
 
 - **Single** a specialized emitter that completes with a value successfully either an error.(doesn't have onComplete callback, instead onSuccess(val))
 
 - **Maybe** a specialized emitter that can complete with / without a value or complete with an error.
 
 - **Completable** a specialized emitter that just signals if it completed successfully or with an error.
 
 I found this [link](https://tomstechnicalblog.blogspot.com/2015_10_01_archive.html) very worthy to understand Observable and its Subscriber's builtin methods.
 
 ![image](https://github.com/anjandebnath/RxJavaTutorial/blob/master/images/animation_1.gif)
 
 
 ## IMPORTANT
 Observable and Flowable can emit **multiple** items
 
 Single, Maybe and Completable are **one or no emission** of items.
 
 ### Flowable Backpressure Strategy
 -  Buffer
 -  Drop
 -  Latest
 -  Error
 -  Missing
 
 ### Simple Operators
 - delay
 - interval
 - scan
 - reduce
 - collect
 
 ### Other components are as follows:
 
 - **Subscription** - work is going on or completed or is used to cancel.
 - **Operators** - Modify Data
 - **Schedulers** - Where the work should be done, which thread like main thread, etc.
 - **Subscriber/Disposable** - where the response will be sent after work has been completed.
 
 ## Understanding RxJava Subject
 
 ### What is Subject?
  > A Subject is a sort of bridge or proxy that is available in some implementations of ReactiveX that acts both as an observer and as an Observable. Because it is an observer, it can subscribe to one or more Observables, and because it is an Observable, it can pass through the items it observes by re-emitting them, and it can also emit new items.
  
  detailed explanation [here](https://mindorks.com/course/learn-rxjava/chapter/id/4/page/id/15)
  
 ### RxJava2 with MVP structure
 
  This is the  diagram of how we can use Rxjava on MVP structure.
  ![image](https://github.com/anjandebnath/RxJavaTutorial/blob/master/RxApi.PNG) 
  
  >This explanation is taken from google's Rx example with MVP [link](https://github.com/googlesamples/android-architecture/tree/todo-mvp-rxjava/)