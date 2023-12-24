package ru.zgdevj.springwithkotlin.relationshipjpasimple.manytomany

import jakarta.persistence.*

// обратная сторона
@Entity
class Person(
    var name: String = "",
    var freeHours: Int = 0,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @ManyToMany(mappedBy = "peopleInContact")
    var animalsInContact: MutableSet<Animal> = LinkedHashSet()
}