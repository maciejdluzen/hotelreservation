package pl.maciejdluzen.hotelreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.maciejdluzen.hotelreservation.domain.entities.Admin;
import pl.maciejdluzen.hotelreservation.services.AdminService;

@Controller
@RequestMapping("/createadmin")
public class AdminAccountController {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    private final AdminService adminService;

    public AdminAccountController(AdminService adminService) {
        this.adminService = adminService;
    }


    @GetMapping
    public String getCreateAdminForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin/createform";
    }

    @PostMapping
    public String createAdminAccount(@ModelAttribute("admin") Admin admin) {
        adminService.createAdminAccount(admin);
        return "login";
    }

}
