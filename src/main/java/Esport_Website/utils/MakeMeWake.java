package Esport_Website.utils;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MakeMeWake {
	
	private final RestTemplate restTemplate = new RestTemplate();
	
//	@Scheduled(fixedRate = 1000 * 60 * 10)
	@Async
    public void pingMyself() {
        try {
            String url = "https://ganews.onrender.com/ping";
            restTemplate.getForObject(url, String.class);
            System.out.println("Pinged successfully");
        } catch (Exception e) {
            System.err.println("Ping failed: " + e.getMessage());
        }
    }

}
