package com.improving.sfgpetclinic.controllers;

import com.improving.sfgpetclinic.models.Owner;
import com.improving.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class OwnerController {

    private final OwnerService ownerService;
    private final String CREATE_UPDATE_OWNER_FORM_VIEW_NAME = "createOrUpdateOwnerForm";

    public OwnerController( OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/owners/find")
    public String findOwners(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping("/owners")
    public String processFindForm(Owner owner, BindingResult result, Model model){
        // allow parameterless GET request for /owners to return all records
        if (owner.getLastName() == null) {
            owner.setLastName(""); // empty string signifies broadest possible search
        }

        // find owners by last name
        List<Owner> results = ownerService.findAllByLastNameLikeIgnoreCase("%" + owner.getLastName() + "%");

        if (results.isEmpty()) {
            // no owners found
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        } else if (results.size() == 1) {
            // 1 owner found
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        } else {
            // multiple owners found
            model.addAttribute("selections", results);
            return "owners/ownersList";
        }
    }

    @GetMapping("/owners/{ownerId}")
    public ModelAndView showOwnerDetails(@PathVariable Long ownerId){
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject("owner", ownerService.findById(ownerId));
        return mav;
    }

    @GetMapping("/owners/new")
    public String initCreationFrom(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return "owners/createOrUpdateOwnerForm";

    }

    @PostMapping("/owners/new")
    public String postCreationFrom(@ModelAttribute Owner owner, Model model){
        Owner savedOwner = ownerService.save(owner);
        model.addAttribute("owner", savedOwner);
        return "redirect:/owners/" + savedOwner.getId();
    }

    @GetMapping("/owners/{id}/edit")
    public String initUpdateForm(@PathVariable Long id, Model model){
        model.addAttribute("owner", ownerService.findById(id));
        return "owners/" + CREATE_UPDATE_OWNER_FORM_VIEW_NAME;
    }

    @PostMapping("/owners/{id}/edit")
    public String processUpdateForm(@ModelAttribute Owner updatedOwner, Model model, @PathVariable Long id){
        //have to SET the Id here so JPA will actually override the old owner (otherwise just creates a new owner)
        updatedOwner.setId(id);
        Owner savedOwner = ownerService.save(updatedOwner);
        model.addAttribute("owner", savedOwner);
        return "redirect:/owners/" + savedOwner.getId();
    }



}
