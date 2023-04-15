package com.example.dao

import com.example.models.Customer

class DAOFacadeImpl : DAOFacade {
    override suspend fun allCustomers(): List<Customer> {
        TODO("Not yet implemented")
    }

    override suspend fun customer(id: Int): Customer? {
        TODO("Not yet implemented")
    }

    override suspend fun addNewCstomer(firstName: String, lastName: String, email: String): Customer? {
        TODO("Not yet implemented")
    }

    override suspend fun editCustomer(id: Int, firstName: String, lastName: String, email: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCustomer(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}