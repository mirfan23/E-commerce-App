package com.example.tokopaerbe.view.auth

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.media.MediaScannerConnection
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.tokopaerbe.R
import com.example.tokopaerbe.databinding.FragmentProfileBinding
import com.example.tokopaerbe.helper.Constant.ALERT_MESSAGE
import com.example.tokopaerbe.helper.Constant.ALERT_TITLE
import com.example.tokopaerbe.helper.Constant.CAMERA_PERMISSION_REQUEST_CODE
import com.example.tokopaerbe.helper.Constant.DATE_FORMAT
import com.example.tokopaerbe.helper.Constant.EXTRAS_DATA
import com.example.tokopaerbe.helper.Constant.INTENT_TYPE
import com.example.tokopaerbe.helper.Constant.MIME_TYPE
import com.example.tokopaerbe.helper.Constant.NEGATIVE_BUTTON_TEXT
import com.example.tokopaerbe.helper.Constant.READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE
import com.example.tokopaerbe.helper.SnK
import com.example.tokopaerbe.viewmodel.PreLoginViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val viewModel: PreLoginViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val profileImage = binding.imageContainer
        profileImage.setOnClickListener {
            showAlertDialog()
        }
        syaratKetentuan()
        initView()
        initListener()
    }

    private fun initListener() {
        binding.buttonFinish.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_dashboardFragment)
        }
    }

//    private fun initObserver() = with(viewModel) {
//        val username = binding.nameEditText.text.toString().trim()
//        val userImage = (binding.imageContainer.drawable as? BitmapDrawable)?.bitmap
//
//        if (username.isNotEmpty() && userImage != null) {
//            val requestBody = RequestBody.create("multipart/from-data".toMediaTypeOrNull(), username)
//            val userImagePart = MultipartBody.Part.createFromData
//        }
//
//    }

    private fun initView() {
        binding.toolbar.title = getString(R.string.profile)
        binding.nameTextInput.hint = getString(R.string.name)
        binding.buttonFinish.text = getString(R.string.finish)
        binding.imageContainer.setImageResource(R.drawable.ic_person_outlined_white)
    }

    private fun showAlertDialog() {
        val DialogBuilder = context?.let { MaterialAlertDialogBuilder(it) }
        DialogBuilder?.setTitle(resources.getString(R.string.select_image))

        val DialogItems = arrayOf(getString(R.string.camera), getString(R.string.gallery))
        DialogBuilder?.setItems(DialogItems) { _, which ->
            when (which) {
                0 -> camera()
                1 -> gallery()
            }
        }
        DialogBuilder?.show()
    }

    private fun camera() {
        if (context?.let {
                ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.CAMERA
                )
            } == PackageManager.PERMISSION_GRANTED
        ) {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            cameraLauncher.launch(intent)
        } else {
            activity?.let {
               requestPermissions(
                    arrayOf(Manifest.permission.CAMERA),
                    CAMERA_PERMISSION_REQUEST_CODE
                )
            }
        }
    }

    private fun gallery() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
            if (context?.let {
                    ContextCompat.checkSelfPermission(
                        it,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                } == PackageManager.PERMISSION_GRANTED
            ) {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = INTENT_TYPE
                galleryLauncher.launch(intent)
            } else {
                activity?.let {
                    requestPermissions(
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE
                    )
                }
            }
        } else {
            requestMediaImagesPermission()
        }
    }

    private fun requestMediaImagesPermission() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_MEDIA_IMAGES
            ) == PackageManager.PERMISSION_GRANTED -> {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = INTENT_TYPE
                galleryLauncher.launch(intent)
            }

            shouldShowRequestPermissionRationale(Manifest.permission.READ_MEDIA_IMAGES) -> {
                showPermissionExplanationDialog()
            }

            else -> {
                requestPermission.launch(Manifest.permission.READ_MEDIA_IMAGES)
            }
        }
    }

    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = INTENT_TYPE
                galleryLauncher.launch(intent)
            } else {
                Toast.makeText(
                    requireContext(),
                    NEGATIVE_BUTTON_TEXT,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    private fun showPermissionExplanationDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(ALERT_TITLE)
            .setMessage(ALERT_MESSAGE)
            .setPositiveButton("Grant") { _, _ ->
                // Request the permission
                requestPermission.launch(Manifest.permission.READ_MEDIA_IMAGES)
            }
            .setNegativeButton("Deny") { _, _ ->
                Toast.makeText(
                    requireContext(),
                    NEGATIVE_BUTTON_TEXT,
                    Toast.LENGTH_SHORT
                ).show()
            }
            .show()
    }


    private val cameraLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val imageBitMap = data?.extras?.get(EXTRAS_DATA) as? Bitmap
                binding.imageContainer.setImageBitmap(imageBitMap)
                binding.imageContainer.scaleType = ImageView.ScaleType.CENTER_CROP

                context?.let { saveImageToInternalStorage(it, imageBitMap!!) }
            }
        }

    private val galleryLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val data: Intent? = result.data
            val selectedImageUri = data?.data
            binding.imageContainer.setImageURI(selectedImageUri)
            binding.imageContainer.scaleType = ImageView.ScaleType.CENTER_CROP
        }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            CAMERA_PERMISSION_REQUEST_CODE -> {
                handlePermissionResult(grantResults) {
                    camera()

                }
            }

            READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE -> {
                handlePermissionResult(grantResults) {
                    gallery()
                }
            }
        }
    }

    private fun handlePermissionResult(
        grantResults: IntArray,
        onPermissionGranted: () -> Unit
    ) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            onPermissionGranted.invoke()
        } else {
            Toast.makeText(
                requireContext(),
                NEGATIVE_BUTTON_TEXT,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun saveImageToInternalStorage(context: Context, bitmap: Bitmap): Boolean {
        val timeStamp = SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(Date())
        val displayName = "image_$timeStamp.jpg"

        val directory = Environment.DIRECTORY_PICTURES

        // Buat value yang akan di masukkan ke dalam detail gambar
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, displayName)
            put(MediaStore.Images.Media.MIME_TYPE, MIME_TYPE)
            put(MediaStore.Images.Media.RELATIVE_PATH, directory)
        }

        // Gunakan content resolver untuk memasukkan detail gambar
        val resolver = context.contentResolver
        val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        return try {
            val file = File(uri?.path ?: "")
            uri?.let { contentUri ->
                resolver.openOutputStream(contentUri)?.use { outputStream ->
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                    MediaScannerConnection.scanFile(
                        context, arrayOf(file.absolutePath),
                        null
                    ) { _, _ -> }
                    true
                } ?: false
            } ?: false
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun syaratKetentuan() {
        val sk = binding.syaratKetentuan
        val fullText = getString(R.string.term_condition_login)
        val defaultLocale = resources.configuration.locales[0].language
        sk.text = context?.let { SnK.applyCustomTextColor(defaultLocale, it, fullText) }
        sk.movementMethod = LinkMovementMethod.getInstance()
    }
}