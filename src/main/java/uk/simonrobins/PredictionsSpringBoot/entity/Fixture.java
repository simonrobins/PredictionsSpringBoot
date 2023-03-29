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

	private int match;
	private int round;
	private Date date;

	@ManyToOne
	private Team home;
	@ManyToOne
	private Team away;

	Integer homeGoals;
	Integer awayGoals;

	public Fixture()
	{
	}

	public Fixture(int match, 
		int round, 
		Date date, 
		Team home, 
		Team away, 
		Integer homeGoals, 
		Integer awayGoals)
	{
		this.match = match;
		this.round = round;
		this.date = date;
		this.home = home;
		this.away = away;
		this.homeGoals = homeGoals;
		this.awayGoals = awayGoals;
	}

	public Long getId()
	{
		return this.id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public int getMatch()
	{
		return match;
	}

	public void setMatch(int match)
	{
		this.match = match;
	}

	public int getRound()
	{
		return round;
	}

	public void setRound(int round)
	{
		this.round = round;
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

	@Override
	public String toString()
	{
		return "Fixture2 [" + this.date + ", " + this.home + ", " + this.away + "]";
	}
}