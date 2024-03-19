package com.novikov.companies.api.model

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*

@Entity
@Table(name = "addresses")
data class Address(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int,
                   var postIndex: Int,
                   var city: String,
                   var street: String,
                   var house: Int,
                   @JsonBackReference @OneToOne(mappedBy = "address")var company: Company?
)
