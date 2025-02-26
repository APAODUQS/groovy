def t = new Thread() { /* Do something */}
t.start()

Thread.start { /* Do something */}
Thread.start('thread name') { /* Do something */}
// runs on background
Thread.startDaemon { /* Do something */}
Thread.startDaemon('thread name') { /* Do something */}

// --------------

import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue

BlockingQueue sharedQueue = [] as LinkedBlockingQueue

Thread.start('push') {
  10.times {
    try{
      println "${Thread.currentThread().name}\t: ${it}"
      sharedQueue << it
      sleep 100
    } catch(InterruptedException ignore) {
      // something
    }
  }
}

Thread.start('pop') {
  for ( i in 0..9) {
    sleep 200
    println "${Thread.currentThread().name}\t: ${sharedQueue.take()}"
  }
}
