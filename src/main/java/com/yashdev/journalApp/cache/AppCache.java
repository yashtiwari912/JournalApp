package com.yashdev.journalApp.cache;

import com.yashdev.journalApp.entity.ConfigJournalAppEntity;
import com.yashdev.journalApp.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {
    public enum keys{
        WEATHER_API
    }

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    public Map<String,String>APP_CACHE;

    @PostConstruct//as soon as the bean is created and dependencies are injected, this method will be called automatically by the Spring framework.
    //iska maatlab hai ki jab bhi AppCache class ka object create hoga, to init() method automatically call ho jayega, jisse hum apne cache ko initialize kar sakte hain.
    public void init(){
        APP_CACHE = new HashMap<>();
        List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();
        for(ConfigJournalAppEntity configJournalAppEntity:all){
            APP_CACHE.put(configJournalAppEntity.getKey(),configJournalAppEntity.getValue());
        }
    }


}
