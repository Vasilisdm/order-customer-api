package com.example.dao

import com.example.models.CustomerCreated
import com.example.models.CustomerCreation

interface Customers {
    suspend fun getAll(): List<CustomerCreated>
    suspend fun get(id: Int): CustomerCreated?
    suspend fun add(customerCreation: CustomerCreation): CustomerCreated?
    suspend fun edit(id: Int, firstName: String, lastName: String, email: String): Boolean
    suspend fun delete(id: Int): Boolean
}