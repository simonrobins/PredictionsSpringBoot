package uk.simonrobins.PredictionsSpringBoot.controller.rest;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uk.simonrobins.PredictionsSpringBoot.entity.Result;
import uk.simonrobins.PredictionsSpringBoot.service.FixtureService;
import uk.simonrobins.PredictionsSpringBoot.service.PredictionService;

@RestController
@RequestMapping("/api")
public class XmlHttpRequestController {
    @Autowired
    private PredictionService predictionService;
    @Autowired
    private FixtureService fixtureService;

    @GetMapping("/predictions/{id}/{result}")
    public void updatePredictionResult(@PathVariable("id") Long id, 
            @PathVariable("result") Result result) {
        predictionService.updateResult(id, result);
    }

    @GetMapping("/missing/home/{id}/{goals}")
    public void updateHomeGoals(@PathVariable("id") Long id, 
            @PathVariable("goals") Integer goals) {
        fixtureService.updateHomeGoals(id, goals);
    }

    @GetMapping("/missing/away/{id}/{goals}")
    public void updateAwayGoals(@PathVariable("id") Long id, 
            @PathVariable("goals") Integer goals) {
        fixtureService.updateAwayGoals(id, goals);
    }

    @GetMapping("/fixture/date/{id}/{date}")
    public void updateFixtureDate(@PathVariable("id") Long id,
            @DateTimeFormat(pattern = "yyyy-MM-dd") 
            @PathVariable("date") Date date) {
        fixtureService.updateDate(id, date);
    }
}