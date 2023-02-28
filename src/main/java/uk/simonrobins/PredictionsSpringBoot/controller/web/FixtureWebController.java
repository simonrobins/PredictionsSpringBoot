package uk.simonrobins.PredictionsSpringBoot.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import uk.simonrobins.PredictionsSpringBoot.entity.Fixture;
import uk.simonrobins.PredictionsSpringBoot.entity.Result;
import uk.simonrobins.PredictionsSpringBoot.service.FixtureService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/fixtures")
public class FixtureWebController
{
    @Autowired
    private FixtureService fixtureService;

    @GetMapping
    public String index(Model model)
    {
        List<Fixture> fixtures = fixtureService.findAll();
        model.addAttribute("fixtures", fixtures);

        Result[] results = Result.values();
        model.addAttribute("results", results);

        // Get yesterday's date
        // The fixture result can only be changed once the fixture is in the past :)
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date yesterday = calendar.getTime();
        model.addAttribute("yesterday", yesterday);

        return "fixtures/index";
    }
}