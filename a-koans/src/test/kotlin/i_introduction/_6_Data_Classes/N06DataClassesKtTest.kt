package i_introduction._6_Data_Classes

import org.junit.Assert.assertEquals
import org.junit.Test


class N06DataClassesKtTest {
    @Test fun testListOfPeople() {
        assertEquals("[Person(name=Alice, age=29), Person(name=Bob, age=31)]", task6().toString())
    }
}