package com.example.aidharbour

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.google.firebase.database.*

class History2 : AppCompatActivity() {
    private lateinit var scrollViewLayout: LinearLayout
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history2)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        val uid = intent.getStringExtra("uid")
        setSupportActionBar(toolbar)
        toolbar.overflowIcon = null
        scrollViewLayout = findViewById(R.id.historylayout)
        databaseReference = FirebaseDatabase.getInstance().reference.child("donations").child("$uid")
        fetchDataFromFirebase()

    }

    private fun fetchDataFromFirebase() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                scrollViewLayout.removeAllViews()

                for (donationSnapshot in dataSnapshot.children) {
                    val donationMap = donationSnapshot.value as HashMap<*, *>
                    addDonationView(donationMap)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
    }

    private fun addDonationView(donationMap: HashMap<*, *>) {
        val donationView = layoutInflater.inflate(R.layout.person, null)
        val titleTextView = donationView.findViewById<TextView>(R.id.text1_textview)
        val descriptionTextView = donationView.findViewById<TextView>(R.id.text2_textview)
        val addressTextView = donationView.findViewById<TextView>(R.id.text4_textview)
        val volunteerTextView = donationView.findViewById<TextView>(R.id.text5_textview)
        val title = donationMap["title"].toString()
        val description = donationMap["description"].toString()
        val address = donationMap["address"].toString()
        val volunteer = donationMap["volunteer"].toString()

        titleTextView.text = title
        descriptionTextView.text = description
        addressTextView.text = address
        volunteerTextView.text = volunteer

        donationView.setBackgroundResource(R.drawable.gradient_background)
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.topMargin = resources.getDimensionPixelSize(R.dimen.donation_view_margin_top)
        layoutParams.bottomMargin = resources.getDimensionPixelSize(R.dimen.donation_view_margin_bottom)

        donationView.layoutParams = layoutParams

        scrollViewLayout.addView(donationView)
    }
}
