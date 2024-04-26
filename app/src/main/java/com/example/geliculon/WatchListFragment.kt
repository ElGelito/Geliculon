package com.example.geliculon

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

class WatchListFragment : Fragment() {
    private lateinit var watchList: RecyclerView
    private lateinit var watchListAdapter: WatchListAdapter
    private lateinit var filter: Spinner

    private val db: AppDatabase by lazy {
        Room.databaseBuilder(
            requireActivity().applicationContext,
            AppDatabase::class.java,
            "geliculonn.db"
        ).allowMainThreadQueries().build()
    }

    override fun onStart() {
        super.onStart()
        filter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> getWatchList()
                    1 -> getMovies()
                    2 -> getTvShows()
                }
            }
        }


}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_watch_list, container, false)
        watchList = view.findViewById(R.id.watchlist)

        filter = view.findViewById(R.id.watchlist_filter)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.watch_list_filter,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            filter.adapter = adapter
        }

        watchList = view.findViewById(R.id.watchlist)
        watchList.layoutManager = GridLayoutManager(context, 3)
        watchListAdapter = WatchListAdapter(listOf()) {
            when (it.type) {
                is WatchListType.MovieType -> showMovieDetails(it)
                is WatchListType.TvShowType -> showTvShowDetails(it)
            }
        }
        watchList.adapter = watchListAdapter

        getWatchList()

        return view
    }
    override fun onHiddenChanged(hidden: Boolean) {
        if (!hidden) {
            when (filter.selectedItemPosition) {
                0 -> getWatchList()
                1 -> getMovies()
                2 -> getTvShows()
            }
        }
    }
    override fun onResume() {
        super.onResume()
        getWatchList()
    }

    private fun showMovieDetails(item: WatchList) {
        val intent = Intent(activity, DetallesPelicula::class.java)
        intent.putExtra(MOVIE_ID, item.id)
        intent.putExtra(MOVIE_BACKDROP, item.backdropPath)
        intent.putExtra(MOVIE_POSTER, item.posterPath)
        intent.putExtra(MOVIE_TITLE, item.title)
        intent.putExtra(MOVIE_RATING, item.rating)
        intent.putExtra(MOVIE_RELEASE_DATE, item.releaseDate)
        intent.putExtra(MOVIE_OVERVIEW, item.overview)
        intent.putExtra(MOVIE_LANGUAGE,item.originallanguage)
        startActivity(intent)
    }

    private fun showTvShowDetails(item: WatchList) {
        val intent = Intent(activity, DetallesTvShow::class.java)
        intent.putExtra(TV_SHOW_ID, item.id)
        intent.putExtra(TV_SHOW_BACKDROP, item.backdropPath)
        intent.putExtra(TV_SHOW_POSTER, item.posterPath)
        intent.putExtra(TV_SHOW_TITLE, item.title)
        intent.putExtra(TV_SHOW_RATING, item.rating)
        intent.putExtra(TV_SHOW_RELEASE_DATE, item.releaseDate)
        intent.putExtra(TV_SHOW_OVERVIEW, item.overview)
        intent.putExtra(TV_SHOW_ORIGINALLANGUAGE,item.originallanguage)
        startActivity(intent)
    }

    private fun getMovies() {
        val movies = db.movieDao().getAll()
        val watchlist = movies.map { movie ->
            WatchList(
                movie.id,
                movie.title,
                movie.overview,
                movie.posterPath,
                movie.backdropPath,
                movie.rating,
                movie.releaseDate,
                WatchListType.MovieType,
                movie.originallanguage
            )
        }
        watchListAdapter.updateItems(watchlist)
    }

    private fun getTvShows() {
        val tvShows = db.tvShowDao().getAll()
        val watchlist = tvShows.map { tvShow ->
            WatchList(
                tvShow.id,
                tvShow.name,
                tvShow.overview,
                tvShow.posterPath,
                tvShow.backdropPath,
                tvShow.rating,
                tvShow.firstAirDate,
                WatchListType.TvShowType,
                tvShow.originallanguage
            )
        }
        watchListAdapter.updateItems(watchlist)
    }


    private fun getWatchList() {
        val movies = db.movieDao().getAll()
        val tvShows = db.tvShowDao().getAll()
        val watchList = mutableListOf<WatchList>()
        watchList.addAll(
            movies.map { movie ->
                WatchList(
                    movie.id,
                    movie.title,
                    movie.overview,
                    movie.posterPath,
                    movie.backdropPath,
                    movie.rating,
                    movie.releaseDate,
                    WatchListType.MovieType,
                    movie.originallanguage
                )

            }
        )
        watchList.addAll(
            tvShows.map { tvShow ->
                WatchList(
                    tvShow.id,
                    tvShow.name,
                    tvShow.overview,
                    tvShow.posterPath,
                    tvShow.backdropPath,
                    tvShow.rating,
                    tvShow.firstAirDate,
                    WatchListType.TvShowType,
                    tvShow.originallanguage
                )
            }
        )
        watchListAdapter.updateItems(watchList)
    }
}
