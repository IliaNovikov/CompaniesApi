package com.novikov.companies.api.controller

import com.novikov.companies.api.model.Address
import com.novikov.companies.api.model.Company
import com.novikov.companies.api.model.CompanyRequest
import com.novikov.companies.api.repository.AddressRepository
import com.novikov.companies.api.repository.CompanyRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/companies")
class CompanyController {

    @Autowired
    private lateinit var companyRepository: CompanyRepository
    @Autowired
    private lateinit var addressRepository: AddressRepository

    @GetMapping("/all")
    @ResponseBody
    private fun getAllCompanies() : Iterable<Company>{
        return companyRepository.findAll()
    }

    @GetMapping("/{id}")
    private fun getCompany(id: Int) : Company{
        return companyRepository.findById(id).get()
    }

    @PostMapping("/add")
    @ResponseBody
    private fun addCompany(newCompany: CompanyRequest) : Company{
//        val address = Address(id = 0, postIndex = postIndex, city = city, street = street, house = house, company = null)
//        addressRepository.save(address)

        val address = Address(id = 0,
            postIndex = newCompany.postIndex,
            city = newCompany.city,
            street = newCompany.street,
            house = newCompany.house,
            company = null)
        addressRepository.save(address)

        val company = Company(id = 0,
            name = newCompany.name,
            contactFio = newCompany.contactFio,
            contactPhone = newCompany.contactPhone,
            email = newCompany.email,
            siteLink = newCompany.siteLink,
            address = address)

        companyRepository.save(company)

//        val company = Company(id = 0, name = name, contactFio = contactFio, contactPhone = contactPhone, email = email, siteLink = siteLink, address = address)
//        companyRepository.save(company)

        return company

    }
    @PutMapping("/{id}/edit")
    @ResponseBody
    private fun editCompany(@PathVariable id: Int, newCompany: CompanyRequest) : Company{

        val company = companyRepository.findById(id).get()
        company.also {
            it.name = newCompany.name
            it.contactFio = newCompany.contactFio
            it.contactPhone = newCompany.contactPhone
            it.email = newCompany.email
            it.siteLink = newCompany.siteLink
        }
        companyRepository.save(company)

        val address = addressRepository.findById(company.id).get()
        address.also {
            it.postIndex = newCompany.postIndex
            it.city = newCompany.city
            it.street = newCompany.street
            it.house = newCompany.house
        }
        addressRepository.save(address)
        return company
    }

    @DeleteMapping("/{id}/delete")
    @ResponseBody
    private fun deleteCompany(@PathVariable id: Int) : Company{
        val company = companyRepository.findById(id)
        companyRepository.deleteById(id)
        addressRepository.delete(company.get().address!!)
        return company.get()
    }

}