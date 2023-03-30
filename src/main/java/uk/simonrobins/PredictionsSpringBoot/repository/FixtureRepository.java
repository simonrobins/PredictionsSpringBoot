package uk.simonrobins.PredictionsSpringBoot.repository;

import java.util.Date;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import uk.simonrobins.PredictionsSpringBoot.entity.Fixture;

import org.springframework.data.jpa.repository.Query;

public interface FixtureRepository extends CrudRepository<Fixture, Long> {

  @Query(value = """
      select f from Fixture f
      order by f.date desc
      """)
  Set<Fixture> findAll();

  @Query(value = """
      select distinct f.round as round from Fixture f
      order by round desc
      """)
  Set<Integer> findAllRounds();

  @Query(value = """
      select distinct f.round as round from Fixture f
      where (f.homeGoals is null or f.awayGoals is null)
      and f.date < ?1
      order by round desc
      """)
  Set<Integer> findMissingRounds(Date date);

  @Query(value = """
      select distinct f.round from Fixture f
      where (f.homeGoals is not null and f.awayGoals is not null)
      and f.date < ?1
      order by round desc
      """)
  Set<Integer> findResultsRounds(Date date);

  @Query(value = """
      select distinct f.round from Fixture f
      where (f.homeGoals is null or f.awayGoals is null)
      and f.date > ?1
      order by round desc
      """)
  Set<Integer> findUpcomingRounds(Date date);

  @Query(value = """
      select f from Fixture f
      where (f.homeGoals is null or f.awayGoals is null)
      and f.date < ?1
      order by f.date
      """)
  Set<Fixture> findPredictionRounds(Date date);

  @Query(value = """
      select f from Fixture f
      where (f.homeGoals is null or f.awayGoals is null)
      and (?2 is null or f.round = ?2)
      and (?3 is null or f.home.id = ?3 or f.away.id = ?3)
      and f.date < ?1
      order by f.date
      """)
  Set<Fixture> findMissing(Date date, Integer round, Long teamId);

  @Query(value = """
      select f from Fixture f
      where (f.homeGoals is null or f.awayGoals is null)
      and (?2 is null or f.round = ?2)
      and (?3 is null or f.home.id = ?3 or f.away.id = ?3)
      and f.date > ?1
      order by f.date
      """)
  Set<Fixture> findUpcoming(Date date, Integer round, Long teamId);

  @Query(value = """
    select f from Fixture f
    where (f.homeGoals is not null and f.awayGoals is not null)
    and (?2 is null or f.round = ?2)
    and (?3 is null or f.home.id = ?3 or f.away.id = ?3)
    and f.date < ?1
    order by f.date
    """)
Set<Fixture> findResults(Date date, Integer round, Long teamId);

  @Query(nativeQuery = true, value = """
          select id,  sum(played) played,
                      sum(won) won,
                      sum(drawn) drawn,
                      sum(lost) lost,
                      sum(gf) gf,
                      sum(ga) ga,
                      sum(gf - ga) gd,
                      sum(won * 3 + drawn) points
              from (
            select id, sum(count) played, 0 won, 0 drawn, 0 lost, 0 gf, 0 ga from (
              select home_id id, count(*) count from fixtures where home_goals is not null group by id
                union all
              select away_id id, count(*) count from fixtures where away_goals is not null group by id
            ) group by id
            union all
            select id, 0 played, sum(count) won, 0 drawn, 0 lost, 0 gf, 0 ga from (
              select home_id id, count(*) count from fixtures where home_goals > away_goals group by id
                union all
              select away_id id, count(*) count from fixtures where away_goals > home_goals group by id
            ) group by id
            union all
            select id, 0 played, 0 won, sum(count) drawn, 0 lost, 0 gf, 0 ga from (
              select home_id id, count(*) count from fixtures where home_goals = away_goals group by id
                union all
              select away_id id, count(*) count from fixtures where away_goals = home_goals group by id
            ) group by id
            union
            select id, 0 played, 0 won, 0 drawn, sum(count) lost, 0 gf, 0 ga from (
              select home_id id, count(*) count from fixtures where home_goals < away_goals group by id
                union all
              select away_id id, count(*) count from fixtures where away_goals < home_goals group by id
            ) group by id
            union all
              select id, 0 played, 0 won, 0 drawn, 0 lost, sum(goals) GF, 0 GA from (
                select home_id id, sum(home_goals) goals from fixtures group by home_id
                union all
                select away_id id, sum(away_goals) goals from fixtures group by away_id
              ) group by id
              union
              select id, 0 played, 0 won, 0 drawn, 0 lost, 0 GF, sum(goals) GA from (
                select home_id id, sum(away_goals) goals from fixtures group by home_id
                union all
                select away_id id, sum(home_goals) goals from fixtures group by away_id
              ) group by id
            ) group by id order by points desc, gd desc
      """)
  Set<Long[]> resultsTable();
}