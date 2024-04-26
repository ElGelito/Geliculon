package com.example.geliculon

sealed class WatchListType {
    object MovieType : WatchListType()
    object TvShowType : WatchListType()
}

