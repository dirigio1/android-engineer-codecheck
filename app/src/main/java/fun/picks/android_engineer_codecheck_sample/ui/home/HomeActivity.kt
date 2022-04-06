package `fun`.picks.android_engineer_codecheck_sample.ui.home

import `fun`.picks.android_engineer_codecheck_sample.R
import `fun`.picks.android_engineer_codecheck_sample.databinding.ActivityHomeBinding
import `fun`.picks.android_engineer_codecheck_sample.ui.home.create.HomeCreateFragment
import `fun`.picks.android_engineer_codecheck_sample.ui.home.list.HomeListFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.lang.IllegalStateException

class HomeActivity : AppCompatActivity(), ScreenTransition {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigationView(binding)
    }

    private fun setupBottomNavigationView(binding: ActivityHomeBinding) {
        val primaryFragment = HomeListFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .add(binding.fragmentContainer.id, primaryFragment)
            .commit()

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

    override fun toHomeListFragment() {
        val bottomNavView: BottomNavigationView = findViewById(R.id.bottom_navigation_view)
        bottomNavView.selectedItemId = R.id.list

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, HomeListFragment.newInstance())
            .commit()
    }
}

interface ScreenTransition {
    fun toHomeListFragment()
}