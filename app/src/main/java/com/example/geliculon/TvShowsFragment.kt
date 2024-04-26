package com.example.geliculon

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class TvShowsFragment : Fragment() {
    /*SERIES POPULARES*/
    private lateinit var popularTvShows: RecyclerView
    private lateinit var popularTvShowsAdapter: TvShowAdapter
    private lateinit var popularTvShowsLayoutMgr: LinearLayoutManager
    private var popularTvShowsPage = 1
    /*SERIES POPULARES*/

    /*Series Proximas a estrenarse*/
    private lateinit var upcomingtvShows: RecyclerView
    private lateinit var upcomingtvShowsAdapter: TvShowAdapter
    private lateinit var upcomingtvShowsLayoutMgr: LinearLayoutManager
    private var upcomingTvShowsPage = 1

    /*Series Proximas a estrenarse*/


    /*SERIES MEJOR VALORADAS*/
    private lateinit var topRatedTvShows: RecyclerView
    private lateinit var topRatedTvShowsAdapter: TvShowAdapter
    private lateinit var topRatedTvShowsLayoutMgr: LinearLayoutManager
    private var topRatedTvShowsPage = 1
    /*Series Mejor valoradas*/


    /*En Emision actualmente*/
    private lateinit var onAirTvShows: RecyclerView
    private lateinit var onAirTvShowsAdapter: TvShowAdapter
    private lateinit var onAirTvShowsLayoutMgr: LinearLayoutManager
    private var onAirTvShowsPage = 1
    /*En Emision actualmente*/

    /*Comedia*/
    private lateinit var comedyTvShows: RecyclerView
    private lateinit var comedyTvShowsAdapter: TvShowAdapter
    private lateinit var comedyTvShowsLayoutMgr: LinearLayoutManager
    private var comedyTvShowsPage = 1
    /*Comedia*/

    /*Accion*/
    private lateinit var actionTvShows: RecyclerView
    private lateinit var actionTvShowsAdapter: TvShowAdapter
    private lateinit var actionTvShowsLayoutMgr: LinearLayoutManager
    private var actionTvShowsPage = 1
    /*Accion*/

    /*drama*/
    private lateinit var dramaTvShows: RecyclerView
    private lateinit var dramaTvShowsAdapter: TvShowAdapter
    private lateinit var dramaTvShowsLayoutMgr: LinearLayoutManager
    private var dramaTvShowsPage = 1
    /*Drama*/

    /*TERROR*/
    private lateinit var horrorTvShows: RecyclerView
    private lateinit var horrorTvShowsAdapter: TvShowAdapter
    private lateinit var horrorTvShowsLayoutMgr: LinearLayoutManager
    private var horrorTvShowsPage = 1

    /*TERROR*/

    /*Españoles*/
    private lateinit var spanishTvShows: RecyclerView
    private lateinit var spanishTvShowsAdapter: TvShowAdapter
    private lateinit var spanishTvShowsLayoutMgr: LinearLayoutManager
    private var spanishTvShowsPage = 1
    /*Españolas*/

    /*Animacion*/
    private lateinit var animatedTvShows: RecyclerView
    private lateinit var animatedTvShowsAdapter: TvShowAdapter
    private lateinit var animatedTvShowsLayoutMgr: LinearLayoutManager
    private var animatedTvShowsPage = 1
    /*Animacion*/

    /*Familiares*/
    private lateinit var familyTvShows: RecyclerView
    private lateinit var familyTvShowsAdapter: TvShowAdapter
    private lateinit var familyTvShowsLayoutMgr: LinearLayoutManager
    private var familyTvShowsPage = 1
    /*Familiares*/

    /*Ciencia ficcion*/
    private lateinit var scifiTvShows: RecyclerView
    private lateinit var scifiTvShowsAdapter: TvShowAdapter
    private lateinit var scifiTvShowsLayoutMgr: LinearLayoutManager
    private var scifiTvShowsPage = 1
    /*Ciencia ficcion*/

    /*FANTASIA*/
    private lateinit var fantasyTvShows: RecyclerView
    private lateinit var fantasyTvShowsAdapter: TvShowAdapter
    private lateinit var fantasyTvShowsLayoutMgr: LinearLayoutManager
    private var fantasyTvShowsPage = 1
    /*FANTAASIA*/

    /*DOCUMENTALES*/
    private lateinit var documentaryTvShows: RecyclerView
    private lateinit var documentaryTvShowsAdapter: TvShowAdapter
    private lateinit var documentaryTvShowsLayoutMgr: LinearLayoutManager
    private var documentaryTvShowsPage = 1
    /*DOCUMENTALES*/

    /*WESTERN*/
    private lateinit var westernTvShows: RecyclerView
    private lateinit var westernTvShowsAdapter: TvShowAdapter
    private lateinit var westernTvShowsLayoutMgr: LinearLayoutManager
    private var westernTvShowsPage = 1
    /*WESTERN*/


    /*war*/
    private lateinit var warTvShows: RecyclerView
    private lateinit var warTvShowsAdapter: TvShowAdapter
    private lateinit var warTvShowsLayoutMgr: LinearLayoutManager
    private var warTvShowsPage = 1
    /*war*/

    /*sports*/
    private lateinit var sportsTvShows: RecyclerView
    private lateinit var sportsTvShowsAdapter: TvShowAdapter
    private lateinit var sportsTvShowsLayoutMgr: LinearLayoutManager
    private var sportsTvShowsPage = 1
    /*sports*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_tv_shows, container, false)


        /*Series Populares*/
        popularTvShows = view.findViewById(R.id.popular_tv_shows)
        popularTvShowsLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        popularTvShows.layoutManager = popularTvShowsLayoutMgr
        popularTvShowsAdapter = TvShowAdapter(mutableListOf()) { tvShow -> showTvShowDetails(tvShow) }
        popularTvShows.adapter = popularTvShowsAdapter
        getPopularTvShows()
        /*Series Populares*/


        /*Series Proximas a estrenarse*/
        upcomingtvShows = view.findViewById(R.id.upcoming_tvshows)
        upcomingtvShowsLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        upcomingtvShows.layoutManager = upcomingtvShowsLayoutMgr
        upcomingtvShowsAdapter = TvShowAdapter(mutableListOf()) { tvShow -> showTvShowDetails(tvShow) }
        upcomingtvShows.adapter = upcomingtvShowsAdapter
        getUpcomingTvShow()
        /*Series Proximas a estrenarse*/

        /*sERIES MEJOR VALORADAS*/
        topRatedTvShows = view.findViewById(R.id.top_rated_tv_shows)
        topRatedTvShowsLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        topRatedTvShows.layoutManager = topRatedTvShowsLayoutMgr
        topRatedTvShowsAdapter = TvShowAdapter(mutableListOf()) { tvShow -> showTvShowDetails(tvShow) }
        topRatedTvShows.adapter = topRatedTvShowsAdapter
        getTopRatedTvShows()
        /*SERIES MEJOR VALORADAS*/

        /*En emision actualmente*/
        onAirTvShows = view.findViewById(R.id.on_air_tv_shows)
        onAirTvShowsLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        onAirTvShows.layoutManager = onAirTvShowsLayoutMgr
        onAirTvShowsAdapter = TvShowAdapter(mutableListOf()) { tvShow -> showTvShowDetails(tvShow) }
        onAirTvShows.adapter = onAirTvShowsAdapter
        getOnAirTvShows()

        /*En emision actualmente*/


        /*Comedy*/
        comedyTvShows = view.findViewById(R.id.comedytv_hows)
        comedyTvShowsLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        comedyTvShows.layoutManager = comedyTvShowsLayoutMgr
        comedyTvShowsAdapter = TvShowAdapter(mutableListOf()) { tvShow -> showTvShowDetails(tvShow) }
        comedyTvShows.adapter = comedyTvShowsAdapter
        getComedyTvShows()
        /*Comedia*/


        /*Accion*/
        actionTvShows = view.findViewById(R.id.actiontv_shows)
        actionTvShowsLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        actionTvShows.layoutManager = actionTvShowsLayoutMgr
        actionTvShowsAdapter = TvShowAdapter(mutableListOf()) { tvShow -> showTvShowDetails(tvShow) }
        actionTvShows.adapter = actionTvShowsAdapter

        getActionTvShows()
        /*Accion*/

        /*dRAMA*/
        dramaTvShows = view.findViewById(R.id.dramatv_shows)
        dramaTvShowsLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        dramaTvShows.layoutManager = dramaTvShowsLayoutMgr
        dramaTvShowsAdapter = TvShowAdapter(mutableListOf()) { tvShow -> showTvShowDetails(tvShow) }
        dramaTvShows.adapter = dramaTvShowsAdapter

        getDramaTvShows()
        /*DRAMA*/

        /*TERROR*/
        horrorTvShows = view.findViewById(R.id.horrortv_shows)
        horrorTvShowsLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        horrorTvShows.layoutManager = horrorTvShowsLayoutMgr
        horrorTvShowsAdapter = TvShowAdapter(mutableListOf()) { tvShow -> showTvShowDetails(tvShow) }
        horrorTvShows.adapter = horrorTvShowsAdapter

        getHorrorTvShows()

        /*TERROR*/

        /*ESPAÑA*/
        spanishTvShows = view.findViewById(R.id.spanishtv_shows)
        spanishTvShowsLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        spanishTvShows.layoutManager = spanishTvShowsLayoutMgr
        spanishTvShowsAdapter = TvShowAdapter(mutableListOf()) { tvShow -> showTvShowDetails(tvShow) }
        spanishTvShows.adapter = spanishTvShowsAdapter
        getSpanishTvShows()
        /*ESPAÑA*/

        /*Snimacion*/
        animatedTvShows = view.findViewById(R.id.animatedtv_shows)
        animatedTvShowsLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        animatedTvShows.layoutManager = animatedTvShowsLayoutMgr
        animatedTvShowsAdapter = TvShowAdapter(mutableListOf()) { tvShow -> showTvShowDetails(tvShow) }
        animatedTvShows.adapter = animatedTvShowsAdapter

        getAnimatedTvShows()
        /*Animacion*/

        /*Fmiliares*/
        familyTvShows = view.findViewById(R.id.familytv_shows)
        familyTvShowsLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        familyTvShows.layoutManager = familyTvShowsLayoutMgr
        familyTvShowsAdapter = TvShowAdapter(mutableListOf()) { tvShow -> showTvShowDetails(tvShow) }
        familyTvShows.adapter = familyTvShowsAdapter

        getFamilyTvShows()
        /*Familiares*/

        /*Ciencia ficcion*/
        scifiTvShows = view.findViewById(R.id.scifi_shows)
        scifiTvShowsLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        scifiTvShows.layoutManager = scifiTvShowsLayoutMgr
        scifiTvShowsAdapter = TvShowAdapter(mutableListOf()) { tvShow -> showTvShowDetails(tvShow) }
        scifiTvShows.adapter = scifiTvShowsAdapter

        getscifiTvShows()

        /*Ciencia ficcion*/

        /*FANTASUA*/
        fantasyTvShows = view.findViewById(R.id.fantasytv_shows)
        fantasyTvShowsLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        fantasyTvShows.layoutManager = fantasyTvShowsLayoutMgr
        fantasyTvShowsAdapter = TvShowAdapter(mutableListOf()) { tvShow -> showTvShowDetails(tvShow) }
        fantasyTvShows.adapter = fantasyTvShowsAdapter

        getFantasyTvShows()

        /*fantasaia*/

        /*Documentales*/
        documentaryTvShows = view.findViewById(R.id.documentarytv_shows)
        documentaryTvShowsLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        documentaryTvShows.layoutManager =documentaryTvShowsLayoutMgr
        documentaryTvShowsAdapter = TvShowAdapter(mutableListOf()) { tvShow -> showTvShowDetails(tvShow) }
        documentaryTvShows.adapter = documentaryTvShowsAdapter

        getDocumentaryTvShows()

        /*Documentales*/

        /*western*/
       westernTvShows = view.findViewById(R.id.westerntv_shows)
       westernTvShowsLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
       westernTvShows.layoutManager = westernTvShowsLayoutMgr
       westernTvShowsAdapter = TvShowAdapter(mutableListOf()) { tvShow -> showTvShowDetails(tvShow) }
       westernTvShows.adapter = westernTvShowsAdapter

        getWesternTvShows()
        /*western*/


        /*WAR*/
        warTvShows = view.findViewById(R.id.wartv_shows)
        warTvShowsLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        warTvShows.layoutManager = warTvShowsLayoutMgr
        warTvShowsAdapter = TvShowAdapter(mutableListOf()) { tvShow -> showTvShowDetails(tvShow) }
        warTvShows.adapter = warTvShowsAdapter

        getWarTvShows()
        /*war*/


        /*sports*/
        sportsTvShows = view.findViewById(R.id.sportstv_shows)
        sportsTvShowsLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        sportsTvShows.layoutManager = sportsTvShowsLayoutMgr
        sportsTvShowsAdapter = TvShowAdapter(mutableListOf()) { tvShow -> showTvShowDetails(tvShow) }
        sportsTvShows.adapter = sportsTvShowsAdapter

        getSportsTvShows()
        /*sports*/


        return view
    }

    private fun getPopularTvShows() {
        TvShowRepository.getPopularTvShows(
            popularTvShowsPage,
            ::onPopularTvShowsFetched,
            ::onError
        )
    }
    private fun onPopularTvShowsFetched(tvShows: List<TvShow>) {
        popularTvShowsAdapter.appendTvShows(tvShows)
        attachPopularTvShowsOnScrollListener()
    }

    private fun attachPopularTvShowsOnScrollListener() {
        popularTvShows.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = popularTvShowsLayoutMgr.itemCount
                val visibleItemCount = popularTvShowsLayoutMgr.childCount
                val firstVisibleItem = popularTvShowsLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    popularTvShows.removeOnScrollListener(this)
                    popularTvShowsPage++
                    getPopularTvShows()
                }
            }
        })
    }


    private fun getUpcomingTvShow() {
        TvShowRepository.getUpcomingTvShows(
            upcomingTvShowsPage,
            ::onupcomingTvShowsFetched,
            ::onError
        )
    }
    private fun attachupcomingShowsOnScrollListener() {
        upcomingtvShows.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount =   upcomingtvShowsLayoutMgr.itemCount
                val visibleItemCount = upcomingtvShowsLayoutMgr.childCount
                val firstVisibleItem = upcomingtvShowsLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    upcomingtvShows.removeOnScrollListener(this)
                    upcomingTvShowsPage++
                    getUpcomingTvShow()
                }
            }
        })
    }
    private fun onupcomingTvShowsFetched(tvShows: List<TvShow>) {
        upcomingtvShowsAdapter.appendTvShows(tvShows)
        attachupcomingShowsOnScrollListener()
    }


    private fun getTopRatedTvShows() {
        TvShowRepository.getTopRatedTvShows(
            topRatedTvShowsPage,
            ::onTopRatedTvShowsFetched,
            ::onError
        )
    }
    private fun attachTopRatedTvShowsOnScrollListener() {
        topRatedTvShows.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = topRatedTvShowsLayoutMgr.itemCount
                val visibleItemCount = topRatedTvShowsLayoutMgr.childCount
                val firstVisibleItem = topRatedTvShowsLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    topRatedTvShows.removeOnScrollListener(this)
                    topRatedTvShowsPage++
                    getTopRatedTvShows()
                }
            }
        })
    }
    private fun onTopRatedTvShowsFetched(tvShows: List<TvShow>) {
        topRatedTvShowsAdapter.appendTvShows(tvShows)
        attachTopRatedTvShowsOnScrollListener()
    }

    private fun getOnAirTvShows() {
        TvShowRepository.getairingtodayTvShow(
            onAirTvShowsPage,
            ::onOnAirTvShowsFetched,
            ::onError
        )
    }
    private fun attachOnAirTvShowsOnScrollListener() {
        onAirTvShows.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = onAirTvShowsLayoutMgr.itemCount
                val visibleItemCount = onAirTvShowsLayoutMgr.childCount
                val firstVisibleItem = onAirTvShowsLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    onAirTvShows.removeOnScrollListener(this)
                    onAirTvShowsPage++
                    getOnAirTvShows()
                }
            }
        })
    }
    private fun onOnAirTvShowsFetched(tvShows: List<TvShow>) {
        onAirTvShowsAdapter.appendTvShows(tvShows)
        attachOnAirTvShowsOnScrollListener()
    }



    private fun getComedyTvShows() {
        TvShowRepository.getComedyTvShows(
            onAirTvShowsPage,
            ::onComedyTvShowsFetched,
            ::onError
        )
    }
    private fun attachComedyTvShowsOnScrollListener() {
        onAirTvShows.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount =   comedyTvShowsLayoutMgr.itemCount
                val visibleItemCount = comedyTvShowsLayoutMgr.childCount
                val firstVisibleItem = comedyTvShowsLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    comedyTvShows.removeOnScrollListener(this)
                    comedyTvShowsPage++
                    getComedyTvShows()
                }
            }
        })
    }
    private fun onComedyTvShowsFetched(tvShows: List<TvShow>) {
        comedyTvShowsAdapter.appendTvShows(tvShows)
        attachComedyTvShowsOnScrollListener()
    }

    private fun getActionTvShows() {
        TvShowRepository.getActionTvShows(
            actionTvShowsPage,
            ::onactionTvShowsFetched,
            ::onError
        )
    }

    private fun attachactionTvShowsOnScrollListener() {
        actionTvShows.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount =   actionTvShowsLayoutMgr.itemCount
                val visibleItemCount = actionTvShowsLayoutMgr.childCount
                val firstVisibleItem = actionTvShowsLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    actionTvShows.removeOnScrollListener(this)
                    actionTvShowsPage++
                    getActionTvShows()
                }
            }
        })
    }
    private fun onactionTvShowsFetched(tvShows: List<TvShow>) {
        actionTvShowsAdapter.appendTvShows(tvShows)
        attachactionTvShowsOnScrollListener()
    }


    private fun getDramaTvShows() {
        TvShowRepository.getDramaTvShows(
            dramaTvShowsPage,
            ::ondramaTvShowsFetched,
            ::onError
        )
    }
    private fun attachdramaTvShowsOnScrollListener() {
        dramaTvShows.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount =   dramaTvShowsLayoutMgr.itemCount
                val visibleItemCount = dramaTvShowsLayoutMgr.childCount
                val firstVisibleItem = dramaTvShowsLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    dramaTvShows.removeOnScrollListener(this)
                    dramaTvShowsPage++
                    getDramaTvShows()
                }
            }
        })
    }
    private fun ondramaTvShowsFetched(tvShows: List<TvShow>) {
        dramaTvShowsAdapter.appendTvShows(tvShows)
        attachdramaTvShowsOnScrollListener()
    }

    private fun getHorrorTvShows() {
        TvShowRepository.getHorrorTvShows(
            horrorTvShowsPage,
            ::onhorrorTvShowsFetched,
            ::onError
        )
    }
    private fun attachhorrorTvShowsOnScrollListener() {
        horrorTvShows.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount =   horrorTvShowsLayoutMgr.itemCount
                val visibleItemCount = horrorTvShowsLayoutMgr.childCount
                val firstVisibleItem = horrorTvShowsLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    horrorTvShows.removeOnScrollListener(this)
                    horrorTvShowsPage++
                    getHorrorTvShows()
                }
            }
        })
    }
    private fun onhorrorTvShowsFetched(tvShows: List<TvShow>) {
        horrorTvShowsAdapter.appendTvShows(tvShows)
        attachhorrorTvShowsOnScrollListener()
    }

    private fun getSpanishTvShows() {
        TvShowRepository.getSpanishTvShows(
            spanishTvShowsPage,
            ::onspanishTvShowsFetched,
            ::onError
        )
    }
    private fun attachspanishTvShowsOnScrollListener() {
        spanishTvShows.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount =   spanishTvShowsLayoutMgr.itemCount
                val visibleItemCount = spanishTvShowsLayoutMgr.childCount
                val firstVisibleItem = spanishTvShowsLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    spanishTvShows.removeOnScrollListener(this)
                    spanishTvShowsPage++
                    getSpanishTvShows()
                }
            }
        })
    }
    private fun onspanishTvShowsFetched(tvShows: List<TvShow>) {
        spanishTvShowsAdapter.appendTvShows(tvShows)
        attachspanishTvShowsOnScrollListener()
    }


    private fun getAnimatedTvShows() {
        TvShowRepository.getAnimationTvShows(
            animatedTvShowsPage,
            ::onanimatedTvShowsFetched,
            ::onError
        )
    }
    private fun attachanimatedTvShowsOnScrollListener() {
        animatedTvShows.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount =   animatedTvShowsLayoutMgr.itemCount
                val visibleItemCount = animatedTvShowsLayoutMgr.childCount
                val firstVisibleItem = animatedTvShowsLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    animatedTvShows.removeOnScrollListener(this)
                    animatedTvShowsPage++
                    getAnimatedTvShows()
                }
            }
        })
    }
    private fun onanimatedTvShowsFetched(tvShows: List<TvShow>) {
        animatedTvShowsAdapter.appendTvShows(tvShows)
        attachanimatedTvShowsOnScrollListener()
    }

    private fun getFamilyTvShows() {
        TvShowRepository.getFamilyTvShows(
            familyTvShowsPage,
            ::onfamilyTvShowsFetched,
            ::onError
        )
    }
    private fun attachfamilyTvShowsOnScrollListener() {
        familyTvShows.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount =   familyTvShowsLayoutMgr.itemCount
                val visibleItemCount = familyTvShowsLayoutMgr.childCount
                val firstVisibleItem = familyTvShowsLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    familyTvShows.removeOnScrollListener(this)
                    familyTvShowsPage++
                    getFamilyTvShows()
                }
            }
        })
    }
    private fun onfamilyTvShowsFetched(tvShows: List<TvShow>) {
        familyTvShowsAdapter.appendTvShows(tvShows)
        attachfamilyTvShowsOnScrollListener()
    }


    private fun getscifiTvShows() {
        TvShowRepository.getSciFiTvShows(
            onAirTvShowsPage,
            ::onscifiTvShowsFetched,
            ::onError
        )
    }
    private fun attachscifiTvShowsOnScrollListener() {
        scifiTvShows.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount =   scifiTvShowsLayoutMgr.itemCount
                val visibleItemCount = scifiTvShowsLayoutMgr.childCount
                val firstVisibleItem = scifiTvShowsLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    scifiTvShows.removeOnScrollListener(this)
                    scifiTvShowsPage++
                    getscifiTvShows()
                }
            }
        })
    }
    private fun onscifiTvShowsFetched(tvShows: List<TvShow>) {
        scifiTvShowsAdapter.appendTvShows(tvShows)
        attachscifiTvShowsOnScrollListener()
    }

    private fun getFantasyTvShows() {
        TvShowRepository.getFantasyTvShows(
            fantasyTvShowsPage,
            ::onfantasyTvShowsFetched,
            ::onError
        )
    }
    private fun attachfantasyTvShowsOnScrollListener() {
        fantasyTvShows.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount =   fantasyTvShowsLayoutMgr.itemCount
                val visibleItemCount = fantasyTvShowsLayoutMgr.childCount
                val firstVisibleItem = fantasyTvShowsLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    fantasyTvShows.removeOnScrollListener(this)
                    fantasyTvShowsPage++
                    getFantasyTvShows()
                }
            }
        })
    }
    private fun onfantasyTvShowsFetched(tvShows: List<TvShow>) {
        fantasyTvShowsAdapter.appendTvShows(tvShows)
        attachfantasyTvShowsOnScrollListener()
    }

    private fun getDocumentaryTvShows() {
        TvShowRepository.getDocumentaryTvShows(
            documentaryTvShowsPage,
            ::ondocumentaryTvShowsFetched,
            ::onError
        )
    }
    private fun attachdocumentaryTvShowsOnScrollListener() {
        documentaryTvShows.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount =   documentaryTvShowsLayoutMgr.itemCount
                val visibleItemCount = documentaryTvShowsLayoutMgr.childCount
                val firstVisibleItem = documentaryTvShowsLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    documentaryTvShows.removeOnScrollListener(this)
                    documentaryTvShowsPage++
                    getDocumentaryTvShows()
                }
            }
        })
    }
    private fun ondocumentaryTvShowsFetched(tvShows: List<TvShow>) {
        documentaryTvShowsAdapter.appendTvShows(tvShows)
        attachdocumentaryTvShowsOnScrollListener()
    }

    private fun getWesternTvShows() {
        TvShowRepository.getWesternTvShows(
            westernTvShowsPage,
            ::onwesternTvShowsFetched,
            ::onError
        )
    }
    private fun attachwesternTvShowsOnScrollListener() {
        westernTvShows.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount =   westernTvShowsLayoutMgr.itemCount
                val visibleItemCount = westernTvShowsLayoutMgr.childCount
                val firstVisibleItem = westernTvShowsLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    westernTvShows.removeOnScrollListener(this)
                    westernTvShowsPage++
                    getWesternTvShows()
                }
            }
        })
    }
    private fun onwesternTvShowsFetched(tvShows: List<TvShow>) {
        westernTvShowsAdapter.appendTvShows(tvShows)
        attachwesternTvShowsOnScrollListener()
    }

    private fun getWarTvShows() {
        TvShowRepository.getWarTvShows(
            warTvShowsPage,
            ::onwarTvShowsFetched,
            ::onError
        )
    }
    private fun attachwarTvShowsOnScrollListener() {
        warTvShows.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount =   warTvShowsLayoutMgr.itemCount
                val visibleItemCount = warTvShowsLayoutMgr.childCount
                val firstVisibleItem = warTvShowsLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    warTvShows.removeOnScrollListener(this)
                    warTvShowsPage++
                    getWarTvShows()
                }
            }
        })
    }
    private fun onwarTvShowsFetched(tvShows: List<TvShow>) {
        warTvShowsAdapter.appendTvShows(tvShows)
        attachwarTvShowsOnScrollListener()
    }

    private fun getSportsTvShows() {
        TvShowRepository.getSportsTvShows(
            sportsTvShowsPage,
            ::onsportsTvShowsFetched,
            ::onError
        )
    }
    private fun attachsportsTvShowsOnScrollListener() {
        sportsTvShows.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount =   sportsTvShowsLayoutMgr.itemCount
                val visibleItemCount = sportsTvShowsLayoutMgr.childCount
                val firstVisibleItem = sportsTvShowsLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    sportsTvShows.removeOnScrollListener(this)
                    sportsTvShowsPage++
                    getSportsTvShows()
                }
            }
        })
    }
    private fun onsportsTvShowsFetched(tvShows: List<TvShow>) {
        sportsTvShowsAdapter.appendTvShows(tvShows)
        attachsportsTvShowsOnScrollListener()
    }


    private fun showTvShowDetails(tvShow: TvShow) {
        val intent = Intent(activity, DetallesTvShow::class.java)
        intent.putExtra(TV_SHOW_ID, tvShow.id)
        intent.putExtra(TV_SHOW_BACKDROP, tvShow.backdropPath)
        intent.putExtra(TV_SHOW_POSTER, tvShow.posterPath)
        intent.putExtra(TV_SHOW_TITLE, tvShow.nombre)
        intent.putExtra(TV_SHOW_RATING, tvShow.valoracion)
        intent.putExtra(TV_SHOW_RELEASE_DATE, tvShow.fechaPiloto)
        intent.putExtra(TV_SHOW_OVERVIEW, tvShow.overview)
        intent.putExtra(TV_SHOW_ORIGINALLANGUAGE, tvShow.idiomaOriginal)
        intent.putExtra("usuario",arguments?.getString("usuario"))
        intent.putExtra("tipousuario",arguments?.getInt("tipousuario"))
        startActivity(intent)
    }



    private fun onError() {
        Toast.makeText(activity, getString(R.string.error_fetch_movies), Toast.LENGTH_SHORT).show()
    }
}





