package ir.imancn.oms.config

import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component

@Component
class ModelMapperConfig : ModelMapper() {
    init {
        this.configuration.setFieldMatchingEnabled(true).fieldAccessLevel = org.modelmapper.config.Configuration.AccessLevel.PUBLIC
    }
}