import com.sweetsavvy.core.entity.UserEntity
import com.sweetsavvy.core.model.SignUpRequestDto
import com.sweetsavvy.core.repository.UserRepository
import com.sweetsavvy.core.service.UserService
import com.sweetsavvy.core.service.impl.UserServiceImpl
import com.news360horizon.test.core.TestConfig
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification
import spock.lang.Subject

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.times
import static org.mockito.Mockito.verify

@SpringBootTest(classes = TestConfig)
class UserServiceTest extends Specification {


    UserRepository userRepository = Mock()

    AuthenticationManager authenticationManager = Mock()

    PasswordEncoder encoder = Mock()

    ModelMapper modelMapper = Mock()

    @Subject
    UserService userService

    def setup() {
        userService = new UserServiceImpl(userRepository, encoder, modelMapper, authenticationManager)
    }

    def "register user with UserService"() {
        given:
        def user = new UserEntity()
        user.setId(1L)
        user.setName("test")
        user.setEmail("test@news360horizon.com")
        user.setPassword("test@1234")
        user.setAddress("Baridhara, Dhaka")
        user.setPhoneNo("01700000000")

        def authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())

        and:
        1 * userRepository.save(_) >> user
        1 * authenticationManager.authenticate(authentication as UsernamePasswordAuthenticationToken) >> authentication

        def signUpRequestDto = new SignUpRequestDto(
                "test@news360horizon.com",
                "test@1234",
                Set.of(1L),
                "Baridhara, Dhaka",
                "test",
                "01700000000"
        )

        when:
        def result = userService.save(signUpRequestDto)

        then:
        result != null
        result.getEmail() == user.getEmail()
    }
}