package ir.imancn.modelmapper

import ir.imancn.modelmapper.config.JMapperBeans
import ir.imancn.modelmapper.model.Dto
import ir.imancn.modelmapper.model.Entity
import ir.imancn.modelmapper.model.MappingType
import org.dozer.DozerBeanMapper
import org.modelmapper.ModelMapper
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct
import kotlin.system.measureTimeMillis

@Component
class Test(
        private val modelMapper: ModelMapper,
        private val jMapper: JMapperBeans
    ) {

    private val dozer = DozerBeanMapper()
    private val logger = LoggerFactory.getLogger(javaClass.simpleName)
    private val objCount = 10_000_000

    @PostConstruct
    fun start(){
        val dtoList = generateDtoList()

        // Mapping Time + Configuration Time
        map(dtoList, MappingType.DOZER)
        map(dtoList, MappingType.MODEL_MAPPER)
        map(dtoList, MappingType.JMAPPER)

        map(dtoList, MappingType.EXTENSION_FUNCTION)

        // Just Mapping Time
        map(dtoList, MappingType.JMAPPER)
        map(dtoList, MappingType.MODEL_MAPPER)
        map(dtoList, MappingType.DOZER)
    }

    private fun map(dtoList: List<Dto>, mappingType: MappingType) {
        logger.info("Map $objCount Objects with $mappingType started.")
        measureTimeMillis {
            dtoList.map { dto ->
                dto.mappingType = mappingType
                when (mappingType) {
                    MappingType.DOZER -> dozer.map(dto, Entity::class.java)
                    MappingType.MODEL_MAPPER -> modelMapper.map(dto, Entity::class.java)
                    MappingType.JMAPPER -> jMapper.convertDtoToEntity().getDestination(dto)
                    MappingType.EXTENSION_FUNCTION -> dto.toEntity()
                }
            }
        }.let {
            logger.info("Map $objCount Objects with $mappingType ended and take ${it / 1000.000} sec time.")
        }
        logger.info("#########################################################")
    }

    private fun generateDtoList(): MutableList<Dto> {
        val nameList = initNameList()
        val dtoList = mutableListOf<Dto>()
        for (i in 1 .. objCount) {
            val dto = Dto(nameList.random(), (Math.random() * 60).toInt() + 15)
            dtoList.add(dto)
        }
        return dtoList
    }

    private fun initNameList(): MutableList<String> {
        return mutableListOf(
                "MAX", "KOBE", "OSCAR",
                "COOPER", "OAKLEY", "MAC",
                "CHARLIE", "REX", "RUDY",
                "TEDDY", "BAILEY", "CHIP",
                "BEAR", "CASH", "WALTER",
                "MILO", "JASPER", "BLAZE",
                "BENTLEY", "BO", "OZZY",
                "OLLIE", "BOOMER", "ODIN",
                "BUDDY", "LUCKY", "AXEL",
                "ROCKY", "RUGER", "BRUCE",
                "LEO", "BEAU", "ODIE",
                "ZEUS", "BAXTER", "ARLO",
                "DUKE", "OREO", "ECHO",
                "FINN", "GUNNER", "TANK",
                "APOLLO", "HENRY", "ROMEO",
                "MURPHY", "SIMBA", "PORTER",
                "DIESEL", "GEORGE", "HARLEY",
                "TOBY", "COCO", "OTIS",
                "LOUIE", "ROCKET", "ROCCO",
                "TUCKER", "ZIGGY", "REMI",
                "JAX", "PRINCE", "WHISKEY",
                "ACE", "SHADOW", "SAM",
                "JACK", "RILEY", "BUSTER",
                "KODA", "COPPER", "BUBBA",
                "WINSTON", "LUKE", "JAKE",
                "OLIVER", "MARLEY", "BENNY",
                "GUS", "ZEKE", "BOWIE",
                "LOKI", "LEVI", "DOZER",
                "MOOSE", "BENJI", "RUSTY",
                "ARCHIE", "RANGER", "JOEY",
                "BANDIT", "REMY", "KYLO",
                "SCOUT", "DEXTER", "RYDER",
                "THOR", "GIZMO", "TYSON",
                "BRUNO", "CHASE", "SAMSON",
                "KING", "CODY", "RAMBO",
                "BLUE", "SARGE", "HARRY",
                "ATLAS", "CHESTER", "GUCCI",
                "THEO", "MAVERICK", "MILES",
                "JACKSON", "LINCOLN", "WATSON",
                "HANK", "WALLY", "PEANUT", "TITAN"
        )
    }
}