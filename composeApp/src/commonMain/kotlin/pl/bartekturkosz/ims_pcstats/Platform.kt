package pl.bartekturkosz.ims_pcstats

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform