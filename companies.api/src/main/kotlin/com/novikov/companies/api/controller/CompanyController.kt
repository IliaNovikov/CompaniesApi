package com.novikov.companies.api.controller

import com.novikov.companies.api.model.Address
import com.novikov.companies.api.model.Company
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

    @PostMapping("/add")
    @ResponseBody
    private fun addCompany(@RequestParam name: String,
                           @RequestParam contactFio: String,
                           @RequestParam contactPhone: String,
                           @RequestParam email: String,
                           @RequestParam siteLink: String,
                           @RequestParam postIndex: Int,
                           @RequestParam city: String,
                           @RequestParam street: String,
                           @RequestParam house: Int) : Company{
        val address = Address(id = 0, postIndex = postIndex, city = city, street = street, house = house, company = null)
        addressRepository.save(address)

        val company = Company(id = 0, name = name, contactFio = contactFio, contactPhone = contactPhone, email = email, siteLink = siteLink, address = address)
        companyRepository.save(company)

        return company

    }
    @PutMapping("/{id}/edit")
    @ResponseBody
    private fun editCompany(@PathVariable id: Int,
                            @RequestParam name: String,
                            @RequestParam contactFio: String,
                            @RequestParam contactPhone: String,
                            @RequestParam email: String,
                            @RequestParam siteLink: String) : Company{

        val company = companyRepository.findById(id)
        company.get().also {
            it.name = name
            it.contactFio = contactFio
            it.contactPhone = contactPhone
            it.email = email
            it.siteLink = siteLink
        }
        companyRepository.save(company.get())
        return company.get()
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