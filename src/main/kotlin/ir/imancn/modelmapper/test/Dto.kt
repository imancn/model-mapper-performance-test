package ir.imancn.modelmapper.test

import com.googlecode.jmapper.annotations.JMap

class Dto{
    @JMap
    var name: String? = null
    @JMap
    var age: Int? = null
    @JMap
    var mappingType: MappingType? = null

    constructor()

    constructor(name: String, age: Int) : this() {
        this.name = name
        this.age = age
        this.mappingType = null
    }

    fun toEntity(): Entity {
        val entity = Entity()
        entity.age = this.age ?: -1
        entity.name = this.name ?: "N/A"
        entity.mappingType = this.mappingType
        return entity
    }
}

