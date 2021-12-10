package ir.imancn.modelmapper.model

import com.googlecode.jmapper.annotations.JMap
import java.util.*

data class Entity (
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
