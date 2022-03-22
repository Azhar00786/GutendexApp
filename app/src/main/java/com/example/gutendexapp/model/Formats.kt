package com.example.gutendexapp.model

import com.google.gson.annotations.SerializedName

data class Formats(
    @SerializedName("application/epub+zip") val ePubZipFormat: String? = null,
    @SerializedName("application/pdf") val pdfFormat: String? = null,
    @SerializedName("application/rdf+xml") val rdfXmlFormat: String? = null,
    @SerializedName("application/x-mobipocket-ebook") val xMobiPocketEbookFormat: String? = null,
    @SerializedName("application/zip") val zipFormat: String? = null,
    @SerializedName("image/jpeg") val jpegFormat: String? = null,
    @SerializedName("text/html; charset=iso-8859-1") val textHtmlCharsetIso88591Format: String? = null,
    @SerializedName("text/html; charset=us-ascii") val textHtmlCharsetUsAsciiFormat: String? = null,
    @SerializedName("text/html; charset=utf-8") val textHtmlCharsetUtf8Format: String? = null,
    @SerializedName("text/plain") val textPlainFormat: String? = null,
    @SerializedName("text/plain; charset=iso-8859-1") val textPlainCharsetIso88591Format: String? = null,
    @SerializedName("text/plain; charset=us-ascii") val textPlainCharsetUsAsciiFormat: String? = null,
    @SerializedName("text/plain; charset=utf-8") val textPlainCharsetUtf8Format: String? = null,
    @SerializedName("text/rtf") val textRtfFormat: String? = null
)