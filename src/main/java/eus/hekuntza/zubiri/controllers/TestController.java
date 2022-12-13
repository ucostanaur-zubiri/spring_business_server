package eus.hekuntza.zubiri.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test(Authentication auth) {
      if (auth != null) {
        return "Logged in as " + auth.getName() + "\n";
      }
      else {
        return "Not logged in.";
      }
    }
}
