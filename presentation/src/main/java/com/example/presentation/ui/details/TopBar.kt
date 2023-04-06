package com.example.presentation.ui.details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.data.models.CharacterDomain


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailAppBar(
    details: CharacterDomain?, scrollBehavior: TopAppBarScrollBehavior, onBackPressed: () -> Unit
) {

    val isCollapsed = remember { derivedStateOf { scrollBehavior.state.collapsedFraction > 0.7 } }

    val colorStops = arrayOf(
        0.3f to Color.Transparent, 1f to Color.Black.copy(alpha = 0.4f)
    )

    if (details != null) Box(modifier = Modifier.background(Color.White)) {
        if (isCollapsed.value.not()) {
            Box {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current).data(details.image)
                        .crossfade(true).build(),
                    contentDescription = "",
                    modifier = Modifier.fillMaxWidth()
                )
                Column(
                    modifier = Modifier
                        .background(Brush.verticalGradient(colorStops = colorStops))
                        .fillMaxWidth()
                        .height(100.dp)
                        .align(Alignment.BottomCenter)
                ) {}

                Text(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp),
                    text = details.displayName,
                    color = Color.White,
                    fontSize = MaterialTheme.typography.headlineLarge.fontSize
                )

            }
        }
        LargeTopAppBar(title = {
            AnimatedVisibility(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 56.dp),
                visible = isCollapsed.value
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = details.name,
                    textAlign = TextAlign.Center,
                    fontSize = TextUnit(24f, TextUnitType.Sp)
                )
            }
        }, navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "navigate back",
                    tint = if (isCollapsed.value) Color.Black else Color.White
                )
            }
        }, scrollBehavior = scrollBehavior,
            colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
        )

    }
    else IconButton(
        onClick = onBackPressed, modifier = Modifier.padding(8.dp)
    ) {
        Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "")
    }
}