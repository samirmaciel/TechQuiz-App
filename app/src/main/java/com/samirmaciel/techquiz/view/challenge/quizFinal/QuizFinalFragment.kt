package com.samirmaciel.techquiz.view.challenge.quizFinal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.samirmaciel.techquiz.R
import com.samirmaciel.techquiz.databinding.FragmentQuizFinalBinding


class QuizFinalFragment : Fragment() {

    private var _binding: FragmentQuizFinalBinding? = null
    private val binding: FragmentQuizFinalBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizFinalBinding.bind(inflater.inflate(R.layout.fragment_quiz_final, container, false))
        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}