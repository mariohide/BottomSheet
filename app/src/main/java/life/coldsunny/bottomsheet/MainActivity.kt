package life.coldsunny.bottomsheet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    lateinit var mBottomSheetBehavior: BottomSheetBehavior<FrameLayout>
    lateinit var mLlSheet: LinearLayout
    lateinit var mFlSheet: FrameLayout
    lateinit var mButton: Button
    lateinit var mRecyclerView: RecyclerView
    lateinit var mHeaderImg: ImageView
    var dataList: MutableList<Bean> = mutableListOf(Bean("Adam", "He is a good man"))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mLlSheet = findViewById(R.id.ll_sheet)
        mButton = findViewById(R.id.button)
        mRecyclerView = findViewById(R.id.recyclerView)
        mHeaderImg = findViewById(R.id.headerImg)
        mFlSheet = findViewById(R.id.fl_sheet)

        mRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = MyAdapter(dataList)
        mRecyclerView.adapter = adapter

        mBottomSheetBehavior = BottomSheetBehavior.from(mFlSheet)
        mBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        mBottomSheetBehavior.addBottomSheetCallback(object : BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                Log.d(TAG, "onSlide: $slideOffset")
                if (slideOffset < 0) return
                val lp: LinearLayout.LayoutParams =
                    mHeaderImg.layoutParams as LinearLayout.LayoutParams

                lp.bottomMargin = ((1.minus(slideOffset)) * lp.height).toInt() * -1
                if (lp.bottomMargin > TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        -190f,
                        resources.displayMetrics
                    )
                ) {
                    mHeaderImg.layoutParams = lp
                }
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {

            }
        })

        mButton.setOnClickListener { v ->
            if (mBottomSheetBehavior.state == BottomSheetBehavior.STATE_HIDDEN) {
                mBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }

        for (i in 1..10) {
            dataList.add(Bean("Adam$i", "He is a good man$i"))
        }
        adapter.notifyDataSetChanged()
    }
}