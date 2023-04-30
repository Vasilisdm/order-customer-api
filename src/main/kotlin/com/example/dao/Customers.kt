package com.example.dao

import com.example.models.CustomerCreated
import com.example.models.CustomerCreation

interface Customers {
    suspend fun getAll(): List<CustomerCreated>
    suspend fun get(id: Long): CustomerCreated?
    suspend fun add(customerCreation: CustomerCreation): CustomerCreated?
    suspend fun edit(customerCreated: CustomerCreated): Boolean
    suspend fun delete(id: Long): Boolean
}