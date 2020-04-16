package es.labproj.moodprism;

import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class kafkaConsumer {
    
    private final Logger log = LoggerFactory.getLogger(kafkaConsumer.class);
    private final LinkedList<String> msgs = new LinkedList<>();
    ReentrantLock lock = new ReentrantLock();
    
    @KafkaListener(topics="moodprismTopic", groupId="group_id")
    public synchronized void listen(String msg) throws IOException{
        lock.lock();
        try{msgs.add(msg);}
        finally{lock.unlock();}
        log.info(String.format("Producing-> %s", msg));
    }
    
    public String getMessages(){
        String tmp;
        lock.lock();
        try
        {
            if(msgs.size() > 1) {tmp = msgs.pop();}
            else {tmp = msgs.getFirst();}
        }
        finally{lock.unlock();}
        return tmp;
    }
}
