package com.example.individual_spring.stores.web;

import com.example.individual_spring.stores.Master;
import com.example.individual_spring.stores.Service;
import com.example.individual_spring.stores.data.MasterRepository;
import com.example.individual_spring.stores.data.ServiceRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/services")
public class ServicesController {
    private ServiceRepository serviceRepo;
    private MasterRepository masterRepo;

    @Autowired
    public ServicesController(ServiceRepository serviceRepo, MasterRepository masterRepo) {
        this.serviceRepo = serviceRepo;
        this.masterRepo = masterRepo;
    }

    @ModelAttribute
    public void addServicesToModel(@NotNull Model model) {
        Iterable<Service> services = serviceRepo.findAll();

        model.addAttribute("services", services);
    }

    @ModelAttribute(name = "service")
    public Service service() {
        return new Service();
    }

    @GetMapping
    public String showServices() {
        return "services";
    }

    @GetMapping("/create/{masterId}")
    public String showServiceCreateForm(@PathVariable("masterId") Long masterId, Model model) {
        Master master = masterRepo.findById(masterId)
                .orElseThrow(() -> new IllegalArgumentException("Неправильный id мастера:" + masterId));

        model.addAttribute("master", master);
        return "create-service";
    }

    @PostMapping("/create/{masterId}")
    public String createService(@PathVariable("masterId") Long masterId, @Valid Service service, BindingResult result, Model model) {
        if (result.hasErrors()) {
            service.setId(service.getId());

            Master master = masterRepo.findById(masterId)
                    .orElseThrow(() -> new IllegalArgumentException("Неправильный id мастера:" + masterId));

            model.addAttribute("master", master);
            return "create-service";
        }

        serviceRepo.createService(service.getTitle(), service.getPrice(), service.getDuration(), masterId);

        return "redirect:/masters";
    }

    @GetMapping("/edit/{id}")
    public String showServiceUpdateForm(@PathVariable("id") Long id, Model model) {
        Service service = serviceRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Неправильный id услуги:" + id));

        model.addAttribute("service", service);
        return "update-service";
    }

    @PostMapping("/update/{id}")
    public String updateService(@PathVariable("id") Long id, @Valid Service service, BindingResult result, Model model) {
        if (result.hasErrors()) {
            service.setId(id);
            return "update-service";
        }

        serviceRepo.save(service);

        return "redirect:/masters";
    }

    @GetMapping("/delete/{id}")
    public String deleteService(@PathVariable("id") Long id) {
        serviceRepo.deleteById(id);
        return "redirect:/masters";
    }
}
