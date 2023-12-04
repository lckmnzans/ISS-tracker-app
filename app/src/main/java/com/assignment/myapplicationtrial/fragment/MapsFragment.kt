package com.assignment.myapplicationtrial.fragment

import android.graphics.Color
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.assignment.myapplicationtrial.R
import com.assignment.myapplicationtrial.databinding.FragmentMapsBinding
import com.assignment.myapplicationtrial.network.response.ISSPosition
import com.assignment.myapplicationtrial.utils.ISSPositionLiveData

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment() {
    private var _binding: FragmentMapsBinding? = null
    private val binding get() = _binding!!

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        //val sydney = LatLng(-34.0, 151.0)
        //googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        googleMap.uiSettings.isMapToolbarEnabled = true

        ISSPositionLiveData.issPosition.observe(viewLifecycleOwner) {
            updateMapWithISSPosition(it, googleMap)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateMapWithISSPosition(position: ISSPosition, gMap: GoogleMap) {
        gMap.clear()
        val iss = LatLng(position.latitude, position.longitude)
        Log.d("ISSPosition", "Lat ${position.latitude} | Lon ${position.longitude}")
        gMap.addMarker(MarkerOptions()
            .position(iss)
            .title("ISS Current Position")
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.iss_icon))
        )
        gMap.moveCamera(CameraUpdateFactory.newLatLng(iss))

        val issRadius = 100000.0
        val circleOptions = CircleOptions()
            .center(iss)
            .radius(issRadius)
            .strokeColor(Color.BLUE)
            .fillColor(Color.parseColor("#330000FF"))
        gMap.addCircle(circleOptions)

    }
}