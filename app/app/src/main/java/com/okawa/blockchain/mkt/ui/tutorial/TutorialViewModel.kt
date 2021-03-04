package com.okawa.blockchain.mkt.ui.tutorial

import androidx.annotation.ColorRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.okawa.blockchain.mkt.R
import com.okawa.blockchain.mkt.ui.tutorial.model.TutorialPage
import com.okawa.blockchain.mkt.utils.SingleLiveEvent

class TutorialViewModel : ViewModel() {

    data class ViewState(
        @ColorRes val colorRes: Int,
        val isLastPage: Boolean
    )

    private val _navigation = SingleLiveEvent<Unit>()
    val navigation: LiveData<Unit> = _navigation

    private val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = _viewState

    fun onPageSelected(position: Int) {
        @ColorRes val color = when (position) {
            TutorialPage.PAGE_STATS -> R.color.tutorial_page_stats_color
            TutorialPage.PAGE_CHARTS -> R.color.tutorial_page_charts_color
            TutorialPage.PAGE_POOLS -> R.color.tutorial_page_pools_color
            TutorialPage.PAGE_FINAL -> R.color.tutorial_page_final_color
            else -> throw IllegalStateException()
        }

        _viewState.postValue(ViewState(color, position == TutorialPage.PAGE_FINAL))
    }

    fun onCloseTapped() {
        _navigation.call()
    }
}