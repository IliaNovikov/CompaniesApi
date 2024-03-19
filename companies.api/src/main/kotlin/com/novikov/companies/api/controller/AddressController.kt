package com.novikov.companies.api.controller

import com.novikov.companies.api.model.Address
import com.novikov.companies.api.repository.AddressRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/addresses")
class AddressController {

    @Autowired
    private lateinit var addressRepository: AddressRepository

    @PutMapping("/{id}/edit")
    @ResponseBody
    private fun editAddress(@PathVariable id: Int,
                            @RequestParam postIndex: Int,
                            @RequestParam city: String,
                            @RequestParam street: String,
                            @RequestParam house: Int) : Address{

        val address = addressRepository.findById(id).get()

        address.also {
            it.postIndex = postIndex
            it.city = city
            it.street = street
            it.house = house
        }

        addressRepository.save(address)
        return address

    }

//    @PutMapping("/{id}/edit")
//    @ResponseBody
//    private fun editAddress(@PathVariable id: Int, newAddress: Address) : Address{
//
//        var address = addressRepository.findById(id).get()
//
//        address = newAddress
//
//        addressRepository.save(address)
//        return address
//
//    }

}