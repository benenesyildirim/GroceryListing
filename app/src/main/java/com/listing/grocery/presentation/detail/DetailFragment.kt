package com.listing.grocery.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.listing.grocery.R
import com.listing.grocery.common.Resource
import com.listing.grocery.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private val viewModel: DetailViewModel by viewModels()
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater)

        observeProduct()

        return binding.root
    }

    private fun observeProduct() {
        with(viewModel){
            productLiveData.observe(viewLifecycleOwner){response ->
                binding.product = response.data

                when(response){
                    is Resource.Loading -> {
                        if (response.data == null)
                            binding.detailLoading.visibility = VISIBLE
                        else
                            binding.detailLoading.visibility = GONE
                    }
                    is Resource.Success -> {
                        binding.detailLoading.visibility = GONE
                    }
                    is Resource.Error -> {
                        Toast.makeText(context, response.message ?: getString(R.string.went_wrong), Toast.LENGTH_SHORT).show()
                        binding.detailLoading.visibility = GONE
                    }
                }

            }
        }
    }
}