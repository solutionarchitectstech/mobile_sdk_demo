package tech.solutionarchitects.testapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import tech.solutionarchitects.testapplication.databinding.ActivityLogsBinding

class LogsActivity : AppCompatActivity() {

    companion object {
        fun startLogsActivity(context: Context) {
            val intent =
                Intent(context, LogsActivity::class.java)
            context.startActivity(intent)
        }

        private const val APP_PREFERENCES = "APP_PREFERENCES"
        private const val APP_LOGS = "APP_LOGS"

    }

    private lateinit var mBinding: ActivityLogsBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityLogsBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mBinding.tvLogs.movementMethod = ScrollingMovementMethod()
        sharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE).apply {
            if (contains(APP_LOGS)) {
                mBinding.tvLogs.text = getString(APP_LOGS, "")
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}