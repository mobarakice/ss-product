import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.modelmapper.record.RecordModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TestConfig {
    @Bean
    static ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper()
        modelMapper.configuration.matchingStrategy = MatchingStrategies.STRICT
        modelMapper.registerModule(new RecordModule())
        return modelMapper
    }
}