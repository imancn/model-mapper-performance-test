package ir.imancn.modelmapper

import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component

@Component
class ModelMapperUtil : ModelMapper() {
    init {
        this.configuration.setFieldMatchingEnabled(true).fieldAccessLevel = org.modelmapper.config.Configuration.AccessLevel.PUBLIC
    }
}