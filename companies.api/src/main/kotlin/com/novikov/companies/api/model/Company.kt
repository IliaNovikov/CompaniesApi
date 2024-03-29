package com.novikov.companies.api.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*

@Entity
@Table(name = "companies")
data class Company(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Int,
                   var name: String,
                   var contactFio: String,
                   var contactPhone: String,
                   var email: String,
                   var siteLink: String,
                   @JsonManagedReference @OneToOne @JoinColumn(name = "address_id") var address: Address?
)
