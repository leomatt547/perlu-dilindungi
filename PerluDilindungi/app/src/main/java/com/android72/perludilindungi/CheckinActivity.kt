package com.android72.perludilindungi

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.android72.perludilindungi.databinding.ActivityCheckinBinding
import com.google.android.gms.location.*
import com.google.zxing.Result
import me.dm7.barcodescanner.core.IViewFinder
import me.dm7.barcodescanner.zxing.ZXingScannerView


class CheckinActivity : AppCompatActivity(), ZXingScannerView.ResultHandler, View.OnClickListener{
    private lateinit var binding: ActivityCheckinBinding
    //lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val PERMISSIONLOCATIONID = 1010
    private lateinit var mScannerView: ZXingScannerView
//    private fun checkPermission():Boolean{
//        //this function will return a boolean
//        //true: if we have permission
//        //false if not
//        if(
//            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
//            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
//        ){
//            return true
//        }
//
//        return false
//
//    }
//    private fun isLocationEnabled():Boolean{
//        //this function will return to us the state of the location service
//        //if the gps or the network provider is enabled then it will return true otherwise it will return false
//        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
//    }

//    private fun getLastLocation(){
//        if(checkPermission()){
//            if(isLocationEnabled()){
//                if (ActivityCompat.checkSelfPermission(
//                        this,
//                        Manifest.permission.ACCESS_FINE_LOCATION
//                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                        this,
//                        Manifest.permission.ACCESS_COARSE_LOCATION
//                    ) != PackageManager.PERMISSION_GRANTED
//                ) {
//                    // TODO: Consider calling
//                    //    ActivityCompat#requestPermissions
//                    // here to request the missing permissions, and then overriding
//                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                    //                                          int[] grantResults)
//                    // to handle the case where the user grants the permission. See the documentation
//                    // for ActivityCompat#requestPermissions for more details.
//                    return
//                }
//                fusedLocationProviderClient.lastLocation.addOnCompleteListener { task->
//                    val location:Location? = task.result
//                    if(location == null){
//                        Toast.makeText(this,"Please Turn on Your device Location",Toast.LENGTH_SHORT).show()
//                    }else{
//                        Log.d("Debug:" ,"Your Location:"+ location.longitude + ", Latitude:" + location.latitude)
//                        binding.qrLocation.text = "You Current Location is : Long: "+ location.longitude + " , Lat: " + location.latitude + "\n"
//                        binding.qrLocation.visibility = View.VISIBLE
//                    }
//                }
//            }else{
//                Toast.makeText(this,"Please Turn on Your device Location",Toast.LENGTH_SHORT).show()
//            }
//        }else{
//            doRequestPermission()
//        }
//    }

//    fun NewLocationData(){
//        var locationRequest =  LocationRequest()
//        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
//        locationRequest.interval = 0
//        locationRequest.fastestInterval = 0
//        locationRequest.numUpdates = 1
//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
//        if (ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return
//        }
//        Looper.myLooper()?.let {
//            fusedLocationProviderClient!!.requestLocationUpdates(
//                locationRequest,locationCallback, it
//            )
//        }
//    }


//    private val locationCallback = object : LocationCallback(){
//        override fun onLocationResult(locationResult: LocationResult) {
//            var lastLocation: Location = locationResult.lastLocation
//            Log.d("Debug:","your last last location: "+ lastLocation.longitude.toString())
//            binding.qrLocation.text = "You Last Location is : Long: "+ lastLocation.longitude + " , Lat: " + lastLocation.latitude + "\n"
//        }
//    }

    fun onClickBack() {
        super.onBackPressed() // or super.finish();
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckinBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
//        Log.d("Debug:",CheckPermission().toString())
//        Log.d("Debug:",isLocationEnabled().toString())
//        fusedLocationProviderClient.lastLocation.addOnSuccessListener{location: Location? ->
//            binding.qrLocation.text = location?.latitude.toString() + "," + location?.longitude.toString()
//        }
        initScannerView()
        initDefaultView()
    }

//    private fun getLocation() {
//        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
//            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), locationPermissionCode)
//        }
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)
//    }
//    override fun onLocationChanged(location: Location) {
//        binding.qrLocation.text = "Latitude: " + location.latitude + " , Longitude: " + location.longitude
//    }


//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        var result = IntentIntegrator.parseActivityResult(resultCode, data)
//        if (result != null) {
//            //hasil di sini
//            AlertDialog.Builder(this)
//                .setMessage("Would you like to go to ${result.contents}?")
//                .setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
//                    val intent = Intent(Intent.ACTION_WEB_SEARCH)
//                    intent.putExtra(SearchManager.QUERY,result.contents)
//                    startActivity(intent)
//                })
//                .setNegativeButton("No",DialogInterface.OnClickListener { dialogInterface, i ->  })
//                .create()
//                .show()
//        }
//    }

    private fun initScannerView() {
        mScannerView = object : ZXingScannerView(this) {
            override fun createViewFinderView(context: Context?): IViewFinder {
                return CustomViewFinderView(context!!)
            }
        }
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
//            checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED -> {
//                ActivityCompat.requestPermissions(
//                    this,
//                    arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
//                    PERMISSIONLOCATIONID
//                )
//            }
//            checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED -> {
//                ActivityCompat.requestPermissions(
//                    this,
//                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
//                    PERMISSIONLOCATIONID
//                )
//            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            100 -> {
                initScannerView()
            }
            PERMISSIONLOCATIONID -> {
                //getLastLocation()
            }
        }
    }

    override fun onPause() {
        mScannerView.stopCamera()
        super.onPause()
    }

    private fun initDefaultView() {
        binding.qrTemperature.text = "Loading"
//        binding.qrResult.visibility = View.GONE
//        binding.qrLocation.visibility = View.GONE
    }

    override fun handleResult(p0: Result?) {
        binding.qrResult.text = p0?.text
//        binding.qrResult.visibility = View.VISIBLE
//        binding.qrLocation.visibility = View.VISIBLE
        //getLastLocation()
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

}