package com.novikov.companies.api.repository

import com.novikov.companies.api.model.Company
import org.springframework.data.jpa.repository.JpaRepository

interface CompanyRepository : JpaRepository<Company, Int> {
}