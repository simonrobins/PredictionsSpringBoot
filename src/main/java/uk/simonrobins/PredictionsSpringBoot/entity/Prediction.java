package uk.simonrobins.PredictionsSpringBoot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id; 
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "predictions")
public class Prediction
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne
	private User user;
	@ManyToOne
	private Fixture fixture;
	private Result result;

	public Prediction()
	{
	}

	public Prediction(User user, Fixture fixture, Result result)
	{
		this.user = user;
		this.fixture = fixture;
		this.result = result;
	}

	public Long getId()
	{
		return this.id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public Fixture getFixture()
	{
		return fixture;
	}

	public void setFixture(Fixture fixture)
	{
		this.fixture = fixture;
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
		return "Prediction [" + this.user + ", " + this.fixture + ", " + this.result + "]";
	}
}