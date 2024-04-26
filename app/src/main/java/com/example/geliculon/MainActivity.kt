package com.example.geliculon

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import de.hdodenhof.circleimageview.CircleImageView

private const val MOVIES_FRAGMENT = "movies_fragment"
private const val TV_SHOWS_FRAGMENT = "tv_shows_fragment"
private const val WATCH_LIST_FRAGMENT = "watch_list_fragment"
private const val REVIEW_FRAGMENT = "review_fragment"
private const val CHAT_FRAGMENT = "chat_fragment"

private lateinit var drawerLayout: DrawerLayout
private lateinit var bottomNavView: BottomNavigationView


class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {



    override fun onCreate(savedInstanceState: Bundle?) {
        val usuario2=intent.getStringExtra("user")
        val tipousuario=intent.getIntExtra("tipousuario",0)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(tipousuario!=2){
            Toast.makeText(this, "Usuario: $usuario2", Toast.LENGTH_LONG).show()
        }

        var drawerLayout1: DrawerLayout = findViewById(R.id.drawer_layout)
        val navigationView1: NavigationView = findViewById(R.id.nav_view)
        val headerView = navigationView1.getHeaderView(0)
        val textViewUsuario = headerView.findViewById<TextView>(R.id.phnusuario)
        val correo=headerView.findViewById<TextView>(R.id.tipocuenta)
        val fotousuario=headerView.findViewById<CircleImageView>(R.id.phomagen)
        if(tipousuario==0){
            correo.text=getString(R.string.correouser)
            fotousuario.setImageResource(R.drawable.correoph)
        }else if(tipousuario==1){
            correo.text=getString(R.string.correogoogle)
            fotousuario.setImageResource(R.drawable.cgoogleplaceholder)
        }else if(tipousuario==2){
            correo.text=getString(R.string.anonimo)
            fotousuario.setImageResource(R.drawable.ano)
        }
        textViewUsuario.text = usuario2

        bottomNavView = findViewById(R.id.bottom_navigation_view)
        bottomNavView.setOnNavigationItemSelectedListener { menuItem ->

            when (menuItem.itemId) {
                R.id.movies -> showMoviesFragment(usuario2.toString(),tipousuario)
                R.id.shows->showTvShowsFragment(usuario2.toString(),tipousuario)
                R.id.watchlist -> if(tipousuario==2){
                    Toast.makeText(this,"Inicia Sesion Primero",Toast.LENGTH_LONG).show()
                }else{
                    showWatchListFragment()
                }

                R.id.reseñas->if(tipousuario==2){
                    Toast.makeText(this,"Inicia Sesion Primero",Toast.LENGTH_LONG).show()
                }else{
                    showReviewFragment()
                }
                R.id.chats-> showChatFragment(usuario2.toString())
            }
            return@setOnNavigationItemSelectedListener true
        }
        showMoviesFragment(usuario2.toString(),tipousuario)


        drawerLayout=findViewById(R.id.drawer_layout)
        val toolbar=findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navigationView=findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        val toggle=ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.open_nav,R.string.close_nav)
        drawerLayout.addDrawerListener(toggle)

        toggle.syncState()

