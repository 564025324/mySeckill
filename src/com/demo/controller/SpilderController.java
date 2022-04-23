package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/spilder")
public class SpilderController {

    // "WebContent/WEB-INF/jsp/spilder/index.html"
    @ResponseBody
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        // List<Seckill> seckillList = seckillService.getSeckillList();
        // model.addAttribute("list", seckillList);
        System.out.println("Enter Spilder Index Controller");
        return "list";
    }

}
