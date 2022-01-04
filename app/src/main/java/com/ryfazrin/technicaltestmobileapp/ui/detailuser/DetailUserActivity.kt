package com.ryfazrin.technicaltestmobileapp.ui.detailuser

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ryfazrin.technicaltestmobileapp.data.DetailUserResponse
import com.ryfazrin.technicaltestmobileapp.databinding.ActivityDetailUserBinding
import com.ryfazrin.technicaltestmobileapp.ui.detailpost.DetailPostActivity

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val getUser: DetailUserResponse = intent.getParcelableExtra<DetailUserResponse>(
            EXTRA_DETAIL_USER
        ) as DetailUserResponse

        binding.tvUserName.text = getUser.name
        binding.tvUserEmail.text = getUser.email
        val address = getUser.address
        binding.tvUserAddress.text = "${address.street} ${address.suite}, ${address.city}, ${address.zipcode}"
        val company = getUser.company
        binding.tvUserCompanyName.text = company.name

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        var EXTRA_DETAIL_USER = "extra_detail_user"
    }
}