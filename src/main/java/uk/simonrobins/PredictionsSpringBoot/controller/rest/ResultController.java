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
@RequestMapping("api/")
public class ResultController
{
    @Autowired
    private PredictionService predictionService;
    @Autowired
    private FixtureService fixtureService;

    @GetMapping("prediction/result/{id}/{result}")
    public void prediction(@PathVariable("id") Long id, @PathVariable("result") Result result)
    {
        predictionService.updateResult(id, result);
    }

    @GetMapping("fixture/result/{id}/{result}")
    public void fixture(@PathVariable("id") Long id, @PathVariable("result") Result result)
    {
        fixtureService.updateResult(id, result);
    }

    @GetMapping("fixture/date/{id}/{date}")
    public void updateFixtureDate(@PathVariable("id") Long id,
        @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("date") Date date)
    {
        fixtureService.updateDate(id, date);
    }
}