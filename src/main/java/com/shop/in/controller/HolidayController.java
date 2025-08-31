package com.shop.in.controller;

import com.shop.in.model.Holiday;
import com.shop.in.servicImpl.HolidayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HolidayController {

    @Autowired
    private HolidayServiceImpl service;

    @GetMapping("/holidays")
    public String holidays(Model model,
                           @RequestParam(required = false) boolean festival,
                           @RequestParam(required = false) boolean federal) {


        model.addAttribute("festival", festival);
        model.addAttribute("federal", federal);

        List<Holiday> holidays = service.getHolidaysList();
        Holiday.Type[] types = Holiday.Type.values();
        for (Holiday.Type type : types) {
            model.addAttribute(type.toString(),
                    (holidays.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList())));

        }
        return "holidays";
    }
}
