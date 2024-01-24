package com.example.tokopaerbe.ui.auth

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.tokopaerbe.R
import com.example.tokopaerbe.databinding.FragmentProfileBinding
import com.example.tokopaerbe.helper.SnK
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFinish.setOnClickListener{
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
        }

        val profileImage = binding.imageContainer
        profileImage.setOnClickListener {
            showAlertDialog()
        }

        tColor()
        iniView()
    }

    private fun iniView(){
        binding.toolbar.title = getString(R.string.profile)
        binding.nameTextInput.hint = getString(R.string.name)
        binding.buttonFinish.text = getString(R.string.finish)
        binding.imageContainer.setImageResource(R.drawable.ic_person_outlined_white)
    }

    private fun showAlertDialog() {
        val DialogBuilder = context?.let { MaterialAlertDialogBuilder(it) }
        DialogBuilder?.setTitle(resources.getString(R.string.select_image))

        val DialogItems = arrayOf(getString(R.string.camera), getString(R.string.gallery))
        DialogBuilder?.setItems(DialogItems) {
            _, which ->
            when(which){
                0 -> camera()
                1 -> gallery()
            }
        }
        DialogBuilder?.show()
    }

    private fun camera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraLauncher.launch(intent)
    }

    private fun gallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type="image/*"
        galleryLauncher.launch(intent)
    }
    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val imageBitMap = data?.extras?.get("data") as? Bitmap
//            val result = BitmapFactory.decodeFile(data.path)
            binding.imageContainer.setImageBitmap(imageBitMap)
            binding.imageContainer.scaleType = ImageView.ScaleType.CENTER_CROP
        }
    }

    private val galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val data: Intent? = result.data
        val selectedImageUri = data?.data
        binding.imageContainer.setImageURI(selectedImageUri)
        binding.imageContainer.scaleType = ImageView.ScaleType.CENTER_CROP
    }

    fun tColor() {
        val sk = binding.syaratKetentuan
        val fullText = getString(R.string.term_condition_login)
        val defaultLocale = resources.configuration.locales[0].language
        sk.text = context?.let { SnK.applyCustomTextColor(defaultLocale, it, fullText) }
        sk.movementMethod = LinkMovementMethod.getInstance()
    }

    companion object {
        const val REQUEST_CAMERA = 123
        const val REQUEST_GALLERY = 456
    }
}
