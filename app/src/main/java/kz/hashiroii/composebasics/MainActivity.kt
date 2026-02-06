package kz.hashiroii.composebasics

import android.os.Bundle
import android.util.MutableBoolean
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.hashiroii.composebasics.data.Note
import kz.hashiroii.composebasics.data.NotesRepository
import kz.hashiroii.composebasics.ui.theme.ComposeBasicsTheme
import kotlin.collections.mutableListOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeBasicsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NotesApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun NotesApp(modifier: Modifier = Modifier) {

    val showOnBoarding = remember { mutableStateOf(true) }
    val notes = remember { mutableStateListOf<Note>().apply {
        addAll(NotesRepository().getNotes())
    } }

    if (showOnBoarding.value) {
        NotesOnBoarding(
            onContinueClicked = { showOnBoarding.value = false }
        )
    } else {
        NotesList(notes = notes)
    }
}

@Composable
fun NotesOnBoarding(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Text("Welcome to this app!")
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = onContinueClicked
        ) {
            Text("Continue")
        }
    }
}

@Composable
fun NotesList(
    modifier: Modifier = Modifier,
    notes: List<Note>
) {
    LazyColumn(
        contentPadding = PaddingValues(8.dp)
    ) {
        items(notes) { note ->
            NoteItem(note = note)
        }
    }
}

@Composable
fun NoteItem(
    modifier: Modifier = Modifier,
    note: Note
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = note.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AppPreview() {
    MaterialTheme {
        NotesApp()
    }
}