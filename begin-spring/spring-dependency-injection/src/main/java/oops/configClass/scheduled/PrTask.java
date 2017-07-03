package oops.configClass.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PrTask
{
    @Scheduled(fixedRate = 10000)
    public void doIt()
    {
        System.out.println("!!!");
    }
}
