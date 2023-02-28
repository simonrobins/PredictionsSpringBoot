package uk.simonrobins.PredictionsSpringBoot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Sort;

import uk.simonrobins.PredictionsSpringBoot.entity.User;

public interface UserRepository extends CrudRepository<User, Long>
{
    List<User> findAll(Sort sort);
}