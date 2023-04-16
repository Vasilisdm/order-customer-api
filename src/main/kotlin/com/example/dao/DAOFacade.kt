package com.example.dao

import com.example.models.CustomerCreated
import com.example.models.CustomerCreation

interface DAOFacade {
    suspend fun allCustomers(): List<CustomerCreated>
    suspend fun customer(id: Int): CustomerCreated?
    suspend fun addNewCustomer(customerCreation: CustomerCreation): CustomerCreated?
    suspend fun editCustomer(id: Int, firstName: String, lastName: String, email: String): Boolean
    suspend fun deleteCustomer(id: Int): Boolean
}