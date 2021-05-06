package io.showgazer.ipldashboard.controller;

import io.showgazer.ipldashboard.model.Match;
import io.showgazer.ipldashboard.model.Team;
import io.showgazer.ipldashboard.repository.MatchRepository;
import io.showgazer.ipldashboard.repository.TeamRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


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

    @GetMapping("/team/{teamName}/matches")
    public List<Match> getMatchesForTeam(@PathVariable String teamName, @RequestParam int year){
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year+1, 1, 1);

        return this.matchRepository.getMatchesByTeamBetweenDates(
                teamName,
                startDate,
                endDate
        );
    }

    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {

        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;

    }
}
