package ru.netology


interface Attachment {
    val type: String
}

data class Audio(
    val id: Int,
    val ownerId: Int,
    val artist: String,
    val title: String,
    val duration: Int,
    val url: String,
    val lyricsId: Int,
    val albumId: Int,
    val genreId: Int,
    val date: Int,
    val noSearch: Int,
    val lsHq: Int
)

data class AudioAttachment(
    override val type: String = "audio",
    val id: Int,
    val ownerId: Int,
    val artist: String,
    val title: String,
    val duration: Int,
    val url: String,
    val lyricsId: Int,
    val albumId: Int,
    val genreId: Int,
    val date: Int,
    val noSearch: Int,
    val lsHq: Int,
    val audioAttachment: Audio = Audio(
        id,
        ownerId,
        artist,
        title,
        duration,
        url,
        lyricsId,
        albumId,
        genreId,
        date,
        noSearch,
        lsHq
    )
) : Attachment

data class App(
    val id: Int,
    val name: String,
    val photo130: String,
    val photo604: String
)

data class AppAttachment(
    override val type: String = "app",
    val id: Int,
    val name: String,
    val photo130: String,
    val photo604: String,
    val appAttachment: App = App(id, name, photo130, photo604)
) : Attachment

data class Graffiti(
    val id: Int,
    val ownerId: Int,
    val photo130: String,
    val photo604: String
)

data class GraffitiAttachment(
    override val type: String = "graffiti",
    val id: Int,
    val ownerId: Int,
    val photo130: String,
    val photo604: String,
    val graffitiAttachment: Graffiti = Graffiti(id, ownerId, photo130, photo604)
) : Attachment

data class Note(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val text: String,
    val date: Int,
    val comments: Int,
    val readComments: Int,
    val viewUrl: String
)

data class NoteAttachment(
    override val type: String = "note",
    val id: Int,
    val ownerId: Int,
    val title: String,
    val text: String,
    val date: Int,
    val comments: Int,
    val readComments: Int,
    val viewUrl: String,
    val attachedNote: Note = Note(id, ownerId, title, text, date, comments, readComments, viewUrl)
) : Attachment

data class PostedPhoto(
    val id: Int,
    val ownerId: Int,
    val photo130: String,
    val photo604: String
)

data class PostedPhotoAttachment(
    override val type: String = "posted_photo",
    val id: Int,
    val ownerId: Int,
    val photo130: String,
    val photo604: String,
    val attachedPostedPhoto: PostedPhoto = PostedPhoto(
        id, ownerId, photo130, photo604
    )
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

class PostNotFoundException(message:String) : RuntimeException(message)

class WallService {
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

}