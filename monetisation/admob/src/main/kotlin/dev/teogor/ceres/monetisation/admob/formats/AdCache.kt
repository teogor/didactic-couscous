package dev.teogor.ceres.monetisation.admob.formats

object AdCache {
  private val adCache: MutableMap<String, MutableList<CacheAdModel>> = mutableMapOf()

  @Synchronized
  fun cacheAd(adId: String, ad: CacheAdModel) {
    adCache.getOrPut(adId) {
      mutableListOf()
    }.apply {
      add(ad)
    }
  }

  @Synchronized
  fun getAd(adId: String): CacheAdModel? {
    return adCache[adId]?.firstOrNull()
  }

  @Synchronized
  fun getAds(adId: String, count: Int): List<CacheAdModel> {
    return adCache[adId]?.take(count) ?: emptyList()
  }

  @Synchronized
  fun removeAd(adId: String) {
    adCache[adId]?.let { adList ->
      if (adList.isNotEmpty()) {
        adList.removeAt(0)
      }
      if (adList.isEmpty()) {
        adCache.remove(adId)
      }
    }
  }

  @Synchronized
  fun getAdCount(adId: String) = adCache[adId]?.size ?: 0
}

