package com.example.geliculon

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PeliculasFragment : Fragment() {
    /*PELICULAS POPULARES*/
    private lateinit var popularMovies: RecyclerView
    private lateinit var popularMoviesAdapter: PeliculasAdapter
    private lateinit var popularMoviesLayoutMgr: LinearLayoutManager
    private var popularMoviesPage = 1
    /*PELICULAS POPULARES*/

    /*PELICULAS PROXIMAS A ESTRENAR*/
    private lateinit var upcomingMovies: RecyclerView
    private lateinit var upcomingMoviesAdapter: PeliculasAdapter
    private lateinit var upcomingMoviesLayoutMgr: LinearLayoutManager
    private var upcomingMoviesPage = 1
    /*PELICULAS PROXIMAS A ESTRENAR*/


    /*PELICULAS MEJOR VALORADAS*/
    private lateinit var topRatedMovies: RecyclerView
    private lateinit var topRatedMoviesAdapter: PeliculasAdapter
    private lateinit var topRatedMoviesLayoutMgr: LinearLayoutManager
    private var topRatedMoviesPage = 1
    /*PELICULAS MEJOR VALORADAS*/


    /*Peliculas en cartelera*/

    private lateinit var nowplaying: RecyclerView
    private lateinit var nowplayingAdapter: PeliculasAdapter
    private lateinit var nowplayingLayoutMgr: LinearLayoutManager
    private var nowplayingsPage = 1

    /*Peliculas en cartelera*/


    /*Peliculas de Comedia*/
    private lateinit var comedyMovies: RecyclerView
    private lateinit var comedyMoviesAdapter: PeliculasAdapter
    private lateinit var comedyMoviesLayoutMgr: LinearLayoutManager
    private var comedyMoviesPage = 1
    /*Peliculas de Comedia*/


    /*Peliculas de accion*/
    private lateinit var actionMovies: RecyclerView
    private lateinit var actionMoviesAdapter: PeliculasAdapter
    private lateinit var actionMoviesLayoutMgr: LinearLayoutManager
    private var actionMoviesPage = 1
    /*Peliculas de accion*/


    /*Peliculas de drama*/
    private lateinit var dramaMovies: RecyclerView
    private lateinit var dramaMoviesAdapter: PeliculasAdapter
    private lateinit var dramaMoviesLayoutMgr: LinearLayoutManager
    private var dramaMoviesPage = 1
    /*Peliculas de drama*/


    /*Peliculas de Terror*/
    private lateinit var horrorMovies: RecyclerView
    private lateinit var horrorMoviesAdapter: PeliculasAdapter
    private lateinit var horrorMoviesLayoutMgr: LinearLayoutManager
    private var horrorMoviesPage = 1

    /*Peliculas de Terror*/


    /*Peliculas Españolas*/
    private lateinit var spanishMovies: RecyclerView
    private lateinit var spanishMoviesAdapter: PeliculasAdapter
    private lateinit var spanishMoviesLayoutMgr: LinearLayoutManager
    private var spanishMoviesPage = 1
    /*Peliculas Españolas*/


    /*Peliculas ANIMADAS*/
    private lateinit var animatedMovies: RecyclerView
    private lateinit var animatedMoviesAdapter: PeliculasAdapter
    private lateinit var animatedMoviesLayoutMgr: LinearLayoutManager
    private var animatedMoviesPage = 1

    /*Peliculas ANIMADAS*/


    /*Peliculas Familiares*/
    private lateinit var familyMovies: RecyclerView
    private lateinit var familyMoviesAdapter: PeliculasAdapter
    private lateinit var familyMoviesLayoutMgr: LinearLayoutManager
    private var familyMoviesPage = 1

    /*Peliculas Familiares*/


    /*Peliculas de ciencia Ficcion*/
    private lateinit var scifiMovies: RecyclerView
    private lateinit var scifiMoviesAdapter: PeliculasAdapter
    private lateinit var scifiMoviesLayoutMgr: LinearLayoutManager
    private var scifiMoviesPage = 1
    /*Peliculas de ciencia Ficcion*/


    /*Peliculas de Fantasia*/
    private lateinit var fantasyMovies: RecyclerView
    private lateinit var fantasyMoviesAdapter: PeliculasAdapter
    private lateinit var fantasyMoviesLayoutMgr: LinearLayoutManager
    private var fantasyMoviesPage = 1
    /*Peliculas de fantasia*/


    /*Documentales*/
    private lateinit var documentaryMovies: RecyclerView
    private lateinit var documentaryMoviesAdapter: PeliculasAdapter
    private lateinit var documentaryMoviesLayoutMgr: LinearLayoutManager
    private var documentaryMoviesPage = 1
    /*Documentales*/



    /*Western*/
    private lateinit var westernMovies: RecyclerView
    private lateinit var westernMoviesAdapter: PeliculasAdapter
    private lateinit var westernMoviesLayoutMgr: LinearLayoutManager
    private var westernMoviesPage = 1

    /*Western*/

    /*War Movies*/
    private lateinit var warMovies: RecyclerView
    private lateinit var warMoviesAdapter: PeliculasAdapter
    private lateinit var warMoviesLayoutMgr: LinearLayoutManager
    private var warMoviesPage = 1
    /* Peliculas belicas*/



    /*sports Movies*/
    private lateinit var sportsMovies: RecyclerView
    private lateinit var sportsMoviesAdapter: PeliculasAdapter
    private lateinit var sportsMoviesLayoutMgr: LinearLayoutManager
    private var sportsMoviesPage = 1
    /* Peliculas sports*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_peliculas, container, false)
        popularMovies=view.findViewById(R.id.popular_movies)
        popularMoviesLayoutMgr= LinearLayoutManager(
            context,LinearLayoutManager.HORIZONTAL,false
        )
        popularMovies.layoutManager=popularMoviesLayoutMgr
        popularMoviesAdapter= PeliculasAdapter(mutableListOf()){ movie -> mostrarDetallesPelicula(movie) }
        popularMovies.adapter=popularMoviesAdapter





        upcomingMovies = view.findViewById(R.id.upcoming_movies)
        upcomingMoviesLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        upcomingMovies.layoutManager = upcomingMoviesLayoutMgr
        upcomingMoviesAdapter = PeliculasAdapter(mutableListOf()){ movie -> mostrarDetallesPelicula(movie) }
        upcomingMovies.adapter = upcomingMoviesAdapter





        topRatedMovies = view.findViewById(R.id.top_rated_movies)
        topRatedMoviesLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        topRatedMovies.layoutManager = topRatedMoviesLayoutMgr
        topRatedMoviesAdapter = PeliculasAdapter(mutableListOf()){ movie -> mostrarDetallesPelicula(movie) }
        topRatedMovies.adapter = topRatedMoviesAdapter




        nowplaying = view.findViewById(R.id.nowplaying)
        nowplayingLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        nowplaying.layoutManager = nowplayingLayoutMgr
        nowplayingAdapter = PeliculasAdapter(mutableListOf()){ movie -> mostrarDetallesPelicula(movie) }
        nowplaying.adapter = nowplayingAdapter




        comedyMovies=view.findViewById(R.id.comedy_movies)
        comedyMoviesLayoutMgr= LinearLayoutManager(
            context,LinearLayoutManager.HORIZONTAL,false
        )
        comedyMovies.layoutManager = comedyMoviesLayoutMgr
        comedyMoviesAdapter = PeliculasAdapter(mutableListOf()){ movie -> mostrarDetallesPelicula(movie) }
        comedyMovies.adapter = comedyMoviesAdapter





        actionMovies = view.findViewById(R.id.action_movies)
        actionMoviesLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        actionMovies.layoutManager = actionMoviesLayoutMgr
        actionMoviesAdapter = PeliculasAdapter(mutableListOf()){ movie -> mostrarDetallesPelicula(movie) }
        actionMovies.adapter = actionMoviesAdapter




        dramaMovies = view.findViewById(R.id.drama_movies)
        dramaMoviesLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        dramaMovies.layoutManager = dramaMoviesLayoutMgr
        dramaMoviesAdapter = PeliculasAdapter(mutableListOf()){ movie -> mostrarDetallesPelicula(movie) }
        dramaMovies.adapter = dramaMoviesAdapter





        horrorMovies = view.findViewById(R.id.horror_movies)
        horrorMoviesLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        horrorMovies.layoutManager = horrorMoviesLayoutMgr
        horrorMoviesAdapter = PeliculasAdapter(mutableListOf()){ movie -> mostrarDetallesPelicula(movie) }
        horrorMovies.adapter = horrorMoviesAdapter





        spanishMovies = view.findViewById(R.id.spanish_movies)
        spanishMoviesLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        spanishMovies.layoutManager = spanishMoviesLayoutMgr
        spanishMoviesAdapter = PeliculasAdapter(mutableListOf()){ movie -> mostrarDetallesPelicula(movie) }
        spanishMovies.adapter = spanishMoviesAdapter



        animatedMovies = view.findViewById(R.id.animated_movies)
        animatedMoviesLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        animatedMovies.layoutManager = animatedMoviesLayoutMgr
        animatedMoviesAdapter = PeliculasAdapter(mutableListOf()){ movie -> mostrarDetallesPelicula(movie) }
        animatedMovies.adapter = animatedMoviesAdapter




        familyMovies = view.findViewById(R.id.family_movies)
        familyMoviesLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        familyMovies.layoutManager = familyMoviesLayoutMgr
        familyMoviesAdapter = PeliculasAdapter(mutableListOf()){ movie -> mostrarDetallesPelicula(movie) }
        familyMovies.adapter = familyMoviesAdapter





        scifiMovies = view.findViewById(R.id.scifi_movies)
        scifiMoviesLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        scifiMovies.layoutManager = scifiMoviesLayoutMgr
        scifiMoviesAdapter = PeliculasAdapter(mutableListOf()){ movie -> mostrarDetallesPelicula(movie) }
        scifiMovies.adapter = scifiMoviesAdapter



        fantasyMovies = view.findViewById(R.id.fantasy_movies)
        fantasyMoviesLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        fantasyMovies.layoutManager = fantasyMoviesLayoutMgr
        fantasyMoviesAdapter = PeliculasAdapter(mutableListOf()){ movie -> mostrarDetallesPelicula(movie) }
        fantasyMovies.adapter = fantasyMoviesAdapter




        documentaryMovies = view.findViewById(R.id.documentary_movies)
        documentaryMoviesLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        documentaryMovies.layoutManager = documentaryMoviesLayoutMgr
        documentaryMoviesAdapter = PeliculasAdapter(mutableListOf()){ movie -> mostrarDetallesPelicula(movie) }
        documentaryMovies.adapter = documentaryMoviesAdapter




        westernMovies = view.findViewById(R.id.western_movies)
        westernMoviesLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        westernMovies.layoutManager = westernMoviesLayoutMgr
        westernMoviesAdapter = PeliculasAdapter(mutableListOf()){ movie -> mostrarDetallesPelicula(movie) }
        westernMovies.adapter = westernMoviesAdapter




        warMovies = view.findViewById(R.id.war_movies)
        warMoviesLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        warMovies.layoutManager = warMoviesLayoutMgr
        warMoviesAdapter = PeliculasAdapter(mutableListOf()){ movie -> mostrarDetallesPelicula(movie) }
        warMovies.adapter = warMoviesAdapter




        sportsMovies = view.findViewById(R.id.sports_movies)
        sportsMoviesLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        sportsMovies.layoutManager = sportsMoviesLayoutMgr
        sportsMoviesAdapter = PeliculasAdapter(mutableListOf()){ movie -> mostrarDetallesPelicula(movie) }
        sportsMovies.adapter = sportsMoviesAdapter


        getSportsMovies()
        getWarMovies()
        getWesternMovies()
        getDocumentaryMovies()
        getFantasyMovies()
        getSciFiMovies()
        getFamilyMovies()
        getAnimatedMovies()
        getSpanishMovies()
        getHorrorMovies()
        getDramaMovies()
        getactionMovies()
        getComedyMovies()
        getNowPlayingMovies()
        getTopRatedMovies()
        getUpcomingMovies()
        getPopularMovies()


        return view
    }
    private fun onError() {
        Toast.makeText(context, getString(R.string.error_fetch_movies), Toast.LENGTH_SHORT).show()
    }


    /*PELICULAS POPULARES*/
    private fun attachPopularMoviesOnScrollListener() {
        popularMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = popularMoviesLayoutMgr.itemCount
                val visibleItemCount = popularMoviesLayoutMgr.childCount
                val firstVisibleItem = popularMoviesLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    popularMovies.removeOnScrollListener(this)
                    popularMoviesPage++
                    getPopularMovies()
                }
            }
        })
    }

    private fun onPopularMoviesFetched(movies: List<Pelicula>) {
        popularMoviesAdapter.appendMovies(movies)
        attachPopularMoviesOnScrollListener()
    }

    private fun getPopularMovies() {
        PeliculasRespository.getPopularMovies(
            popularMoviesPage,
            ::onPopularMoviesFetched,
            ::onError
        )
    }
    /*PELICULAS POPULARES*/





    /*PELICULAS PROXIMAS A ESTRENAR*/
    private fun attachUpcomingMoviesOnScrollListener() {
        upcomingMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = upcomingMoviesLayoutMgr.itemCount
                val visibleItemCount = upcomingMoviesLayoutMgr.childCount
                val firstVisibleItem = upcomingMoviesLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    upcomingMovies.removeOnScrollListener(this)
                    upcomingMoviesPage++
                    getUpcomingMovies()
                }
            }
        })
    }
    private fun onUpcomingMoviesFetched(movies: List<Pelicula>) {
        upcomingMoviesAdapter.appendMovies(movies)
        attachUpcomingMoviesOnScrollListener()
    }
    private fun getUpcomingMovies() {
        PeliculasRespository.getUpcomingMovies(
            upcomingMoviesPage,
            ::onUpcomingMoviesFetched,
            ::onError
        )
    }
    /*PELICULAS PROXIMAS A ESTRENAR*/





    /*PELICULAS MEJOR VALORADAS*/

    private fun onTopRatedMoviesFetched(movies: List<Pelicula>) {
        topRatedMoviesAdapter.appendMovies(movies)
        attachTopRatedMoviesOnScrollListener()
    }

    private fun getTopRatedMovies() {
        PeliculasRespository.getTopRatedMovies(
            topRatedMoviesPage,
            ::onTopRatedMoviesFetched,
            ::onError
        )
    }
    private fun attachTopRatedMoviesOnScrollListener() {
        topRatedMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = topRatedMoviesLayoutMgr.itemCount
                val visibleItemCount = topRatedMoviesLayoutMgr.childCount
                val firstVisibleItem = topRatedMoviesLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    topRatedMovies.removeOnScrollListener(this)
                    topRatedMoviesPage++
                    getTopRatedMovies()
                }
            }
        })
    }
    /*PELICULAS MEJOR VALORADAS*/


    /*Peliculas En cartelera*/
    private fun getNowPlayingMovies() {
        PeliculasRespository.getNowPlaying(
            nowplayingsPage,
            ::onTopnowplayingMoviesFetched,
            ::onError
        )
    }

    private fun attachNowPlayingMoviesOnScrollListener() {
        nowplaying.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = nowplayingLayoutMgr.itemCount
                val visibleItemCount = nowplayingLayoutMgr.childCount
                val firstVisibleItem = nowplayingLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    nowplaying.removeOnScrollListener(this)
                    nowplayingsPage++
                    getNowPlayingMovies()
                }
            }
        })
    }
    private fun onTopnowplayingMoviesFetched(movies: List<Pelicula>) {
        nowplayingAdapter.appendMovies(movies)
        attachNowPlayingMoviesOnScrollListener()
    }

    /*Peliculas en cartelera*/


    /*Peliculas de comedia*/
    private fun getComedyMovies() {
        PeliculasRespository.getComedyMovies(
            comedyMoviesPage,
            ::oncomedyMoviesFetched,
            ::onError
        )
    }

    private fun attachcomedyMoviesOnScrollListener() {
        comedyMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount =   comedyMoviesLayoutMgr.itemCount
                val visibleItemCount = comedyMoviesLayoutMgr.childCount
                val firstVisibleItem = comedyMoviesLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    comedyMovies.removeOnScrollListener(this)
                    comedyMoviesPage++
                    getComedyMovies()
                }
            }
        })
    }

    private fun oncomedyMoviesFetched(movies: List<Pelicula>) {
        comedyMoviesAdapter.appendMovies(movies)
        attachcomedyMoviesOnScrollListener()
    }
    /*Peliculas de comedia*/


    /*Peliculas de accion*/
    private fun getactionMovies() {
        PeliculasRespository.getactionMovies(
            actionMoviesPage,
            ::onactionMoviesFetched,
            ::onError
        )
    }

    private fun attachactionMoviesOnScrollListener() {
        actionMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount =   actionMoviesLayoutMgr.itemCount
                val visibleItemCount = actionMoviesLayoutMgr.childCount
                val firstVisibleItem = actionMoviesLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    actionMovies.removeOnScrollListener(this)
                    actionMoviesPage++
                    getactionMovies()
                }
            }
        })
    }

    private fun onactionMoviesFetched(movies: List<Pelicula>) {
        actionMoviesAdapter.appendMovies(movies)
        attachactionMoviesOnScrollListener()
    }
    /*Peliculas de accion*/



    /*Peliculas de Drama*/
    private fun getDramaMovies() {
        PeliculasRespository.getDramaMovies(
            dramaMoviesPage,
            ::ondramaMoviesFetched,
            ::onError
        )
    }

    private fun attachdramaMoviesOnScrollListener() {
        dramaMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount =   dramaMoviesLayoutMgr.itemCount
                val visibleItemCount = dramaMoviesLayoutMgr.childCount
                val firstVisibleItem = dramaMoviesLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    dramaMovies.removeOnScrollListener(this)
                    dramaMoviesPage++
                    getDramaMovies()
                }
            }
        })
    }

    private fun ondramaMoviesFetched(movies: List<Pelicula>) {
        dramaMoviesAdapter.appendMovies(movies)
        attachdramaMoviesOnScrollListener()
    }
    /*Peliculas de Drama*/

    /*Peliculas de horror*/
    private fun getHorrorMovies() {
        PeliculasRespository.getTerrorMovies(
            horrorMoviesPage,
            ::onHorrorMoviesFetched,
            ::onError
        )
    }

    private fun attachHorrorMoviesOnScrollListener() {
        horrorMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount =   horrorMoviesLayoutMgr.itemCount
                val visibleItemCount = horrorMoviesLayoutMgr.childCount
                val firstVisibleItem = horrorMoviesLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    horrorMovies.removeOnScrollListener(this)
                    horrorMoviesPage++
                    getHorrorMovies()
                }
            }
        })
    }

    private fun onHorrorMoviesFetched(movies: List<Pelicula>) {
        horrorMoviesAdapter.appendMovies(movies)
        attachHorrorMoviesOnScrollListener()
    }

    /*Peliculas de horror*/


    /*Peliculas Españolas*/

    private fun getSpanishMovies() {
        PeliculasRespository.getSpanishMovies(
            spanishMoviesPage,
            ::onSpanishMoviesFetched,
            ::onError
        )
    }

    private fun attachSpanishMoviesOnScrollListener() {
        spanishMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount =   spanishMoviesLayoutMgr.itemCount
                val visibleItemCount = spanishMoviesLayoutMgr.childCount
                val firstVisibleItem = spanishMoviesLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    spanishMovies.removeOnScrollListener(this)
                    spanishMoviesPage++
                    getSpanishMovies()
                }
            }
        })
    }

    private fun onSpanishMoviesFetched(movies: List<Pelicula>) {
        spanishMoviesAdapter.appendMovies(movies)
        attachSpanishMoviesOnScrollListener()
    }

    /*Peliculas Españolas*/


    /*Peliculas animadas*/
    private fun getAnimatedMovies() {
        PeliculasRespository.getAnimatedMovies(
            animatedMoviesPage,
            ::onAnimatedMoviesFetched,
            ::onError
        )
    }


    private fun attachAnimatedMoviesOnScrollListener() {
        animatedMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount =   animatedMoviesLayoutMgr.itemCount
                val visibleItemCount = animatedMoviesLayoutMgr.childCount
                val firstVisibleItem = animatedMoviesLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    animatedMovies.removeOnScrollListener(this)
                    animatedMoviesPage++
                    getAnimatedMovies()
                }
            }
        })
    }

    private fun onAnimatedMoviesFetched(movies: List<Pelicula>) {
        animatedMoviesAdapter.appendMovies(movies)
        attachAnimatedMoviesOnScrollListener()
    }
    /*Peliculas animadas*/

    /*Peliculas Familiares*/

    private fun getFamilyMovies() {
        PeliculasRespository.getFamilyMovies(
            familyMoviesPage,
            ::onFamilyMoviesFetched,
            ::onError
        )
    }

    private fun attachFamilyMoviesOnScrollListener() {
        familyMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount =   familyMoviesLayoutMgr.itemCount
                val visibleItemCount = familyMoviesLayoutMgr.childCount
                val firstVisibleItem = familyMoviesLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    familyMovies.removeOnScrollListener(this)
                    familyMoviesPage++
                    getFamilyMovies()
                }
            }
        })
    }
    private fun onFamilyMoviesFetched(movies: List<Pelicula>) {
        familyMoviesAdapter.appendMovies(movies)
        attachFamilyMoviesOnScrollListener()
    }

    /*Peliculas Familiares*/

    /*Peliculas de ciencia ficcion*/
    private fun getSciFiMovies() {
        PeliculasRespository.getSciFiMovies(
            scifiMoviesPage,
            ::onSciFiMoviesFetched,
            ::onError
        )
    }

    private fun attachSciFiMoviesOnScrollListener() {
        scifiMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount =   scifiMoviesLayoutMgr.itemCount
                val visibleItemCount = scifiMoviesLayoutMgr.childCount
                val firstVisibleItem = scifiMoviesLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    scifiMovies.removeOnScrollListener(this)
                    scifiMoviesPage++
                    getSciFiMovies()
                }
            }
        })
    }

    private fun onSciFiMoviesFetched(movies: List<Pelicula>) {
        scifiMoviesAdapter.appendMovies(movies)
        attachSciFiMoviesOnScrollListener()
    }

    /*Peliculas de ciencia ficcion*/

    /*Peliculas de fantasia*/
    private fun getFantasyMovies() {
        PeliculasRespository.getFantasyMovies(
            fantasyMoviesPage,
            ::onFantasyMoviesFetched,
            ::onError
        )
    }

    private fun attachFantasyMoviesOnScrollListener() {
        fantasyMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount =   fantasyMoviesLayoutMgr.itemCount
                val visibleItemCount = fantasyMoviesLayoutMgr.childCount
                val firstVisibleItem = fantasyMoviesLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    fantasyMovies.removeOnScrollListener(this)
                    fantasyMoviesPage++
                    getFantasyMovies()
                }
            }
        })
    }

    private fun onFantasyMoviesFetched(movies: List<Pelicula>) {
        fantasyMoviesAdapter.appendMovies(movies)
        attachFantasyMoviesOnScrollListener()
    }

    /*Peliculas de fantasia*/

    /*Documentales*/
    private fun getDocumentaryMovies() {
        PeliculasRespository.getDocumentaryMovies(
            documentaryMoviesPage,
            ::onDocumentaryMoviesFetched,
            ::onError
        )
    }

    private fun attachDocumentaryMoviesOnScrollListener() {
        documentaryMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount =   documentaryMoviesLayoutMgr.itemCount
                val visibleItemCount = documentaryMoviesLayoutMgr.childCount
                val firstVisibleItem = documentaryMoviesLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    documentaryMovies.removeOnScrollListener(this)
                    documentaryMoviesPage++
                    getDocumentaryMovies()
                }
            }
        })
    }

    private fun onDocumentaryMoviesFetched(movies: List<Pelicula>) {
        documentaryMoviesAdapter.appendMovies(movies)
        attachDocumentaryMoviesOnScrollListener()
    }
    /*Documentales*/

    /*Western*/
    private fun getWesternMovies() {
        PeliculasRespository.getWesternMovies(
            westernMoviesPage,
            ::onWesternMoviesFetched,
            ::onError
        )
    }

    private fun attachWesternMoviesOnScrollListener() {
        westernMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount =   westernMoviesLayoutMgr.itemCount
                val visibleItemCount = westernMoviesLayoutMgr.childCount
                val firstVisibleItem = westernMoviesLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    westernMovies.removeOnScrollListener(this)
                    westernMoviesPage++
                    getWesternMovies()
                }
            }
        })
    }

    private fun onWesternMoviesFetched(movies: List<Pelicula>) {
        westernMoviesAdapter.appendMovies(movies)
        attachWesternMoviesOnScrollListener()
    }
    /*Western*/

    /*war*/
    private fun getWarMovies() {
        PeliculasRespository.getWarMovies(
            warMoviesPage,
            ::onWarMoviesFetched,
            ::onError
        )
    }

    private fun attachWarMoviesOnScrollListener() {
        warMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount =   warMoviesLayoutMgr.itemCount
                val visibleItemCount = warMoviesLayoutMgr.childCount
                val firstVisibleItem = warMoviesLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    warMovies.removeOnScrollListener(this)
                    warMoviesPage++
                    getWarMovies()
                }
            }
        })
    }

    private fun onWarMoviesFetched(movies: List<Pelicula>) {
        warMoviesAdapter.appendMovies(movies)
        attachWarMoviesOnScrollListener()
    }

    /*war*/

    /*Sports*/
    private fun getSportsMovies() {
        PeliculasRespository.getSportsMovies(
            sportsMoviesPage,
            ::onSportsMoviesFetched,
            ::onError
        )
    }

    private fun attachSportsMoviesOnScrollListener() {
        sportsMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount =   sportsMoviesLayoutMgr.itemCount
                val visibleItemCount = sportsMoviesLayoutMgr.childCount
                val firstVisibleItem = sportsMoviesLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    sportsMovies.removeOnScrollListener(this)
                    sportsMoviesPage++
                    getSportsMovies()
                }
            }
        })
    }

    private fun onSportsMoviesFetched(movies: List<Pelicula>) {
        sportsMoviesAdapter.appendMovies(movies)
        attachSportsMoviesOnScrollListener()
    }

    private fun mostrarDetallesPelicula(movie: Pelicula) {
        val intent = Intent(context, DetallesPelicula::class.java)
        intent.putExtra(MOVIE_ID, movie.id)
        intent.putExtra(MOVIE_BACKDROP, movie.rutaBackdrop)
        intent.putExtra(MOVIE_POSTER, movie.rutaPoster)
        intent.putExtra(MOVIE_TITLE, movie.titulo)
        intent.putExtra(MOVIE_RATING, movie.valoracion)
        intent.putExtra(MOVIE_RELEASE_DATE, movie.fechaEstreno)
        intent.putExtra(MOVIE_OVERVIEW, movie.descripcionGeneral)
        intent.putExtra(MOVIE_LANGUAGE,movie.idiomaOriginal)
        intent.putExtra("usuario",arguments?.getString("usuario"))
        intent.putExtra("tipousuario",arguments?.getInt("tipousuario"))
        startActivity(intent)
    }
}