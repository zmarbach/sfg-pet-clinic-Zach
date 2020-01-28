package com.improving.sfgpetclinic.controllers;

import com.improving.sfgpetclinic.models.Vet;
import com.improving.sfgpetclinic.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Controller
public class VetController {

    private VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @GetMapping({"/vets", "/vets.html"})
    public String vetIndex(Model model){
        model.addAttribute("vets", vetService.findAll());
        return "vets/index";
    }

    @GetMapping("/api/vets")
    //@ResponseBody tells Spring to bind whatever we return out of the method to the HTTP Response Body (either JSON or XML)
    public @ResponseBody Set<Vet> getVetsJson(){
        return vetService.findAll();
    }
}
