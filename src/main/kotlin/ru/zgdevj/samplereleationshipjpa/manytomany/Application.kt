package ru.zgdevj.springwithkotlin.relationshipjpasimple.manytomany

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component

@SpringBootApplication
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

@Component
class ShelterCommandLineRunner(
    @Autowired val entityService: EntityService,
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        entityService.insertEntities()
        entityService.addPersonToSet()
        entityService.deletePersonFromSet()
        entityService.addAnimalToSet()
        entityService.deleteAnimalFromSet()
    }

}
