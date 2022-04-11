package tech.solutionarchitects.testapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import tech.solutionarchitects.advertisingsdk.core.model.Size
import tech.solutionarchitects.advertisingsdk.types.CloseButtonType
import tech.solutionarchitects.testapplication.InitAdActivity.Companion.ENABLE_LOGS
import tech.solutionarchitects.testapplication.bannermodel.BannerModel
import tech.solutionarchitects.testapplication.databinding.ActivityAddNewBannerBinding

class AdBannerAdvertisingActivity : AppCompatActivity() {
    companion object {
        private const val TWO_FIX_SIZE_BANNERS: String = "2 Fix size banners"

        fun startActivity(context: Context, enableLogs: Boolean) {
            Intent(context, AdBannerAdvertisingActivity::class.java).apply {
                putExtra(ENABLE_LOGS, enableLogs)
            }.let { context.startActivity(it) }
        }
    }

    private lateinit var mBinding: ActivityAddNewBannerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityAddNewBannerBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mBinding.srAdType.setSelection(0)
        mBinding.srAdType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (mBinding.srAdType.selectedItem == TWO_FIX_SIZE_BANNERS) {
                    mBinding.srCloseButtonType2.visibility = View.VISIBLE
                    mBinding.placementId2.visibility = View.VISIBLE
                    mBinding.tvExampleId2.visibility = View.VISIBLE
                    mBinding.timeout2.visibility = View.VISIBLE
                    mBinding.tvExampleTimeout2.visibility = View.VISIBLE
                    mBinding.refresh2.visibility = View.VISIBLE
                    mBinding.tvExampleRefresh2.visibility = View.VISIBLE
                    mBinding.sizes2.visibility = View.VISIBLE
                    mBinding.tvExampleSizes2.visibility = View.VISIBLE
                    mBinding.tvTitleBanner.visibility = View.VISIBLE
                    mBinding.dividedLine.visibility = View.VISIBLE
                } else {
                    mBinding.srCloseButtonType2.visibility = View.GONE
                    mBinding.placementId2.visibility = View.GONE
                    mBinding.tvExampleId2.visibility = View.GONE
                    mBinding.timeout2.visibility = View.GONE
                    mBinding.tvExampleTimeout2.visibility = View.GONE
                    mBinding.refresh2.visibility = View.GONE
                    mBinding.tvExampleRefresh2.visibility = View.GONE
                    mBinding.sizes2.visibility = View.GONE
                    mBinding.tvExampleSizes2.visibility = View.GONE
                    mBinding.tvTitleBanner.visibility = View.GONE
                    mBinding.dividedLine.visibility = View.GONE
                }
            }

        }

        mBinding.bmAddAd.setOnClickListener {
            val banner1 = BannerModel(
                checkNullInt(mBinding.refresh.text.toString()),
                checkNullInt(mBinding.timeout.text.toString()),
                checkNullInt(mBinding.placementId.text.toString()),
                CloseButtonType.valueOf(mBinding.srCloseButtonType.selectedItem.toString()),
                getListSizes(mBinding.sizes.text.toString())
            )
            val banner2 = BannerModel(
                checkNullInt(mBinding.refresh2.text.toString()),
                checkNullInt(mBinding.timeout2.text.toString()),
                checkNullInt(mBinding.placementId2.text.toString()),
                CloseButtonType.valueOf(mBinding.srCloseButtonType2.selectedItem.toString()),
                getListSizes(mBinding.sizes2.text.toString())
            )
            try {
                MainActivity.startMainActivity(
                    this,
                    banner1,
                    banner2,
                    mBinding.srAdType.selectedItem.toString(),
                    intent.getBooleanExtra(ENABLE_LOGS, false),
                )
            } catch (e: Exception) {
                Log.e(AdBannerAdvertisingActivity::class.simpleName, "Incorrect data", e)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun checkNullInt(string: String): Int {
        if (string == "") {
            return 0
        }
        return string.toInt()
    }

    private fun getListSizes(string: String): List<Size> {
        val arraySizes = string.split(";")
        val listSizes = mutableListOf<Size>()
        arraySizes.forEach {
            it.split("x").let { wh ->
                if (wh.size > 1) {
                    listSizes.add(Size(wh[0].toInt(), wh[1].toInt()))
                }
            }
        }
        return listSizes
    }
}