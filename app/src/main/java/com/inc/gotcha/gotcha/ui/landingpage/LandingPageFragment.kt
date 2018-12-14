package com.inc.gotcha.gotcha.ui.landingpage

import android.content.Intent
import android.nfc.*
import android.nfc.tech.IsoDep
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.inc.gotcha.gotcha.HceUtils
import com.inc.gotcha.gotcha.R
import com.inc.gotcha.gotcha.databinding.LandingPageFragmentBinding

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

        viewModel = LandingPageViewModel(this)
        val binding: LandingPageFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.landing_page_fragment, container, false)
        binding.viewmodel = viewModel
        binding.setLifecycleOwner(this)
        return binding.root
    }

    override fun startHceScan() {
        nfcAdapter?.setNdefPushMessage(null, activity)
        nfcAdapter?.enableReaderMode(activity, this,
                NfcAdapter.FLAG_READER_NFC_A or
                        NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK,
                null)
    }

    override fun sendNdefMessage(message: NdefMessage) {
        /*nfcAdapter?.disableReaderMode(activity)
        nfcAdapter?.setNdefPushMessage(message, activity)*/
    }

    override fun createNdefMessage(): NdefMessage {
        val text = "Beam me up, Android!\n\n" +
                "Beam Time: " + System.currentTimeMillis()
        return NdefMessage(
                arrayOf(
                        NdefRecord.createMime("application/com.inc.gotcha.gotcha", text.toByteArray())
                )
                /**
                 * The Android Application Record (AAR) is commented out. When a device
                 * receives a push with an AAR in it, the application specified in the AAR
                 * is guaranteed to run. The AAR overrides the tag dispatch system.
                 * You can add it back in to guarantee that this
                 * activity starts when receiving a beamed message. For now, this code
                 * uses the tag dispatch system.
                 *///,NdefRecord.createApplicationRecord("com.example.android.beam")
        )
    }

    override fun onResume() {
        super.onResume()
        val grabIntent: Intent? = activity?.intent
        grabIntent?.let {
            if (NfcAdapter.ACTION_NDEF_DISCOVERED == grabIntent.action) {
                processIntent(grabIntent)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        nfcAdapter?.disableReaderMode(activity)
    }

    private fun processIntent(intent: Intent) {
        intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)?.also { rawMsgs ->
            (rawMsgs[0] as NdefMessage).apply {
                // record[0] contains the MIME type, record[1] is the AAR, if present
                viewModel.nfcMessageInput(String(records[0].payload))
            }
        }
    }

    override fun onTagDiscovered(tag: Tag?) {
        val isoDep = IsoDep.get(tag)
        isoDep.connect()
        val response = isoDep.transceive(HceUtils.hexStringToByteArray(
                "00A4040007A0000002471001"))
        activity?.runOnUiThread {
            viewModel.hceMessageInput(HceUtils.toHex(response))
        }
        isoDep.close()
    }
}

