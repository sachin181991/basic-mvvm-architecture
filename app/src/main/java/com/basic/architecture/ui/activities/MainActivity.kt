package com.basic.architecture.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.basic.architecture.data.network.responses.BreedResponse
import com.basic.architecture.databinding.ActivityMainBinding
import com.basic.architecture.factory.MainActivityViewModelFactory
import com.basic.architecture.ui.adapters.BreedsAdapter
import com.basic.architecture.ui.interfaces.BreedsClicks
import com.basic.architecture.utils.Constants
import com.basic.architecture.utils.moveTo
import com.basic.architecture.utils.viewGone
import com.basic.architecture.utils.viewVisible
import com.basic.architecture.viewModels.MainActivityViewModel
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware {

    private val TAG = "MainActivity"

    override val kodein by kodein()
    private val factory: MainActivityViewModelFactory by instance()
    private var binding: ActivityMainBinding? = null
    private var viewModel: MainActivityViewModel? = null

    private val intListLimit = 10
    private var intPageCount = 0
    private var intItemLast = 1
    private var dataArray = ArrayList<BreedResponse.BreedResponseItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        init()
        setup()
        onClickListeners()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun init() {
        viewModel = ViewModelProvider(this, factory)[MainActivityViewModel::class.java]
        binding?.viewModel = viewModel

        setRcvBreeds()

        viewModel?.getBreedsResponse?.observe(this) {
            viewGone(binding?.llProgress)
            if (it != null) {
                setBreedData(it)
                intPageCount += 1
            } else {
                Log.d(TAG, "init: $it")
            }
        }
    }

    private fun setup() {
        retrofitGetBreedsCall()
    }

    private fun onClickListeners() {
        binding?.rcvBreeds?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val lastCompletelyVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                if (intItemLast != 0) {
                    if (lastCompletelyVisibleItemPosition + 1 == dataArray.size && binding?.llProgress?.visibility == View.GONE) {
                        retrofitGetBreedsCall()
                    }
                }
            }
        })

    }


    private fun setBreedData(breedResponse: BreedResponse) {
        val size = dataArray.size
        dataArray.addAll(breedResponse)
        val sizeNew = dataArray.size
        intItemLast = breedResponse.size
        binding?.rcvBreeds?.adapter?.notifyItemRangeChanged(size, sizeNew)
    }

    private fun retrofitGetBreedsCall() {
        viewVisible(binding?.llProgress)
        viewModel?.getBreeds(
            intPageCount.toString(),
            intListLimit.toString()
        )
    }

    private fun setRcvBreeds() {
        binding?.rcvBreeds?.layoutManager = GridLayoutManager(this, 2)
        binding?.rcvBreeds?.adapter = BreedsAdapter(dataArray, object : BreedsClicks {
            override fun getBreedPosition(position: Int) {
                moveTo(BreedDetailsActivity::class.java) {
                    putSerializable(
                        Constants.ConstantValues.BREED_DETAILS,
                        dataArray[position]
                    )
                }
            }
        })
    }
}
