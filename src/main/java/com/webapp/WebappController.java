package com.webapp;

import com.webapp.domain.Subscriber;
import com.webapp.repos.SubscriberRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class WebappController {

        @Autowired
        private SubscriberRepos subscriberRepos;

        @GetMapping("/greeting")
        public String greeting(@RequestParam(name="name", required = false, defaultValue = "World") String name,
                           Map<String, Object> model) {
            model.put("name", name);
        return "greeting";
        }

        @GetMapping
        public String main(Map<String, Object> model) {
            Iterable<Subscriber> subscribers = subscriberRepos.findAll();
            model.put("subscribers", subscribers);
            return "main";
        }

        @PostMapping("addSubscriber")
        public String addSubscriber(@RequestParam String firstName, @RequestParam String plane,
                                    Map<String, Object> model) {
            Subscriber subscriber = new Subscriber(firstName, plane);
            subscriberRepos.save(subscriber);

            Iterable<Subscriber> subscribers = subscriberRepos.findAll();
            model.put("subscribers", subscribers);

            return "main";
        }

        @PostMapping("filter")
        public String filter(@RequestParam String firstName, Map<String, Object> model) {
            Iterable<Subscriber> subscribers = null;
            if (firstName != null && !firstName.isEmpty()) {
                subscribers = subscriberRepos.findByFirstName(firstName);
            } else {
                subscribers = subscriberRepos.findAll();
            }
            model.put("subscribers", subscribers);
            return "main";
        }
}
