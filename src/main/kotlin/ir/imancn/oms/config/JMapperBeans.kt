package ir.imancn.oms.config

import com.googlecode.jmapper.JMapper
import com.googlecode.jmapper.api.JMapperAPI
import ir.imancn.oms.model.Dto
import ir.imancn.oms.model.Entity
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class JMapperBeans {

    @Bean
    fun convertDtoToEntity(): JMapper<Entity, Dto> {
        val api = JMapperAPI().add(JMapperAPI.mappedClass(Entity::class.java))
        return JMapper(Entity::class.java, Dto::class.java, api)
    }
}