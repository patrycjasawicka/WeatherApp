package com.example.weatherapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.weatherapp.R
import com.example.weatherapp.domain.RegexUtils
import com.example.weatherapp.domain.model.Hint
import com.example.weatherapp.navigation.NavigationItem
import com.example.weatherapp.ui.theme.Colors
import com.example.weatherapp.ui.theme.Dimens
import com.example.weatherapp.ui.theme.FontSizes
import kotlinx.coroutines.delay

@Composable
fun MainScreen(navController: NavHostController, weatherViewModel: WeatherViewModel) {
    var searchQuery by remember { mutableStateOf("") }
    var debouncedQuery by remember { mutableStateOf("") }
    val hints by weatherViewModel.hints.collectAsState()
    val locationIsSelected = weatherViewModel.locationIsSelected.value

    LaunchedEffect(searchQuery) {
        delay(500)
        debouncedQuery = searchQuery
    }

    LaunchedEffect(debouncedQuery) {
        weatherViewModel.fetchLocations(debouncedQuery)
    }

    MainScreenContent(
        searchQuery = searchQuery,
        onQueryEntered = { newQuery ->
            if (newQuery.matches(RegexUtils.polishCharsRegex)) {
                searchQuery = newQuery
            }
        },
        onHintSelected = { hint ->
            weatherViewModel.onHintSelected(hint)
            searchQuery = hint.localizedName
        },
        items = hints,
        locationIsSelected = locationIsSelected,
        onButtonClick = {
            weatherViewModel.fetchDetails()
            navController.navigate(NavigationItem.Details.getRoute(searchQuery))
        }
    )
}

@Composable
fun MainScreenContent(
    searchQuery: String,
    onQueryEntered: (String) -> Unit,
    onHintSelected: (Hint) -> Unit,
    items: List<Hint>,
    locationIsSelected: Boolean,
    onButtonClick: () -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Colors.NavyBlue,
                        Colors.LightBlue
                    ),
                )
            )
            .padding(Dimens.Medium),
        contentAlignment = Alignment.Center
    ) {
        val screenHeight = maxHeight

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.woman_with_umbrella),
                contentDescription = stringResource(R.string.weather_icon),
                tint = Color.Unspecified,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(screenHeight / 3)
            )

            Spacer(modifier = Modifier.height(Dimens.Large))

            Text(
                text = stringResource(R.string.weather),
                fontSize = FontSizes.Large,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(Dimens.Small))

            Text(
                text = stringResource(R.string.forecasts),
                fontSize = FontSizes.Medium,
                color = Color.White,
                fontFamily = FontFamily.SansSerif
            )

            Spacer(modifier = Modifier.height(Dimens.ExtraLarge))

            DropdownTextField(searchQuery, onQueryEntered, onHintSelected, items)

            Spacer(modifier = Modifier.height(Dimens.Medium))

            Button(
                onClick = onButtonClick,
                enabled = locationIsSelected,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.Medium),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Colors.Yellow,
                    contentColor = Color.White,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.LightGray
                )
            ) {
                Text(text = stringResource(R.string.check_weather))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownTextField(
    searchQuery: String,
    onQueryEntered: (String) -> Unit,
    onHintSelected: (Hint) -> Unit,
    items: List<Hint>
) {
    Column(modifier = Modifier.padding(horizontal = Dimens.Medium)) {

        OutlinedTextField(
            value = searchQuery,
            onValueChange = onQueryEntered,
            placeholder = { Text(stringResource(R.string.city)) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                containerColor = Colors.LightBlue
            )
        )

        LazyColumn(modifier = Modifier.fillMaxHeight(0.3f)) {
            items(items) { item ->
                Text(
                    text = item.localizedName, modifier = Modifier
                        .padding(8.dp)
                        .clickable { onHintSelected(item) },
                    color = if (item.isHistorical) Color.DarkGray else Color.Unspecified
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreenContent(searchQuery = "Warsaw", {}, {}, emptyList(), true, {})
}