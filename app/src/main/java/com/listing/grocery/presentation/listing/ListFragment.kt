package com.listing.grocery.presentation.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.listing.grocery.R
import com.listing.grocery.common.Constants.PRODUCT_ID
import com.listing.grocery.common.Resource
import com.listing.grocery.data.remote.dto.ProductDto
import com.listing.grocery.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private val viewModel: ListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(layoutInflater)

        observeProducts()

        return binding.root
    }

    private fun observeProducts() {
        with(viewModel) {
            productsLiveData.observe(viewLifecycleOwner) { response ->
                setProductList(response)

                when (response) {
                    is Resource.Loading -> {
                        if (response.data!!.isNotEmpty())
                            binding.listingLoading.visibility = GONE
                        else
                            binding.listingLoading.visibility = VISIBLE
                    }
                    is Resource.Success -> {
                        binding.listingLoading.visibility = GONE
                    }
                    is Resource.Error -> {
                        Toast.makeText(
                            context,
                            response.message ?: getString(R.string.went_wrong),
                            Toast.LENGTH_SHORT
                        ).show()

                        if (response.data!!.isNotEmpty())
                            binding.listingLoading.visibility = GONE
                    }
                }

            }
        }
    }

    private fun setProductList(response: Resource<List<ProductDto>>) {
        binding.productList.adapter = ProductListAdapter(response.data!!) {
            val bundle = bundleOf(PRODUCT_ID to it.product_id)
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_listFragment_to_detailFragment, bundle)
        }
    }
}