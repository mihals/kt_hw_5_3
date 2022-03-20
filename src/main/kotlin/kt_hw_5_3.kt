package ru.netology

data class Post(
    val id: Long = 0L,
    val ownerId: Long = -1L,
    val fromId: Long = -1L,
    val createdBy: Long = -1L,
    val date: Long = -1L,
    val text: String = "",
    val replyOwnerId: Long = -1L,
    val replyPostId: Long = -1L,
    val friendsOnly: Boolean = false,
    val comments: Boolean = false,
    val copyright: String = "",
    val likes: Long = -1L,
    val reposts: Long = -1L,
    val views: Long = -1L,
    val postType: String = "",
    val signerId: Long = -1L,
    val canPin: Boolean = false,
    val canDelete: Boolean = false,
    val canEdit: Boolean = false,
    val isPinned: Boolean = false,
    val markedAsAdds: Boolean = false,
    val isFavorite: Boolean = false,
    val postponedId: Long = -1L
)

class WallService {
    private var posts = emptyArray<Post>()
    private var id = 0L

    fun add(post: Post): Post {
        id++
        val myPost = post.copy(id = this.id)
        posts += myPost
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((index, value) in posts.withIndex()) {
            if (value.id == post.id) {
                posts[index] = post.copy()
                return true
            }
        }
        return false
    }
}

fun main() {

}