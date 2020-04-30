package com.autopass.autocard.core.nfc.managers

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import android.util.Log
import com.autopass.autocard.core.extensions.toHexString
import com.autopass.autocard.core.nfc.legacy.VSC_COMM
import com.autopass.autocard.core.nfc.utils.APDUs
import com.autopass.autocard.core.nfc.utils.DeviceSlot
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class SerialDeviceManager(
    private val context: Context,
    private val comm: VSC_COMM
) {

    private var device: UsbDevice? = null
    private var isCardPresent = false

    fun open() {
        if (device == null) {
            val manager = context.getSystemService(Context.USB_SERVICE) as UsbManager
            val deviceList = manager.deviceList
            val iterator = deviceList.values.iterator()

            while (iterator.hasNext()) {
                val currentDevice = iterator.next()
                val intent =
                    PendingIntent.getBroadcast(context, 0, Intent(ACTION_USB_PERMISSION), 0)
                manager.requestPermission(currentDevice, intent)


                if ((currentDevice.productId == 87 || currentDevice.productId == 21) &&
                    (currentDevice.vendorId == 2816 || currentDevice.vendorId == 8619)
                ) {
                    while (!manager.hasPermission(currentDevice)) {
                        Thread.sleep(20)
                    }
                    device = currentDevice
                    resetSam()
                }

            }
        }
    }

    private fun resetSam() {
        val ret = comm.newSCardTransmit(DeviceSlot.SAM.slot.toShort(), APDUs.SAM_ATR)
        Log.d("READER_LIB", "Reset SAM ret = ${ret.toHexString()}, size = ${ret.size}")
    }

    private fun waitCard() {
        val ret = comm.newSCardTransmit(DeviceSlot.CARD.slot.toShort(), APDUs.ATR)
        Log.d("READER_LIB", "Wait card ret = ${ret.toHexString()}, size = ${ret.size}")
        if (ret.size >= 5) {
            isCardPresent = true
            val atq = ByteArray(3)
            System.arraycopy(ret, 0, atq, 0, atq.size)
            Log.d("READER_LIB", "Cartão detectado")
        }
    }

    private fun readCardSerialNumber(): ByteArray {
        var answer = comm.newSCardTransmit(DeviceSlot.CARD.slot.toShort(), APDUs.SELECT_FILE_2FF7)
        Log.d("READER_LIB", "Select file 2FF7 answer = ${answer.toHexString()}")
        if (isAnswerOk(answer)) {
            answer = comm.newSCardTransmit(DeviceSlot.CARD.slot.toShort(), APDUs.READ_FILE)
            Log.d("READER_LIB", "Read file 2FF7 answer = ${answer.toHexString()}")
            if (isAnswerOk(answer)) {
                val resp = ByteArray(4)
                System.arraycopy(answer, 17, resp, 0, 4)
                return resp
            }
        }
        return ByteArray(0)
    }

    private fun verifySW(array: ByteArray): String {
        var ret = "0000"
        if (array.size >= 2) {
            ret = array.copyOfRange(array.size - 2, array.size).toHexString()
        }
        return ret
    }

    private fun isAnswerOk(array: ByteArray): Boolean {
        return verifySW(array) == "9000"
    }

    fun startReading(): Observable<ByteArray> {
        return Observable.timer(500, TimeUnit.MILLISECONDS)
            .map { waitCard() }
            .map { if (!isCardPresent) throw Exception() }
            .retry()
            .map { readCardSerialNumber() }
    }

    fun waitCardRemove() {
        while (true) {
            val answer = comm.newSCardTransmit(DeviceSlot.CARD.slot.toShort(), APDUs.WAIT_REMOVE)
            Log.d(
                "READER_LIB",
                "Esperando cartão ser removido. Ret = ${answer.toHexString()} size = ${answer.size}"
            )
            if (answer.size <= 2) {
                isCardPresent = false
                return
            }
            try {
                Thread.sleep(500)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
    }

    companion object {
        private const val ACTION_USB_PERMISSION = "br.com.autopass.USB_PERMISSION"
    }
}