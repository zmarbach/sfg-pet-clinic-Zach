package com.improving.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping({"/", ""})
@Controller
public class IndexController {

    @RequestMapping({"index-page", "index.html"})
    public String index(){
        return "index";
    }

    @RequestMapping("oups")
    public String notImplemented(){
        return "notimplemented";
    }
}
