package life.coldsunny.bottomsheet

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

private const val KEY_NAME = "itemName"

private fun Intent.extra(itemName: String) = putExtra(KEY_NAME, itemName)

fun Context.startDetailActivity(itemName: String) =
    Intent(this, DetailActivity::class.java).apply { extra(itemName) }.let(this::startActivity)

class DetailActivity : AppCompatActivity() {
    private lateinit var mTvDetail: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        mTvDetail = findViewById(R.id.tv_detail)
        mTvDetail.text = intent.getStringExtra(KEY_NAME)
    }
}