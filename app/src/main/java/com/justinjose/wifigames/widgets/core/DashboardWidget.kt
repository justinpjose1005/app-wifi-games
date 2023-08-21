package com.justinjose.wifigames.widgets.core

import android.app.Activity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.justinjose.wifigames.FirebaseEvent
import com.justinjose.wifigames.data.gamezop_sub_models.Game
import com.justinjose.wifigames.others.InternetStatus
import com.justinjose.wifigames.ui.theme.ShowSystemsBars
import com.justinjose.wifigames.ui.viewmodels.GamesViewModel
import com.justinjose.wifigames.utilities.Utilities
import com.justinjose.wifigames.widgets.customviews.ImageView
import com.justinjose.wifigames.widgets.customviews.TypoBody1
import com.justinjose.wifigames.widgets.reuse.LoadingScreenProgressBar
import com.justinjose.wifigames.widgets.reuse.ToolBar
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun DashboardWidget(activity: Activity, gamesViewModel: GamesViewModel = viewModel()) {
    ShowSystemsBars(flag = true, barColor = MaterialTheme.colors.primary)
    when (gamesViewModel.appUpdateStateFlow.collectAsState().value) {
        AppUpdateState.Loading -> {
            LoaderWidget()
            CheckForAppUpdate(activity)
        }
        AppUpdateState.NotAvailable -> {
            RootUI()
        }
        AppUpdateState.Updating -> {
            LoaderWidget()
        }
    }
}

@Composable
fun RootUI(gamesViewModel: GamesViewModel = viewModel()) {
    when (gamesViewModel.internetObserver.collectAsState().value) {
        InternetStatus.Available -> {
            gamesViewModel.fetchGames()
            GamesScreen()   //RootUI
        }
        InternetStatus.Unavailable -> {
            NoInternetWidget()  //RootUI
        }
        InternetStatus.Invalid -> {
            LoaderWidget() //RootUI
            gamesViewModel.registerNetworkObserver(LocalContext.current)
        }
    }
}

@Composable
fun GamesScreen(gamesViewModel: GamesViewModel = viewModel()) {
    when (val gameListResponse = gamesViewModel.gameListResponse.collectAsState().value) {
        GamesViewModel.GamesViewModelAction.Empty -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.primary)
            ) {
                ToolBar()   //GamesScreen
                NoGamesWidget() //GamesScreen
            }
        }
        is GamesViewModel.GamesViewModelAction.GameList -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.primary)
            ) {
                ToolBar()   //GamesScreen
                GameListUI(gameListResponse.itemType)  //GamesScreen
            }
        }
        GamesViewModel.GamesViewModelAction.Loading -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.primary)
            ) {
                ToolBar()   //GamesScreen
                LoadingScreenProgressBar()  //GamesScreen
            }
        }
    }
}

@Composable
fun GameListUI(itemTypeList: List<GamesViewModel.ItemType>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(Alignment.Top),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        contentPadding = PaddingValues(18.dp, 0.dp, 18.dp, 5.dp),
    ) {
        items(itemTypeList) { itemType ->
            when (itemType) {
                is GamesViewModel.ItemType.GameItem -> {
                    GameItemUI(itemType.gameList)
                }
                GamesViewModel.ItemType.UnlockMoreGamesItem -> {
                    UnlockMoreGamesUI()
                }
                GamesViewModel.ItemType.Finish -> {
                    FinishUI()
                }
            }
        }
    }
}

@Composable
fun GameItemUI(game: List<Game>) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 8.dp)
            .background(MaterialTheme.colors.primary),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        game.forEach {
            CoilImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .aspectRatio(1f)
                    .weight(1f)
                    .clickable {
                        Utilities.openCustomTab(context, it.url)
                    },
                imageModel = { it.assets.square },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop, alignment = Alignment.Center
                ),
                loading = {
                    Box(modifier = Modifier.matchParentSize()) {
                        ImageView(
                            imgResId = com.justinjose.wifigames.R.drawable.image_placeholder,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(48.dp)
                        )
                    }
                },
                failure = {
                    Text(text = "image request failed.")
                },
            )
        }
    }
}

@Composable
fun UnlockMoreGamesUI(gamesViewModel: GamesViewModel = viewModel()) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(0.dp, 10.dp, 0.dp, 20.dp)
    ) {
        Button(
            shape = RoundedCornerShape(6.dp),
            border = BorderStroke(0.5.dp, color = MaterialTheme.colors.secondary),
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center),
            onClick = {
                FirebaseEvent.moreGamesOnClick()
                gamesViewModel.populateNextList()
            },
            contentPadding = PaddingValues(),
        ) {
            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .background(MaterialTheme.colors.primary)
                    .padding(10.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TypoBody1(
                    text = "Unlock more games",
                    color = MaterialTheme.colors.secondary
                )  //UnlockMoreGamesItem
                ImageView(  //UnlockMoreGamesItem
                    imgResId = com.justinjose.wifigames.R.drawable.game_stick,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}

@Composable
fun FinishUI() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .padding(0.dp, 10.dp, 0.dp, 20.dp)
    ) {
        ImageView(
            imgResId = com.justinjose.wifigames.R.drawable.finish,
            modifier = Modifier.size(72.dp)
        )
    }
}