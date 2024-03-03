package com.novikov.companies.api.repository

import com.novikov.companies.api.model.Address
import org.springframework.data.jpa.repository.JpaRepository

interface AddressRepository : JpaRepository<Address, Int>{
}