package uk.simonrobins.PredictionsSpringBoot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import uk.simonrobins.PredictionsSpringBoot.entity.Fixture;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;

public interface FixtureRepository extends CrudRepository<Fixture, Long>
{
    List<Fixture> findAll(Sort sort);

    @Query(nativeQuery = true, value="""
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
          union all
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
            union all
            select id, 0 played, 0 won, 0 drawn, 0 lost, 0 GF, sum(goals) GA from (
              select home_id id, sum(away_goals) goals from fixtures group by home_id
              union all
              select away_id id, sum(home_goals) goals from fixtures group by away_id
            ) group by id
          ) group by id order by points desc
    """
    )
    List<Long[]> resultsTable();
}