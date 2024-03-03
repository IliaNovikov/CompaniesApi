package com.novikov.companies.api.model

import jakarta.persistence.*

@Entity
@Table(name = "addresses")
data class Address(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int,
                   val postIndex: Int,
                   val city: String,
                   val street: String,
                   val house: Int,
                   @OneToOne(mappedBy = "address") val company: Company?
)
