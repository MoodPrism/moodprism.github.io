package es.labproj.moodprism;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class MoodprismScheduler
{
    @Autowired
    RestTemplate restTemplate;
    
    @Autowired
    kafkaProducer producer;

    private static final Logger log = LoggerFactory.getLogger(MoodprismScheduler.class);
    
    private int i = 0;

    @Scheduled(fixedRate = 10000)
    public void updateDataRepository()
    {         
        log.info("Generating test input...");
        JSONObject obj = new JSONObject();
        obj.put("name", "Joao");
        obj.put("keys", Integer.toString(i++));
        producer.sendMsg(obj.toString());  
    }
}
