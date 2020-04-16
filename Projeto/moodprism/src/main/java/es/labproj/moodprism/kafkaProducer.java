package es.labproj.moodprism;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class kafkaProducer {
    
    private static final Logger log = LoggerFactory.getLogger(kafkaProducer.class);
    private static final String Topic = "moodprismTopic";
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
    public void sendMsg(String msg)
    {
        log.info(String.format("Producing test intput-> %s", msg));
        this.kafkaTemplate.send(Topic, msg);
    }
    
}
