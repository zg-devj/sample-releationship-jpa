package ru.zgdevj.samplereleationshipjpa.onetomany

import jakarta.persistence.*

// the owner's side
@Entity
class User(

    @Id
    var id: Long = 0,

    @OneToMany
    @JoinColumn(name = "user_id", nullable = false)
    var tweets: MutableList<Tweet> = ArrayList(),
)

@Entity
class Tweet(
    @Id
    @Column(name = "tweet_id")
    var id: Long = 0,

    var user_id: Long = 0,
)