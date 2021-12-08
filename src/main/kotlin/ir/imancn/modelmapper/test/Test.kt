package ir.imancn.modelmapper.test

import org.modelmapper.ModelMapper
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class Test(
        private val repository: Repository,
        private val mapper: ModelMapper
    ) {

    private val logger = LoggerFactory.getLogger(javaClass.simpleName)
    private val objCount = 100000

    @PostConstruct
    fun start(){
        val dtoList = generateDtoList()
        saveMappedDto(dtoList, MappingType.EXTENSION_FUNCTION)
        saveMappedDto(dtoList, MappingType.MODEL_MAPPER)
    }

    private fun saveMappedDto(dtoList: List<Dto>, mappingType: MappingType) {
        logger.info("Map $objCount Objects with $mappingType started.")
        val entities = mutableListOf<Entity>()
        val startTime = System.currentTimeMillis()
        dtoList.forEach { dto ->
            dto.mappingType = mappingType
            when (mappingType){
                MappingType.MODEL_MAPPER -> entities.add(mapper.map(dto, Entity::class.java))
                MappingType.EXTENSION_FUNCTION -> entities.add(dto.toEntity())
            }
        }
        val endTime = System.currentTimeMillis()
        logger.info("Map $objCount Objects with $mappingType ended.")
        logger.info("Map $objCount Objects with $mappingType take ${(endTime - startTime) / 1000.000} sec time.")
        val savedEntities = repository.saveAll(entities)
        logger.info("${savedEntities.size} has been saved.")
        logger.info("#########################################################")
    }

    private fun generateDtoList(): MutableList<Dto> {
        val nameList = initNameList()
        val dtoList = mutableListOf<Dto>()
        for (i in 1 .. objCount) {
            val dto = Dto(nameList.random(), (Math.random() * 80).toInt())
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