       //if(savedInstanceState==null){
       //    supportFragmentManager.beginTransaction()
       //        .replace(R.id.fragment_container,HomeFragment()).commit()
       //    navigationView.setCheckedItem(R.id.nav_home)
       //}

    }
    private fun showMoviesFragment(usuario: String,tipousuario: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = supportFragmentManager.findFragmentByTag(MOVIES_FRAGMENT)
        val tvShowsFragment = supportFragmentManager.findFragmentByTag(TV_SHOWS_FRAGMENT)
        val watchListFragment = supportFragmentManager.findFragmentByTag(WATCH_LIST_FRAGMENT)
        val reviewFragment = supportFragmentManager.findFragmentByTag(REVIEW_FRAGMENT)
        val chatFragment = supportFragmentManager.findFragmentByTag(CHAT_FRAGMENT)
        chatFragment?.let { transaction.hide(it) }
        tvShowsFragment?.let { transaction.hide(it) }
        watchListFragment?.let { transaction.hide(it) }
        reviewFragment?.let { transaction.hide(it) }

        val moviesFragment = if (fragment == null) {
            val fragment = PeliculasFragment()
            fragment.arguments = Bundle().apply {
                putString("usuario", usuario)
                putInt("tipousuario", tipousuario)
            }
            fragment
        } else {
            fragment
        }

        transaction.replace(R.id.fragment_container, moviesFragment, MOVIES_FRAGMENT)
        transaction.commit()
    }




    private fun showTvShowsFragment(usuario: String,tipousuario: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = supportFragmentManager.findFragmentByTag(TV_SHOWS_FRAGMENT)
        val moviesFragment = supportFragmentManager.findFragmentByTag(MOVIES_FRAGMENT)
        val watchListFragment = supportFragmentManager.findFragmentByTag(WATCH_LIST_FRAGMENT)
        val reviewFragment = supportFragmentManager.findFragmentByTag(REVIEW_FRAGMENT)
        val chatFragment = supportFragmentManager.findFragmentByTag(CHAT_FRAGMENT)
        chatFragment?.let { transaction.hide(it) }
        moviesFragment?.let { transaction.hide(it) }
        watchListFragment?.let { transaction.hide(it) }
        reviewFragment?.let { transaction.hide(it) }

        val tvShowsFragment = if (fragment == null) {
            val fragment = TvShowsFragment()
            fragment.arguments = Bundle().apply {
                putString("usuario", usuario)
                putInt("tipousuario",tipousuario)
            }
            fragment
        } else {
            fragment
        }

        transaction.replace(R.id.fragment_container, tvShowsFragment, TV_SHOWS_FRAGMENT)
        transaction.commit()
    }


    private fun showWatchListFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = supportFragmentManager.findFragmentByTag(WATCH_LIST_FRAGMENT)
        val moviesFragment = supportFragmentManager.findFragmentByTag(MOVIES_FRAGMENT)
        val tvShowsFragment = supportFragmentManager.findFragmentByTag(TV_SHOWS_FRAGMENT)
        val reviewFragment=supportFragmentManager.findFragmentByTag(REVIEW_FRAGMENT)
        val chatFragment=supportFragmentManager.findFragmentByTag(CHAT_FRAGMENT)
        chatFragment?.let { transaction.hide(it) }
        moviesFragment?.let { transaction.hide(it) }
        tvShowsFragment?.let { transaction.hide(it) }
        reviewFragment?.let { transaction.hide(it) }

        if (fragment == null) {
            transaction.add(R.id.fragment_container, WatchListFragment(), WATCH_LIST_FRAGMENT)
        } else {
            transaction.show(fragment)
        }
        transaction.commit()
    }

    private fun showReviewFragment(){
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = supportFragmentManager.findFragmentByTag(REVIEW_FRAGMENT)
        val moviesFragment = supportFragmentManager.findFragmentByTag(MOVIES_FRAGMENT)
        val tvShowsFragment = supportFragmentManager.findFragmentByTag(TV_SHOWS_FRAGMENT)
        val watchListFragment=supportFragmentManager.findFragmentByTag(WATCH_LIST_FRAGMENT)
        val chatFragment=supportFragmentManager.findFragmentByTag(CHAT_FRAGMENT)
        chatFragment?.let { transaction.hide(it) }
        moviesFragment?.let { transaction.hide(it) }
        tvShowsFragment?.let { transaction.hide(it) }
        watchListFragment?.let { transaction.hide(it) }

        if (fragment == null) {
            transaction.add(R.id.fragment_container, Review_Fragment(), REVIEW_FRAGMENT)
        } else {
            transaction.show(fragment)
        }
        transaction.commit()
    }


    private fun showChatFragment(usuario: String) {
        if(intent.getStringExtra("user") == null){
            Toast.makeText(this,"Inicia Sesion Primero",Toast.LENGTH_LONG).show()
        }else{
            val transaction = supportFragmentManager.beginTransaction()
            val fragment = supportFragmentManager.findFragmentByTag(CHAT_FRAGMENT)
            val moviesFragment = supportFragmentManager.findFragmentByTag(MOVIES_FRAGMENT)
            val tvShowsFragment = supportFragmentManager.findFragmentByTag(TV_SHOWS_FRAGMENT)
            val watchListFragment = supportFragmentManager.findFragmentByTag(WATCH_LIST_FRAGMENT)
            val reviewFriendsFragment = supportFragmentManager.findFragmentByTag(REVIEW_FRAGMENT)
            reviewFriendsFragment?.let { transaction.hide(it) }
            moviesFragment?.let { transaction.hide(it) }
            tvShowsFragment?.let { transaction.hide(it) }
            watchListFragment?.let { transaction.hide(it) }

            if (fragment == null) {
                val chatFragment = ListOfChatsFragment()
                val bundle = Bundle()
                bundle.putString("user", usuario) // Establecer el valor en el Bundle
                chatFragment.arguments = bundle // Pasar el Bundle al fragmento
                transaction.add(R.id.fragment_container, chatFragment, CHAT_FRAGMENT)
            } else {
                val intent = Intent(this, ListOfChatsFragment::class.java)
                intent.putExtra("user", usuario) // Establecer el valor en el intent
                transaction.show(fragment)
            }
            transaction.commit()
        }

    }




    @SuppressLint("MissingInflatedId")
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        val shareBody = "Descarga mi aplicación aquí: https://drive.google.com/file/d/1eIBspp5IaBka_bIfKM2SEsZZdfIW5rFg/view?usp=share_link"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "¡Descarga mi aplicación!")
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody)

        when (item.itemId) {
            R.id.nav_home ->   if(intent.getStringExtra("user") == null){
                Toast.makeText(this,"Inicia Sesion Primero",Toast.LENGTH_LONG).show()
            }else{
                val bundle = Bundle()
                bundle.putString("usuario", intent.getStringExtra("user"))
                bundle.putInt("tipousuario",intent.getIntExtra("tipousuario",0))
                val homeFragment = HomeFragment()
                homeFragment.arguments = bundle
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, homeFragment).commit()
            }
            R.id.nav_settings ->   if(intent.getStringExtra("user") == null){
                Toast.makeText(this,"Inicia Sesion Primero",Toast.LENGTH_LONG).show()
            }else{
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, SettingsFragment()).commit()
            }
            R.id.nav_share -> startActivity(Intent.createChooser(shareIntent, "Compartir a través de"))
            R.id.nav_about -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, AboutUsFragment()).commit()
            R.id.nav_find -> {
                val searchDialog = AlertDialog.Builder(this)
                val dialogView = layoutInflater.inflate(R.layout.dialog_search, null)
                val searchView = dialogView.findViewById<EditText>(R.id.search_view)
                val radioGroup = dialogView.findViewById<RadioGroup>(R.id.radio_group)
                val movieRadioButton = dialogView.findViewById<RadioButton>(R.id.radio_movie)
                val seriesRadioButton = dialogView.findViewById<RadioButton>(R.id.radio_series)

                searchDialog.setTitle("Buscar película o serie")
                searchDialog.setView(dialogView)
                searchDialog.setPositiveButton("Buscar") { _, _ ->
                    val query = searchView.text.toString().toLowerCase() // Convertir a minúsculas
                    val selectedId = radioGroup.checkedRadioButtonId
                    if (selectedId == movieRadioButton.id) {
                        searchMovie(query,intent.getStringExtra("user").toString(),intent.getIntExtra("tipousuario",0))
                    } else if (selectedId == seriesRadioButton.id) {
                        searchSeries(query,intent.getStringExtra("user").toString(),intent.getIntExtra("tipousuario",0))
                    }
                }
                searchDialog.setNegativeButton("Cancelar", null)
                searchDialog.show()
            }
            R.id.nav_logout -> logout()

        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }



    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }
    private fun searchMovie(query: String,usuario: String,tipousuario:Int) {
        val apiKey = "54e87eee285a99614f18a228f764e2ea"
        val language = "es"
        val searchUrl = "https://api.themoviedb.org/3/search/movie?api_key=$apiKey&query=$query&language=$language"

        val request = JsonObjectRequest(
            Request.Method.GET, searchUrl, null,
            { response ->
                val results = response.getJSONArray("results")
                if (results.length() > 0) {
                    val movie = results.getJSONObject(0)
                    val movieId = movie.getLong("id")
                    val movieTitle = movie.getString("title")
                    val movieBackdrop = movie.getString("backdrop_path")
                    val moviePoster = movie.getString("poster_path")
                    val movieRating = movie.getDouble("vote_average").toFloat()
                    val movieReleaseDate = movie.getString("release_date")
                    val movieOverview = movie.getString("overview")
                    val movieLanguage = movie.getString("original_language")

                    val intent = Intent(this, DetallesPelicula::class.java)
                    intent.putExtra(MOVIE_ID, movieId)
                    intent.putExtra(MOVIE_TITLE, movieTitle)
                    intent.putExtra(MOVIE_BACKDROP, movieBackdrop)
                    intent.putExtra(MOVIE_POSTER, moviePoster)
                    intent.putExtra(MOVIE_RATING, movieRating)
                    intent.putExtra(MOVIE_RELEASE_DATE, movieReleaseDate)
                    intent.putExtra(MOVIE_OVERVIEW, movieOverview)
                    intent.putExtra(MOVIE_LANGUAGE, movieLanguage)
                    intent.putExtra("usuario",usuario)
                    intent.putExtra("tipousuario",tipousuario)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "No se encontraron resultados", Toast.LENGTH_SHORT).show()
                }
            },
            { error ->
                Toast.makeText(this, "Error en la búsqueda de películas: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        )

        Volley.newRequestQueue(this).add(request)
    }


    private fun searchSeries(query: String,usuario: String,tipousuario: Int) {
        val apiKey = "54e87eee285a99614f18a228f764e2ea"
        val language = "es"
        val searchUrl = "https://api.themoviedb.org/3/search/tv?api_key=$apiKey&query=$query&language=$language"

        val request = JsonObjectRequest(Request.Method.GET, searchUrl, null,
            { response ->
                val results = response.getJSONArray("results")
                if (results.length() > 0) {
                    val series = results.getJSONObject(0)
                    val seriesId = series.getLong("id")
                    val seriesTitle = series.getString("name")
                    val seriesBackdrop = series.getString("backdrop_path")
                    val seriesPoster = series.getString("poster_path")
                    val seriesRating = series.getDouble("vote_average").toFloat()
                    val seriesReleaseDate = series.getString("first_air_date")
                    val seriesOverview = series.getString("overview")
                    val seriesLanguage = series.getString("original_language")
                    val intent = Intent(this, DetallesTvShow::class.java)

                    intent.putExtra(TV_SHOW_ID, seriesId)
                    intent.putExtra(TV_SHOW_TITLE, seriesTitle)
                    intent.putExtra(TV_SHOW_BACKDROP, seriesBackdrop)
                    intent.putExtra(TV_SHOW_POSTER, seriesPoster)
                    intent.putExtra(TV_SHOW_RATING, seriesRating)
                    intent.putExtra(TV_SHOW_RELEASE_DATE, seriesReleaseDate)
                    intent.putExtra(TV_SHOW_OVERVIEW, seriesOverview)
                    intent.putExtra(TV_SHOW_ORIGINALLANGUAGE, seriesLanguage)
                    intent.putExtra("user",usuario)
                    intent.putExtra("tipousuario",tipousuario)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "No se encontraron resultados", Toast.LENGTH_SHORT).show()
                }
            },
            { error ->
                Toast.makeText(this, "Error en la búsqueda de series: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        )

        Volley.newRequestQueue(this).add(request)
    }}