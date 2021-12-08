package ir.imancn.modelmapper.test

class Dto(name: String, age: Int) {

    var name: String? = name
    var age: Int? = age
    var mappingType: MappingType? = null

    fun toEntity(): Entity {
        val entity = Entity()
        entity.age = this.age ?: -1
        entity.name = this.name ?: "N/A"
        entity.mappingType = this.mappingType
        return entity
    }
}

enum class MappingType {
    MODEL_MAPPER,
    EXTENSION_FUNCTION,
    DOZER
}
