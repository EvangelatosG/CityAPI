package com.example.cityAPI.api;

import com.example.cityAPI.model.ApiCall;
import com.example.cityAPI.service.ApiCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/website")
public class WebsiteController {

    ApiCallService apiCallService;

    @Autowired
    public WebsiteController(ApiCallService apiCallService) {
        this.apiCallService = apiCallService;
    }

    @GetMapping("/list")
    public String showList(Model model) {
        model.addAttribute("apicalls", apiCallService.localFindAll());
        return "index";
    }

    @GetMapping("/details/{id}")
    public String showDetails(@PathVariable int id, Model model) {
        ApiCall apiCall = apiCallService.localFindById(id);
        model.addAttribute("apicall", apiCall);
        return "details";
    }

}
