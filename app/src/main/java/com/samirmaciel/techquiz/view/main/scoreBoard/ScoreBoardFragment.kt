package com.samirmaciel.techquiz.view.main.scoreBoard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.samirmaciel.techquiz.R
import com.samirmaciel.techquiz.databinding.FragmentScoreBoardBinding


class ScoreBoardFragment : Fragment() {

    private var _binding: FragmentScoreBoardBinding? = null
    private val binding: FragmentScoreBoardBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScoreBoardBinding.bind(inflater.inflate(R.layout.fragment_score_board, container, false))
        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}