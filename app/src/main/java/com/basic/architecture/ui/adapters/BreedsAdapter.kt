package com.basic.architecture.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.basic.architecture.R
import com.basic.architecture.data.network.responses.BreedResponse
import com.basic.architecture.databinding.RcvBreedItemBinding
import com.basic.architecture.ui.interfaces.BreedsClicks
import com.basic.architecture.utils.setImage

class BreedsAdapter(
    private val breedList: ArrayList<BreedResponse.BreedResponseItem>,
    private val breedsClicks: BreedsClicks
) : RecyclerView.Adapter<BreedsAdapter.BreedsHolder>() {

    class BreedsHolder(val binding: RcvBreedItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BreedsHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.rcv_breed_item, parent, false
        )
    )

    override fun onBindViewHolder(holder: BreedsHolder, position: Int) {
        with(holder.binding) {
            setImage(imgBreed, breedList[position].image?.url)
            txtBreedName.text = breedList[position].name

            crdBreeds.setOnClickListener {
                breedsClicks.getBreedPosition(position)
            }
        }
    }

    override fun getItemCount(): Int = breedList.size
}
