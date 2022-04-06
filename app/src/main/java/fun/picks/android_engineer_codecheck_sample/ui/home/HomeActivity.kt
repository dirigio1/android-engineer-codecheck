package `fun`.picks.android_engineer_codecheck_sample.ui.home

import `fun`.picks.android_engineer_codecheck_sample.R
import `fun`.picks.android_engineer_codecheck_sample.databinding.ActivityHomeBinding
import `fun`.picks.android_engineer_codecheck_sample.ui.home.create.HomeCreateFragment
import `fun`.picks.android_engineer_codecheck_sample.ui.home.list.HomeListFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.IllegalStateException

class HomeActivity : AppCompatActivity(), ScreenTransition {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigationView()
    }

    private fun setupBottomNavigationView() {
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
        binding.bottomNavigationView.selectedItemId = R.id.list

        supportFragmentManager
            .beginTransaction()
            .replace(binding.fragmentContainer.id, HomeListFragment.newInstance())
            .commit()
    }
}

interface ScreenTransition {
    fun toHomeListFragment()
}