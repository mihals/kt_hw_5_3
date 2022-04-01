package ru.netology


interface Attachment {
    val type: String
}

data class Audio(
    val id: Int? = null,
    val ownerId: Int? = null,
    val artist: String? = null,
    val title: String? = null,
    val duration: Int? = null,
    val url: String? = null,
    val lyricsId: Int? = null,
    val albumId: Int? = null,
    val genreId: Int? = null,
    val date: Int? = null,
    val noSearch: Int? = null,
    val lsHq: Int? = null
)

data class AudioAttachment(
    override val type: String = "audio",
    val audioAttachment: Audio = Audio()
) : Attachment

data class App(
    val id: Int? = null,
    val name: String? = null,
    val photo130: String? = null,
    val photo604: String? = null
)

data class AppAttachment(
    override val type: String = "app",
    val appAttachment: App = App()
) : Attachment

data class Graffiti(
    val id: Int? = null,
    val ownerId: Int? = null,
    val photo130: String? = null,
    val photo604: String? = null
)

data class GraffitiAttachment(
    override val type: String = "graffiti",
    val graffitiAttachment: Graffiti = Graffiti()
) : Attachment

data class Note(
    val id: Int? = null,
    val ownerId: Int? = null,
    val title: String? = null,
    val text: String? = null,
    val date: Int? = null,
    val comments: Int? = null,
    val readComments: Int? = null,
    val viewUrl: String? = null
)

data class NoteAttachment(
    override val type: String = "note",
    val attachedNote: Note = Note()
) : Attachment

data class PostedPhoto(
    val id: Int? = null,
    val ownerId: Int? = null,
    val photo130: String? = null,
    val photo604: String? = null
)

data class PostedPhotoAttachment(
    override val type: String = "posted_photo",
    val attachedPostedPhoto: PostedPhoto = PostedPhoto()
) : Attachment

data class Comment(
    val id: Long? = null, val postId: Long, val fromId: Int? = null, val date: Int? = null, val text: String,
    val donat: Any? = null, val replyToUser: Int? = 0, val replyToComment: Int? = 0,
    val attachment: Any? = null, val parentsStack: Array<Int>? = null, val thread: Any? = null
)

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
    val comments: Any? = null,
    val copyright: String = "",
    val likes: Long = -1L,
    val reposts: Any? = null,
    val views: Any? = null,
    val postType: String = "",
    val postSource: Any? = null,
    val geo: Any? = null,
    val signerId: Long = -1L,
    val copyHistory: Any? = null,
    val canPin: Boolean = false,
    val canDelete: Boolean = false,
    val canEdit: Boolean = false,
    val isPinned: Boolean = false,
    val markedAsAdds: Boolean = false,
    val isFavorite: Boolean = false,
    val postponedId: Long = -1L,
    val attachments: Array<Attachment> = emptyArray<Attachment>()
)


object WallService {
    private var posts = emptyArray<Post>()
    private var id = 0L

    private var comments = emptyArray<Comment>()
    private var commentId = 0L

    fun createComment(comment: Comment) {
        for (value in posts) {
            if (value.id == comment.postId) {
                commentId++
                val newComment = comment.copy(id = commentId)
                comments += newComment
                return
            }
        }

        class PostNotFoundException(message:String) : RuntimeException(message)
        throw PostNotFoundException(message = "postId ${comment.postId} not found")
    }

    fun add(post: Post): Post {
        id++
        val myPost = post.copy(id = this.id, ownerId = post.ownerId, date = post.date)
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
    WallService.add(Post())
    WallService.add(Post())
    WallService.add(Post())

    WallService.createComment(Comment(postId=2, text = "Two"))
    WallService.createComment((Comment(postId = 20, text = "Twenty")))
}