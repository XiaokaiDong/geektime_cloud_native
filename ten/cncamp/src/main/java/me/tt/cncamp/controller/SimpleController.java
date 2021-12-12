package me.tt.cncamp.controller;

import io.prometheus.client.Counter;
import io.prometheus.client.Histogram;
import me.tt.cncamp.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController()
public class SimpleController {

    private Random rand = new Random();

    private final Counter userCounter = Counter.build()
            .name("user_counter")
            .help("user_counter")
            .labelNames("endpoint", "status")
            .register();

    private final Histogram httpRequestDurationMs = Histogram.build()
            .name("http_request_duration_milliseconds")
            .help("Http request latency histogram")
            .exponentialBuckets(25, 2, 7)
            .labelNames("endpoint", "status")
            .register();

    @GetMapping("/user")
    public User getUser(){
        User user = new User();
        user.setId(1);
        user.setName("tt");

        long jobStart = System.currentTimeMillis();
        try {
            userCounter.labels("/getUser", "200").inc();

            Thread.sleep(rand.nextInt(2000));

            return user;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

            int duration = (int)((System.currentTimeMillis() - jobStart) / 1000);
            httpRequestDurationMs.labels("/getUser", "200").observe(duration);
        }
        return user;
    }
}
