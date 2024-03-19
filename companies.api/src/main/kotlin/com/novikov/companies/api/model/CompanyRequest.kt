package com.novikov.companies.api.model

data class CompanyRequest(var name: String,
                          var contactFio: String,
                          var contactPhone: String,
                          var email: String,
                          var siteLink: String,
                          var postIndex: Int,
                          var city: String,
                          var street: String,
                          var house: Int)
