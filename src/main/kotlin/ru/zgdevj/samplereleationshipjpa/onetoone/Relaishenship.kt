package ru.zgdevj.springwithkotlin.relationshipjpasimple.onetoone

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne

/*
Unidirectional relationship

A unidirectional relationship has only the owner side,
which means the side of the relationship that contains the foreign key in the database.
*/
@Entity
class User(

    @Id
    var id: Long = 0,

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    var user_details: UserDetails? = null,
)

@Entity
class UserDetails (

    var user_id: Long = 0,

    @Id
    var user_detail_id: Long = 0,

    var name: String? = null,
    var location: String? = null,
    var email: String? = null
)