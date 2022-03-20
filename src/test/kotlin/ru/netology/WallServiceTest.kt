package ru.netology

import org.junit.Test

import org.junit.Assert.*

class WallServiceTest {

    @Test
    fun add_post() {
        val service = WallService()
        var result = service.add(Post())
        assertNotEquals(result.id, 0)

        result = service.add(Post())
        assertNotEquals(result.id, 0)

        result = service.add(Post())
        assertNotEquals(result.id, 0)
    }

    @Test
    fun update() {
        val service = WallService()

        service.add(Post())
        service.add(Post())
        service.add(Post())

        val update = Post(id = 2)

        val result = service.update(update)

        assertTrue(result)
    }
}