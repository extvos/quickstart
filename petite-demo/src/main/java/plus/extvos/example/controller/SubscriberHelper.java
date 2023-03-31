package plus.extvos.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import plus.extvos.mqtt.helpers.MqttSubscribeProcessor;
import plus.extvos.mqtt.subscribe.MqttSubscriber;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
@Slf4j
public class SubscriberHelper {
    @Autowired
    private ExampleController exampleController;

    @PostConstruct
    public void start() throws InterruptedException, NoSuchMethodException {
        log.info("SubscriberHelper::start ...");
        // Not good....
//        MqttSubscribeProcessor.SUBSCRIBERS.add(MqttSubscriber.of(exampleController, exampleController.getClass().getMethod("myth2ws01", String.class, String.class, Map.class), "myth2ws01/{devId}"));
//        MqttSubscribeProcessor.SUBSCRIBERS.add(MqttSubscriber.of(exampleController, exampleController.getClass().getMethod("flexbox", String.class, String.class, Map.class), "flexbox/report/{devId}"));
    }
}
