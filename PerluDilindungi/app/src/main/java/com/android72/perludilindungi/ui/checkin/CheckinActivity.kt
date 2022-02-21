package com.android72.perludilindungi.ui.checkin

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.android72.perludilindungi.backend.api.RetrofitAPI
import com.android72.perludilindungi.backend.model.CheckinDataModal
import com.android72.perludilindungi.R
import com.android72.perludilindungi.backend.model.CheckinData
import com.android72.perludilindungi.databinding.ActivityCheckinBinding
import com.google.android.gms.location.*
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CheckinActivity : AppCompatActivity(), ZXingScannerView.ResultHandler, View.OnClickListener{
    private lateinit var binding: ActivityCheckinBinding
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val PERMISSIONLOCATIONID = 1010
    private var longitude = ""
    private var latitude = ""
    private lateinit var mScannerView: ZXingScannerView
    private fun checkPermission():Boolean{
        //this function will return a boolean
        //true: if we have permission
        //false if not
        if(
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ){
            return true
        }

        return false

    }
    private fun isLocationEnabled():Boolean{
        //this function will return to us the state of the location service
        //if the gps or the network provider is enabled then it will return true otherwise it will return false
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    private fun getLastLocation(){
        if(checkPermission()){
            if(isLocationEnabled()){
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return
                }
                fusedLocationProviderClient.lastLocation.addOnCompleteListener { task->
                    val location:Location? = task.result
                    if(location == null){
                        NewLocationData()
                    }else{
                        Log.d("Debug:" ,"Your Location:"+ location.longitude + ", Latitude:" + location.latitude)
                        //binding.qrLocation.text = "You Current Location is : Long: "+ location.longitude + " , Lat: " + location.latitude + "\n"
                        longitude = location.longitude.toString()
                        latitude = location.latitude.toString()
                        //binding.qrLocation.visibility = View.VISIBLE
                    }
                }
            }else{
                Toast.makeText(this,"Please Turn on Your device Location",Toast.LENGTH_SHORT).show()
            }
        }else{
            doRequestPermission()
        }
    }

    fun NewLocationData(){
        var locationRequest =  LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 0
        locationRequest.fastestInterval = 0
        locationRequest.numUpdates = 1
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        Looper.myLooper()?.let {
            fusedLocationProviderClient.requestLocationUpdates(
                locationRequest,locationCallback, it
            )
        }
    }


    private val locationCallback = object : LocationCallback(){
        override fun onLocationResult(locationResult: LocationResult) {
            val lastLocation: Location = locationResult.lastLocation
            Log.d("Debug:","your last last location: "+ lastLocation.longitude.toString())
        }
    }

    fun onClickBack(v: View) {
        super.onBackPressed() // or super.finish();
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckinBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
//        Log.d("Debug:",CheckPermission().toString())
//        Log.d("Debug:",isLocationEnabled().toString())
//        fusedLocationProviderClient.lastLocation.addOnSuccessListener{location: Location? ->
//            binding.qrLocation.text = location?.latitude.toString() + "," + location?.longitude.toString()
//        }
        initScannerView()
        initDefaultView()
    }

    private fun initScannerView() {
        mScannerView = ZXingScannerView(this)
        mScannerView.setAutoFocus(true)
        mScannerView.setResultHandler(this)
        binding.frameLayoutCamera.addView(mScannerView)
    }

    override fun onStart() {
        mScannerView.startCamera()
        doRequestPermission()
        super.onStart()
    }

    private fun doRequestPermission() {
        when {
            checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED -> {
                requestPermissions(arrayOf(Manifest.permission.CAMERA), 100)
            }
            checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED -> {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                    PERMISSIONLOCATIONID
                )
            }
            checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED -> {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    PERMISSIONLOCATIONID
                )
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            100 -> {
                initScannerView()
            }
            PERMISSIONLOCATIONID -> {
                getLastLocation()
            }
        }
    }

    override fun onPause() {
        mScannerView.stopCamera()
        super.onPause()
    }

    private fun initDefaultView() {
        binding.qrTemperature.text = "Loading"
        binding.qrIconStatus.visibility = View.GONE
        binding.qrResult.visibility = View.GONE
        binding.qrMessage.visibility = View.GONE
        getLastLocation()
    }

    override fun handleResult(p0: Result?) {
//        binding.qrResult.text = p0?.text
        p0?.text?.let { postData(it, latitude, longitude) };
        Log.d("Debug" ,"QRCODE_CODE: "+ p0?.text)
        binding.qrResult.visibility = View.VISIBLE
    }

    override fun onClick(view: View?) {
        when (view?.id) {
//            R.id.button_reset -> {
//                mScannerView.resumeCameraPreview(this)
//                initDefaultView()
//            }
            else -> {
                /* nothing to do in here */
            }
        }
    }
    private fun postData(qr: String, lat: String, longt: String) {

        // on below line we are creating a retrofit
        // builder and passing our base url
        val retrofit = Retrofit.Builder()
            .baseUrl("https://perludilindungi.herokuapp.com") // as we are sending data in json format so
            // we have to add Gson converter factory
            .addConverterFactory(GsonConverterFactory.create()) // at last we are building our retrofit builder.
            .build()
        // below line is to create an instance for our retrofit api class.
        val retrofitAPI: RetrofitAPI = retrofit.create(RetrofitAPI::class.java)

        // passing data from our text fields to our modal class.
        val modal = CheckinData(qr, lat, longt)

        // calling a method to create a post and passing our modal class.
        val call: Call<CheckinData> = retrofitAPI.checkIn(modal)

        // on below line we are executing our method.
        call.enqueue(object : Callback<CheckinData> {
            override fun onResponse(
                call: Call<CheckinData>?,
                response: Response<CheckinData>?
            ) {
                // this method is called when we get response from our api.
                Toast.makeText(this@CheckinActivity, "Proses Selesai", Toast.LENGTH_SHORT).show()

                // we are getting response from our body
                // and passing it to our modal class.
                val responseFromAPI: CheckinData? = response?.body()

                // below line we are setting our
                // string to our text view.
                if (responseFromAPI != null) {
                    if(responseFromAPI.data?.userStatus == "green"){
                        binding.qrIconStatus.setImageResource(R.drawable.ic_round_check_circle_80)
                        binding.qrResult.text = "Berhasil"
                    }else {
                        when (responseFromAPI.data?.userStatus) {
                            "black" -> {
                                binding.qrIconStatus.setImageResource(R.drawable.ic_round_cancel_black_80)
                            }
                            "red" -> {
                                binding.qrIconStatus.setImageResource(R.drawable.ic_round_cancel_red_80)
                            }
                            "yellow" -> {
                                binding.qrIconStatus.setImageResource(R.drawable.ic_round_cancel_yellow_80)
                            }
                        }
                        binding.qrResult.text = "Gagal"
                    }
                    binding.qrMessage.text = responseFromAPI.data?.reason ?: responseFromAPI.message
                    binding.qrIconStatus.visibility = View.VISIBLE
                    binding.qrMessage.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<CheckinData>?, t: Throwable?) {
                // setting text to our text view when
                // we get error response from API.
                if (t != null) {
                    binding.qrResult.text = t.message
                }
            }
        })
    }

}