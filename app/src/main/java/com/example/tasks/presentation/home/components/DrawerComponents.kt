package com.example.tasks.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DrawerComponents(
    name: String,
    profileUrl: String,
    scope: CoroutineScope,
    drawerState: DrawerState,
    onItemClicked: () -> Unit
) {
    val painter = rememberImagePainter(data = profileUrl)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)
    ) {
        IconButton(
            onClick = {
                scope.launch {
                    drawerState.close()
                }
            },
            modifier = Modifier
                .align(alignment = Alignment.End)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colors.onBackground.copy(alpha = .3f),
                    CircleShape
                )

        ) {
            Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "Back Arrow")
        }
        Image(
            painter = painter,
            contentDescription = "Profile Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .fillMaxWidth(.35f)
                .aspectRatio(1f)
                .clickable { onItemClicked() }

        )

        Text(
            text = name,
            modifier = Modifier
                .fillMaxWidth(.8f)
                .padding(vertical = 16.dp),
            style = MaterialTheme.typography.h3,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

    }
}

@Preview(showBackground = true)
@Composable
fun DrawerComponentsPreview() {
    DrawerComponents(
        "Olivia Mitchell", "",
        scope = rememberCoroutineScope(), drawerState = rememberDrawerState(
            DrawerValue.Open
        )
    ){}
}