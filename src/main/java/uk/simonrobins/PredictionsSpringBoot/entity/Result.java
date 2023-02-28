package uk.simonrobins.PredictionsSpringBoot.entity;

public enum Result
{
    HOME("Home"),
    AWAY("Away"),
    DRAW("Draw");

    private final String name;

    private Result(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }
}