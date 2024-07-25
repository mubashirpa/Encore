package player

import android.content.Context
import androidx.annotation.OptIn
import androidx.media3.common.AudioAttributes
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.mediacodec.MediaCodecRenderer.DecoderInitializationException
import androidx.media3.exoplayer.mediacodec.MediaCodecUtil.DecoderQueryException

class AndroidMediaPlayer(
    context: Context,
    private val playerState: PlayerState,
) : MediaPlayer {
    private var player: ExoPlayer? = null
    private var onPlaybackStateChanged: ((PlaybackState) -> Unit)? = null
    private var onPlayerError: ((String?) -> Unit)? = null

    init {
        player =
            ExoPlayer.Builder(context).build().apply {
                addListener(
                    object : Player.Listener {
                        override fun onPlaybackStateChanged(playbackState: Int) {
                            when (playbackState) {
                                Player.STATE_IDLE -> {
                                    onPlaybackStateChanged?.invoke(PlaybackState.STATE_IDLE)
                                }

                                Player.STATE_BUFFERING -> {
                                    onPlaybackStateChanged?.invoke(PlaybackState.STATE_BUFFERING)
                                    playerState.isBuffering = true
                                }

                                Player.STATE_READY -> {
                                    onPlaybackStateChanged?.invoke(PlaybackState.STATE_READY)
                                    playerState.isBuffering = false
                                    playerState.duration = duration / 1000
                                }

                                Player.STATE_ENDED -> {
                                    onPlaybackStateChanged?.invoke(PlaybackState.STATE_ENDED)
                                }
                            }
                        }

                        override fun onIsPlayingChanged(isPlaying: Boolean) {
                            playerState.isPlaying = isPlaying
                        }

                        @OptIn(UnstableApi::class)
                        override fun onPlayerError(error: PlaybackException) {
                            val cause = error.cause
                            var message = "Playback failed"

                            if (cause is DecoderInitializationException) {
                                // Special case for decoder initialization failures.
                                val decoderInitializationException = cause
                                message =
                                    if (decoderInitializationException.codecInfo == null) {
                                        if (decoderInitializationException.cause is DecoderQueryException) {
                                            "Unable to query device decoders"
                                        } else if (decoderInitializationException.secureDecoderRequired) {
                                            "This device does not provide a secure decoder for ${decoderInitializationException.mimeType}"
                                        } else {
                                            ">This device does not provide a decoder for ${decoderInitializationException.mimeType}"
                                        }
                                    } else {
                                        "Unable to instantiate decoder ${decoderInitializationException.codecInfo!!.name}"
                                    }
                            }
                            onPlayerError?.invoke(message)
                        }
                    },
                )
                setAudioAttributes(AudioAttributes.DEFAULT, true)
            }
    }

    override fun play() {
        player?.play()
    }

    override fun play(url: String) {
        val mediaItem = MediaItem.fromUri(url)
        player?.apply {
            setMediaItem(mediaItem)
            prepare()
            play()
        }
    }

    override fun pause() {
        player?.pause()
    }

    override fun stop() {
        player?.stop()
        player?.release()
        player = null
    }

    override fun onPlaybackStateChanged(listener: (playbackState: PlaybackState) -> Unit) {
        onPlaybackStateChanged = listener
    }

    override fun onPlayerError(listener: (message: String?) -> Unit) {
        onPlayerError = listener
    }
}
