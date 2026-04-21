package com.example.bakeryapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity

// Cookie class
data class Cookie(
    val name: String,
    val softBaked: Boolean,
    val hasFilling: Boolean,
    val price: Double
)

// List of cookies
val cookies = listOf(
    Cookie("Chocolate Chip", false, false, 1.69),
    Cookie("Banana Walnut", true, false, 1.49),
    Cookie("Vanilla Creme", false, true, 1.59),
    Cookie("Chocolate Peanut Butter", false, true, 1.49),
    Cookie("Snickerdoodle", true, false, 1.39),
    Cookie("Blueberry Tart", true, true, 1.79),
    Cookie("Sugar and Sprinkles", false, false, 1.39)
)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val textView = TextView(this)
        textView.textSize = 16f
        setContentView(textView)

        // 1. Full formatted menu (map)
        val fullMenu = cookies.map {
            "${it.name} - $${it.price}"
        }

        // 2. Soft baked filter
        val softCookies = cookies.filter { it.softBaked }

        // 3. Grouped menu
        val groupedMenu = cookies.groupBy { it.softBaked }

        val crunchyCookies = groupedMenu[false] ?: emptyList()

        // 4. Total price (fold)
        val totalPrice = cookies.fold(0.0) { total, cookie ->
            total + cookie.price
        }

        // 5. Sorted alphabetically
        val alphabeticalMenu = cookies.sortedBy { it.name }

        // Build output text
        val output = StringBuilder()

        output.append("FULL MENU:\n")
        fullMenu.forEach {
            output.append("$it\n")
        }

        output.append("\nSOFT COOKIES:\n")
        softCookies.forEach {
            output.append("${it.name}\n")
        }

        output.append("\nCRUNCHY COOKIES:\n")
        crunchyCookies.forEach {
            output.append("${it.name}\n")
        }

        output.append("\nALPHABETICAL MENU:\n")
        alphabeticalMenu.forEach {
            output.append("${it.name}\n")
        }

        output.append("\nTOTAL PRICE: $${"%.2f".format(totalPrice)}")

        textView.text = output.toString()
    }
}