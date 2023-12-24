package ru.zgdevj.springwithkotlin.relationshipjpasimple.manytomany

import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class EntityService(
    @Autowired entityManagerFactory: EntityManagerFactory,
) {
    private val entityManager: EntityManager

    init {
        entityManager = entityManagerFactory.createEntityManager()
    }

    fun insertEntities() {
        entityManager.transaction.begin()

        val catLeo = Animal("cat", "Leo", false)
        val dogCharlie = Animal("dog", "Charlie", true)
        val dogBella = Animal("dog", "Bella", false)

        val catLover1 = Person("James", 8)
        val catLover2 = Person("Mary", 6)
        val dogLover1 = Person("John", 4)

        catLeo.peopleInContact = mutableSetOf(catLover1,catLover2)
        dogCharlie.peopleInContact.add(dogLover1)
        dogBella.peopleInContact.add(dogLover1)

        catLover1.animalsInContact.add(catLeo)
        catLover2.animalsInContact.add(catLeo)
        dogLover1.animalsInContact = mutableSetOf(dogCharlie, dogBella)

        entityManager.persist(catLeo)
        entityManager.persist(dogCharlie)
        entityManager.persist(dogBella)

        entityManager.transaction.commit()
        entityManager.clear()
    }

    fun addPersonToSet() {
        entityManager.transaction.begin()
        val foundAnimal = entityManager.find(Animal::class.java, 2L)
        val newDogLover = Person("Emma", 5)

        // INSERT INTO person VALUES("Emma", 5);
        // INSERT INTO animal_person VALUES(2, 4)
        foundAnimal.peopleInContact.add(newDogLover)
        entityManager.transaction.commit()
        entityManager.clear()
    }

    fun deletePersonFromSet() {
        entityManager.transaction.begin()
        val foundAnimal = entityManager.find(Animal::class.java, 1L)
        val firstPersonFromSet = foundAnimal.peopleInContact.iterator().next()

        // DELETE FROM animal_person
        // WHERE animal_id=1 and person_id=1
        foundAnimal.peopleInContact.remove(firstPersonFromSet)
        entityManager.transaction.commit()
        entityManager.clear()
    }

    fun addAnimalToSet() {
        entityManager.transaction.begin()
        val foundPerson = entityManager.find(Person::class.java, 3L)
        val newDog = Animal("dog", "Oscar", false)

        //doesn't generate a query
        foundPerson.animalsInContact.add(newDog)
        entityManager.transaction.commit()
        entityManager.clear()
    }

    fun deleteAnimalFromSet() {
        entityManager.transaction.begin()
        val foundPerson = entityManager.find(Person::class.java, 1L)
        val firstAnimalFromSet = foundPerson.animalsInContact.iterator().next()

        //doesn't generate a query
        foundPerson.animalsInContact.remove(firstAnimalFromSet)
        entityManager.transaction.commit()
        entityManager.clear()
    }
}