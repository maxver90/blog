package maxver90.blog.security;

import maxver90.blog.entity.User;
import maxver90.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    // UserDetailsService - интерфейс, в котором описан метод для поиска пользователя. В реализации этого метода
    // должна быть описана конкретная логика для поиска и формирования объекта UserDetails

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByLogin(username);
        if (user.isPresent()){
            UserDetails userDetails = new UserDetailsImpl(user.get());
            return userDetails;
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
