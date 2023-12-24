package ru.zgdevj.springwithkotlin.relationshipjpasimple.manytoone

import jakarta.persistence.*

/*
bidirectional relationships
 */

// the owner's side
@Entity
class Tweet(

    @Id
    @Column(name = "tweet_id")
    var id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User? = null
)

@Entity
class User(
    @Id
    var id: Long = 0,

    @OneToMany(mappedBy = "user")
    var tweets: MutableList<Tweet> = ArrayList()
)