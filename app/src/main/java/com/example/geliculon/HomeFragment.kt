package com.example.geliculon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class HomeFragment : Fragment() {

    private var usuario: String? = null
    private var tipousuario: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            usuario = it.getString("usuario")
            tipousuario = it.getInt("tipousuario")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val username = usuario?.substringBefore("@")
        val textViewUsuario = view.findViewById<TextView>(R.id.textView15)
        val textViewUsuario2 = view.findViewById<TextView>(R.id.textView8)
        val textViewNombre = view.findViewById<TextView>(R.id.textView7)
        val textViewNombre2 = view.findViewById<TextView>(R.id.textView13)
        val fotoUsuario = view.findViewById<ImageView>(R.id.imageView7)

        textViewNombre.text = username
        textViewNombre2.text = username
        textViewUsuario?.text = usuario
        textViewUsuario2?.text = usuario

        when (tipousuario) {
            0 -> fotoUsuario.setImageResource(R.drawable.correoph)
            1 -> fotoUsuario.setImageResource(R.drawable.cgoogleplaceholder)
            2 -> fotoUsuario.setImageResource(R.drawable.ano)
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(usuario: String, tipousuario: Int) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString("usuario", usuario)
                    putInt("tipousuario", tipousuario)
                }
            }
    }
}
