package com.autopass.autocard.core.nfc.managers

import android.util.Log
import com.autopass.autocard.core.extensions.blockToByteArray
import com.autopass.autocard.core.extensions.toBlockString
import com.autopass.autocard.core.nfc.cardblocks.*
import com.autopass.autocard.core.nfc.cardblocks.producers.*
import com.autopass.autocard.core.nfc.legacy.VL4MIF
import com.autopass.autocard.core.nfc.utils.CardBlocks

class CardManager(private val vl: VL4MIF) {

    fun createConnection(serial: ByteArray) {
        val key = byteArrayOf(0x00, 0x00, 0x00, 0x00, 0x00)
        val keyType: Byte = 0x60
        val keyIndex: Byte = 0x01
        val atqsak = byteArrayOf(0x00, 0x04, 0x028)
        val fid = byteArrayOf(0x03, 0x05)
        val samLtc = ByteArray(3)
        vl.VL4MIF_Open(atqsak, serial.size.toByte(), serial, fid, samLtc)
        vl.VL4MIF_LoadKey(key, keyType, keyIndex)
        vl.VL4MIF_Authenticate(0.toByte(), keyType, keyIndex)
    }

    fun readIssueDataBlock(): IssueDataBlock {
        return readBlockFromCard(CardBlocks.CARD_ISSUE_DATA.byte).toIssueDataBlock()
    }

    fun writeIssueDataBlock(block: IssueDataBlock): Int {
        return writeBlockToCard(block.toBinaryString(), CardBlocks.CARD_ISSUE_DATA.byte)
    }

    fun readStatusBlock(): StatusBlock {
        return readBlockFromCard(CardBlocks.CARD_STATUS.byte).toStatusBlock()
    }

    fun writeStatusBlock(statusBlock: StatusBlock): Int {
        return writeBlockToCard(statusBlock.toBinaryString(), CardBlocks.CARD_STATUS.byte)
    }

    fun readMainBlock(): AppBlock {
        return readBlockFromCard(CardBlocks.APP.byte).toAppBlock()
    }

    fun writeMainBlock(mainBlock: AppBlock): Int {
        return writeBlockToCard(mainBlock.toBinaryString(), CardBlocks.APP.byte)
    }

    fun readInfoBlock(block: CardBlocks): InfoBlock {
        return readBlockFromCard(block.byte).toInfoBlock()
    }

    fun writeInfoBlock(infoBlock: InfoBlock, block: CardBlocks): Int {
        return writeBlockToCard(infoBlock.toBinaryString(), block.byte)
    }

    fun readRechargeDataBlock(): RechargeDataBlock {
        return readBlockFromCard(CardBlocks.RECHARGE_DATA.byte).toRechargeDataBlock()
    }

    fun writeRechargeDataBlock(mainBlock: RechargeDataBlock): Int {
        return writeBlockToCard(mainBlock.toBinaryString(), CardBlocks.RECHARGE_DATA.byte)
    }

    private fun writeBlockToCard(value: String, block: Byte, blockSize: Int = 1): Int {
        val blockBytes = value.blockToByteArray(blockSize)
        val ret = vl.VL4MIF_Write(block, blockSize.toByte(), blockBytes)
        Log.d(
            "READER_LIB",
            "Write block $block with value = ${blockBytes.toBlockString(blockSize)} Result: $ret"
        )
        return ret
    }

    private fun readBlockFromCard(block: Byte, blockSize: Int = 1): String {
        var ret = ""
        val blockBytes = ByteArray(16 * blockSize)
        val result = vl.VL4MIF_Read(block, blockSize.toByte(), blockBytes)
        Log.d("READER_LIB", "Read block $block. Result: $result")
        ret = if (result >= 0) blockBytes.toBlockString(blockSize)
        else ret.padEnd(128, '0')
        return ret
    }
}