package es.labproj.moodprism;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MoodprismController
{
    private static final Logger log = LoggerFactory.getLogger(MoodprismController.class);
    
    @Autowired
    kafkaConsumer consumer;
    
    @GetMapping("/")
    public String index(Model model)
    {                         
        log.info("Returning request!");
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try{json = (JSONObject) parser.parse(consumer.getMessages());}
        catch(Exception e) {System.out.println(e);}
        model.addAttribute("title", "Mood Prism");
        model.addAttribute("name", json.get("name"));
        model.addAttribute("keys", json.get("keys"));
        return "index";
    }

}