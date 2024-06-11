package com.example.button

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.prefs.Preferences

class MainActivity : AppCompatActivity()
{
    private lateinit var sharedPreferences: SharedPreferences
    private var clickCount = 0

    override fun onCreate(savedInstanceState: Bundle?)
    {
        /*onCreate() fonksiyonu, aktivitenin başlatıldığı zaman çağrılan bir yaşam döngüsü yöntemidir.
        Bu metot, aktivite başlatıldığında çalıştırılır.
        Bu durumda, super.onCreate(savedInstanceState) çağrısı,
        üst sınıfın onCreate() metodunu çağırır
        ve aktivitenin varsayılan davranışını gerçekleştirir.
        setContentView(R.layout.activity_main) çağrısı, bu aktivitenin kullanıcı arayüzünün hangi layout dosyasından
        yükleneceğini belirtir. R.layout.activity_main ifadesi,
        activity_main.xml dosyasının kaynak (resource) kimliğini ifade eder.*/
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        // Kayıtlı click sayısını yükle, yoksa varsayılan olarak 0 kullan
        clickCount = sharedPreferences.getInt("clickCount", 0)

        /* belirtilen ID'ye sahip bir buton bileşenini XML dosyasından bulur ve onu button adında bir değişkene atar.
         Böylece, bu değişken aracılığıyla buton nesnesine erişilebilir ve üzerinde işlemler yapılabilir.*/
        val button = findViewById<Button>(R.id.click_btn)
        val clickCountText = findViewById<TextView>(R.id.click_count_text)

        clickCountText.text = clickCount.toString()


        // Butona tıklanma olayını işleme eklemek için OnClickListener kullanılır
        button.setOnClickListener {
            clickCount++
            clickCountText.text = clickCount.toString()

            // SharedPreferences ile click sayısını kaydet
            sharedPreferences.edit().putInt("clickCount", clickCount).apply()

            Toast.makeText(this@MainActivity, "Click count = $clickCount", Toast.LENGTH_SHORT).show()
        }

    }
}
