package com.inc.gotcha.gotcha.ui.landingpage

import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.IsoDep
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.gson.Gson
import com.inc.gotcha.gotcha.HceUtils
import com.inc.gotcha.gotcha.ProfileData
import com.inc.gotcha.gotcha.R
import com.inc.gotcha.gotcha.databinding.LandingPageFragmentBinding
import java.nio.charset.Charset
import java.util.*

class LandingPageFragment : Fragment(), NfcController, HceController, NfcAdapter.ReaderCallback {

    companion object {
        fun newInstance() = LandingPageFragment()
    }

    private lateinit var viewModel: LandingPageViewModel
    private var nfcAdapter: NfcAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nfcAdapter = NfcAdapter.getDefaultAdapter(activity)//Handle on the UI the case where the nfcAdapter doesn't come back
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        viewModel = LandingPageViewModel(this, this)
        val binding: LandingPageFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.landing_page_fragment, container, false)
        binding.viewmodel = viewModel
        binding.setLifecycleOwner(this)
        return binding.root
    }

    override fun startHceScan() {
        nfcAdapter?.enableReaderMode(activity, this,
                NfcAdapter.FLAG_READER_NFC_A or
                        NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK,
                null)
    }

    override fun sendNdefMessage(message: String) {
        nfcAdapter?.disableReaderMode(activity)
        nfcAdapter?.setNdefPushMessage(NdefMessage(arrayOf(createTextRecord(message, Locale.CANADA, true))), activity)
    }

    private fun createTextRecord(payload: String, locale: Locale, encodeInUtf8: Boolean): NdefRecord {
        val langBytes = locale.language.toByteArray(Charset.forName("US-ASCII"))
        val utfEncoding = if (encodeInUtf8) Charset.forName("UTF-8") else Charset.forName("UTF-16")
        val textBytes = payload.toByteArray(utfEncoding)
        val utfBit: Int = if (encodeInUtf8) 0 else 1 shl 7
        val status = (utfBit + langBytes.size).toChar()
        val data = ByteArray(1 + langBytes.size + textBytes.size)
        data[0] = status.toByte()
        System.arraycopy(langBytes, 0, data, 1, langBytes.size)
        System.arraycopy(textBytes, 0, data, 1 + langBytes.size, textBytes.size)
        return NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT, ByteArray(0), data)
    }

    override fun onPause() {
        super.onPause()
        nfcAdapter?.disableReaderMode(activity)
    }

    override fun onTagDiscovered(tag: Tag?) {
        val isoDep = IsoDep.get(tag)
        isoDep.connect()
        val response = isoDep.transceive(HceUtils.hexStringToByteArray(
                "00A4040007A0000002471001"))

        activity?.runOnUiThread {
            viewModel.hceMessageInput(String(response))
            val profileData = String(response)
            val action = LandingPageFragmentDirections.actionLandingPageFragmentToContactFragment()
            action.setContact(profileData)
            NavHostFragment.findNavController(this).navigate(action)
        }
        isoDep.close()
    }
}

