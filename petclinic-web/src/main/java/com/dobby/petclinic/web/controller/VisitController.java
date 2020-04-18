package com.dobby.petclinic.web.controller;

import com.dobby.petclinic.domain.Pet;
import com.dobby.petclinic.domain.Visit;
import com.dobby.petclinic.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class VisitController {


    @Autowired
    private ClinicService clinicService;


    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }


    /**
     * Called before each and every @RequestMapping annotated method.
     * 2 goals:
     * - Make sure we always have fresh data
     * - Since we do not use the session scope, make sure that Pet object always has an id
     * (Even though id is not part of the form fields)
     *
     * @param petId
     * @return Pet
     */
    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable("petId") int petId, ModelMap modelMap) {
        Pet pet = this.clinicService.findPetById(petId);
        modelMap.put("pet", pet);
        Visit visit = new Visit();
        pet.addVisit(visit);
        return visit;
    }


    /**
     * Spring MVC calls method loadPetWithVisit(...) before initNewVisitForm is called
     */
    @GetMapping(value = "/owners/*/pets/{petId}/visits/new")
    public String initNewVisitForm(@PathVariable("petId") int petId, Map<String, Object> model) {
        return "pets/createOrUpdateVisitForm";
    }


    /**
     * Spring MVC calls method loadPetWithVisit(...) before processNewVisitForm is called
     */
    @PostMapping(value = "/owners/{ownerId}/pets/{petId}/visits/new")
    public String processNewVisitForm(@Valid Visit visit, BindingResult result) {
        if (result.hasErrors()) {
            return "pets/createOrUpdateVisitForm";
        } else {
            this.clinicService.saveOrUpdateVisit(visit);
            return "redirect:/owners/{ownerId}";
        }
    }


    @GetMapping(value = "/owners/*/pets/{petId}/visits")
    public String showVisits(@PathVariable int petId, Map<String, Object> model) {
        model.put("visits", this.clinicService.findVisitsByPetId(petId));
        return "visitList";
    }


}
