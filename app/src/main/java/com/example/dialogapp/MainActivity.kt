package com.example.dialogapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toppingsList = arrayOf("Onion", "Capsicum", "Tomato")

        //***************Yes No Dialog***************
        val deleteDialog = MaterialAlertDialogBuilder(this)
            .setIcon(R.drawable.ic_baseline_delete_24)
            .setTitle("Delete Toppings")
            .setMessage("Do you really want to delete your toppings")
            .setPositiveButton("Okay") { _,_ ->
                toppings_text_view.text = ""
            }
            .setNegativeButton("Cancel",null)

        delete_toppings.setOnClickListener {
            if(toppings_text_view.text.isNotEmpty())
                deleteDialog.show()
        }

        //***************Radio Button Dialog***************
        val radioButtonToppingsDialog = MaterialAlertDialogBuilder(this)
            .setIcon(R.drawable.ic_baseline_restaurant_menu_24)
            .setTitle("Pick Topping")
            .setSingleChoiceItems(toppingsList,0) { _, checkedIndex ->
                toppings_text_view.text = "Your toppings is ${toppingsList[checkedIndex]}"
            }
            .setPositiveButton("Okay",null)
            .setNegativeButton("Cancel") { _, _ ->
                toppings_text_view.text = ""
            }

        one_topping.setOnClickListener {
            toppings_text_view.text = "Your toppings is ${toppingsList[0]}"
            radioButtonToppingsDialog.show()
        }

        //***************Check Box Dialog***************
        var selectedToppingsList = mutableListOf<String>()
        val checkBoxToppingsDialog = MaterialAlertDialogBuilder(this)
            .setIcon(R.drawable.ic_baseline_restaurant_menu_24)
            .setTitle("Pick Topping")
            .setCancelable(false)
            .setMultiChoiceItems(toppingsList, null ) { _, which, isChecked ->
                if (isChecked) {
                    selectedToppingsList.add(toppingsList[which])
                } else if (selectedToppingsList.contains(toppingsList[which])) {
                    selectedToppingsList.remove(toppingsList[which])
                }
            }
            .setPositiveButton("Okay") { _, _ ->
                if(selectedToppingsList.isNotEmpty()) {
                    var toppingString = "Your Toppings :-"
                    selectedToppingsList.forEach {
                        toppingString += "\n$it"
                    }
                    toppings_text_view.text = toppingString
                    selectedToppingsList = mutableListOf()
                } else {
                    toppings_text_view.text = ""
                }
            }
            .setNegativeButton("Cancel") { _, _ ->
                toppings_text_view.text = ""
                selectedToppingsList = mutableListOf()
            }

        multiple_topping.setOnClickListener {
            checkBoxToppingsDialog.show()
        }
    }
}