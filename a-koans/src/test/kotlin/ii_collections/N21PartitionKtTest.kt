package ii_collections

import ii_collections.data.customers
import ii_collections.data.reka
import ii_collections.data.shop
import org.junit.Assert.assertEquals
import org.junit.Test

class N21PartitionKtTest {
    @Test fun testGetCustomersWhoHaveMoreUndeliveredOrdersThanDelivered() {
        assertEquals(setOf(customers[reka]), shop.getCustomersWithMoreUndeliveredOrdersThanDelivered())
    }
}
