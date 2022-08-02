package com.nirwashh.android.viewmodeltest.viewmodel

import com.nirwashh.android.viewmodeltest.data.DataSource
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class ModelTest {

    private class TestCallback : TextCallback {
        var text = ""
        override fun updateText(str: String) {
            text = str
        }
    }

    private class TestDataSource : DataSource {
        private var int: Int = Int.MIN_VALUE
        override fun saveInt(key: String, value: Int) {
            int = value
        }

        override fun getInt(key: String) = int
    }

    @Test
    fun test_start_with_saved_value() {
        val testDataSource = TestDataSource()
        val model = Model(testDataSource)
        val callback = TestCallback()
        testDataSource.saveInt("", 5)
        model.start(callback)
        Thread.sleep(5)
        val actual = callback.text
        val expected = "5"
        assertEquals(expected, actual)
    }

    @Test
    fun test_stop_after_2_seconds() {
        val testDataSource = TestDataSource()
        val model = Model(testDataSource)
        val callback = TestCallback()
        testDataSource.saveInt("", 0)
        model.start(callback)
        Thread.sleep(2010)
        val actual = callback.text
        val expected = "2"
        assertEquals(expected, actual)
        model.stop()
        val savedCountActual = testDataSource.getInt("")
        val savedCountExpected = 2
        assertEquals(savedCountExpected,savedCountActual)
    }
}


