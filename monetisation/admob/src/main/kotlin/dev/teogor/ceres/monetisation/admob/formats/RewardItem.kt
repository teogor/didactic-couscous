package dev.teogor.ceres.monetisation.admob.formats

data class RewardItem(
  val amount: Int,
  val type: String,
)

fun com.google.android.gms.ads.rewarded.RewardItem.toRewardItem() : RewardItem {
  return RewardItem(
    amount = amount,
    type = type,
  )
}
