package com.example.presentation.ui.details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.data.models.CharacterDomain

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailsScreen(
    viewModel: CharacterViewModel = viewModel(), navController: NavController
) {

    val details by viewModel.character.collectAsState()
    val scrollBehavior: TopAppBarScrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            DetailAppBar(details = details, scrollBehavior = scrollBehavior) {
                navController.popBackStack()
            }
        }) {

        Details(
            modifier = Modifier.padding(it),
            details = details
        )

    }
}

@Composable
fun Details(modifier: Modifier = Modifier, details: CharacterDomain?) {
    val list = listOf(
        Pair(
            Color(0xFF592E83), Triple("Name", details?.name ?: "Name", Icons.Rounded.Badge)
        ),
        Pair(
            Color(0xFFE56399),
            Triple("Species", details?.species ?: "Species", Icons.Rounded.Person)
        ),
        Pair(
            Color(0xFF6DC0D5),
            Triple("Gender", details?.gender ?: "Gender", Icons.Rounded.Transgender)
        ),
        Pair(
            Color(0xFFFFAD05),
            Triple("Ancestry", details?.ancestry ?: "Ancestry", Icons.Rounded.Explore)
        ),
        Pair(
            Color(0xFF48392A), Triple("House", details?.house ?: "House", Icons.Rounded.Navigation)
        ),
        Pair(
            Color(0xFFA1E887), Triple(
                "Status", if (details?.alive == true) "Alive" else "Dead", Icons.Rounded.Emergency
            )
        ),
        Pair(
            Color(0xFF592E83), Triple("Wand", details?.wand?.core ?: "Wand", Icons.Rounded.AutoFixNormal)
        ),
    )
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
    ) {
        items(items = list) { (color, item) ->
            val (title, value, icon) = item
            Row(
                modifier = Modifier.padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    modifier = Modifier.padding(8.dp),
                    backgroundColor = color,
                    border = BorderStroke(1.dp, Color.Black)
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "name",
                        modifier = Modifier.padding(8.dp),
                        tint = Color.White
                    )
                }
                Column(modifier = Modifier.padding(start = 16.dp)) {
                    Text(
                        text = title,
                        fontSize = TextUnit(18f, TextUnitType.Sp),
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.alpha(0.3f)
                    )
                    Text(
                        text = value.replaceFirstChar { it.uppercase() },
                        fontSize = TextUnit(24f, TextUnitType.Sp),
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun DetailsPreview() {
    CharacterDetailsScreen(navController = NavController(LocalContext.current))
}

