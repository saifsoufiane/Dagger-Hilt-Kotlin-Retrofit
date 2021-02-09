package com.sfs.mytemplate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.sfs.mytemplate.databinding.ActivityMainBinding
import com.sfs.mytemplate.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.hello.setOnClickListener {
            viewModel.convert(
                "100",
                "EUR",
                "USD"
            )
        }
        subscribedata()
    }

    private fun subscribedata() {
     lifecycleScope.launchWhenCreated {
         viewModel.conversion.collect { event->
             when(event){
                 is MainViewModel.CurrencyEvent.Success -> {
                     Timber.d(event.resultText)
                     binding.hello.text = event.resultText
                 }
                 is MainViewModel
                 .CurrencyEvent.Failure -> {
                     Timber.e(event.errorText)
                 }
                 else -> Unit
             }
         }
     }
    }
}