##RxJava 2.x

###What is RxJava?
 > RxJava is an art and endless possibilities await those who can master it.
 
 **RxJava** is a Java VM implementation of Reactive Extensions.
 
 From the official doc, Reactive Extensions(ReactiveX) is as a library for composing **asynchronous** and **event-based** programs by using **observable sequences**.
 
 Now we have to learn these three terms - **asynchronous**, **event-based** and **observable sequences**.
 
 **Asynchronous:** Different parts of the program run simultaneously.
 
 **Event-Based:** The program executes the code based on the event generated.
 
 
 ###Flowable and Observable
 
 RxJava provides more types of event publishers:
 
 - **Flowable** Publisher that emits 0..N elements, and then completes successfully or with an error
 
 - **Observable** like Flowables but without a backpressure strategy. They were introduced in RxJava 1.x
 
 - **Single** a specialized emitter that completes with a value successfully either an error.(doesn't have onComplete callback, instead onSuccess(val))
 
 - **Maybe** a specialized emitter that can complete with / without a value or complete with an error.
 
 - **Completable** a specialized emitter that just signals if it completed successfully or with an error.
 
 I found this [link](https://tomstechnicalblog.blogspot.com/2015_10_01_archive.html) very worthy to understand Observable and its Subscriber's builtin methods.