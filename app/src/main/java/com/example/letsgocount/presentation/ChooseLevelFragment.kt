package com.example.letsgocount.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.letsgocount.R
import com.example.letsgocount.databinding.FragmentChooseLevelBinding
import com.example.letsgocount.domain.entities.Level
import com.example.letsgocount.presentation.GameFragment.Companion.GAME_FRAGMENT_NAME


class ChooseLevelFragment : Fragment() {


    private var _binding: FragmentChooseLevelBinding? = null
    private val binding: FragmentChooseLevelBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseLevel == null")


    private fun setUpButtons() {

        //разница между with и apply в том что with возвращает что-то а apply нет
        //val some = with(object){.add(3) /n this}  this - к примеру коллекция обьект-приёмник

        binding.apply {
            buttonTestLevel.setOnClickListener {
                launchGameFragment(Level.TEST)
            }

            buttonEasyLevel.setOnClickListener {
                launchGameFragment(Level.EASY)
            }

            buttonNormalLevel.setOnClickListener {
                launchGameFragment(Level.NORMAL)
            }

            buttonHardLevel.setOnClickListener {
                launchGameFragment(Level.HARD)
            }
        }
    }


    private fun launchGameFragment(level: Level) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, GameFragment.newInstanceGameFragment(level))
            .addToBackStack(GAME_FRAGMENT_NAME)
            .commit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentChooseLevelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpButtons()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        const val CHOOSE_LEVEL_FRAGMENT = "clFragment"

        fun newInstance(): ChooseLevelFragment {
            return ChooseLevelFragment()

        }

    }

}