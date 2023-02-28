package uk.simonrobins.PredictionsSpringBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import uk.simonrobins.PredictionsSpringBoot.entity.User;
import uk.simonrobins.PredictionsSpringBoot.repository.UserRepository;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    public User create(User user)
    {
        return userRepository.save(user);
    }

    public User get(Long userId)
    {
        return userRepository.findById(userId).get();
    }

    public List<User> findAll()
    {
        return userRepository.findAll(Sort.by("lastName", "firstName"));
    }

    public User update(User user)
    {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setInitials(user.getInitials());
        existingUser.setEmail(user.getEmail());
        return userRepository.save(existingUser);
    }

    public void delete(Long userId)
    {
        userRepository.deleteById(userId);
    }
}