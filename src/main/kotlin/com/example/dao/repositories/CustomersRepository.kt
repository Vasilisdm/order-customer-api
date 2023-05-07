package com.example.dao.repositories

import com.example.dao.Database
import com.example.dao.interfaces.Customers
import com.example.models.CustomerCreated
import com.example.models.CustomerCreation
import org.jooq.generated.Tables.CUSTOMERS

class CustomersRepository : Customers {

    private val create = Database.getConnection()

    override suspend fun getAll(): List<CustomerCreated> =
        create.select().from(CUSTOMERS).fetchInto(CustomerCreated::class.java)

    override suspend fun get(id: Long): CustomerCreated? =
        create.select().from(CUSTOMERS).where(CUSTOMERS.ID.eq(id)).fetchOneInto(CustomerCreated::class.java)

    override suspend fun add(customerCreation: CustomerCreation): CustomerCreated? =
        create.insertInto(CUSTOMERS, CUSTOMERS.FIRST_NAME, CUSTOMERS.LAST_NAME, CUSTOMERS.EMAIL)
            .values(customerCreation.firstName, customerCreation.lastName, customerCreation.email)
            .returningResult(CUSTOMERS)
            .fetchOneInto(CustomerCreated::class.java)

    override suspend fun edit(customerCreated: CustomerCreated): Boolean {
        val rowUpdated = create.update(CUSTOMERS)
            .set(CUSTOMERS.FIRST_NAME, customerCreated.firstName)
            .set(CUSTOMERS.LAST_NAME, customerCreated.lastName)
            .set(CUSTOMERS.EMAIL, customerCreated.email)
            .where(CUSTOMERS.ID.eq(customerCreated.id)).execute()

        return rowUpdated > 0
    }

    override suspend fun delete(id: Long): Boolean {
        val numberOfRowsDeleted = create.deleteFrom(CUSTOMERS).where(CUSTOMERS.ID.eq(id)).execute()
        return numberOfRowsDeleted > 0
    }

}