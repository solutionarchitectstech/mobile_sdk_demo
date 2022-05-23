package tech.solutionarchitects.testapplication

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import tech.solutionarchitects.advertisingsdk.TechAdvertising
import tech.solutionarchitects.advertisingsdk.types.HashType
import tech.solutionarchitects.advertisingsdk.types.RequestType
import tech.solutionarchitects.testapplication.databinding.ActivityInitAdBinding

class InitAdActivity : AppCompatActivity() {
    companion object {
        const val ENABLE_LOGS = "ENABLE_LOGS"
    }

    private lateinit var mBinding: ActivityInitAdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityInitAdBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.requestType.adapter =
            ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, RequestType.values())
        mBinding.requestType.setSelection(1, false)
        mBinding.hashType.adapter =
            ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, HashType.values())
        mBinding.bmAddAd.setOnClickListener {
            try {
                TechAdvertising.init(
                    context = applicationContext,
                    storeUrl = mBinding.storeUrl.text.toString(),
                    apiKey = mBinding.apiKey.text.toString(),
                    apiSecret = mBinding.apiSecret.text.toString(),
                    partnerId = checkNullInt(mBinding.publisherId.text.toString()),
                    tagId = mBinding.tagId.text.toString(),
                    uid = mBinding.userId.text.toString(),
                    hashType = mBinding.hashType.selectedItem as HashType,
                    requestType = mBinding.requestType.selectedItem as RequestType,
                    enableExternalExceptionHandler = mBinding.shEnableExternalExceptionHandler.isChecked,
                    enableCompression = mBinding.shEnableCompression.isChecked,
                    baseUrl = mBinding.etBaseUrl.text.toString(),
                    postUrl = selectRequest(RequestType.POST),
                    getUrl = selectRequest(RequestType.GET),
                    enableLogsHandler = mBinding.shEnableLogs.isChecked,
                )
                AdBannerAdvertisingActivity.startActivity(this, mBinding.shEnableLogs.isChecked)
            } catch (e: Exception) {
                Log.e(AdBannerAdvertisingActivity::class.simpleName, "Incorrect data")
            }
        }
    }

    private fun selectRequest(type: RequestType): String? {
        if (type == mBinding.requestType.selectedItem) {
            return mBinding.etPostUrl.text.toString()
        }
        return null
    }

    private fun checkNullInt(string: String): Int {
        if (string == "") {
            return 0
        }
        return string.toInt()
    }
}