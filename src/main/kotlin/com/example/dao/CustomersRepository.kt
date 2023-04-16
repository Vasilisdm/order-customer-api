package com.example.dao

import com.example.models.CustomerCreated
import com.example.models.CustomerCreation
import com.example.models.CustomersTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class CustomersRepository : Customers {
    override suspend fun getAll(): List<CustomerCreated> = DatabaseFactory.dbQuery {
        CustomersTable
            .selectAll()
            .map(::resultRowToCustomer)
    }

    override suspend fun get(id: Int): CustomerCreated? = DatabaseFactory.dbQuery {
        CustomersTable
            .select { CustomersTable.id eq id }
            .map(::resultRowToCustomer)
            .singleOrNull()
    }

    override suspend fun add(customerCreation: CustomerCreation): CustomerCreated? =
        DatabaseFactory.dbQuery {
            val insertStatement = CustomersTable.insert {
                autoIncColumn
                it[firstName] = customerCreation.firstName
                it[lastName] = customerCreation.lastName
                it[email] = customerCreation.email
            }
            insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToCustomer)
        }

    override suspend fun edit(id: Int, firstName: String, lastName: String, email: String): Boolean =
        DatabaseFactory.dbQuery {
            CustomersTable.update({ CustomersTable.id eq id }) {
                it[CustomersTable.lastName] = firstName
                it[CustomersTable.lastName] = lastName
                it[CustomersTable.email] = email
            } > 0
        }

    override suspend fun delete(id: Int): Boolean = DatabaseFactory.dbQuery {
        CustomersTable.deleteWhere { CustomersTable.id eq id } > 0
    }

    private fun resultRowToCustomer(row: ResultRow) = CustomerCreated(
        id = row[CustomersTable.id],
        firstName = row[CustomersTable.firstName],
        lastName = row[CustomersTable.lastName],
        email = row[CustomersTable.email]
    )
}