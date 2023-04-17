package com.example.dao

import com.example.models.CustomerCreated
import com.example.models.CustomerCreation

class CustomersRepository : Customers {
    override suspend fun getAll(): List<CustomerCreated> {
        TODO("Not yet implemented")
    }

    override suspend fun get(id: Int): CustomerCreated? {
        TODO("Not yet implemented")
    }

    override suspend fun add(customerCreation: CustomerCreation): CustomerCreated? {
        TODO("Not yet implemented")
    }

    override suspend fun edit(id: Int, firstName: String, lastName: String, email: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: Int): Boolean {
        TODO("Not yet implemented")
    }

}