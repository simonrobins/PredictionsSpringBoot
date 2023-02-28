package uk.simonrobins.PredictionsSpringBoot.entity;

public class UserAndScore
{
    private User user;
    private Long score;

    public UserAndScore(User user, Long score)
    {
        this.user = user;
        this.score = score;
    }

    public User getUser()
    {
        return this.user;
    }

    public Long getScore()
    {
        return this.score;
    }
}
