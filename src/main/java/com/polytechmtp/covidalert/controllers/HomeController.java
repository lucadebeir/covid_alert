package com.polytechmtp.covidalert.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/home")
public class HomeController {

    @Value("${app.version}")
    private String appVersion;

    @GetMapping
    @RequestMapping("/")
    public HashMap<String, String> get () {
        System.out.println(appVersion);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("version", appVersion);
        return map;
    }

}
