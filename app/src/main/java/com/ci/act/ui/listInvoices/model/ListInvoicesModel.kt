package com.ci.act.ui.listInvoices.model


import com.google.gson.annotations.SerializedName

class ListInvoicesModel : ArrayList<ListInvoicesModel.ListInvoicesModelItem>() {
    data class ListInvoicesModelItem(
        @SerializedName("amount")
        var amount: String?,
        @SerializedName("expiredOn")
        var expiredOn: String?,
        @SerializedName("invoiceId")
        var invoiceId: Int?,
        @SerializedName("paymentStatus")
        var paymentStatus: String?,
        @SerializedName("planName")
        var planName: String?,
        @SerializedName("purchasedOn")
        var purchasedOn: String?,
        @SerializedName("image")
        var image: String?
    )
}