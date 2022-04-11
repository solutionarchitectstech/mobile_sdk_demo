package tech.solutionarchitects.testapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import tech.solutionarchitects.advertisingsdk.core.InterstitialAdvertisement
import tech.solutionarchitects.testapplication.InitAdActivity.Companion.ENABLE_LOGS
import tech.solutionarchitects.testapplication.bannermodel.BannerModel
import tech.solutionarchitects.testapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private var interstitialAdvertisement: InterstitialAdvertisement? = null

    companion object {
        private const val ADVERTISEMENT_TYPE: String = "ADVERTISEMENT_TYPE"
        private const val BANNER_ONE: String = "BANNER_ONE"
        private const val BANNER_TWO: String = "BANNER_TWO"
        fun startMainActivity(
            context: Context,
            banner1: BannerModel,
            banner2: BannerModel?,
            adType: String,
            enableLogs: Boolean,
        ) {
            val intent: Intent =
                Intent(context, MainActivity::class.java).apply {
                    putExtra(ADVERTISEMENT_TYPE, adType)
                    putExtra(BANNER_ONE, banner1)
                    putExtra(BANNER_TWO, banner2)
                    putExtra(ENABLE_LOGS, enableLogs)
                }
            context.startActivity(intent)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mBinding.button.setOnClickListener {
            intent.getStringExtra(ADVERTISEMENT_TYPE).toString().let {
                val banner1 = intent.getParcelableExtra<BannerModel>(BANNER_ONE)
                val banner2 = intent.getParcelableExtra<BannerModel>(BANNER_TWO)
                when (it) {
                    "Interstitial banner" -> {
                        interstitialAdvertisement =
                            InterstitialAdvertisement(
                                this,
                                banner1?.placementId!!,
                                banner1.sizes,
                                banner1.timeout,
                                banner1.refresh,
                                banner1.closeButtonType,
                            )
                        interstitialAdvertisement?.load()
                    }
                    "Fix size banner" -> {

                        mBinding.bannerAd.initBanner(
                            banner1?.placementId!!,
                            banner1.sizes,
                            banner1.timeout,
                            banner1.refresh,
                            banner1.closeButtonType,
                        )
                        mBinding.bannerAd.load()
                    }
                    "2 Fix size banners" -> {
                        mBinding.bannerAd.initBanner(
                            banner1?.placementId!!,
                            banner1.sizes,
                            banner1.timeout,
                            banner1.refresh,
                            banner1.closeButtonType,
                        )
                        mBinding.bannerAd.load()

                        mBinding.bannerAd2.initBanner(
                            banner2?.placementId!!,
                            banner2.sizes,
                            banner2.timeout,
                            banner2.refresh,
                            banner2.closeButtonType,
                        )
                        mBinding.bannerAd2.load()
                        mBinding.button.visibility = View.GONE
                        mBinding.bLogActivity.visibility = View.GONE
                    }
                }
            }
        }
        intent.getBooleanExtra(ENABLE_LOGS, false).let {
            if (it) {
                mBinding.bLogActivity.setOnClickListener {
                    LogsActivity.startLogsActivity(this)
                }
            } else {
                mBinding.bLogActivity.visibility = View.GONE
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        interstitialAdvertisement?.onDestroy()
        mBinding.bannerAd.onDestroy()
    }
}