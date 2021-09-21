package com.yfve.common.util

import android.media.MediaPlayer

import android.media.MediaPlayer.OnCompletionListener

import android.media.MediaRecorder

import android.os.Environment
import android.util.Log
import java.io.IOException
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException


/**
 * @description:录音工具类
 * @author:  THUANG8
 * @date :   2021/9/21 14:01
 */
class RecorderUtils {
    //录音
    private var mRecorder: MediaRecorder? = null

    /** 采样率  */
    private val SAMPLE_RATE_IN_HZ = 8000

    //播放录音
    private var mPlayer: MediaPlayer? = null
    private var playState = false // 录音的播放状态

    private var mFileName: String? = null


    fun getPlayer(): MediaPlayer? {
        return mPlayer
    }

    fun setPlayer(mPlayer: MediaPlayer?) {
        this.mPlayer = mPlayer
    }

    fun setFileName(name: String) {
        if (mRecorder == null) {
            mRecorder = MediaRecorder()
        }
        if (mPlayer == null) {
            mPlayer = MediaPlayer()
        }
        mFileName = Environment.getExternalStorageDirectory().absolutePath + "/menmen"
        //		mFileName += "/" + name + ".3gp";
        //		mFileName += "/" + name + ".mp3";
        mFileName += "/$name.arm"
    }

    /*
	 * 开始录音
	 */
    fun startRecording() {
        // 实例化MediaRecorder
        if (mRecorder == null) {
            mRecorder = MediaRecorder()
        }
        // 设置音频源为MIC
        mRecorder!!.setAudioSource(MediaRecorder.AudioSource.MIC)
        // 设置输出文件的格式
        mRecorder!!.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB)
        //		mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        // 设置输出文件的名称
        mRecorder!!.setOutputFile(mFileName)
        //设置音频的编码格式
        mRecorder!!.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
        //		mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB);
        //设置采样率
        mRecorder!!.setAudioSamplingRate(SAMPLE_RATE_IN_HZ)
        try {
            //得到设置的音频来源，编码器，文件格式等等内容，在start()之前调用
            mRecorder!!.prepare()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        try {
            //开始录音
            mRecorder!!.start()
        } catch (e: Exception) {
            mRecorder = null
            mRecorder = MediaRecorder()
        }
    }


    /**
     *
     * @return  String
     */
    /*
	 * 停止录音
	 */
    fun stopRecording(): String? {
        try {
            mRecorder!!.stop()
        } catch (e: Exception) {
            //释放资源
            mRecorder = null
            mRecorder = MediaRecorder()
        }
        //释放资源
        mRecorder!!.release()
        mRecorder = null
        return mFileName
    }


    /**
     *
     * @param Filename  Filename
     * @param completion  completion
     */
    //
    fun startPlaying(Filename: String?, completion: OnCompletionListener?) {
        if (!playState) {
            if (mPlayer == null) {
                mPlayer = MediaPlayer()
            }
            try {
                mPlayer!!.setDataSource(Filename)
                mPlayer!!.prepare()
                playState = true
                mPlayer!!.start()
                mPlayer!!.setOnCompletionListener(completion)
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            } catch (e: SecurityException) {
                e.printStackTrace()
            } catch (e: IllegalStateException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } else {
            playState = if (mPlayer!!.isPlaying) {
                mPlayer!!.stop()
                false
                //				startPlaying(Filename);
            } else {
                false
            }
        }
    }

    /**
     * 播放完后释放资源
     */
    fun playingFinish() {
        Log.i("spoort_list", "RecorderControl 播放结束释放资源")
        if (playState) {
            playState = false
        }
        mPlayer!!.release()
        mPlayer = null
    }

    /**
     * 停止播放
     * @return    boolean
     */
    fun stopPlaying(): Boolean {
        return if (mPlayer != null) {
            //			if(mPlayer!=null&&mPlayer.isPlaying()){
            Log.i("spoort_list", "RecorderControl mPlayer.stop()")
            mPlayer!!.stop()
            mPlayer!!.release()
            mPlayer = null
            playState = false
            true
        } else {
            Log.i("spoort_list", "RecorderControl mPlayer.stop() is null")
            false
        }
    }
    //当文件播放结束后调用此方法
    //	OnCompletionListener completion = new OnCompletionListener() {
    //		@Override
    //		public void onCompletion(MediaPlayer mp) {
    ////			if(playState){
    ////				playState = false;
    ////			}
    //			Log.i("spoort_list", "RecorderControl 播放结束");
    //			mPlayer.release();
    //			mPlayer = null;
    //		}
    //	};

    //当文件播放结束后调用此方法
    //	OnCompletionListener completion = new OnCompletionListener() {
    //		@Override
    //		public void onCompletion(MediaPlayer mp) {
    ////			if(playState){
    ////				playState = false;
    ////			}
    //			Log.i("spoort_list", "RecorderControl 播放结束");
    //			mPlayer.release();
    //			mPlayer = null;
    //		}
    //	};
    /**
     * 获取音量的大小
     * @return   double
     */
    fun getAmplitude(): Double {
        return if (mRecorder != null) {
            mRecorder!!.maxAmplitude.toDouble()
        } else 0.0
    }
}