package uk.simonrobins.PredictionsSpringBoot.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "fixtures")
public class Fixture
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private int matchNumber;
	private int roundNumber;
	private Date date;

	@ManyToOne
	private Team home;
	@ManyToOne
	private Team away;

	Integer homeGoals;
	Integer awayGoals;
	private Result result;

	public Fixture()
	{
	}

	public Fixture(int matchNumber, 
		int roundNumber, 
		Date date, 
		Team home, 
		Team away, 
		Integer homeGoals, 
		Integer awayGoals, 
		Result result)
	{
		this.matchNumber = matchNumber;
		this.roundNumber = roundNumber;
		this.date = date;
		this.home = home;
		this.away = away;
		this.homeGoals = homeGoals;
		this.awayGoals = awayGoals;
		this.result = result;
	}

	public Long getId()
	{
		return this.id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public int getMatchNumber()
	{
		return matchNumber;
	}

	public void setMatchNumber(int matchNumber)
	{
		this.matchNumber = matchNumber;
	}

	public int getRoundNumber()
	{
		return roundNumber;
	}

	public void setRoundNumber(int roundNumber)
	{
		this.roundNumber = roundNumber;
	}

	public Date getDate()
	{
		return this.date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public Team getHome()
	{
		return this.home;
	}

	public void setHome(Team home)
	{
		this.home = home;
	}

	public Team getAway()
	{
		return this.away;
	}

	public void setAway(Team away)
	{
		this.away = away;
	}

	public Integer getHomeGoals()
	{
		return homeGoals;
	}

	public void setHomeGoals(Integer homeGoals)
	{
		this.homeGoals = homeGoals;
	}

	public Integer getAwayGoals()
	{
		return awayGoals;
	}

	public void setAwayGoals(Integer awayGoals)
	{
		this.awayGoals = awayGoals;
	}

	public Result getResult()
	{
		return this.result;
	}

	public void setResult(Result result)
	{
		this.result = result;
	}

	@Override
	public String toString()
	{
		return "Fixture2 [" + this.date + ", " + this.home + ", " + this.away + ", " + this.result + "]";
	}
}