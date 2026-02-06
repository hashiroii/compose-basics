package kz.hashiroii.composebasics.data

class NotesRepository {
    fun getNotes(): List<Note> {
        return List(20) { index ->
            Note(
                title = "Note ${index + 1}",
                description = "This is the description for note number ${index + 1}. it contains some sample text to fill the UI space for testing purposes."
            )
        }
    }
}