package ir.imancn.modelmapper.model

import com.googlecode.jmapper.annotations.JMap
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Entity (
        @Id  @GeneratedValue(strategy= GenerationType.AUTO)
        var id: Long? = null,
        @JMap
        var name: String = "N/A",
        @JMap
        var age: Int = -1,
        @JMap
        var mappingType: MappingType? = null,
        var date: Date = Date(System.currentTimeMillis())
) {
    override fun toString(): String {
        return "Entity(" +
                "id=$id, " +
                "name='$name', " +
                "age=$age, " +
                "mappingType=$mappingType, " +
                "date=$date)"
    }
}
