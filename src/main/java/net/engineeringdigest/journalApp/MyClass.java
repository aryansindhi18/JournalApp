package net.engineeringdigest.journalApp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MyClass {

    @GetMapping("api/hello")
    public String sayHello(){
        return "Hello ji, mai keha spring boot ton....!";
    }
}
