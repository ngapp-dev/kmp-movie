package ui.home

import data.model.MovieItem
import data.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import utils.network.DataState

class NowPlayingViewModel : ViewModel() {
    private val viewModelScope = CoroutineScope(Dispatchers.Main)
    private val repo = MovieRepository()
    val nowPlayingResponse = MutableStateFlow<DataState<List<MovieItem>>?>(DataState.Loading)

    fun nowPlayingView(page: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            repo.nowPlayingMovie(page).collectLatest {
                nowPlayingResponse.value = it
            }
        }
    }
}