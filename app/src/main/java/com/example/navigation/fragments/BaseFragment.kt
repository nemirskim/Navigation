package com.example.navigation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

open class BaseFragment(private val fragment: String, @LayoutRes contentLayoutId: Int) :
    Fragment(contentLayoutId) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Logs", "$fragment's onCreate() is called")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("Logs", "$fragment's onCreateView() is called")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Logs", "$fragment's onViewCreated() is called")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("Logs", "$fragment's onDestroyView() is called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Logs", "$fragment's onDestroy() is called")
    }
}