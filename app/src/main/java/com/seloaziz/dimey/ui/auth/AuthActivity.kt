package com.seloaziz.dimey.ui.auth

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.seloaziz.dimey.R
import com.seloaziz.dimey.databinding.ActivityAuthBinding
import com.seloaziz.dimey.ui.auth.login.LoginFragment
import com.seloaziz.dimey.ui.auth.register.RegisterFragment
import com.seloaziz.dimey.utils.toggleVisibility

class AuthActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.LoginBtn.setOnClickListener {
            binding.welcomeTvAndBtn.toggleVisibility()
            startFragment(LoginFragment())
        }

        binding.RegisterBtn.setOnClickListener{
            binding.welcomeTvAndBtn.toggleVisibility()
            startFragment(RegisterFragment())
        }

    }


    private fun startFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.fragment_enter,
                R.anim.fragment_exit,
                R.anim.fragment_enter,
                R.anim.fragment_exit
            )
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
            Handler(Looper.getMainLooper()).postDelayed({
                toggleWelcomeVisibility()
            }, 300)
        } else {
            super.onBackPressed()
        }
    }


        private fun toggleWelcomeVisibility() {
        binding.welcomeTvAndBtn.toggleVisibility()
    }
}