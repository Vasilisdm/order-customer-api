package com.example.dao

import com.example.models.CustomerCreated
import com.example.models.CustomerCreation
import com.example.models.Customers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class DAOFacadeImpl : DAOFacade {
    override suspend fun allCustomers(): List<CustomerCreated> = DatabaseFactory.dbQuery {
        Customers
            .selectAll()
            .map(::resultRowToCustomer)
    }

    override suspend fun customer(id: Int): CustomerCreated? = DatabaseFactory.dbQuery {
        Customers
            .select { Customers.id eq id }
            .map(::resultRowToCustomer)
            .singleOrNull()
    }

    override suspend fun addNewCustomer(customerCreation: CustomerCreation): CustomerCreated? =
        DatabaseFactory.dbQuery {
            val insertStatement = Customers.insert {
                autoIncColumn
                it[firstName] = customerCreation.firstName
                it[lastName] = customerCreation.lastName
                it[email] = customerCreation.email
            }
            insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToCustomer)
        }

    override suspend fun editCustomer(id: Int, firstName: String, lastName: String, email: String): Boolean =
        DatabaseFactory.dbQuery {
            Customers.update({ Customers.id eq id }) {
                it[Customers.lastName] = firstName
                it[Customers.lastName] = lastName
                it[Customers.email] = email
            } > 0
        }

    override suspend fun deleteCustomer(id: Int): Boolean = DatabaseFactory.dbQuery {
        Customers.deleteWhere { Customers.id eq id } > 0
    }

    private fun resultRowToCustomer(row: ResultRow) = CustomerCreated(
        id = row[Customers.id],
        firstName = row[Customers.firstName],
        lastName = row[Customers.lastName],
        email = row[Customers.email]
    )
}