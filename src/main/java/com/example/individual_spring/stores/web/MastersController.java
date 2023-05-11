package com.example.individual_spring.stores.web;


import com.example.individual_spring.stores.Master;
import com.example.individual_spring.stores.data.MasterRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequestMapping("/masters")
public class MastersController {
    private final MasterRepository masterRepo;

    @Autowired
    public MastersController(MasterRepository masterRepo) {
        this.masterRepo = masterRepo;
    }

    @ModelAttribute
    public void addMastersToModel(@NotNull Model model) {
        Iterable<Master> masters = masterRepo.findAll();
        model.addAttribute("masters", masters);
    }

    @ModelAttribute(name = "master")
    public Master master() {
        return new Master();
    }

    @GetMapping
    public String showMasters() {
        return "masters";
    }

    @PostMapping
    public String addMaster(@Valid Master master, Errors errors, SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "masters";
        }
        masterRepo.save(master);
        sessionStatus.setComplete();

        return "redirect:/masters";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Master master = masterRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Неправильный id мастера:" + id));

        model.addAttribute("master", master);
        return "update-master";
    }

    @PostMapping("/update/{id}")
    public String updateMaster(@PathVariable("id") Long id, @Valid Master master, BindingResult result, Model model) {
        if (result.hasErrors()) {
            master.setMasterId(id);
            return "update-master";
        }

        masterRepo.updateMaster(id, master.getFullName(), master.getPost(), master.getPhone());

        return "redirect:/masters";
    }

    @GetMapping("/delete/{id}")
    public String deleteMaster(@PathVariable("id") Long id) {
        masterRepo.deleteById(id);
        return "redirect:/masters";
    }

    @GetMapping("/deleteAll")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteAll() {
        masterRepo.deleteAll();
        return "redirect:/masters";
    }
}
