package io.showgazer.ipldashboard.controller;

import io.showgazer.ipldashboard.model.Team;
import io.showgazer.ipldashboard.repository.MatchRepository;
import io.showgazer.ipldashboard.repository.TeamRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class TeamController {

    private TeamRepository teamRepository;
    private MatchRepository matchRepository;

    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName) {

        Team team = teamRepository.findByTeamName(teamName);

        team.setMatches(matchRepository.findLastMatchesByTeam(teamName,4));

        return team;

    }

    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {

        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;

    }
}
