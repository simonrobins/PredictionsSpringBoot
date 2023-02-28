package uk.simonrobins.PredictionsSpringBoot.entity;

public class TeamData
{
    private static final int 
        ID = 0, 
        PLAYED = 1,
        WON = 2,
        DRAWN = 3,
        LOST = 4,
        GF = 5,
        GA = 6,
        GD = 7,
        POINTS = 8;

    private final Team team;
    private final Long[] data;

    public TeamData(Team team, Long[] data)
    {
        this.team = team;
        this.data = data;
    }

    public Team getTeam()
    {
        return this.team;
    }

    public Long getId()
    {
        return this.data[ID];
    }

    public Long getPlayed()
    {
        return this.data[PLAYED];
    }

    public Long getWon()
    {
        return this.data[WON];
    }

    public Long getDrawn()
    {
        return this.data[DRAWN];
    }

    public Long getLost()
    {
        return this.data[LOST];
    }

    public Long getGoalsFor()
    {
        return this.data[GF];
    }

    public Long getGoalsAgainst()
    {
        return this.data[GA];
    }

    public Long getGoalDifference()
    {
        return this.data[GD];
    }

    public Long getPoints()
    {
        return this.data[POINTS];
    }
}
