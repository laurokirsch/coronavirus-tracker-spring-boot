package com.laurokirsch.coronavirustracker.controllers;

import com.laurokirsch.coronavirustracker.models.LocationStats;
import com.laurokirsch.coronavirustracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalDiffFromPreviousDay = allStats.stream().mapToInt(stat -> stat.getDiffFromPreviousDay()).sum();
        model.addAttribute("locationStats", coronaVirusDataService.getAllStats());
        model.addAttribute("totalDiffFromPreviousDay", totalDiffFromPreviousDay);
        model.addAttribute("totalReportedCases", totalReportedCases);
        return "home"; // maps to a file inside templates/ with the same name
    }
}
