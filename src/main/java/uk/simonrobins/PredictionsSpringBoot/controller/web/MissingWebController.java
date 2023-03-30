package uk.simonrobins.PredictionsSpringBoot.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uk.simonrobins.PredictionsSpringBoot.entity.Fixture;
import uk.simonrobins.PredictionsSpringBoot.entity.Team;
import uk.simonrobins.PredictionsSpringBoot.service.FixtureService;
import uk.simonrobins.PredictionsSpringBoot.service.TeamService;

import java.util.Set;

@Controller
@RequestMapping("/missing")
public class MissingWebController {
    @Autowired
    private FixtureService fixtureService;
    @Autowired
    private TeamService teamService;

    @GetMapping
    public String index(Model model,
            @RequestParam(required = false) Integer round,
            @RequestParam(required = false) Long teamId) {

        Set<Fixture> fixtures = fixtureService.findMissing(round, teamId);
        Set<Integer> rounds = fixtureService.findMissingRounds();
        Set<Team> teams = teamService.findAll();

        model.addAttribute("rounds", rounds);
        model.addAttribute("teams", teams);

        model.addAttribute("round", round);
        model.addAttribute("teamId", teamId);
        model.addAttribute("fixtures", fixtures);

        return "missing";
    }
}