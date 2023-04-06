package com.example.presentation.ui.characters

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.data.models.CharacterDomain
import com.example.presentation.navigation.Routes
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CharactersScreenContent(
    viewModel: CharactersViewModel = viewModel(), navController: NavController
) {
    val characters by viewModel.characters.collectAsState(emptyList())
    val uiState by viewModel.uiState.collectAsState()
    var isSearching by remember { mutableStateOf(false) }
    val query by viewModel.query.collectAsState()


    Scaffold(topBar = {
        Surface(
            color = (MaterialTheme.colors.surface), elevation = 2.dp
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Voldemort",
                        color = (MaterialTheme.colors.onSurface),
                        fontSize = MaterialTheme.typography.h4.fontSize
                    )
                    AnimatedVisibility(visible = isSearching.not()) {
                        IconButton(onClick = { isSearching = isSearching.not() }) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = "search")
                        }
                    }

                }

                AnimatedVisibility(visible = isSearching) {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = query,
                        onValueChange = { viewModel.updateQuery(it) },
                        trailingIcon = {

                            if (query.isBlank()) IconButton(onClick = {
                                isSearching = isSearching.not()
                                viewModel.updateQuery("")
                            }) {
                                Icon(
                                    imageVector = Icons.Rounded.Close,
                                    contentDescription = "close search"
                                )
                            }
                            else IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Rounded.Search,
                                    contentDescription = "search"
                                )
                            }
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor = MaterialTheme.colors.onSurface,
                            trailingIconColor = MaterialTheme.colors.onSurface
                        )
                    )
                }
            }
        }

    }) {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            if (uiState.isLoading) Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
            else CharactersList(isSearching = isSearching, list = characters) {
                navController.navigate(Routes.Details.route.plus("?id=").plus(it.id))
            }
        }
    }

}

@Composable
private fun Count(index: Int, size: Int) {
    Row(modifier = Modifier.height(IntrinsicSize.Min)) {
        Text(
            modifier = Modifier.padding(end = 8.dp),
            text = index.toString(),
            fontSize = MaterialTheme.typography.h3.fontSize,
            color = MaterialTheme.colors.primary
        )
        Divider(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp),
            color = MaterialTheme.colors.onBackground
        )
        Text(text = size.toString(), modifier = Modifier.padding(start = 8.dp))
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CharactersList(
    isSearching: Boolean, list: List<CharacterDomain>, onItemClicked: (CharacterDomain) -> Unit
) {

    var page by remember { mutableStateOf(0) }
    val pagerState = rememberPagerState()
    val coroutine = rememberCoroutineScope()

    if (isSearching) LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items = list) {
            CharacterItem(isPager = false, character = it, onItemClicked = onItemClicked)
        }
    }
    else Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            count = list.size,
            modifier = Modifier.weight(1f),
            state = pagerState,
        ) { index ->
            page = index
            Card(modifier = Modifier.padding(16.dp)) {
                CharacterItem(
                    isPager = true,
                    character = list[index],
                    onItemClicked = onItemClicked
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = {
                if (page != 0) {
                    page -= 1
                    coroutine.launch { pagerState.scrollToPage(page) }
                }
            }) {
                Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "")
            }
            Count(index = page + 1, size = list.size + 1)
            IconButton(onClick = {
                if (page != list.lastIndex) {
                    page += 1
                    coroutine.launch { pagerState.scrollToPage(page) }
                }
            }) {
                Icon(imageVector = Icons.Rounded.ArrowForward, contentDescription = "")
            }
        }
    }


}

@Composable
private fun CharacterImage(modifier: Modifier, link: String) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current).data(link).crossfade(true).build(),
        loading = { CircularProgressIndicator() },
        modifier = modifier,
        contentDescription = "character image",
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun CharacterItem(
    isPager: Boolean,
    character: CharacterDomain,
    onItemClicked: (CharacterDomain) -> Unit
) {

    val colorStops = arrayOf(
        0.6f to Color.Transparent, 1f to Color.Black.copy(alpha = 0.7f)
    )

    val modifier = if (isPager) Modifier
        .fillMaxWidth()
        .fillMaxHeight()
    else
        Modifier
            .fillMaxWidth()
            .height(200.dp)

    Box(
        modifier = modifier.clickable { onItemClicked.invoke(character) }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(colorStops = colorStops)
                )
        ) {
            CharacterImage(modifier = Modifier.fillMaxWidth(), link = character.image)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .align(Alignment.BottomStart)
        ) {
            Text(
                text = character.name,
                fontWeight = FontWeight.Bold,
                fontSize = TextUnit(24f, TextUnitType.Sp),
                color = Color.White
            )
            Text(
                text = character.house,
                color = Color.White
            )

        }
    }

}

@Preview
@Composable
private fun CharacterScreenPreview() {
    CharactersScreenContent(navController = NavController(LocalContext.current))
}