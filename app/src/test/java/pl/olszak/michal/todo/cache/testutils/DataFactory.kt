package pl.olszak.michal.todo.cache.testutils

import java.util.*
import java.util.concurrent.ThreadLocalRandom

/**
 * @author molszak
 *         created on 26.01.2018.
 */
class DataFactory {

    companion object Factory {

        fun randomUuid(): String {
            return UUID.randomUUID().toString()
        }

        fun randomInt(): Int {
            return ThreadLocalRandom.current().nextInt(0, 1000 + 1)
        }

        fun randomLong(): Long {
            return randomInt().toLong()
        }

        fun randomBoolean(): Boolean {
            return Math.random() < 0.5
        }
    }
}