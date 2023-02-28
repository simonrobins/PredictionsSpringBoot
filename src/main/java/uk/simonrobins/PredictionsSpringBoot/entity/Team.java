package uk.simonrobins.PredictionsSpringBoot.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@Table(name = "teams")
public class Team
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)
	private String name;
	@OneToMany(mappedBy="home")
	Set<Fixture> homeFixtures;
	@OneToMany(mappedBy="away")
	Set<Fixture> awayFixtures;

	public Team()
	{
	}

	public Team(String name)
	{
		this.name = name;
	}

	public Long getId()
	{
		return this.id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public String toString()
	{
		return "Team [" + this.name + "]";
	}
}