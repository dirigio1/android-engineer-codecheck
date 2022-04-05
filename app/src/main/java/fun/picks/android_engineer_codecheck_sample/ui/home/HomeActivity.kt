package `fun`.picks.android_engineer_codecheck_sample.ui.home

import `fun`.picks.android_engineer_codecheck_sample.R
import `fun`.picks.android_engineer_codecheck_sample.databinding.ActivityHomeBinding
import `fun`.picks.android_engineer_codecheck_sample.ui.home.create.HomeCreateFragment
import `fun`.picks.android_engineer_codecheck_sample.ui.home.list.HomeListFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.IllegalStateException

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigationView(binding)
    }

    private fun setupBottomNavigationView(binding: ActivityHomeBinding) {
        binding.bottomNavigationView.setOnItemSelectedListener {
            val nextFragment = when (it.itemId) {
                R.id.list -> HomeListFragment.newInstance()
                R.id.create -> HomeCreateFragment.newInstance()
                else -> throw IllegalStateException()
            }

            supportFragmentManager
                .beginTransaction()
                .replace(binding.fragmentContainer.id, nextFragment)
                .commit()

            return@setOnItemSelectedListener true
        }
    }
}