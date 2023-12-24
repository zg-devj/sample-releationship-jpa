package ru.zgdevj.springwithkotlin.relationshipjpasimple.manytomany

import jakarta.persistence.*

/*
отношения двунаправленные
 */

// сторона-владелец
@Entity
class Animal(
    var species: String = "",
    var name: String = "",
    var ruinsFurniture: Boolean = false,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long = 0

    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
        name = "animal_person",
        joinColumns = [JoinColumn(name = "animal_id")],
        inverseJoinColumns = [JoinColumn(name = "person_id")]
    )
    var peopleInContact: MutableSet<Person> = LinkedHashSet()
}