package com.justinjose.wifigames

import com.justinjose.wifigames.others.AppUtils
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        for (index in 0 until 12) {
            println("----------------")
            AppUtils.getIndexListViaListSize(index)
        }
    }
}