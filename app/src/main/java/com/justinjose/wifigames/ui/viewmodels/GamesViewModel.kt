package com.justinjose.wifigames.ui.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justinjose.wifigames.data.gamezop_sub_models.Game
import com.justinjose.wifigames.others.InternetStatus
import com.justinjose.wifigames.others.InternetStatusListener
import com.justinjose.wifigames.others.NetworkObserver
import com.justinjose.wifigames.repositories.GamesRepo
import com.justinjose.wifigames.widgets.core.AppUpdateState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GamesViewModel : ViewModel() {

    companion object {
        const val MAX_ITEMS_PER_PAGE = 18
    }


    private val _internetObserver = MutableStateFlow<InternetStatus>(InternetStatus.Invalid)
    val internetObserver = _internetObserver.asStateFlow()

    fun registerNetworkObserver(current: Context) {
        val networkObserver = NetworkObserver.getInstance(current)
        networkObserver.registerListener(object : InternetStatusListener {
            override fun available() {
                _internetObserver.value = InternetStatus.Available
            }

            override fun unavailable() {
                _internetObserver.value = InternetStatus.Unavailable
            }
        })
    }

    private val mGameList = mutableListOf<Game>()

    private val _gameListResponse =
        MutableStateFlow<GamesViewModelAction>(GamesViewModelAction.Loading)
    val gameListResponse = _gameListResponse.asStateFlow()

    private var mPageNo = 1

    fun fetchGames() {
        viewModelScope.launch {
            if (mGameList.isNotEmpty()) {
                return@launch
            }
            mPageNo = 1
            val response = GamesRepo.getGames()
            if (response == null) {
                _gameListResponse.value = GamesViewModelAction.Empty
                return@launch
            }
            mGameList.clear()
            mGameList.addAll(response.games)
            mGameList.shuffle()
            populateNextList()
        }
    }

    fun populateNextList() {
        val itemTypeList = mutableListOf<ItemType>()
        val gamePack = mutableListOf<Game>()
        val gamePackCount = 3
        val gameGroupCount = 9
        val lastItemIndex = (mPageNo++ * MAX_ITEMS_PER_PAGE)
        mGameList.forEachIndexed { index, game ->
            gamePack.add(game)
            if ((index + 1) % gamePackCount == 0) {
                val item = gamePack.toList()
                itemTypeList.add(ItemType.GameItem(item))
                gamePack.clear()
            }
            if (index + 1 == mGameList.size) {
                itemTypeList.add(ItemType.Finish)
                _gameListResponse.value = GamesViewModelAction.GameList(itemTypeList)
                return
            }
            if (index + 1 == lastItemIndex) {   //is last item so add a 3-cell get-more-games btn
                itemTypeList.add(ItemType.UnlockMoreGamesItem)
                _gameListResponse.value = GamesViewModelAction.GameList(itemTypeList)
                return
            }
        }
    }

    var isDarkTheme = MutableStateFlow<Boolean>(false)
    fun updateTheme() {
        isDarkTheme.value = !isDarkTheme.value
    }

    private val _appUpdateStateFlow =
        MutableStateFlow<AppUpdateState>(AppUpdateState.Loading)
    val appUpdateStateFlow = _appUpdateStateFlow.asStateFlow()

    fun getUpdateResponse(appUpdateState: AppUpdateState) {
        _appUpdateStateFlow.value = appUpdateState
    }

    sealed class GamesViewModelAction {
        object Loading : GamesViewModelAction()
        data class GameList(val itemType: List<ItemType>) : GamesViewModelAction()

        object Empty : GamesViewModelAction()
    }

    sealed class ItemType {
        data class GameItem(val gameList: List<Game>) : ItemType()
        object UnlockMoreGamesItem : ItemType()
        object Finish : ItemType()
    }
}
