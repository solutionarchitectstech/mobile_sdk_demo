package tech.solutionarchitects.testapplication

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import tech.solutionarchitects.advertisingsdk.TechAdvertising
import tech.solutionarchitects.advertisingsdk.types.HashType
import tech.solutionarchitects.advertisingsdk.types.RequestType
import tech.solutionarchitects.testapplication.databinding.ActivityInitAdBinding
import timber.log.Timber

class InitAdActivity : AppCompatActivity() {
    companion object {
        const val ENABLE_LOGS = "ENABLE_LOGS"
    }

    private lateinit var binding: ActivityInitAdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInitAdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.requestType.adapter =
            ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, RequestType.values())
        binding.requestType.setSelection(1, false)
        binding.hashType.adapter =
            ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, HashType.values())
        binding.bmAddAd.setOnClickListener {
            try {
                TechAdvertising.init(
                    context = applicationContext,
                    storeUrl = binding.storeUrl.text.toString(),
                    apiKey = binding.apiKey.text.toString(),
                    apiSecret = binding.apiSecret.text.toString(),
                    partnerId = binding.publisherId.text.toString(),
                    tagId = binding.tagId.text.toString(),
                    uid = binding.userId.text.toString(),
                    hashType = binding.hashType.selectedItem as HashType,
                    requestType = binding.requestType.selectedItem as RequestType,
                    enableExternalExceptionHandler = binding.shEnableExternalExceptionHandler.isChecked,
                    enableCompression = binding.shEnableCompression.isChecked,
                    baseUrl = binding.etBaseUrl.text.toString(),
                    postUrl = selectRequest(RequestType.POST),
                    getUrl = selectRequest(RequestType.GET),
                    trackingSdkBaseUrl = binding.trackingEndPointEditText.text.toString(),
                    enableLogsHandler = binding.shEnableLogs.isChecked,
                )
                AdBannerAdvertisingActivity.startActivity(this, binding.shEnableLogs.isChecked)
            } catch (e: Exception) {
                Timber.e("Incorrect data")
            }
        }
    }

    private fun selectRequest(type: RequestType): String? {
        if (type == binding.requestType.selectedItem) {
            return binding.etPostUrl.text.toString()
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