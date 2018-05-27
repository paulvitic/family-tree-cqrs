package energy.getfresh.homework.infrastructure.queue;

import java.util.concurrent.BlockingQueue;

public abstract class MessageConsumer implements Runnable {

    protected BlockingQueue<Message> queue;

    void setQueue(BlockingQueue<Message> queue){
        this.queue = queue;
    }

    protected abstract void onMessage(Message msg);
}
