package com.samirmaciel.techquiz.view.challenge.quizMain

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.samirmaciel.techquiz.R
import com.samirmaciel.techquiz.databinding.FragmentQuizMainBinding


class QuizMainFragment : Fragment() {

    private var _binding: FragmentQuizMainBinding? = null
    private val binding: FragmentQuizMainBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizMainBinding.bind(inflater.inflate(R.layout.fragment_quiz_main, container, false))
        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}