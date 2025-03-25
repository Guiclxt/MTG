package com.example.mtg

data class MagicCard(
    val image_uris: ImageUris?
) {
    fun getImageUrl(): String {
        return image_uris?.normal  ?: "https://png.pngtree.com/png-vector/20190820/ourmid/pngtree-no-image-vector-illustration-isolated-png-image_1694547.jpg"
    }
}

data class ImageUris(
    val normal: String
)
