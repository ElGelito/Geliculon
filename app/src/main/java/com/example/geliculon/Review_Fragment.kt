package com.example.geliculon

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Review_Fragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var reviewAdapter: ReviewUsuarioAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_review_, container, false)
        recyclerView = view.findViewById(R.id.review_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        reviewAdapter = ReviewUsuarioAdapter(getUserReviews())
        recyclerView.adapter = reviewAdapter
        return view
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getUserReviews(): List<DetallesPelicula.Review> {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid
        val firestore = FirebaseFirestore.getInstance()

        val reviews = mutableListOf<DetallesPelicula.Review>()

        // Obtener las reseñas del usuario desde Firestore
        firestore.collection("reviews")
            .whereEqualTo("userId", userId)
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot.documents) {
                    val review = document.toObject(DetallesPelicula.Review::class.java)
                    if (review != null) {
                        reviews.add(review)
                    }
                }
                reviewAdapter.notifyDataSetChanged() // Notificar después de agregar los datos a la lista
            }
            .addOnFailureListener { exception ->
                // Manejar el error de lectura desde Firestore
                // Aquí puedes mostrar un mensaje de error o realizar alguna otra acción.
            }

        return reviews
    }

}
