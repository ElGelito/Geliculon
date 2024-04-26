package com.example.geliculon
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

import java.text.SimpleDateFormat
import java.util.*

class SettingsFragment : Fragment() {

    private lateinit var notificationSwitch: Switch
    private lateinit var soundCheckBox: CheckBox
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var datepickerButton: Button
    private lateinit var timepickerButton: Button
    private lateinit var selectedDateTextView: TextView
    private lateinit var selectedTimeTextView: TextView
    private lateinit var languageSpinner: Spinner
    private lateinit var volumeSeekBar: SeekBar
    private lateinit var colorPickerButton: Button
    private lateinit var selectedColorTextView: TextView
    private lateinit var saveButton: Button

    private val calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        notificationSwitch = view.findViewById(R.id.notificationSwitch)
        soundCheckBox = view.findViewById(R.id.soundCheckBox)
        usernameEditText = view.findViewById(R.id.usernameEditText)
        passwordEditText = view.findViewById(R.id.passwordEditText)
        datepickerButton = view.findViewById(R.id.datepickerButton)
        timepickerButton = view.findViewById(R.id.timepickerButton)
        selectedDateTextView = view.findViewById(R.id.selectedDateTextView)
        selectedTimeTextView = view.findViewById(R.id.selectedTimeTextView)
        languageSpinner = view.findViewById(R.id.languageSpinner)
        volumeSeekBar = view.findViewById(R.id.volumeSeekBar)
        colorPickerButton = view.findViewById(R.id.colorPickerButton)
        selectedColorTextView = view.findViewById(R.id.selectedColorTextView)
        saveButton = view.findViewById(R.id.saveButton)

        datepickerButton.setOnClickListener {
            showDatePicker()
        }

        timepickerButton.setOnClickListener {
            showTimePicker()
        }

        val languages = resources.getStringArray(R.array.languages)
        val languageAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, languages)
        languageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        languageSpinner.adapter = languageAdapter

        colorPickerButton.setOnClickListener {
            showColorPicker()
        }

        saveButton.setOnClickListener {
            val isNotificationsEnabled = notificationSwitch.isChecked
            val isSoundEnabled = soundCheckBox.isChecked
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            val selectedDate = selectedDateTextView.text.toString()
            val selectedTime = selectedTimeTextView.text.toString()
            val selectedLanguage = languageSpinner.selectedItem.toString()
            val selectedVolume = volumeSeekBar.progress
            val selectedColor = selectedColorTextView.text.toString()

            // Realizar acciones con los datos de configuración
            val message = buildSettingsMessage(
                isNotificationsEnabled,
                isSoundEnabled,
                username,
                password,
                selectedDate,
                selectedTime,
                selectedLanguage,
                selectedVolume,
                selectedColor
            )
            showToast(message)
        }

        return view
    }

    private fun showDatePicker() {
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val selectedDate = dateFormat.format(calendar.time)
                selectedDateTextView.text = selectedDate
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun showTimePicker() {
        val timePickerDialog = TimePickerDialog(
            requireContext(),
            { _, hourOfDay, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)
                val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
                val selectedTime = timeFormat.format(calendar.time)
                selectedTimeTextView.text = selectedTime
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        )
        timePickerDialog.show()
    }

    private fun showColorPicker() {
        val colors = arrayOf(
            Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW,
            Color.CYAN, Color.MAGENTA, Color.GRAY, Color.BLACK
        )

        val colorPickerDialog = AlertDialog.Builder(requireContext())
        colorPickerDialog.setTitle("Seleccionar color")

        val colorGridView = GridView(requireContext())
        colorGridView.numColumns = 4
        colorGridView.horizontalSpacing = 8
        colorGridView.verticalSpacing = 8

        val colorAdapter = ColorAdapter(colors)
        colorGridView.adapter = colorAdapter

        val selectedColor = IntArray(1)

        colorGridView.setOnItemClickListener { _, _, position, _ ->
            selectedColor[0] = colors[position]
        }

        colorPickerDialog.setView(colorGridView)
        colorPickerDialog.setNegativeButton("Cancelar", null)
        colorPickerDialog.setPositiveButton("Aceptar") { _, _ ->
            val colorHex = String.format("#%06X", 0xFFFFFF and selectedColor[0])
            selectedColorTextView.text = colorHex
        }
        colorPickerDialog.show()
    }

    private class ColorAdapter(private val colors: Array<Int>) : BaseAdapter() {
        override fun getCount(): Int {
            return colors.size
        }

        override fun getItem(position: Int): Any {
            return colors[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val colorView = View(parent?.context)
            colorView.setBackgroundColor(colors[position])
            val size = 100 // Tamaño deseado de los elementos de color en píxeles
            val layoutParams = AbsListView.LayoutParams(size, size)
            colorView.layoutParams = layoutParams
            return colorView
        }
    }







    private fun buildSettingsMessage(
        isNotificationsEnabled: Boolean,
        isSoundEnabled: Boolean,
        username: String,
        password: String,
        selectedDate: String,
        selectedTime: String,
        selectedLanguage: String,
        selectedVolume: Int,
        selectedColor: String
    ): String {
        val builder = StringBuilder()
        builder.append("Configuración guardada:\n")
        builder.append("Notificaciones: ").append(if (isNotificationsEnabled) "Habilitadas" else "Deshabilitadas").append("\n")
        builder.append("Sonido: ").append(if (isSoundEnabled) "Habilitado" else "Deshabilitado").append("\n")
        builder.append("Nombre de usuario: ").append(username).append("\n")
        builder.append("Contraseña: ").append(password).append("\n")
        builder.append("Fecha de nacimiento: ").append(selectedDate).append("\n")
        builder.append("Hora de recordatorio: ").append(selectedTime).append("\n")
        builder.append("Idioma: ").append(selectedLanguage).append("\n")
        builder.append("Volumen: ").append(selectedVolume).append("\n")
        builder.append("Color: ").append(selectedColor)
        return builder.toString()
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}
