package com.example.geliculon

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class AboutUsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_about_us,container,false)

        val facebookButton = view.findViewById<ImageView>(R.id.imageView3)
        facebookButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/profile.php?id=100092029153933"))
            startActivity(intent)

        }

        val twitterButton = view.findViewById<ImageView>(R.id.imageView4)
        twitterButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/Geliculon"))
            startActivity(intent)

        }

        val igbutton = view.findViewById<ImageView>(R.id.imageView13)
        igbutton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/geliculon/"))
            startActivity(intent)

        }

        view.findViewById<ImageView>(R.id.imageView5).setOnClickListener {
            val intent=Intent(Intent.ACTION_SENDTO).apply {
                data=Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf("asistenciageliculon@gmail.com"))
            }
            startActivity(intent)
        }

        val geliculon=view.findViewById<TextView>(R.id.textView16)
       geliculon.setOnClickListener {
           val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.geliculon.com/"))
           startActivity(intent)
        }


        view.findViewById<ImageView>(R.id.imageView6).setOnClickListener {
            val intent=Intent(Intent.ACTION_VIEW,Uri.parse("https://github.com/ElGelito"))
            startActivity(intent)
        }
        return view
    }


}